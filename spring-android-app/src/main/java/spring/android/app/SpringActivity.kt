package spring.android.app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

abstract class SpringActivity : AppCompatActivity() {


    @Suppress("UNCHECKED_CAST")
    fun <T : Fragment> retrieveFragment(
        savedInstanceState: Bundle?,
        clazz: Class<T>,
        tag: String? = null,
        id: Int = 0
    ): T {
        var fragment: T? = null
        if (savedInstanceState != null) {
            fragment = if (tag == null) {
                supportFragmentManager.findFragmentById(id)
            } else {
                supportFragmentManager.findFragmentByTag(tag)
            } as? T?
        }
        return fragment ?: clazz.newInstance()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.activityCallbacks().forEach {
            it.onCreate(savedInstanceState)
        }
    }

    override fun onStart() {
        super.onStart()
        App.activityCallbacks().forEach {
            it.onStart()
        }
    }

    override fun onRestart() {
        super.onRestart()
        App.activityCallbacks().forEach {
            it.onRestart()
        }
    }

    override fun onResume() {
        super.onResume()
        App.activityCallbacks().forEach {
            it.onResume()
        }
    }

    override fun onPause() {
        super.onPause()
        App.activityCallbacks().forEach {
            it.onPause()
        }
    }

    override fun onStop() {
        super.onStop()
        App.activityCallbacks().forEach {
            it.onStop()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        App.activityCallbacks().forEach {
            it.onDestroy()
        }
    }

}