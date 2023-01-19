package com.github.hyunwoo.picsum.common.intent

import android.app.Activity
import android.os.Parcelable
import kotlin.properties.ReadOnlyProperty

fun <P : Parcelable> parcelableExtra(defaultValue: P? = null) =
    ReadOnlyProperty<Activity, P?> { thisRef, property ->
        thisRef.intent.extras?.getParcelable(property.name) ?: defaultValue
    }
