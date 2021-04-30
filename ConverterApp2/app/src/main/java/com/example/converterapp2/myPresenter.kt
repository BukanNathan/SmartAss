package com.example.converterapp2

class myPresenter(setView : MyVPInterface) {
    private var view = setView
    private var myModel = MyModel()

    fun resize(panjang : Int=0, lebar : Int=0, titik : Int) = when(titik) {
        1166400 -> 10 + ((1166400 - panjang * lebar) / 1166400) * -1
        else -> panjang*lebar

    }

    fun hitungResize(panjang : Int=0, lebar : Int=0, titik : Int) {
        var resize = resize(panjang,lebar,titik)
        myModel.resize = resize
        view.tampilkanSize(myModel)
    }
}