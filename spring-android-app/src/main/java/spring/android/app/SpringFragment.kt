package spring.android.app

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment

abstract class SpringFragment : Fragment() {


    @Suppress("UNCHECKED_CAST")
    private fun <T : Fragment> retrieveFragment(
        savedInstanceState: Bundle?,
        clazz: Class<T>,
        tag: String? = null,
        id: Int = 0
    ): T {
        var fragment: T? = null
        if (savedInstanceState != null) {
            fragment = if (tag == null) {
                childFragmentManager.findFragmentById(id)
            } else {
                childFragmentManager.findFragmentByTag(tag)
            } as? T?
        }
        return fragment ?: clazz.newInstance()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        App.fragmentCallbacks().forEach {
            it.onAttach(context)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.fragmentCallbacks().forEach {
            it.onCreate(savedInstanceState)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        App.fragmentCallbacks().forEach {
            it.onViewCreated(view, savedInstanceState)
        }
    }

    override fun onPause() {
        super.onPause()
        App.fragmentCallbacks().forEach {
            it.onPause()
        }
    }


    override fun onStop() {
        super.onStop()
        App.fragmentCallbacks().forEach {
            it.onStop()
        }
    }

    override fun onResume() {
        super.onResume()
        App.fragmentCallbacks().forEach {
            it.onResume()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        App.fragmentCallbacks().forEach {
            it.onDestroy()
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        App.fragmentCallbacks().forEach {
            it.onDestroyView()
        }

    }

    override fun onDetach() {
        super.onDetach()
        App.fragmentCallbacks().forEach {
            it.onDetach()
        }
    }

}