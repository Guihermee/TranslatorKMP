package br.com.cerniauskas.translatorkmp.core.domain.util

import kotlinx.coroutines.ExperimentalForInheritanceCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow

@OptIn(ExperimentalForInheritanceCoroutinesApi::class)
actual open class CommonMutableStateFlow<T> actual constructor(
    private val flow: MutableStateFlow<T>
) : MutableStateFlow<T> by flow