package jp.ne.keisuke.kiuchi.devicekanrisan.presenter.list

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import butterknife.bindView
import io.swagger.client.model.DeviceEntity
import jp.ne.keisuke.kiuchi.devicekanrisan.R
import org.threeten.bp.LocalDate

class DeviceAdapter(var entities: List<DeviceEntity>, val onClickEvent: (DeviceEntity) -> Unit) : RecyclerView.Adapter<DeviceAdapter.DeviceViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): DeviceViewHolder {
        return DeviceViewHolder(LayoutInflater.from(parent!!.context).inflate(R.layout.adapter_device, parent, false))
    }

    override fun getItemCount(): Int = entities.size

    override fun onBindViewHolder(holder: DeviceViewHolder?, position: Int) {
        holder!!.onBindViewHolder(entities[position])
    }

    inner class DeviceViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        private val deviceName: TextView by bindView(R.id.device_name_text_view)
        private val deviceId: TextView by bindView(R.id.device_id_text_view)
        private val userName: TextView by bindView(R.id.user_name_text_view)
        private val mailAddress: TextView by bindView(R.id.mail_address_text_view)
        private val rentalLimit: TextView by bindView(R.id.rental_limit_text_view)
        private val status: TextView by bindView(R.id.status_text_view)

        fun onBindViewHolder(entity: DeviceEntity) {
            itemView.setOnClickListener { onClickEvent(entity) }
            deviceName.text = itemView.context.getString(R.string.device_name, entity.deviceName)
            deviceId.text = itemView.context.getString(R.string.device_id, entity.deviceId)
            userName.text = itemView.context.getString(R.string.user_name, entity.userName)
            mailAddress.text = itemView.context.getString(R.string.mail_address, entity.mailAddress)
            if (entity.status == DeviceEntity.StatusEnum.USING && entity.returnDate != null) {
                rentalLimit.text = itemView.context.getString(R.string.rental_limit, entity.returnDate.year.toString() + "/" + entity.returnDate.month.value + "/" + entity.returnDate.dayOfMonth)
                if (LocalDate.now().isAfter(entity.returnDate)) {
                    itemView.background = itemView.resources.getDrawable(R.color.limitOverColor)
                    status.text = itemView.context.getString(R.string.status_limit_over)
                } else {
                    itemView.background = itemView.resources.getDrawable(R.color.rentalColor)
                    status.text = itemView.context.getString(R.string.status_using)
                }
            } else {
                rentalLimit.text = itemView.context.getString(R.string.rental_limit, "--")
                status.text = itemView.context.getString(R.string.status_free)
            }


        }
    }
}
