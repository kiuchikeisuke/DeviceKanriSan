package jp.ne.keisuke.kiuchi.devicekanrisan

import jp.ne.keisuke.kiuchi.devicekanrisan.utils.commons.ExecutionThreads

import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/* Module for Application */
@Module
class ApplicationModule {
    @Provides fun provideContext(application: MainApplication): Context = application
    @Provides fun provideSharedPreference(application: MainApplication): SharedPreferences = application.getSharedPreferences(application.getString(R.string.app_name), Context.MODE_PRIVATE)

    @Provides
    fun provideExecutionThreads(): ExecutionThreads {
        return object : ExecutionThreads {
            override fun ui(): Scheduler = AndroidSchedulers.mainThread()
            override fun io(): Scheduler = Schedulers.io()
        }
    }


}
