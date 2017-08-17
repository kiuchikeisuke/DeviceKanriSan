package jp.ne.keisuke.kiuchi.devicekanrisan.presenter.reader

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.zxing.ResultPoint
import com.journeyapps.barcodescanner.BarcodeCallback
import com.journeyapps.barcodescanner.BarcodeResult
import dagger.android.support.DaggerFragment
import io.swagger.client.model.MessageEntity
import jp.ne.keisuke.kiuchi.devicekanrisan.R
import jp.ne.keisuke.kiuchi.devicekanrisan.data.entity.UserEntity
import jp.ne.keisuke.kiuchi.devicekanrisan.presenter.main.MainActivity
import jp.ne.keisuke.kiuchi.devicekanrisan.utils.commons.QRCodeDecorder
import kotlinx.android.synthetic.main.fragment_qrcode_reader.*
import javax.inject.Inject

class QRCodeReaderFragment : DaggerFragment(), QRCodeReaderContract.View {

    override fun logined(userEntity: UserEntity) {
        Toast.makeText(context, userEntity.userName + "でログインしました", Toast.LENGTH_LONG).show()
        MainActivity.launch(context)
        activity.finish()
    }

    override fun rentaled(messageEntity: MessageEntity) {
        AlertDialog.Builder(context).setMessage(messageEntity.message + context.getString(R.string.rental_dialog_message))
                .setPositiveButton(R.string.yes, { dialogInterface, _ ->
                    dialogInterface.dismiss()
                })
                .setNegativeButton(R.string.no, { dialogInterface, _ ->
                    dialogInterface.dismiss()
                    MainActivity.launch(context)
                    activity.finish()
                })
                .setCancelable(false)
                .create().show()
    }

    override fun returned(messageEntity: MessageEntity) {
        if (messageEntity.message != "パラメータがおかしいです") {
            AlertDialog.Builder(context).setMessage(messageEntity.message + context.getString(R.string.returned_dialog_message))
                    .setPositiveButton(R.string.yes, { dialogInterface, _ ->
                        dialogInterface.dismiss()
                    })
                    .setNegativeButton(R.string.no, { dialogInterface, _ ->
                        dialogInterface.dismiss()
                        MainActivity.launch(context)
                        activity.finish()
                    })
                    .setCancelable(false)
                    .create().show()
        } else {
            AlertDialog.Builder(context).setMessage(R.string.parameter_error_message)
                    .setPositiveButton(R.string.yes, { dialogInterface, _ ->
                        dialogInterface.dismiss()
                    })
                    .setCancelable(false)
                    .create().show()
        }
    }

    @Inject lateinit var presenter: QRCodeReaderPresenter
    private val navigateMessageId: Int by lazy {
        arguments.getInt(QRCodeReaderActivity.NAVIGATE_MESSAGE_ID)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_qrcode_reader, container, false)
        return view
    }

    override fun onPause() {
        super.onPause()
        barcode_view.pause()
    }

    override fun onResume() {
        super.onResume()
        barcode_view.resume()
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        debug_button.setOnClickListener {
            presenter.execRental("Galaxy Nexus 7", "1")
        }
        barcode_navigate_message.setText(navigateMessageId)
        barcode_view.apply {
            setStatusText(getString(R.string.code_reader_hint))
            decodeSingle(object : BarcodeCallback {
                override fun barcodeResult(result: BarcodeResult?) {
                    Log.d("qr", "call barcodeResult")
                    if (result != null) {
                        Log.d("qr", "result is" + result.text)
                        when (navigateMessageId) {
                            R.string.navigate_message_login -> {
                                val strings = QRCodeDecorder.decodeUserInfo(result.text)
                                if (strings.size == 5) {
                                    val userName = strings[1]
                                    val mailAddress = strings[2]
                                    presenter.execLogin(userName, mailAddress)
                                } else {
                                    Toast.makeText(context, R.string.qrcode_read_error, Toast.LENGTH_LONG).show()
                                }
                            }
                            R.string.navigate_message_rental -> {
                                val strings = QRCodeDecorder.decodeDeviceInfo(result.text)
                                if (strings.size == 2) {
                                    val deviceName = strings[0]
                                    val deviceId = strings[1]
                                    presenter.execRental(deviceName, deviceId)
                                } else {
                                    Toast.makeText(context, R.string.qrcode_read_error, Toast.LENGTH_LONG).show()
                                }
                            }
                            R.string.navigate_message_return -> {
                                val strings = QRCodeDecorder.decodeDeviceInfo(result.text)
                                if (strings.size == 2) {
                                    val deviceName = strings[0]
                                    val deviceId = strings[1]
                                    presenter.execReturn(deviceName, deviceId)
                                } else {
                                    Toast.makeText(context, R.string.qrcode_read_error, Toast.LENGTH_LONG).show()
                                }
                            }
                            else -> {
                                Toast.makeText(context, R.string.navigate_message_error, Toast.LENGTH_LONG).show()
                            }
                        }
                    }
                }

                override fun possibleResultPoints(resultPoints: MutableList<ResultPoint>?) {
                }
            })
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.dispose()
    }

    companion object {
        fun newInstance(navigateMessageId: Int): QRCodeReaderFragment {
            val args = Bundle()
            val fragment = QRCodeReaderFragment()
            fragment.arguments = args
            fragment.arguments.putInt(QRCodeReaderActivity.NAVIGATE_MESSAGE_ID, navigateMessageId)
            return fragment
        }
    }
}
