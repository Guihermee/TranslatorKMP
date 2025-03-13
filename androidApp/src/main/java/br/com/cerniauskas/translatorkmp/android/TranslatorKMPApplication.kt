package br.com.cerniauskas.translatorkmp.android

import android.app.Application
import br.com.cerniauskas.translatorkmp.core.di.androidModule
import br.com.cerniauskas.translatorkmp.core.di.initKoin
import org.koin.android.ext.koin.androidContext

class TranslatorKMPApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin(
            extraModule = androidModule,
            config = {
                androidContext(this@TranslatorKMPApplication)
            }
        )
    }
}