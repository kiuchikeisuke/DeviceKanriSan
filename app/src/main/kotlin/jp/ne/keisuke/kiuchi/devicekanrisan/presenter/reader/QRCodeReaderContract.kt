package jp.ne.keisuke.kiuchi.devicekanrisan.presenter.reader

import io.swagger.client.model.MessageEntity
import jp.ne.keisuke.kiuchi.devicekanrisan.data.entity.UserEntity
import jp.ne.keisuke.kiuchi.devicekanrisan.utils.commons.BasePresenter
import jp.ne.keisuke.kiuchi.devicekanrisan.utils.commons.BaseView

interface QRCodeReaderContract {

    interface View : BaseView {
        fun logined(userEntity: UserEntity)
        fun rentaled(messageEntity: MessageEntity)
        fun returned(messageEntity: MessageEntity)
    }

    interface Presenter : BasePresenter {
        fun execLogin(userName: String, mailAddress: String)
        fun execRental(deviceName: String, deviceId: String)
        fun execReturn(deviceName: String, deviceId: String)
    }
}
