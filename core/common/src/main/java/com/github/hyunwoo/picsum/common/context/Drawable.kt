package com.github.hyunwoo.picsum.common.context

import android.content.Context
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat

fun Context.drawableOf(@DrawableRes resId: Int) = ContextCompat.getDrawable(this, resId)
