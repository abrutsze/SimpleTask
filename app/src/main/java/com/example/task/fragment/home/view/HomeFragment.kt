package com.example.task.fragment.home.view


import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.task.MainActivity
import com.example.task.base.FragmentBaseMVVM
import com.example.task.base.utils.viewBinding
import com.example.task.databinding.FragmentHomeBinding
import com.example.task.fragment.home.DataInfoListAdapter
import com.example.task.fragment.home.viewmodel.HomeViewModel

class HomeFragment : FragmentBaseMVVM<HomeViewModel, FragmentHomeBinding>() {
    override val viewModel: HomeViewModel by viewModel()
    override val binding: FragmentHomeBinding by viewBinding()

    private var imagesAdapter = DataInfoListAdapter()
    override fun initView(savedInstanceState: Bundle?) {
        with(binding) {
            rvData.apply {
                context?.let {
                    layoutManager =
                        LinearLayoutManager(it)
                    setHasFixedSize(true)
                    adapter = imagesAdapter
                }
            }
        }
    }

    override fun observes() {
        observe(viewModel.getDataInfoList) {
            imagesAdapter.submitList(it)
        }
        observe(viewModel.errorNullData) {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        }
        observe(viewModel.loadingData) {
            if (it)
                binding.vLoadingData.visibility = View.VISIBLE
            else
                binding.vLoadingData.visibility = View.GONE
        }
    }

    override fun navigateBackStack() {
        (activity as? MainActivity)?.finish()
    }

}