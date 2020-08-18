package com.e.pokemontraining.ui.main

import android.content.Context
import android.view.View
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.e.pokemontraining.ui.base.BaseViewModel

class MainViewModel : BaseViewModel() {

    var radiobutton = MutableLiveData<String>()

    fun winterradio(){
        changeradio(1)
    }

    fun springradio(){
        changeradio(2)
    }

    fun summerradio(){
        changeradio(3)
    }

    fun fallradio(){
        changeradio(4)
    }

    fun changeradio(season:Int):MutableLiveData<String>{
        when(season){
            1-> radiobutton.value="winter"
            2-> radiobutton.value="spring"
            3-> radiobutton.value="summer"
            4-> radiobutton.value="fall"
        }
        return radiobutton
    }
    

}