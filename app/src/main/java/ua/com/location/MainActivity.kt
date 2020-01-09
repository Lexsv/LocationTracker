package ua.com.location

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*
import ua.com.location.di.mainActivity.DaggerMainComponent
import ua.com.location.di.mainActivity.MainPresenterModul
import ua.com.location.models.mainModel.IMainVM
import ua.com.location.models.mainModel.MainVM
import ua.com.location.presentation.mainActivity.MainPresenterInterfas
import ua.com.location.presentation.mainActivity.MainView
import ua.com.location.repository.data.LocalStoreVW
import ua.com.location.repository.room.userinfo.UserInfo
import javax.inject.Inject


class MainActivity : AppCompatActivity(), MainView,
    NavigationView.OnNavigationItemSelectedListener {


    @Inject
    lateinit var mainPresenterInterfas: MainPresenterInterfas


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addDaggerDepand()
        mainPresenterInterfas.onStart(this)
        setContentView(R.layout.activity_main)
        addNavigationDrawer()


    }








    override fun getLifecycleOwner(): LifecycleOwner = this
    override fun getVM(): IMainVM = ViewModelProviders.of(this).get(MainVM::class.java)

    private fun addDaggerDepand() {
        DaggerMainComponent.builder()
            .mainPresenterModul(MainPresenterModul(this))
            .build()
            .inject(this)
    }

    override fun actionMassege(key: String) {
        Toast.makeText(this, key, Toast.LENGTH_LONG).show()
    }


    override fun onBackPressed() {

        if (LocalStoreVW.nowFragment != "COLECTION" && LocalStoreVW.nowFragment != "LOGIN") {
            super.onBackPressed()
        }
    }





    override fun onNavigationItemSelected(p0: MenuItem): Boolean {
        when (p0.itemId) {
            R.id.db_list -> mainPresenterInterfas.onClick("COLECTION")
            R.id.db_map -> mainPresenterInterfas.onClick("MAP")
            R.id.db_exit -> mainPresenterInterfas.onExit()
            R.id.linck -> mainPresenterInterfas.onClick("LINCK")
            R.id.db_calendar -> mainPresenterInterfas.onClick("CALENDAR")
        }
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }


    private fun addNavigationDrawer(){

        val actionBarDrawerToggle = ActionBarDrawerToggle(
            this, drawer_layout, toolbar, 0, 0
        )

        drawer_layout.addDrawerListener(actionBarDrawerToggle)
        drawer_layout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
        actionBarDrawerToggle.syncState()

        navigationView.setNavigationItemSelectedListener(this)


    }

   override fun goTo(key: String) {
        when (key) {
            "MAP" -> Navigation.findNavController(this, R.id.my_nav_host_fragment).navigate(R.id.map)
            "LOGIN" -> Navigation.findNavController(this, R.id.my_nav_host_fragment).navigate(R.id.login)
            "COLECTION" -> Navigation.findNavController(this,R.id.my_nav_host_fragment).navigate(R.id.listAndTrack)
            "CALENDAR" -> Navigation.findNavController(this,R.id.my_nav_host_fragment).navigate(R.id.calendar)
            "LINCK" ->startActivity(Intent(Intent.ACTION_VIEW,Uri.parse("https://www.youtube.com/?gl=UA")))
        }
    }

    override fun unMenuvisibility(){
        drawer_layout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
        toolbar.visibility = View.GONE
    }

    override fun menuvisibility() {
        toolbar.visibility = View.VISIBLE
        drawer_layout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
    }

    override fun nameUser(userInfo: UserInfo) {
        navigationView.getHeaderView(0).findViewById<TextView>(R.id.main_menu_name).text = userInfo.name

    }

}
