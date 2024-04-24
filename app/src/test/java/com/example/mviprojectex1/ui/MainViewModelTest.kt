package com.example.mviprojectex1.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import com.example.mviprojectex1.data.repo.QuoteRepoImpl
import com.example.mviprojectex1.data.source.remote.QuoteRemoteSourceImpl
import com.example.mviprojectex1.model.MainAppUIState
import com.example.mviprojectex1.model.Result
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class MainViewModelTest {

    @MockK(relaxed = true)
    private lateinit var quoteRepoImpl: QuoteRepoImpl

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val testDispatcher = StandardTestDispatcher()

    private lateinit var mainViewModel: MainViewModel

    private val mResult = Result(
        "id", "author",
        "authoerslug", "content", "dateAdded", "dateModified",
        2, listOf()
    )
    private val mList = listOf(mResult)
    private val mFLow: Flow<List<Result>> = flowOf(mList)


    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        MockKAnnotations.init(this)
        mainViewModel = MainViewModel(quoteRepoImpl)
    }

    @Test
    fun getQuoteListDataSuccess() {
        runTest {
            coEvery { quoteRepoImpl.getQuoteList("1") } returns mFLow
            testDispatcher.scheduler.advanceUntilIdle()
            mainViewModel.mutableStateFlow.test {
                mainViewModel.getQuoteListData()
                assertEquals(MainAppUIState.Loading, awaitItem())
                assertEquals(MainAppUIState.Success(mList), awaitItem())

            }
        }

    }

    @Test
    fun getQuoteListDataSuccessWithoutTurbine() {
        runTest {
            coEvery { quoteRepoImpl.getQuoteList("1") } returns mFLow
            mainViewModel.getQuoteListData()
            assertEquals(MainAppUIState.Loading, mainViewModel.mutableStateFlow.value)
            testDispatcher.scheduler.advanceUntilIdle()
            assertEquals(MainAppUIState.Success(mList), mainViewModel.mutableStateFlow.value)
        }

    }
    @Test
    fun getQuoteListDataFailure() {
        runTest {
            val error = RuntimeException("Failed to load items")
            coEvery { quoteRepoImpl.getQuoteList("1") } returns flow { throw error }
            mainViewModel.getQuoteListData()
            assertEquals(MainAppUIState.Loading, mainViewModel.mutableStateFlow.value)
            testDispatcher.scheduler.advanceUntilIdle()
            assertEquals(MainAppUIState.Error, mainViewModel.mutableStateFlow.value)
        }

    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}