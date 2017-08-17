package jp.ne.keisuke.kiuchi.devicekanrisan.domain

import io.reactivex.Observable
import io.swagger.client.model.DeviceEntity
import jp.ne.keisuke.kiuchi.devicekanrisan.data.datasource.DeviceDataSource
import jp.ne.keisuke.kiuchi.devicekanrisan.utils.commons.ExecutionThreads
import jp.ne.keisuke.kiuchi.devicekanrisan.utils.commons.OutputOnlyUseCase
import jp.ne.keisuke.kiuchi.devicekanrisan.utils.commons.UseCase
import javax.inject.Inject

class GetList @Inject constructor(private val deviceDataSource: DeviceDataSource, executionThreads: ExecutionThreads)
    : OutputOnlyUseCase<GetList.GetListResponse>(executionThreads) {
    override fun execute(): Observable<GetListResponse> {
        return deviceDataSource.getList().map { GetListResponse(it) }
    }

    data class GetListResponse(val deviceEntities: List<DeviceEntity>) : UseCase.ResponseValue
}
