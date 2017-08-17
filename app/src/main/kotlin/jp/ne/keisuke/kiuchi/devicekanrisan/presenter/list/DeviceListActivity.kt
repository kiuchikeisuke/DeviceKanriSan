package jp.ne.keisuke.kiuchi.devicekanrisan.presenter.list

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.widget.SearchView
import jp.ne.keisuke.kiuchi.devicekanrisan.R
import kotlinx.android.synthetic.main.base_activity.*

class DeviceListActivity : AppCompatActivity(), SearchView.OnQueryTextListener {
    override fun onQueryTextSubmit(p0: String?): Boolean {
        return false
    }

    override fun onQueryTextChange(p0: String?): Boolean {
        var fragment = supportFragmentManager.findFragmentById(R.id.base_fragment) as DeviceListFragment?
        if (fragment != null && p0 != null) {
            fragment.updateSearchQuery(p0)
        }
        return false
    }

    private lateinit var searchView: SearchView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.base_activity)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        var fragment = supportFragmentManager.findFragmentById(R.id.base_fragment) as DeviceListFragment?
        if (fragment == null) {
            fragment = DeviceListFragment.newInstance()
            supportFragmentManager.beginTransaction().add(R.id.base_fragment, fragment).commit()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search_menu, menu)
        searchView = menu?.findItem(R.id.search_menu_item)?.actionView as SearchView
        searchView.queryHint = getString(R.string.search_hint)
        searchView.setOnQueryTextListener(this)
        searchView.isSubmitButtonEnabled = false

        return super.onCreateOptionsMenu(menu)

    }

    companion object {
        fun launch(context: Context, flags: Int = Intent.FLAG_ACTIVITY_NEW_TASK) {
            val intent = Intent(context, DeviceListActivity::class.java)
            intent.flags = flags
            context.startActivity(intent)
        }
    }
}
