package jp.ne.keisuke.kiuchi.devicekanrisan

import com.jakewharton.threetenabp.AndroidThreeTen
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import jp.ne.keisuke.kiuchi.devicekanrisan.data.entity.UserEntity

class MainApplication : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerRootComponent.builder().create(this)
    }

    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this);
    }

    companion object {
        val emptyUser = UserEntity("--", "--")
        var loginUser: UserEntity = emptyUser
    }
}
