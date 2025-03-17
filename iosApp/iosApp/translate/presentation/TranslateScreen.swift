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
                            
                        })
                }
                
            }
        }
    }
}
