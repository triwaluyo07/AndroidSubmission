package com.triwaluyo07.androidsubmission2.ui.main


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.view.*
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.triwaluyo07.androidsubmission2.R
import com.triwaluyo07.androidsubmission2.data.model.UserData
import com.triwaluyo07.androidsubmission2.databinding.ActivityMainBinding
import com.triwaluyo07.androidsubmission2.ui.detail.DetailActivity
import com.triwaluyo07.androidsubmission2.ui.favorite.FavoriteActivity
import com.triwaluyo07.androidsubmission2.ui.settings.SettingActivity

//by Tri Waluyo A0070678 ( a0070678@bangkit.academy )

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: UserViewModel
    private lateinit var adapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        adapter = UserAdapter()
        adapter.notifyDataSetChanged()
        adapter.setOnItemClickCallback(object : UserAdapter.OnItemClickCallback{
            override fun onItemClicked(data: UserData) {
                Intent(this@MainActivity,DetailActivity::class.java).also {
                    it.putExtra(DetailActivity.EXTRA_USERNAME, data.login)
                    it.putExtra(DetailActivity.EXTRA_ID,data.id)
                    it.putExtra(DetailActivity.EXTRA_AVATAR_URL,data.avatarUrl)
                    it.putExtra(DetailActivity.EXTRA_URL,data.url)
                    startActivity(it)
                }
            }

        })
        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(UserViewModel::class.java)

        binding.apply {
            rvMain.layoutManager = LinearLayoutManager(this@MainActivity)
            rvMain.setHasFixedSize(true)
            rvMain.adapter = adapter

            btnSearch.setOnClickListener {
                searchUser()
            }
            etSearch.setOnKeyListener { v, keyCode, event ->
                if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER)
                {
                    //coment
                    searchUser()
                    return@setOnKeyListener true
                }
                return@setOnKeyListener false
            }
        }

        viewModel.getSearchUsers().observe(this,
                {
                    if (it!=null)
                    {
                        adapter.setList(it)
                        showLoading(false)
                    }
                })
    }


    private fun showLoading(state: Boolean){
        if(state)
        {
            binding.mainProgress.visibility = View.VISIBLE
        }
        else
        {
            binding.mainProgress.visibility = View.GONE
        }
    }


    private fun searchUser(){
        binding.apply {
            val query = etSearch.text.toString()

            if (query.isNotEmpty())
            {
                showLoading(false)
                viewModel.setSearchUsers(query)
                showLoading(true)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.option_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {


        when(item.itemId)
        {
            R.id.changeLanguage -> {
                Intent(Settings.ACTION_LOCALE_SETTINGS).also {
                    startActivity(it)
                }
            }

            R.id.fav_menu -> {
                Intent(this, FavoriteActivity::class.java).also {
                    startActivity(it)
                }
            }

            R.id.setting_alarm -> {
                Intent(this,SettingActivity::class.java).also {
                    startActivity(it)
                }
            }

        }

        return super.onOptionsItemSelected(item)
    }
}