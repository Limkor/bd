package com.example.plugins

import com.example.model.Client
import com.example.model.bd_id
import java.sql.DriverManager
import java.sql.ResultSet
import java.sql.SQLException
import java.sql.Statement
import java.util.*
import kotlin.collections.ArrayList

object MySQLDatabaseExampleKotlin {
    var conn: java.sql.Connection? = null // исправленный тип
    private var username = "root" // укажите имя пользователя
    private var password = "" // укажите соответствующий пароль
    var stmt: Statement? = null
    private var resultset: ResultSet? = null

    fun executeMySQLQuery1() {
        try {
            stmt = conn!!.createStatement() // Создаем SQL-запрос
            println("lol:")
            resultset = stmt!!.executeQuery("SHOW TABLES") // Запрашиваем список таблиц
            while (resultset!!.next()) {
                println(resultset!!.getString(1)) // Выводим имя каждой таблицы
            }
        } catch (ex: SQLException) {
            ex.printStackTrace() // Обрабатываем ошибки
        }
    }
    fun into(client: Client, tableName: String) {
            try{
                stmt = conn!!.createStatement()
                val sql = """
                    INSERT INTO $tableName (name, surname, patronymic, orde, result)
                    VALUES ('${client.name}', '${client.surname}', '${client.patronymic}', '${client.orde}', '${client.result}')
                """.trimIndent()
                stmt!!.executeUpdate(sql)
            } catch (ex: SQLException){
                ex.printStackTrace()
            }
    }
    fun dele(clientId: bd_id, tableName: String) {
        try{
            val num : Int = clientId.bd_id.toInt()
            stmt = conn!!.createStatement()
            val sql="""DELETE FROM $tableName WHERE id_$tableName = '${num}';""".trimIndent()
            stmt!!.executeUpdate(sql)
        }catch (ex: SQLException){
            ex.printStackTrace()
        }
    }
    fun select(tableName: String): ArrayList<Map<String, String>> {
        val results = ArrayList<Map<String, String>>()
        try {
            stmt = conn!!.createStatement()
            //println("pol:")
            resultset = stmt!!.executeQuery("SELECT * FROM $tableName")
            val metaData = resultset!!.metaData
            val columnCount = metaData.columnCount

            //println()
            while (resultset!!.next()) {
                val row = mutableMapOf<String, String>()
                for (i in 1..columnCount) {
                    row[metaData.getColumnName(i)] = resultset!!.getString(i)
                }
                    //println(row.toString())
                results.add(row)
            }
            println(results.toString())
        } catch (ex: SQLException) {
            ex.printStackTrace() // Обрабатываем ошибки
        } finally {
            if (resultset != null) {
                try {
                    resultset!!.close()
                } catch (_: SQLException) {
                }
            }
            if (stmt != null) {
                try {
                    stmt!!.close()
                } catch (_: SQLException) {
                }
            }
            if (conn != null) {
                try {
                    conn!!.close()
                } catch (_: SQLException) {
                }
            }
        }
        return results
    }

    fun getConnection() {
        val connectionProps = Properties()
        connectionProps["user"] = username
        connectionProps["password"] = password
        try {
            // Подключаемся к базе данных "clothes"
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/clothes", connectionProps)
        } catch (ex: SQLException) {
            ex.printStackTrace()
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }
}
//добавить регистрацию кнопки удолить создать информация поиск



