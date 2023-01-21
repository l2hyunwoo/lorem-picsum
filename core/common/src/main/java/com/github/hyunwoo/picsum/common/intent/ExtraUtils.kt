package com.github.hyunwoo.picsum.common.intent

import android.app.Activity
import android.os.Build
import android.os.Bundle
import android.os.Parcelable
import kotlin.properties.ReadOnlyProperty

inline fun <reified T : Parcelable> Bundle?.parcelable(name: String): T? {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        return this?.getParcelable(name, T::class.java)
    }
    return this?.getParcelable(name)
}

inline fun <reified P : Parcelable> parcelableExtra(defaultValue: P? = null) =
    ReadOnlyProperty<Activity, P?> { thisRef, property ->
        thisRef.intent.extras?.parcelable(property.name) ?: defaultValue
    }
