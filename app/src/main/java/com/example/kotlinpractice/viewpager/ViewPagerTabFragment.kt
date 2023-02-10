package com.example.kotlinpractice.viewpager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.kotlinpractice.R
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class ViewPagerTabFragment : Fragment() {

    private lateinit var collectionAdapter: CollectionAdapter
    private lateinit var viewPager2: ViewPager2
    private lateinit var tabLayout: TabLayout

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_viewpager, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        collectionAdapter = CollectionAdapter(this)
        viewPager2 = view.findViewById(R.id.view_pager)
        viewPager2.adapter = collectionAdapter

        tabLayout = view.findViewById(R.id.tab_layout)
        TabLayoutMediator(tabLayout, viewPager2) {tab, position ->
            tab.text = "OBJECT ${(position + 1)}"
        }.attach()
    }
}

class CollectionAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 10

    override fun createFragment(position: Int): Fragment {
        val fragment = ObjectFragment()
        fragment.arguments = Bundle().apply {
            putInt("arg", position + 1)
        }
        return fragment
    }
}

class ObjectFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_viewpager_object, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        arguments?.takeIf { it.containsKey("arg") }?.apply {
            val textView: TextView = view.findViewById(R.id.viewpager_text)
            textView.text = getInt("arg").toString()
        }
    }

}