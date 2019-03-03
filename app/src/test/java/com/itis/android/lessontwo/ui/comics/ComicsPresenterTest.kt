package com.itis.android.lessontwo.ui.comics

import com.itis.android.lessontwo.model.comics.Comics
import com.itis.android.lessontwo.repository.ComicsRepository
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.Spy
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ComicsPresenterTest {

    @Mock
    lateinit var mockComicsRepository: ComicsRepository

    @Mock
    lateinit var mockViewState: `ComicsView$$State`

    @InjectMocks
    @Spy
    lateinit var presenter: ComicsPresenter

    @Before
    fun setUp() = presenter.setViewState(mockViewState)

    @Test
    fun onFirstViewAttach() {
        val mockView = mock(ComicsView::class.java)

        presenter.attachView(mockView)

        verify(mockViewState).getComicsId()
    }

    @Test
    fun init() {
        // Arrange
        val expectedId = 5L
        val mockComics = mock(Comics::class.java)
        doReturn(Single.just(mockComics)).`when`(mockComicsRepository).comics(expectedId)
        // Act
        presenter.init(expectedId)
        // Assert
        verify(mockViewState).showProgress()
        verify(mockViewState).hideProgress()
        verify(mockViewState).setImage(mockComics)
        verify(mockViewState).setDescription(mockComics)
        verify(mockViewState).setPageCount(mockComics)
        verify(mockViewState).setPrice(mockComics)
    }
}
