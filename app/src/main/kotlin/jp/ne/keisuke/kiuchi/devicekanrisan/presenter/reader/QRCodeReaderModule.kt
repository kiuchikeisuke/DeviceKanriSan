package jp.ne.keisuke.kiuchi.devicekanrisan.presenter.reader

import dagger.Binds
import dagger.Module

@Module
abstract class QRCodeReaderModule {
    @Binds abstract fun bindView(fragment: QRCodeReaderFragment): QRCodeReaderContract.View
    @Binds abstract fun bindPresenter(presenter: QRCodeReaderPresenter): QRCodeReaderContract.Presenter
}
