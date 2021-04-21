package com.triwaluyo07.androidsubmission2.ui.favorite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.triwaluyo07.androidsubmission2.data.local.FavoriteUser
import com.triwaluyo07.androidsubmission2.data.model.UserData
import com.triwaluyo07.androidsubmission2.databinding.ActivityFavoriteBinding
import com.triwaluyo07.androidsubmission2.ui.detail.DetailActivity
import com.triwaluyo07.androidsubmission2.ui.main.UserAdapter

class FavoriteActivity : AppCompatActivity() {

    private lateinit var binding : ActivityFavoriteBinding
    private lateinit var adapter: UserAdapter
    private lateinit var viewModel: FavoriteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = UserAdapter()
        adapter.notifyDataSetChanged()

        viewModel = ViewModelProvider(this).get(FavoriteViewModel::class.java)

        adapter.setOnItemClickCallback(object : UserAdapter.OnItemClickCallback{
            override fun onItemClicked(data: UserData) {
                Intent(this@FavoriteActivity, DetailActivity::class.java).also {

                    it.putExtra(DetailActivity.EXTRA_USERNAME, data.login)
                    it.putExtra(DetailActivity.EXTRA_ID,data.id)
                    it.putExtra(DetailActivity.EXTRA_AVATAR_URL,data.avatarUrl)
                    it.putExtra(DetailActivity.EXTRA_URL,data.url)
                    startActivity(it)
                }
            }

        })

        binding.apply {
            rvFavorite.setHasFixedSize(true)
            rvFavorite.layoutManager = LinearLayoutManager(this@FavoriteActivity)
            rvFavorite.adapter = adapter
        }

        viewModel.getFavoriteUser()?.observe(this,{
            if(it != null)
            {
                val list = mapList(it)
                adapter.setList(list)
            }
        })
    }

    private fun mapList(users: List<FavoriteUser>): ArrayList<UserData> {
        val listUsers = ArrayList<UserData>()
        for (user in users)
        {
            val userMapped = UserData(
                user.login,
                user.id,
                user.avatar_url,
                user.url
            )
            listUsers.add(userMapped)
        }
        return listUsers
    }
}