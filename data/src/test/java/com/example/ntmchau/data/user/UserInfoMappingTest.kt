package com.example.ntmchau.data.user

import com.example.ntmchau.data.ApiCommons
import com.example.ntmchau.data.entity.ProvinceResponse
import com.example.ntmchau.data.entity.UserInfo
import com.example.ntmchau.data.entity.response.UserInfoResponse
import org.junit.Assert
import org.junit.Test

class UserInfoMappingTest {
    private val userExpected: UserInfo = UserInfo(1L, "Nguyễn Văn Tuấn","0987000001", "111111111", 3000001L, "An Giang")
    private val provincesExpected: List<String> = listOf(
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

    @Test
    fun `User info from province json file are properly mapped to domain object`() {
        val userResponse = parseUserInfoJsonFromFile("user_info.json")
        Assert.assertEquals(userExpected, userResponse.toUserInfo())
    }

    private fun parseUserInfoJsonFromFile(filename: String): UserInfoResponse {
        val bufferedReader = javaClass.classLoader.getResourceAsStream(filename).bufferedReader()
        val inputString = bufferedReader.use { it.readText() }
        return ApiCommons.gson().fromJson<UserInfoResponse>(inputString, UserInfoResponse::class.java)
    }

    @Test
    fun `List provinces from province json file are properly mapped to domain objects`() {
        val provinceResponse = parseProvincesJsonFromFile("province_list.json")
        val provinces = provinceResponse.provinces

        Assert.assertEquals(provincesExpected.size, provinces.size)
        provinces.forEachIndexed { index, province ->
            Assert.assertEquals(provincesExpected[index], province)
        }
    }

    private fun parseProvincesJsonFromFile(filename: String): ProvinceResponse {
        val bufferedReader = javaClass.classLoader.getResourceAsStream(filename).bufferedReader()
        val inputString = bufferedReader.use { it.readText() }
        return ApiCommons.gson()
            .fromJson<ProvinceResponse>(inputString, ProvinceResponse::class.java)
    }
}