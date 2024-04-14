package com.practice.myapplication.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.practice.myapplication.R

class ViewPagerTabFragment : Fragment() {

    private val TAG = ViewPagerTabFragment::class.simpleName

    private lateinit var collectionAdapter: CollectionAdapter
    private lateinit var viewPager2: ViewPager2
    private lateinit var tabLayout: TabLayout

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.i(TAG, "onCreateView")
        return inflater.inflate(R.layout.fragment_viewpager, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.i(TAG, "onViewCreated")
        collectionAdapter = CollectionAdapter(this)
        viewPager2 = view.findViewById(R.id.view_pager)
        viewPager2.adapter = collectionAdapter

        tabLayout = view.findViewById(R.id.tab_layout)
        TabLayoutMediator(tabLayout, viewPager2) { tab, position ->
            tab.text = "OBJECT ${(position + 1)}"
        }.attach()

        viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                Log.i(TAG, "onPageSelected position = $position")
            }
        })
    }
}

class CollectionAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    private val TAG = CollectionAdapter::class.simpleName

    override fun getItemCount(): Int = 10

    override fun createFragment(position: Int): Fragment {
        Log.i(TAG, "createFragment")
        val fragment = ObjectFragment()
        fragment.arguments = Bundle().apply {
            putInt("arg", position + 1)
        }
        return fragment
    }
}

class ObjectFragment : Fragment() {
    private val TAG = ObjectFragment::class.simpleName

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.i(TAG, "onCreateView")
        return inflater.inflate(R.layout.fragment_viewpager_object, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.i(TAG, "onViewCreated")
        arguments?.takeIf { it.containsKey("arg") }?.apply {
            val textView: TextView = view.findViewById(R.id.viewpager_text)
            textView.text = getInt("arg").toString()
        }
    }

}