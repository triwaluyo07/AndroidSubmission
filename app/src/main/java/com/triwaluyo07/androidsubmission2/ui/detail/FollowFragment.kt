package com.triwaluyo07.androidsubmission2.ui.detail

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.triwaluyo07.androidsubmission2.R
import com.triwaluyo07.androidsubmission2.data.model.UserData
import com.triwaluyo07.androidsubmission2.databinding.FragmentFollowBinding
import com.triwaluyo07.androidsubmission2.ui.main.UserAdapter


class FollowFragment : Fragment(R.layout.fragment_follow) {

    private var _binding: FragmentFollowBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: FollowersViewModel
    private lateinit var adapter: UserAdapter
    private lateinit var username: String


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //mengambil username
        val args = arguments
        username = args?.getString(DetailActivity.EXTRA_USERNAME).toString()

        //binding
        _binding = FragmentFollowBinding.bind(view)


        //adapter Recyler View Main
        adapter = UserAdapter()
        adapter.notifyDataSetChanged()

        //pindah dari Follow fragment ke activity
        adapter.setOnItemClickCallback(object : UserAdapter.OnItemClickCallback{
            override fun onItemClicked(data: UserData) {
                Intent(activity, DetailActivity::class.java).also {

                    it.putExtra(DetailActivity.EXTRA_USERNAME, data.login)
                    it.putExtra(DetailActivity.EXTRA_ID,data.id)
                    it.putExtra(DetailActivity.EXTRA_AVATAR_URL,data.avatarUrl)
                    it.putExtra(DetailActivity.EXTRA_URL,data.url)
                    startActivity(it)
                }
            }

        })
        binding.apply {
            rvMain.setHasFixedSize(true)
            rvMain.layoutManager = LinearLayoutManager(activity)
            rvMain.adapter = adapter
        }

        showLoading(true)
        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(FollowersViewModel::class.java)
        viewModel.setListFollowers(username)
        viewModel.getListFollowers().observe(viewLifecycleOwner,{
            if(it != null)
            {
                showLoading(false)
                adapter.setList(it)

            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            binding.followProgress.visibility = View.VISIBLE
        } else {
            binding.followProgress.visibility = View.GONE
        }

    }
}