package jp.ne.keisuke.kiuchi.devicekanrisan.data.datasource

import io.reactivex.Observable
import io.swagger.client.api.DefaultApi
import io.swagger.client.model.DeviceEntity
import io.swagger.client.model.MessageEntity
import javax.inject.Inject

class DeviceRepository @Inject constructor(private val defaultApi: DefaultApi) : DeviceDataSource {

    override fun update(deviceEntity: DeviceEntity): Observable<MessageEntity> {
        return defaultApi.update(deviceEntity)
    }

    override fun getList(): Observable<List<DeviceEntity>> {
        return defaultApi.list
    }
}
