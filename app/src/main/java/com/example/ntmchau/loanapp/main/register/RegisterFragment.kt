package com.example.ntmchau.loanapp.main.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.ntmchau.data.main.register.RegisterViewState
import com.example.ntmchau.data.main.register.UpdateMonthlyIncomeList
import com.example.ntmchau.loanapp.R
import com.example.ntmchau.loanapp.base.ViewModelFragment
import com.example.ntmchau.loanapp.base.autoCleared
import com.example.ntmchau.loanapp.databinding.FragmentRegisterBinding
import javax.inject.Inject

class RegisterFragment : ViewModelFragment() {

    companion object {
        private val TAG = RegisterFragment::class.java.simpleName
    }

    @Inject
    override lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: RegisterViewModel

    private var binding by autoCleared<FragmentRegisterBinding>()

    @Inject
    lateinit var monthlyIncomeList: MutableList<Int>


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initViewModels()
        binding.registerViewState = viewModel.viewState()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val dataBinding =
            DataBindingUtil.inflate<FragmentRegisterBinding>(inflater, R.layout.fragment_register, container, false)
        binding = dataBinding
        binding.lifecycleOwner = this
        binding.send.setOnClickListener { viewModel.verifyUserInfo() }
        return dataBinding.root
    }

    override fun onResume() {
        super.onResume()
        viewModel.init()
        viewModel.dispatch(UpdateMonthlyIncomeList(monthlyIncomeList))

    }

    override fun initViewModels() {
        viewModel = provideViewModel(RegisterViewModel::class.java)
        viewModel.viewState().observe(this, Observer { viewState -> processViewState(viewState) })
    }

    private fun processViewState(viewState: RegisterViewState?) {
        viewState?.let { state ->
            state.errorMessage?.let {
                Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
                return
            }

            if (state.userInfo.id != 0L) {
                Toast.makeText(context, R.string.success, Toast.LENGTH_SHORT).show()

            }
        }
    }

}