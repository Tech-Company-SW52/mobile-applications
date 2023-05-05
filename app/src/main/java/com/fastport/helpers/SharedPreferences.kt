package com.fastport.helpers

import android.content.Context

class SharedPreferences (val context: Context) {
    val PREFS_NAME = "sharedPreferences"
    val sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    fun save(KeyName: String, value: String){
        val  editor = sharedPreferences.edit()
        editor.putString(KeyName,value)
        editor.commit()
    }
    fun getValue(KeyName: String): String?{
        return sharedPreferences.getString(KeyName,null)
    }
    fun clear() {
        val editor = sharedPreferences.edit()
        editor.clear()
        editor.commit()
    }
    fun  removeValue (KeyName: String){
        val editor = sharedPreferences.edit()
        editor.remove(KeyName)
        editor.commit()
    }
}