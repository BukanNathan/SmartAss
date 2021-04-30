package com.example.converterapp2

class myPresenter(setView : MyVPInterface) {
    private var view = setView
    private var myModel = MyModel()

    fun resize(panjang : Double=0.0, lebar : Double=0.0, titik : Int) = when(titik) {
        1166400 -> 10 + ((titik - (panjang * lebar)) / titik)
        else -> 0
    }

    fun hitungResize(panjang : Double=0.0, lebar : Double=0.0, titik : Int) {
        var resize = resize(panjang,lebar,titik)
        myModel.resize = resize as Double
        view.tampilkanSize(myModel)
    }
}