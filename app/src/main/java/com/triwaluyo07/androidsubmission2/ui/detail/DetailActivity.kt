package com.triwaluyo07.androidsubmission2.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.StringRes
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.triwaluyo07.androidsubmission2.R
import com.triwaluyo07.androidsubmission2.databinding.ActivityDetailBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailActivity : AppCompatActivity() {


    companion object{
        const val EXTRA_USERNAME = "extra_username"
        const val EXTRA_ID = "extra_id"
        const val EXTRA_AVATAR_URL = "extra_avatar_url"
        const val EXTRA_URL = "extra_url"
        @StringRes
        private val TAB_TITLES = intArrayOf(
                R.string.tab_1,
                R.string.tab_2
        )
    }

    private lateinit var binding: ActivityDetailBinding
    private lateinit var viewModel: DetailViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val username = intent.getStringExtra(EXTRA_USERNAME)
        val id = intent.getIntExtra(EXTRA_ID,0)
        val avatar_url = intent.getStringExtra(EXTRA_AVATAR_URL)
        val url = intent.getStringExtra(EXTRA_URL)

        val bundle = Bundle()
        bundle.putString(EXTRA_USERNAME, username)
        viewModel = ViewModelProvider(this) .get(DetailViewModel::class.java)

        viewModel.setUserDetail(username.toString())

        viewModel.getUserDetail().observe(this,{
            if(it != null)
            {
                binding.apply {
                    Glide.with(this@DetailActivity)
                            .load(it.avatarUrl).apply(RequestOptions())
                            .override(80,80)
                            .into(imgAvatar)
                    tvName.text = it.name
                    tvUsername.text = it.login
                    tvFollowers.text = it.followers
                    tvFollowing.text = it.following
                    tvRepository.text = it.publicRepos
                    tvCompany.text = it.company
                    tvLocation.text = it.location


                }
            }
        })

        var _isChecked = false
        CoroutineScope(Dispatchers.IO).launch {
            val count = viewModel.checkUser(id)
            withContext(Dispatchers.Main){
                if(count != null)
                {
                    if(count > 0)
                    {
                        binding.toogleFavorite.isChecked = true
                        _isChecked = true
                    }
                    else
                    {
                        binding.toogleFavorite.isChecked = false
                        _isChecked = false
                    }
                }
            }
        }

        binding.toogleFavorite.setOnClickListener{
            _isChecked = !_isChecked
            if (_isChecked)
            {
                if (username != null && avatar_url !=null && url != null)
                {
                        viewModel.addToFavorite(username, id, avatar_url, url)
                }
            }
            else
            {
                viewModel.removeFavorite(id)
            }
            binding.toogleFavorite.isChecked = _isChecked
        }

        val sectionPagerAdapter = SectionPagerAdapter(this, bundle)
        val viewPager: ViewPager2 = findViewById(R.id.view_pager)
        viewPager.adapter = sectionPagerAdapter
        val tabs: TabLayout = findViewById(R.id.tabs)
        TabLayoutMediator(tabs, viewPager)
        {
            tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()

        supportActionBar?.elevation = 0f
    }
}