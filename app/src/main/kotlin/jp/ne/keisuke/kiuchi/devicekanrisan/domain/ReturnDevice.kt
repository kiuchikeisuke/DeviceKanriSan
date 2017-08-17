package jp.ne.keisuke.kiuchi.devicekanrisan.domain

import io.reactivex.Observable
import io.swagger.client.model.DeviceEntity
import io.swagger.client.model.MessageEntity
import jp.ne.keisuke.kiuchi.devicekanrisan.MainApplication
import jp.ne.keisuke.kiuchi.devicekanrisan.data.datasource.DeviceDataSource
import jp.ne.keisuke.kiuchi.devicekanrisan.utils.commons.ExecutionThreads
import jp.ne.keisuke.kiuchi.devicekanrisan.utils.commons.IoUseCase
import jp.ne.keisuke.kiuchi.devicekanrisan.utils.commons.UseCase
import javax.inject.Inject

class ReturnDevice @Inject constructor(private val deviceDataSource: DeviceDataSource,
                                       executionThreads: ExecutionThreads)
    : IoUseCase<ReturnDevice.ReturnDeviceRequest, ReturnDevice.ReturnDeviceResponse>(executionThreads) {
    override fun execute(requestValue: ReturnDeviceRequest): Observable<ReturnDeviceResponse> {
        val deviceEntity = DeviceEntity()
        deviceEntity.deviceId = requestValue.deviceId
        deviceEntity.deviceName = requestValue.deviceName
        deviceEntity.userName = MainApplication.emptyUser.userName
        deviceEntity.mailAddress = MainApplication.emptyUser.mailAddress
        deviceEntity.status = DeviceEntity.StatusEnum.FREE
        deviceEntity.returnDate = null
        return deviceDataSource.update(deviceEntity).map { ReturnDeviceResponse(it) }
    }

    data class ReturnDeviceRequest(val deviceName: String, val deviceId: String) : UseCase.RequestValue
    data class ReturnDeviceResponse(val messageEntity: MessageEntity) : UseCase.ResponseValue
}
