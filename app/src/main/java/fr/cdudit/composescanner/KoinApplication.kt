package fr.cdudit.composescanner

import android.app.Application
import fr.cdudit.composescanner.features.main.MainViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class KoinApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@KoinApplication)
            modules(viewModels)
        }
    }
}

val viewModels = module {
    viewModel { MainViewModel() }
}