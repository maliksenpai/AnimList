package com.e.pokemontraining.ui.nickname

import android.view.View
import android.widget.Toast
import com.e.pokemontraining.ui.base.BaseViewModel
import com.e.pokemontraining.utils.Nickname

class NicknameViewModel constructor() : BaseViewModel() {


    public var nickname: String = ""

    fun setnickname(view: View) {
        val context = view.context
        if (nickname.length < 6) {
            Toast.makeText(context, "Short nickname", Toast.LENGTH_SHORT).show()
        } else {
            Nickname().setnickname(nickname, context)
        }
    }
}