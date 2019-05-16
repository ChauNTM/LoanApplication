package com.example.ntmchau.loanapp.register

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.test.core.app.ApplicationProvider
import com.example.ntmchau.data.entity.UserInfo
import com.example.ntmchau.data.main.register.*
import com.example.ntmchau.loanapp.R
import com.example.ntmchau.loanapp.helpers.verifyViewStates
import com.example.ntmchau.loanapp.main.register.RegisterViewModel
import com.example.ntmchau.loanapp.utils.VerifyUtils
import com.nhaarman.mockito_kotlin.any
import io.reactivex.Single
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class RegisterViewModelTest  {

    @Rule
    @JvmField
    var instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var testObserver: Observer<RegisterViewState>

    @Mock
    private lateinit var getProvincesUseCase: GetProvincesUseCase

    @Mock
    private lateinit var sendRequestRegisterUseCaseUseCase: SendRequestRegisterUseCase

    private lateinit var viewModel: RegisterViewModel

    private lateinit var context: Context
    private lateinit var verifyUtils: VerifyUtils

    private val provinceList = mutableListOf(
        "An Giang",
        "Bắc Giang",
        "Bắc Kạn",
        "Bạc Liêu",
        "Bắc Ninh",
        "Bà Rịa - Vũng Tàu",
        "Bến Tre",
        "Bình Định",
        "Bình Dương",
        "Bình Phước"
    )

    private val userInfo: UserInfo = UserInfo(1L, "Nguyễn Văn Tuấn","0987000001", "111111111", 3000001L, "An Giang")

    private val monthlyIncomeList = mutableListOf("> 10000000", "> 3000000", "< 3000000")
    private val monthlyIncomeValueList = mutableListOf(3000000, 10000000)


    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        context = ApplicationProvider.getApplicationContext()
        verifyUtils = VerifyUtils(context)

        viewModel = RegisterViewModel(context, verifyUtils, getProvincesUseCase, sendRequestRegisterUseCaseUseCase)

        viewModel.viewState().observeForever(testObserver)

        Mockito.`when`(getProvincesUseCase.execute()).thenReturn(Single.just(provinceList))
        Mockito.`when`(sendRequestRegisterUseCaseUseCase.execute(any())).thenReturn(Single.just(userInfo))
    }

    @Test
    fun `When ViewModel is initialised offerInfo will be loaded`() {
        viewModel.init()

        testObserver.verifyViewStates(
            RegisterViewState(),
            RegisterViewState(provinces = provinceList)
        )
    }

    @Test
    fun `Proper view state is emitted when UpdateMonthlyIncomeList is dispatched`() {
        viewModel.dispatch(UpdateMonthlyIncomeList(monthlyIncomeValueList))

        val defaultViewState = RegisterViewState()

        testObserver.verifyViewStates(
            defaultViewState.copy(monthlyIncomeList = monthlyIncomeList)
        )
    }

    @Test
    fun `Proper view state is emitted when sendRequestRegister() is called`() {
        viewModel.dispatch(SendRequestRegister("", "", "", 0L, ""))

        val defaultViewState = RegisterViewState()

        testObserver.verifyViewStates(
            defaultViewState,
            defaultViewState.copy(userInfo = userInfo)
        )
    }

    @Test
    fun `NotifyInvalidUserInfo is emitted when user input phone number that are not enough characters with 3 prefix`() {
        viewModel.currentViewState().fullName = userInfo.fullName
        viewModel.currentViewState().nationalIdNumber = userInfo.nationalIdNumber
        viewModel.currentViewState().monthlyIncome = "> 3000000"
        viewModel.currentViewState().province = userInfo.province
        verifyUtils.valid3PrefixPhoneNumbers.forEach { prefix ->
            viewModel.currentViewState().phoneNumber = prefix + "123456"
            viewModel.verifyUserInfo()

            testObserver.verifyViewStates(
                viewModel.currentViewState().copy(errorMessage = context.getString(R.string.phone_number_not_enough_characters))
            )
        }
    }

    @Test
    fun `NotifyInvalidUserInfo is emitted when user input phone number that are not enough characters with 4 prefix`() {
        viewModel.currentViewState().fullName = userInfo.fullName
        viewModel.currentViewState().nationalIdNumber = userInfo.nationalIdNumber
        viewModel.currentViewState().monthlyIncome = "> 3000000"
        viewModel.currentViewState().province = userInfo.province
        verifyUtils.valid4PrefixPhoneNumbers.forEach { prefix ->
            viewModel.currentViewState().phoneNumber = prefix + "123456"
            viewModel.verifyUserInfo()

            testObserver.verifyViewStates(
                viewModel.currentViewState().copy(errorMessage = context.getString(R.string.phone_number_not_enough_characters))
            )
        }
    }

    @Test
    fun `NotifyInvalidUserInfo is emitted when user input phone number that has invalie prefix`() {
        viewModel.currentViewState().fullName = userInfo.fullName
        viewModel.currentViewState().nationalIdNumber = userInfo.nationalIdNumber
        viewModel.currentViewState().monthlyIncome = "> 3000000"
        viewModel.currentViewState().province = userInfo.province
        viewModel.currentViewState().phoneNumber = "0541234567"
        viewModel.verifyUserInfo()

        testObserver.verifyViewStates(
            viewModel.currentViewState().copy(errorMessage = context.getString(R.string.phone_number_invalid_prefix))
        )
    }
}