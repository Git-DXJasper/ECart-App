package com.dongze.ecart.view.dashboard

import android.view.View
import androidx.viewpager2.widget.ViewPager2

class DepthPageTransformer : ViewPager2.PageTransformer {
    override fun transformPage(view: View, position: Float) {
        when {
            position < -1 -> {
                // Page is way off-screen to the left.
                view.alpha = 0f
            }
            position <= 0 -> {
                // Fade the page out.
                view.alpha = 1f
                view.translationX = 0f
                view.translationZ = 0f
                view.scaleX = 1f
                view.scaleY = 1f
            }
            position <= 1 -> {
                // Use the default slide transition when moving to the left page
                view.alpha = 1 - position
                view.translationX = view.width * -position
                view.translationZ = -1f

                // Scale the page down (between MIN_SCALE and 1)
                val scaleFactor = MIN_SCALE + (1 - MIN_SCALE) * (1 - Math.abs(position))
                view.scaleX = scaleFactor
                view.scaleY = scaleFactor
            }
            else -> {
                // Page is way off-screen to the right.
                view.alpha = 0f
            }
        }
    }

    companion object {
        private const val MIN_SCALE = 0.75f
    }
}