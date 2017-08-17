package jp.ne.keisuke.kiuchi.devicekanrisan.presenter.reader

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import jp.ne.keisuke.kiuchi.devicekanrisan.R
import kotlinx.android.synthetic.main.base_activity.*

class QRCodeReaderActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.base_activity)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val naviMessageId = intent.getIntExtra(NAVIGATE_MESSAGE_ID, R.string.navigate_message_error)
        var fragment = supportFragmentManager.findFragmentById(R.id.base_fragment) as QRCodeReaderFragment?
        if (fragment == null) {
            fragment = QRCodeReaderFragment.newInstance(naviMessageId)
            supportFragmentManager.beginTransaction().add(R.id.base_fragment, fragment).commit()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }

    companion object {
        fun launch(context: Context, flags: Int = Intent.FLAG_ACTIVITY_NEW_TASK, navigateMessageId: Int) {
            val intent = Intent(context, QRCodeReaderActivity::class.java)
            intent.flags = flags
            intent.putExtra(NAVIGATE_MESSAGE_ID, navigateMessageId)
            context.startActivity(intent)
        }

        const val NAVIGATE_MESSAGE_ID = "naviId"
    }
}
