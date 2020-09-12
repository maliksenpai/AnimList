package com.e.pokemontraining.utils

import android.content.Context
import android.content.Intent
import com.e.pokemontraining.ui.main.MainActivity

class Nickname {

    fun getnickname(context: Context): String? {
        val sharedPreferences = context.getSharedPreferences("nickname", Context.MODE_PRIVATE)
        return sharedPreferences.getString("nickname", "")
    }

    fun setnickname(nickname: String, context: Context) {
        val sharedPreferences = context.getSharedPreferences("nickname", Context.MODE_PRIVATE)
        sharedPreferences.edit().putString("nickname", nickname).apply()
        context.startActivity(Intent(context, MainActivity::class.java))
    }
}