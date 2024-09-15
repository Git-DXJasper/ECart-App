package com.dongze.ecart.view.category

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter(
    private val fragList: List<Fragment>,
    fragmentManager: FragmentActivity
):FragmentStateAdapter(fragmentManager){

    override fun getItemCount() = fragList.size

    override fun createFragment(position: Int) = fragList[position]
}