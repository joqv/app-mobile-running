package com.cibertec.apprunningmobile.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.cibertec.apprunningmobile.models.Resultado

class ResultadoDBHelper(context: Context) :
    SQLiteOpenHelper(context, "resultados.db", null, 1) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(
            "CREATE TABLE resultados (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "nombre TEXT, " +
                    "distancia REAL, " +
                    "tiempo REAL, " +
                    "velocidad REAL)"
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS resultados")
        onCreate(db)
    }

    fun insertarResultado(resultado: Resultado) {
        val db = writableDatabase
        val values = ContentValues().apply {
            put("nombre", resultado.nombre)
            put("distancia", resultado.distancia)
            put("tiempo", resultado.tiempo)
            put("velocidad", resultado.velocidad)
        }
        db.insert("resultados", null, values)
        db.close()
    }

    fun obtenerTodosLosResultados(): List<Resultado> {
        val resultados = mutableListOf<Resultado>()
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT nombre, distancia, tiempo, velocidad FROM resultados", null)

        if (cursor.moveToFirst()) {
            do {
                val nombre = cursor.getString(0)
                val distancia = cursor.getDouble(1)
                val tiempo = cursor.getDouble(2)
                val velocidad = cursor.getDouble(3)
                resultados.add(Resultado(nombre, distancia, tiempo, velocidad))
            } while (cursor.moveToNext())
        }

        cursor.close()
        db.close()
        return resultados
    }


}