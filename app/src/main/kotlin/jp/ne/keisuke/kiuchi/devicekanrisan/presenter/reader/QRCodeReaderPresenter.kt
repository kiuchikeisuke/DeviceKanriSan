package jp.ne.keisuke.kiuchi.devicekanrisan.presenter.reader

import jp.ne.keisuke.kiuchi.devicekanrisan.domain.Login
import jp.ne.keisuke.kiuchi.devicekanrisan.domain.RentalDevice
import jp.ne.keisuke.kiuchi.devicekanrisan.domain.ReturnDevice
import jp.ne.keisuke.kiuchi.devicekanrisan.utils.commons.DefaultDisposableObserver
import javax.inject.Inject

class QRCodeReaderPresenter @Inject constructor(
        private val login: Login,
        private val rentalDevice: RentalDevice,
        private val returnDevice: ReturnDevice,
        private val view: QRCodeReaderContract.View) : QRCodeReaderContract.Presenter {
    override fun execLogin(userName: String, mailAddress: String) {
        login.execute(Login.LoginRequest(userName, mailAddress),
                DefaultDisposableObserver(next = { view.logined(it.userEntity) }))
    }

    override fun execRental(deviceName: String, deviceId: String) {
        rentalDevice.execute(RentalDevice.RentalDeviceRequest(deviceName, deviceId),
                DefaultDisposableObserver(next = { view.rentaled(it.messageEntity) }))
    }

    override fun execReturn(deviceName: String, deviceId: String) {
        returnDevice.execute(ReturnDevice.ReturnDeviceRequest(deviceName, deviceId),
                DefaultDisposableObserver(next = { view.returned(it.messageEntity) }))
    }

    override fun dispose() {
        login.dispose()
        rentalDevice.dispose()
        returnDevice.dispose()
    }
}
