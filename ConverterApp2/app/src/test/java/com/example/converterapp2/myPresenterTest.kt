package com.example.converterapp2

import org.junit.Test

import org.junit.Assert.*
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class myPresenterTest {

    private var view : MyVPInterface = mock(MyVPInterface::class.java)
    private var presenter = myPresenter(view)
    @Test
    fun hitungResize() {
        var resize = presenter.resize(1920,1920,1166400)
        assertEquals(10,resize)
    }
}