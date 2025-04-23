# TranslatorKMP
Bookpedia is an Kotlin Multiplatform Compose Application created using Jetpack compose targeting IOS and Android, for now just Android is finished.

## Result

| ![Home](previews/home.jpg) | ![Tranlated](previews/translated.jpg) | ![VoiceToText](previews/voiceToText.jpg) | ![ResultVoiceToText](previews/resultVoiceToText.jpg) | ![BackToHome](previews/backToHome.jpg) |
|:----------------:|:----------------:|:----------------:|:----------------:|:----------------:|

## Tech stack & Open-source libraries
- [Kotlin](https://kotlinlang.org/) based
- [sqldelight](https://github.com/sqldelight/sqldelight) for local storage. 
- [Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html) for asynchronous.
- [JetPack](https://developer.android.com/jetpack)
    - [Compose](https://developer.android.com/jetpack/compose) - Modern toolkit for building native UI.
    - [Lifecycle](https://developer.android.com/topic/libraries/architecture/lifecycle) - Create a UI that automatically responds to lifecycle events.
    - [Navigation](https://developer.android.com/jetpack/compose/navigation) - Handle everything needed for in-app navigation.
    - [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - UI related data holder, lifecycle aware.
    - [Flow](https://developer.android.com/kotlin/flow?hl=pt-br) - Handle asynchronous data streams in a lifecycle-aware way.
- [Hilt](https://insert-koin.io/docs/quickstart/android/) - For [dependency injection](https://developer.android.com/training/dependency-injection/hilt-android).
- [Ktor](https://ktor.io/docs/client-create-new-application.html) - Ktor HTTP request
- [Coil](https://coil-kt.github.io/coil/) - For loading images
