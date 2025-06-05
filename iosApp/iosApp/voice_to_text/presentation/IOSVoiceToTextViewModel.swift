//
//  IOSVoiceToTextViewModel.swift
//  iosApp
//
//  Created by Guilherme on 30/05/25.
//  Copyright © 2025 orgName. All rights reserved.
//

import Foundation
import shared
import Combine

@MainActor class IOSVoiceToTextViewModel: ObservableObject {
    private var parser: any VoiceToTextParser
    private let languageCode: String
    
    private let viewModel: VoiceToTextViewModel
    @Published var state = VoiceToTextState(powerRatios: [], spokenText: "", canRecord: false, recordError: nil, displayState: nil)
    private var handle: DisposableHandle?
    
    init(parser: any VoiceToTextParser, languageCode: String) {
        self.parser = parser
        self.languageCode = languageCode
        self.viewModel = VoiceToTextViewModel(parser: parser, coroutineScope: nil)
        self.viewModel.onAction(action: VoiceToTextAction.PermissionResult(isGranted: true, isPermanentlyDeclined: false))
    }
    
    func onAction(action: VoiceToTextAction) {
        viewModel.onAction(action: action)
    }
    
    func startObserving() {
        handle = viewModel.state.subscribe { [weak self] state in
            if let state {
                self?.state = state
            }
        }
    }
    
    func dispose() {
        handle?.dispose()
        onAction(action: VoiceToTextAction.Reset())
    }
    
}
