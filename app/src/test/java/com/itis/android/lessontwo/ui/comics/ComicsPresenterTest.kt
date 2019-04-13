package com.itis.android.lessontwo.ui.comics

import com.itis.android.lessontwo.model.comics.Comics
import com.itis.android.lessontwo.repository.ComicsRepository
import io.mockk.*
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.reactivex.Single
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class ComicsPresenterTest {

    @MockK
    lateinit var mockComicsRepository: ComicsRepository
    @MockK
    lateinit var mockViewState: `ComicsView$$State`

    lateinit var presenter: ComicsPresenter

    @BeforeEach
    fun setUp() {
        presenter = spyk(ComicsPresenter(mockComicsRepository))
        presenter.setViewState(mockViewState)
    }

    @Test
    @DisplayName("OOO MY GOSH IS A DOUBLE RAINBOW!!! I CAN BELIEVE IT")
    fun onFirstViewAttach() {
        val mockView = mockk<ComicsView>()
        every { mockViewState.getComicsId() } just Runs
        every { mockViewState.attachView(mockView) } just Runs

        presenter.attachView(mockView)

        verify {
            mockViewState.getComicsId()
        }
    }

    @Test
    @DisplayName("Шо пацаны ониме?!")
    fun whenInitExpectedSuccess() {
        // Arrange
        val expectedId = 5L
        val mockComics = mockk<Comics>()
        every { mockComicsRepository.comics(expectedId) } returns Single.just(mockComics)
        every { mockViewState.showProgress() } just Runs
        every { mockViewState.hideProgress() } just Runs
        every { mockViewState.setImage(mockComics) } just Runs
        every { mockViewState.setDescription(mockComics) } just Runs
        every { mockViewState.setPageCount(mockComics) } just Runs
        every { mockViewState.setPrice(mockComics) } just Runs
        // Act
        presenter.init(expectedId)
        // Assert
        verifyOrder {
            mockViewState.showProgress()
            mockViewState.hideProgress()
        }
        verify {
            mockViewState.setImage(mockComics)
            mockViewState.setDescription(mockComics)
            mockViewState.setPageCount(mockComics)
            mockViewState.setPrice(mockComics)
        }
    }

    @Test
    @DisplayName("should DIO muda muda muda")
    fun whenInitExpectedError() {
        // Arrange
        val expectedId = 566L
        val expectedError = mockk<Throwable>()
        val mockComics = mockk<Comics>()
        every { mockComicsRepository.comics(expectedId) } returns Single.error(expectedError)
        every { mockViewState.handleError(expectedError) } just Runs
        every { mockViewState.showProgress() } just Runs
        every { mockViewState.hideProgress() } just Runs
        // Act
        presenter.init(expectedId)
        // Assert
        verifyOrder {
            mockViewState.showProgress()
            mockViewState.hideProgress()
        }
        verify { mockViewState.handleError(expectedError) }
        verify(inverse = true) {
            mockViewState.setPrice(mockComics)
            mockViewState.setDescription(mockComics)
            mockViewState.setPageCount(mockComics)
            mockViewState.setImage(mockComics)
        }
    }
}
