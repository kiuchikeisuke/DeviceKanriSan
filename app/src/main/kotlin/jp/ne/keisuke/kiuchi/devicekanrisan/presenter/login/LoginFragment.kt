package jp.ne.keisuke.kiuchi.devicekanrisan.presenter.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.android.support.DaggerFragment
import jp.ne.keisuke.kiuchi.devicekanrisan.BuildConfig
import jp.ne.keisuke.kiuchi.devicekanrisan.R
import jp.ne.keisuke.kiuchi.devicekanrisan.presenter.list.DeviceListActivity
import jp.ne.keisuke.kiuchi.devicekanrisan.presenter.reader.QRCodeReaderActivity
import jp.ne.keisuke.kiuchi.devicekanrisan.utils.commons.AppType
import kotlinx.android.synthetic.main.fragment_login.*
import javax.inject.Inject

class LoginFragment : DaggerFragment(), LoginContract.View {

    @Inject lateinit var presenter: LoginPresenter

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_login, container, false)
        return view
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.dispose()
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (BuildConfig.APP_TYPE == AppType.MASTER) {
            login_button.setOnClickListener {
                QRCodeReaderActivity.launch(context, navigateMessageId = R.string.navigate_message_login)
                activity.finish()
            }
        } else {
            login_button.visibility = View.GONE
        }
        list_button.setOnClickListener {
            DeviceListActivity.launch(context)
        }
    }

    companion object {
        fun newInstance(): LoginFragment {
            val args = Bundle()
            val fragment = LoginFragment()
            fragment.arguments = args
            return fragment
        }
    }
}
