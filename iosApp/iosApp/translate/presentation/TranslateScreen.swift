//
//  TranslateScreen.swift
//  iosApp
//
//  Created by Guilherme on 17/03/25.
//  Copyright © 2025 orgName. All rights reserved.
//

import SwiftUI
import shared

struct TranslateScreen: View {
    private var historyDataSource: HistoryDataSource
    private var translateUseCase: Translate
    @ObservedObject private var viewModel: IOSTranslateViewModel
    private let parser = IOSVoiceToTextParser()
    
    init(historyDataSource: HistoryDataSource, translateUseCase: Translate) {
        self.historyDataSource = historyDataSource
        self.translateUseCase = translateUseCase
        self.viewModel = IOSTranslateViewModel(historyDataSource: historyDataSource, translateUseCase: translateUseCase)
    }
    
    var body: some View {
        ZStack {
            List {
                HStack(alignment: .center) {
                    LanguageDropDown(
                        language: viewModel.state.fromLanguage,
                        isOpen: viewModel.state.isChoosingFromLanguage,
                        selectLanguage: { language in
                            viewModel.onAction(action: TranslateAction.ChooseFromLanguage(language: language))
                        }
                    )
                    Spacer()
                    SwapLanguageButton(
                        onClick: {
                            viewModel.onAction(action: TranslateAction.SwapLanguages())
                        })
                    Spacer()
                    LanguageDropDown(
                        language: viewModel.state.toLanguage,
                        isOpen: viewModel.state.isChoosingToLanguage,
                        selectLanguage: { language in
                            viewModel.onAction(action: TranslateAction.ChooseToLanguage(language: language))
                        }
                    )
                }
                .listRowSeparator(.hidden)
                .listRowBackground(Color.background)
                
                TranslateTextField(
                    fromText: Binding(get: { viewModel.state.fromText }, set: { value in
                        viewModel.onAction(action: TranslateAction.ChangeTranslationText(text: value))
                    }),
                    toText: viewModel.state.toText,
                    isTranslating: viewModel.state.isTranslating,
                    fromLanguage: viewModel.state.fromLanguage,
                    toLanguage: viewModel.state.toLanguage,
                    onTranslateAction: {
                        viewModel.onAction(action: $0)
                    }
                )
                .listRowSeparator(.hidden)
                .listRowBackground(Color.background)
                
                if !viewModel.state.history.isEmpty {
                    Text("History")
                        .font(.title)
                        .bold()
                        .frame(maxWidth: .infinity, alignment: .leading)
                        .listRowSeparator(.hidden)
                        .listRowBackground(Color.background)
                }
                
                ForEach(viewModel.state.history, id: \.id) { item in
                    TranslateHistoryItem(
                        item: item,
                        onClick: { viewModel.onAction(action: TranslateAction.SelectHistoryItem(item: item)) }
                    )
                    .listRowSeparator(.hidden)
                    .listRowBackground(Color.background)
                }
            }
            .listStyle(.plain)
            .buttonStyle(.plain)
            
            VStack {
                Spacer()
                NavigationLink(
                    destination: VoiceToTextScreen(
                        onResult: {spokenText in
                            viewModel.onAction(action: TranslateAction.SubmitVoiceResult(result: spokenText))
                        },
                        parser: parser,
                        languageCode: viewModel.state.fromLanguage.language.langCode
                    )
                ) {
                    ZStack {
                        Circle()
                            .foregroundColor(.primary)
                            .padding()
                        Image(uiImage: UIImage(named: "mic")!)
                            .foregroundColor(.onPrimary)
                    }
                    .frame(maxWidth: 100, maxHeight: 100)
                }
            }
            
        }
        .onAppear {
            viewModel.startObserving()
        }
        .onDisappear {
            viewModel.dispose()
        }
    }
}
