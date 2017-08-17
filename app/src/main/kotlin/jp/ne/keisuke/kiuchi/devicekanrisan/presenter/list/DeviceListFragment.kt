package jp.ne.keisuke.kiuchi.devicekanrisan.presenter.list

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.android.support.DaggerFragment
import io.swagger.client.model.DeviceEntity
import jp.ne.keisuke.kiuchi.devicekanrisan.R
import kotlinx.android.synthetic.main.fragment_device_list.*
import javax.inject.Inject

class DeviceListFragment : DaggerFragment(), DeviceListContract.View {
    private lateinit var deviceList: List<DeviceEntity>

    override fun loaded(deviceEntities: List<DeviceEntity>) {
        this.deviceList = deviceEntities
        if (device_list.adapter == null) {
            device_list.adapter = DeviceAdapter(deviceEntities, {})
        } else {
            (device_list.adapter as DeviceAdapter).entities = deviceEntities
            device_list.adapter.notifyDataSetChanged()
        }
    }

    fun updateSearchQuery(query: String) {
        if (device_list.adapter != null) {
            if (!TextUtils.isEmpty(query)) {
                (device_list.adapter as DeviceAdapter).entities = this.deviceList.filter {
                    it.deviceName.contains(other = query)
                            || it.mailAddress.contains(other = query)
                            || it.userName.contains(other = query)
                            || context.getString(R.string.status_using).contains(other = query)
                            || context.getString(R.string.status_limit_over).contains(other = query)
                            || context.getString(R.string.status_free).contains(other = query)
                }
            } else {
                (device_list.adapter as DeviceAdapter).entities = this.deviceList
            }
            device_list.adapter.notifyDataSetChanged()
        }
    }

    @Inject lateinit var presenter: DeviceListPresenter

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_device_list, container, false)
        return view
    }

    override fun onResume() {
        super.onResume()
        presenter.load()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.dispose()
    }

    companion object {
        fun newInstance(): DeviceListFragment {
            val args = Bundle()
            val fragment = DeviceListFragment()
            fragment.arguments = args
            return fragment
        }
    }
}
