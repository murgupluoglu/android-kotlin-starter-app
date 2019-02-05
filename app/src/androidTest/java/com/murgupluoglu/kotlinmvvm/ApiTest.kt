package com.murgupluoglu.kotlinmvvm

import android.util.Log
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.platform.app.InstrumentationRegistry
import com.murgupluoglu.kotlinmvvm.di.koin.NetworkModule
import com.murgupluoglu.kotlinmvvm.di.koin.myModules
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.standalone.StandAloneContext
import org.koin.standalone.inject
import org.koin.test.KoinTest
import retrofit2.HttpException
import java.util.concurrent.CountDownLatch

/**
 * Created by Mustafa Urgupluoglu on 5.02.2019.
 */

@LargeTest
@RunWith(AndroidJUnit4::class)
class ApiTest : KoinTest {

    lateinit var mockServer: MockWebServer

    val networkModule : NetworkModule by inject()

    private val latch = CountDownLatch(1)

    @Before
    fun beforeTest(){
        mockServer = MockWebServer()
        val serverURL = mockServer.url("/").toString()

        NetworkModule.BASE_URL = serverURL
        StandAloneContext.startKoin(listOf(myModules))
    }

    @After
    fun afterTest(){
        mockServer.shutdown()
    }

    @Test
    fun peoplesTest() {

        val fileName = "peoples_response.json"
        mockServer.enqueue(
                MockResponse()
                        .setResponseCode(200)
                        .setBody(TestUtils.getStringFromFile(InstrumentationRegistry.getInstrumentation().context, fileName))
        )

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val result = networkModule.service().getPeoples(2).await()

                withContext(Dispatchers.Main) {
                    Assert.assertEquals(result.results[0].gender, "female")
                    Assert.assertEquals(result.results[0].name.first, "buse")
                    latch.countDown()
                }
            } catch (httpE: HttpException) {
                Log.e("HttpException", httpE.code().toString())
                Assert.fail()
            }
        }

        latch.await()

    }
}