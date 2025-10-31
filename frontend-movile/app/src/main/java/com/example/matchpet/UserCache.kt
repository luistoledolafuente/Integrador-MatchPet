package com.example.matchpet

import android.content.Context
import android.content.SharedPreferences

class UserCache(context: Context) {

    // Nombre del archivo de preferencias
    private val PREFS_NAME = "com.example.matchpet.user_prefs"

    // Claves para cada dato que vamos a guardar
    private val KEY_NOMBRE = "nombre"
    private val KEY_APELLIDO = "apellido"
    private val KEY_EMAIL = "email"

    // Instancia de SharedPreferences
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    /**
     * Guarda los datos del usuario en SharedPreferences.
     */
    fun saveUser(nombre: String, apellido: String, email: String) {
        val editor = sharedPreferences.edit()
        editor.putString(KEY_NOMBRE, nombre)
        editor.putString(KEY_APELLIDO, apellido)
        editor.putString(KEY_EMAIL, email)
        // .apply() guarda los datos de forma asíncrona (recomendado)
        editor.apply()
    }

    /**
     * Obtiene el nombre del usuario guardado.
     * Devuelve null si no se encuentra.
     */
    fun getNombre(): String? {
        return sharedPreferences.getString(KEY_NOMBRE, null)
    }

    /**
     * Obtiene el apellido del usuario guardado.
     */
    fun getApellido(): String? {
        return sharedPreferences.getString(KEY_APELLIDO, null)
    }

    /**
     * Obtiene el email del usuario guardado.
     */
    fun getEmail(): String? {
        return sharedPreferences.getString(KEY_EMAIL, null)
    }

    /**
     * Borra todos los datos del usuario.
     * Útil para una función de "Cerrar Sesión".
     */
    fun clearUser() {
        val editor = sharedPreferences.edit()
        editor.clear()
        editor.apply()
    }
}