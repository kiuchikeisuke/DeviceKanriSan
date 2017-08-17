package jp.ne.keisuke.kiuchi.devicekanrisan.presenter.list

import jp.ne.keisuke.kiuchi.devicekanrisan.domain.GetList
import jp.ne.keisuke.kiuchi.devicekanrisan.utils.commons.DefaultDisposableObserver
import jp.ne.keisuke.kiuchi.devicekanrisan.utils.commons.UseCase
import javax.inject.Inject

class DeviceListPresenter @Inject constructor(private val getList: GetList, private val view: DeviceListContract.View) : DeviceListContract.Presenter {
    override fun load() {
        getList.execute(UseCase.NoRequestValue.INSTANCE, DefaultDisposableObserver(next = { view.loaded(it.deviceEntities) }))
    }

    override fun dispose() {
        getList.dispose()
    }
}
