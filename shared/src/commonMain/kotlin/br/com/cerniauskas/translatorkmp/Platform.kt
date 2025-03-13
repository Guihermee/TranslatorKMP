package br.com.cerniauskas.translatorkmp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform