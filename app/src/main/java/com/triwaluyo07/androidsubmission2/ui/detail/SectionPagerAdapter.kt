package com.triwaluyo07.androidsubmission2.ui.detail


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class SectionPagerAdapter(activity: AppCompatActivity, data: Bundle) : FragmentStateAdapter(activity) {

    private var fragmentBundle: Bundle = data

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = FollowFragment()
            1 -> fragment = FollowingFragment()
        }
        fragment?.arguments = this.fragmentBundle
        return fragment as Fragment

    }

    override fun getItemCount(): Int {
        return 2
    }
}
