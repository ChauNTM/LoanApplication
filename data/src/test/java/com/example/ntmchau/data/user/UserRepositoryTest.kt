package com.example.ntmchau.data.user

import com.example.ntmchau.data.ApiCommons.Companion.api
import com.example.ntmchau.data.entity.UserInfo
import com.example.ntmchau.data.main.register.UserDataSource
import com.example.ntmchau.data.main.register.UserRepository
import io.reactivex.observers.TestObserver
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.MockitoAnnotations

class UserRepositoryTest {

    private lateinit var userRepository: UserDataSource

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        userRepository = UserRepository(api())
    }

    @Test
    fun `Province list is properly retrieved from the API`() {
        val testObserver = TestObserver<MutableList<String>>()
        val result = userRepository.getProvinces().subscribe(testObserver)
        Assert.assertNotNull(result)
        testObserver.awaitTerminalEvent()
        testObserver.assertNoErrors()

        testObserver.assertValue { it.isNotEmpty() }
    }

    @Test
    fun `Register request is properly sent to the API`() {
        val fullName = "mockFullName"
        val phoneNumber = "mockPhoneNumber"
        val nationalIdNumber = "mockNationalIdNumber"
        val province = "mockProvince"
        val monthlyIncome = 3000001L
        val testObserver = TestObserver<UserInfo>()
        val result = userRepository.sendRegisterLoanRequest(fullName, phoneNumber, nationalIdNumber, monthlyIncome, province).subscribe(testObserver)
        Assert.assertNotNull(result)
        testObserver.awaitTerminalEvent()
        testObserver.assertNoErrors()

        testObserver.assertValueCount(1)
    }
}