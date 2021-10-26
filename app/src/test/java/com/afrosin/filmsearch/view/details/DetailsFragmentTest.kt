package com.afrosin.filmsearch.view.details

import android.os.Build
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario.launch
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.afrosin.filmsearch.model.Film
import junit.framework.Assert.assertNotNull
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

@RunWith(AndroidJUnit4::class)
@Config(sdk = [Build.VERSION_CODES.O_MR1])
class DetailsFragmentTest {

    lateinit var scenario: FragmentScenario<DetailsFragment>
    lateinit var film: Film

    @Before
    fun setup() {
        val film = Film(
            1,
            false,
            "overview",
            "posterPath",
            "title"
        )
        val scenario = launchFragmentInContainer<DetailsFragment>()

    }

    @Test
    fun fragment_AssertNotNull() {
        scenario.onFragment {
            assertNotNull(it)
        }
    }

//    @Test
//    fun activity_IsResumed() {
//        assertEquals(Lifecycle.State.RESUMED, scenario.state)
//    }

}