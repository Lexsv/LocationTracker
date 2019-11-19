package ua.com.location

import android.content.Context
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Before
import ua.com.location.util.getConnectivityNet

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    lateinit var mContext: Context

    @Before
    fun setup() {
        mContext = InstrumentationRegistry.getInstrumentation().context
    }

    @Test
    fun useAppContext() {
        Assert.assertEquals(true,getConnectivityNet(mContext))
    }
}
