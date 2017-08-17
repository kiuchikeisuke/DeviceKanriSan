package jp.ne.keisuke.kiuchi.devicekanrisan.data.datasource

import io.reactivex.Observable
import io.swagger.client.model.DeviceEntity
import io.swagger.client.model.MessageEntity

interface DeviceDataSource {
    fun update(deviceEntity: DeviceEntity): Observable<MessageEntity>
    fun getList(): Observable<List<DeviceEntity>>
}
