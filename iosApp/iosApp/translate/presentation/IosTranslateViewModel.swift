//
//  IosTranslateViewModel.swift
//  iosApp
//
//  Created by Guilherme on 16/03/25.
//  Copyright © 2025 orgName. All rights reserved.
//

import Foundation
import shared

extension TranslateScreen {
    class IOSTranslateViewModel: ObservableObject {
        private var historyDataSource: HistoryDataSource
        private var translateUseCase: Translate
        
        private let viewModel: TranslateViewModel
        private var handle: DisposableHandle?
        
        @Published var state: TranslateState = TranslateState(
            fromText: "",
            toText: nil,
            isTranslating: false,
            fromLanguage: UiLanguage(language: .english, imageName: "english"),
            toLanguage: UiLanguage(language: .portuguese, imageName: "portuguese"),
            isChoosingFromLanguage: false,
            isChoosingToLanguage: false,
            error: nil,
            history: [])
        
        init(historyDataSource: HistoryDataSource, translateUseCase: Translate) {
            self.historyDataSource = historyDataSource
            self.translateUseCase = translateUseCase
            self.viewModel = TranslateViewModel(translate: translateUseCase, historyDataSource: historyDataSource, coroutineScope: nil)
        }
        
        func onAction(action: TranslateAction) {
            self.viewModel.onAction(action: action)
        }
        
        func startObserving() {
            handle = viewModel.state.subscribe(onCollect: { state in
                if let state = state {
                    self.state = state
                }
            })
        }
        
        func dispose() {
            handle?.dispose()
        }
    }
}
