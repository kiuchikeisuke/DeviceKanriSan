package jp.ne.keisuke.kiuchi.devicekanrisan.domain

import io.reactivex.Observable
import io.swagger.client.model.DeviceEntity
import io.swagger.client.model.MessageEntity
import jp.ne.keisuke.kiuchi.devicekanrisan.data.datasource.DeviceDataSource
import jp.ne.keisuke.kiuchi.devicekanrisan.data.datasource.UserDataSource
import jp.ne.keisuke.kiuchi.devicekanrisan.utils.commons.ExecutionThreads
import jp.ne.keisuke.kiuchi.devicekanrisan.utils.commons.IoUseCase
import jp.ne.keisuke.kiuchi.devicekanrisan.utils.commons.UseCase
import org.threeten.bp.LocalDate
import javax.inject.Inject

class RentalDevice @Inject constructor(private val deviceDataSource: DeviceDataSource,
                                       private val userDataSource: UserDataSource,
                                       executionThreads: ExecutionThreads)
    : IoUseCase<RentalDevice.RentalDeviceRequest, RentalDevice.RentalDeviceResponse>(executionThreads) {
    override fun execute(requestValue: RentalDeviceRequest): Observable<RentalDeviceResponse> {

        return userDataSource.getUserEntity().flatMap {
            val deviceEntity = DeviceEntity()
            deviceEntity.deviceId = requestValue.deviceId
            deviceEntity.deviceName = requestValue.deviceName
            deviceEntity.userName = it.userName
            deviceEntity.mailAddress = it.mailAddress
            deviceEntity.status = DeviceEntity.StatusEnum.USING
            deviceEntity.returnDate = LocalDate.now().plusDays(7)
            deviceDataSource.update(deviceEntity)
        }.map { RentalDeviceResponse(it) }
    }

    data class RentalDeviceRequest(val deviceName: String, val deviceId: String) : UseCase.RequestValue
    data class RentalDeviceResponse(val messageEntity: MessageEntity) : UseCase.ResponseValue
}
