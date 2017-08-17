package jp.ne.keisuke.kiuchi.devicekanrisan.presenter.reader

import dagger.Subcomponent
import dagger.android.AndroidInjector

@Subcomponent(modules = arrayOf(QRCodeReaderModule::class))
interface QRCodeReaderComponent : AndroidInjector<QRCodeReaderFragment> {
    @Subcomponent.Builder abstract class Builder : AndroidInjector.Builder<QRCodeReaderFragment>()
}
