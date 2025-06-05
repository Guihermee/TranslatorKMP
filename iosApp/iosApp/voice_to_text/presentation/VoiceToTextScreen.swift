//
//  VoiceToTextScreen.swift
//  iosApp
//
//  Created by Guilherme on 30/05/25.
//  Copyright © 2025 orgName. All rights reserved.
//

import SwiftUI
import shared

struct VoiceToTextScreen: View {
    private let onResult: (String) -> Void
    
    @ObservedObject var viewModel: IOSVoiceToTextViewModel
    private let parser: any VoiceToTextParser
    private let languageCode: String
    
    @Environment(\.presentationMode) var presentation
    
    init(onResult: @escaping (String) -> Void, parser: any VoiceToTextParser, languageCode: String) {
        self.onResult = onResult
        self.viewModel = IOSVoiceToTextViewModel(parser: parser, languageCode: languageCode)
        self.parser = parser
        self.languageCode = languageCode
    }
    
    var body: some View {
        VStack {
            Spacer()
            
            mainView
            
            Spacer()
            
            HStack {
                VoiceRecorderButton(
                    displayState: viewModel.state.displayState ?? .waitingtotalk,
                    onClick: {
                        if viewModel.state.displayState != .displayingresults {
                            viewModel.onAction(action: VoiceToTextAction.ToggleRecording(languageCode: languageCode))
                        } else {
                            onResult(viewModel.state.spokenText)
                            self.presentation.wrappedValue.dismiss()
                        }
                    }
                )
                if viewModel.state.displayState == .displayingresults {
                    Button(action: {
                        viewModel.onAction(action: VoiceToTextAction.ToggleRecording(languageCode: languageCode))
                    }) {
                        Image(systemName: "arrow.clockwise")
                            .foregroundColor(.lightBlue)
                    }
                }
            }
            
            Spacer()
            
        }
        .onAppear {
            viewModel.startObserving()
        }
        .onDisappear {
            viewModel.dispose()
        }
        .frame(maxWidth: .infinity)
        .background(Color.background)

    }
    
    var mainView: some View {
        if let displayState = viewModel.state.displayState {
            switch displayState {
            case .waitingtotalk:
                return AnyView(
                    Text("Click record and start talking")
                        .font(.title2)
                )
            case .displayingresults:
                return AnyView (
                    Text(viewModel.state.spokenText)
                        .font(.title2)
                )
            case .error:
                return AnyView(
                    Text(viewModel.state.recordError ?? "Unknown Error")
                        .font(.title2)
                        .foregroundColor(.red)
                )
            case .speaking:
                return AnyView(
                    VoiceRecorderDisplay(powerRatios: viewModel.state.powerRatios.map { Double(truncating: $0) })
                        .frame(maxHeight: 100)
                        .padding()
                )
            default:
                return AnyView(EmptyView())
            }
        } else {
            return AnyView(EmptyView())
        }
    }
}

