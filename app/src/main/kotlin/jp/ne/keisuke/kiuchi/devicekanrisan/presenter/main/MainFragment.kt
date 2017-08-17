package jp.ne.keisuke.kiuchi.devicekanrisan.presenter.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import dagger.android.support.DaggerFragment
import jp.ne.keisuke.kiuchi.devicekanrisan.R
import jp.ne.keisuke.kiuchi.devicekanrisan.data.entity.UserEntity
import jp.ne.keisuke.kiuchi.devicekanrisan.presenter.list.DeviceListActivity
import jp.ne.keisuke.kiuchi.devicekanrisan.presenter.login.LoginActivity
import jp.ne.keisuke.kiuchi.devicekanrisan.presenter.reader.QRCodeReaderActivity
import kotlinx.android.synthetic.main.fragment_main.*
import javax.inject.Inject

class MainFragment : DaggerFragment(), MainContract.View {
    override fun logouted(userEntity: UserEntity) {
        Toast.makeText(context, R.string.logout_message, Toast.LENGTH_LONG).show()
        LoginActivity.launch(context)
        activity.finish()
    }

    override fun loaded(userEntity: UserEntity) {
        user_name_text_view.text = getString(R.string.current_user, userEntity.userName)
    }

    @Inject lateinit var presenter: MainPresenter

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_main, container, false)
        return view
    }

    override fun onResume() {
        super.onResume()
        presenter.load()
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        list_button.setOnClickListener {
            DeviceListActivity.launch(context)
        }
        logout_button.setOnClickListener {
            presenter.logout()
        }
        rental_button.setOnClickListener {
            QRCodeReaderActivity.launch(context, navigateMessageId = R.string.navigate_message_rental)
        }
        return_button.setOnClickListener {
            QRCodeReaderActivity.launch(context, navigateMessageId = R.string.navigate_message_return)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.dispose()
    }

    companion object {
        fun newInstance(): MainFragment {
            val args = Bundle()
            val fragment = MainFragment()
            fragment.arguments = args
            return fragment
        }
    }
}
