package com.danilo.furtado.theweatherapp.viewbindingext

import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.viewbinding.ViewBinding
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

inline fun <reified T : ViewBinding> AppCompatActivity.viewBinding() =
    ActivityViewBindingDelegate(this, T::class.java)

class ActivityViewBindingDelegate<T : ViewBinding>(
    activity: AppCompatActivity,
    private val bindingClass: Class<T>
) : ReadOnlyProperty<AppCompatActivity, T> {
    private var _binding: T? = null

    init {
        activity.lifecycle.addObserver(
            object : LifecycleEventObserver {
                override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
                    if (event == Lifecycle.Event.ON_DESTROY) {
                        _binding = null
                        source.lifecycle.removeObserver(this)
                    }
                }
            }
        )
    }

    @Suppress("UNCHECKED_CAST")
    override fun getValue(thisRef: AppCompatActivity, property: KProperty<*>): T {
        _binding?.let { return it }
        val inflateMethod = bindingClass.getMethod("inflate", LayoutInflater::class.java)
        val invokeLayout = inflateMethod.invoke(null, thisRef.layoutInflater) as T
        thisRef.setContentView(invokeLayout.root)

        return invokeLayout.also { this._binding = it }
    }
}
