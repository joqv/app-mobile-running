package com.cibertec.apprunningmobile.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.cibertec.apprunningmobile.models.Usuario


class UsuarioDBHelper(context: Context) : SQLiteOpenHelper(context, "usuarios.db", null, 5) {

    private val TABLE_NAME = "usuario"
    private val COL_ID = "id"
    private val COL_NOMBRE = "nombre"
    private val COL_APELLIDOS = "apellidos"
    private val COL_SEXO = "sexo"
    private val COL_EMAIL = "email"
    private val COL_PASSWORD = "password"
    private val COL_FECHA_NACIMIENTO = "fechaNacimiento"


    override fun onCreate(db: SQLiteDatabase) {
        val createTableSQL = """
            CREATE TABLE usuario (          
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                nombre TEXT NOT NULL,
                apellidos TEXT NOT NULL,
                sexo TEXT NOT NULL,
                email TEXT UNIQUE NOT NULL,
                password TEXT NOT NULL,       
                fechaNacimiento TEXT NOT NULL
            )
        """.trimIndent()
        db.execSQL(createTableSQL)
    }


    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS usuario")
        onCreate(db)
    }


    fun insertarUsuario(usuario: Usuario): Long {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COL_NOMBRE, usuario.nombre)
            put(COL_APELLIDOS, usuario.apellidos)
            put(COL_SEXO, usuario.sexo)
            put(COL_EMAIL, usuario.email)
            put(COL_PASSWORD, usuario.password)
            put(COL_FECHA_NACIMIENTO, usuario.fechaNacimiento)
        }
        val id = db.insert("usuario", null, values)
        db.close()
        return id
    }

    /**
     * READ (para Login): Valida las credenciales de un usuario.
     * @param email El email ingresado por el usuario.
     * @param password La contraseña ingresada por el usuario (en texto plano).
     * @return El objeto Usuario completo si las credenciales son correctas, o null si no lo son.
     */
    fun validarCredenciales(email: String, password: String): Usuario? {
        val db = readableDatabase // Obtiene una instancia para leer de la DB
        var usuario: Usuario? = null
        val cursor = db.query(
            TABLE_NAME, // Nombre de la tabla
            arrayOf(COL_ID, COL_NOMBRE, COL_APELLIDOS, COL_SEXO, COL_EMAIL, COL_PASSWORD, COL_FECHA_NACIMIENTO),
            "$COL_EMAIL=? AND $COL_PASSWORD=?", // Cláusula WHERE: buscar por email Y contraseña
            arrayOf(email, password), // Argumentos para la cláusula WHERE (en el mismo orden)
            null, null, null // groupBy, having, orderBy (no usados aquí)
        )

        if (cursor.moveToFirst()) {
            usuario = Usuario(
                id = cursor.getInt(cursor.getColumnIndexOrThrow(COL_ID)),
                nombre = cursor.getString(cursor.getColumnIndexOrThrow(COL_NOMBRE)),
                apellidos = cursor.getString(cursor.getColumnIndexOrThrow(COL_APELLIDOS)),
                sexo = cursor.getString(cursor.getColumnIndexOrThrow(COL_SEXO)),
                email = cursor.getString(cursor.getColumnIndexOrThrow(COL_EMAIL)),
                password = cursor.getString(cursor.getColumnIndexOrThrow(COL_PASSWORD)),
                fechaNacimiento = cursor.getString(cursor.getColumnIndexOrThrow(COL_FECHA_NACIMIENTO))
            )
        }
        cursor.close()
        db.close()
        return usuario
    }

    /**
     * READ (por email): Obtiene un usuario de la base de datos usando su email.
     * Útil para verificar si un email ya está registrado o para cargar el perfil de un usuario.
     * @param email El email del usuario a buscar.
     * @return El objeto Usuario si se encuentra, o null.
     */
    fun obtenerUsuarioPorEmail(email: String): Usuario? {
        val db = readableDatabase
        var usuario: Usuario? = null
        val cursor = db.query(
            TABLE_NAME,
            arrayOf(COL_ID, COL_NOMBRE, COL_APELLIDOS, COL_SEXO, COL_EMAIL, COL_PASSWORD, COL_FECHA_NACIMIENTO),
            "$COL_EMAIL=?",
            arrayOf(email),
            null, null, null
        )

        if (cursor.moveToFirst()) {
            usuario = Usuario(
                id = cursor.getInt(cursor.getColumnIndexOrThrow(COL_ID)),
                nombre = cursor.getString(cursor.getColumnIndexOrThrow(COL_NOMBRE)),
                apellidos = cursor.getString(cursor.getColumnIndexOrThrow(COL_APELLIDOS)),
                sexo = cursor.getString(cursor.getColumnIndexOrThrow(COL_SEXO)),
                email = cursor.getString(cursor.getColumnIndexOrThrow(COL_EMAIL)),
                password = cursor.getString(cursor.getColumnIndexOrThrow(COL_PASSWORD)),
                fechaNacimiento = cursor.getString(cursor.getColumnIndexOrThrow(COL_FECHA_NACIMIENTO))
            )
        }
        cursor.close()
        db.close()
        return usuario
    }
    fun obtenerUsuarioPorId(id: Int): Usuario? {
        val db = readableDatabase
        var usuario: Usuario? = null
        val cursor = db.query(
            TABLE_NAME,
            arrayOf(COL_ID, COL_NOMBRE, COL_APELLIDOS, COL_SEXO, COL_EMAIL, COL_PASSWORD, COL_FECHA_NACIMIENTO),
            "$COL_ID=?",
            arrayOf(id.toString()), // Los IDs en las consultas WHERE siempre deben ser Strings
            null, null, null
        )

        if (cursor.moveToFirst()) {
            usuario = Usuario(
                id = cursor.getInt(cursor.getColumnIndexOrThrow(COL_ID)),
                nombre = cursor.getString(cursor.getColumnIndexOrThrow(COL_NOMBRE)),
                apellidos = cursor.getString(cursor.getColumnIndexOrThrow(COL_APELLIDOS)),
                sexo = cursor.getString(cursor.getColumnIndexOrThrow(COL_SEXO)),
                email = cursor.getString(cursor.getColumnIndexOrThrow(COL_EMAIL)),
                password = cursor.getString(cursor.getColumnIndexOrThrow(COL_PASSWORD)),
                fechaNacimiento = cursor.getString(cursor.getColumnIndexOrThrow(COL_FECHA_NACIMIENTO))
            )
        }
        cursor.close()
        db.close()
        return usuario
    }

    fun obtenerTodosLosUsuarios(): List<Usuario> {
        val listaUsuarios = mutableListOf<Usuario>()
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT * FROM usuario", null)

        while (cursor.moveToNext()) {
            val usuario = Usuario(
                id = cursor.getInt(cursor.getColumnIndexOrThrow(COL_ID)),
                nombre = cursor.getString(cursor.getColumnIndexOrThrow(COL_NOMBRE)),
                apellidos = cursor.getString(cursor.getColumnIndexOrThrow(COL_APELLIDOS)),
                sexo = cursor.getString(cursor.getColumnIndexOrThrow(COL_SEXO)),
                email = cursor.getString(cursor.getColumnIndexOrThrow(COL_EMAIL)),
                password = cursor.getString(cursor.getColumnIndexOrThrow(COL_PASSWORD)),
                fechaNacimiento = cursor.getString(cursor.getColumnIndexOrThrow(COL_FECHA_NACIMIENTO))
            )
            listaUsuarios.add(usuario)
        }
        cursor.close()
        db.close()
        return listaUsuarios
    }


    fun actualizarUsuario(usuario: Usuario): Int {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COL_NOMBRE, usuario.nombre)
            put(COL_APELLIDOS, usuario.apellidos)
            put(COL_SEXO, usuario.sexo)
            put(COL_EMAIL, usuario.email)
            put(COL_PASSWORD, usuario.password)
            put(COL_FECHA_NACIMIENTO, usuario.fechaNacimiento)
        }
        val filasAfectadas = db.update("usuario", values, "$COL_ID=?", arrayOf(usuario.id.toString()))
        db.close()
        return filasAfectadas
    }


    fun eliminarUsuario(id: Int): Int {
        val db = writableDatabase
        val filasEliminadas = db.delete("usuario", "$COL_ID=?", arrayOf(id.toString()))
        db.close()
        return filasEliminadas
    }
}

