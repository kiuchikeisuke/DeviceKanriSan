package jp.ne.keisuke.kiuchi.devicekanrisan.data

import dagger.Binds
import dagger.Module
import jp.ne.keisuke.kiuchi.devicekanrisan.data.datasource.DeviceDataSource
import jp.ne.keisuke.kiuchi.devicekanrisan.data.datasource.DeviceRepository
import jp.ne.keisuke.kiuchi.devicekanrisan.data.datasource.UserDataSource
import jp.ne.keisuke.kiuchi.devicekanrisan.data.datasource.UserRepository

/* module for Repositories */
@Module
abstract class RepositoryModules {
    /*if you add a new datasource & repository, add a new provideMethod. Like this */
//  @Binds abstract fun bindSomeDataSource(repository: SomeRepository): SomeDataSource
    @Binds abstract fun bindDeviceDataSource(repository: DeviceRepository): DeviceDataSource

    @Binds abstract fun bindUserDataSource(repository: UserRepository): UserDataSource
}
