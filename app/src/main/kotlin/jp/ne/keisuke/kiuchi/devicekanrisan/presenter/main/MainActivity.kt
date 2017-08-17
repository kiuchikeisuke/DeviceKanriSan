package jp.ne.keisuke.kiuchi.devicekanrisan.presenter.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import jp.ne.keisuke.kiuchi.devicekanrisan.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.base_activity)

        var fragment = supportFragmentManager.findFragmentById(R.id.base_fragment) as MainFragment?
        if (fragment == null) {
            fragment = MainFragment.newInstance()
            supportFragmentManager.beginTransaction().add(R.id.base_fragment, fragment).commit()
        }
    }

    companion object {
        fun launch(context: Context, flags: Int = Intent.FLAG_ACTIVITY_NEW_TASK) {
            val intent = Intent(context, MainActivity::class.java)
            intent.flags = flags
            context.startActivity(intent)
        }
    }
}
