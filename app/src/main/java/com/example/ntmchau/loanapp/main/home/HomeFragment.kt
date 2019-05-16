package com.example.ntmchau.loanapp.main.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.databinding.DataBindingComponent
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.ntmchau.loanapp.R
import com.example.ntmchau.loanapp.base.ViewModelFragment
import com.example.ntmchau.loanapp.base.autoCleared
import com.example.ntmchau.loanapp.binding.FragmentDataBindingComponent
import com.example.ntmchau.loanapp.databinding.FragmentHomeBinding
import javax.inject.Inject

class HomeFragment: ViewModelFragment() {
    companion object {
        private val TAG = HomeFragment::class.java.simpleName
    }

    @Inject
    override lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: HomeViewModel

    private var dataBindingComponent: DataBindingComponent = FragmentDataBindingComponent(this)
    private var binding by autoCleared<FragmentHomeBinding>()


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initViewModels()
        binding.homeViewState = viewModel.viewState()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val dataBinding = DataBindingUtil.inflate<FragmentHomeBinding>(inflater, R.layout.fragment_home, container, false, dataBindingComponent)
        binding = dataBinding
        binding.lifecycleOwner = this
        binding.register.setOnClickListener { navController().navigate(R.id.showRegister) }
        return dataBinding.root
    }

    override fun onResume() {
        super.onResume()
        viewModel.init()
    }

    override fun initViewModels() {
        viewModel = provideViewModel(HomeViewModel::class.java)
    }

    private fun navController() = findNavController()

}