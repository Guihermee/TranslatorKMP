//
//  SwapLanguageButton.swift
//  iosApp
//
//  Created by Guilherme on 16/03/25.
//  Copyright © 2025 orgName. All rights reserved.
//

import SwiftUI
import shared

struct SwapLanguageButton: View {
    var onClick: () -> Void
    var body: some View {
        Button(action: onClick) {
            Image(uiImage: UIImage(named: "swap_languages")!)
                .padding()
                .background(Color.primary)
                .clipShape(Circle())
        }
    }
}
