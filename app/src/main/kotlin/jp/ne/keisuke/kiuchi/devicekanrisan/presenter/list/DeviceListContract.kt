package jp.ne.keisuke.kiuchi.devicekanrisan.presenter.list

import io.swagger.client.model.DeviceEntity
import jp.ne.keisuke.kiuchi.devicekanrisan.utils.commons.BasePresenter
import jp.ne.keisuke.kiuchi.devicekanrisan.utils.commons.BaseView

interface DeviceListContract {

    interface View : BaseView {
        fun loaded(deviceEntities: List<DeviceEntity>)
    }

    interface Presenter : BasePresenter {
        fun load()
    }
}
