package com.example.plugins.table.sql


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
    var resultset: ResultSet? = null

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
            var p: String
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
                /*
            val index = 0 // Укажите индекс строки, которую хотите вывести
            if (index in results.indices) {
                p = results[index].toString()
                val regex = """id_client=(\d+)""".toRegex()
                val m = regex.find(p)
                val idcl = m?.groupValues?.get(1).toString()
                println(idcl)
                println(results[index])
                println(p)
            } else {
                println("Индекс вне диапазона.")
            }

                 */
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
    fun select_w(tableName: String, limit: Int, offset: Int): ArrayList<Map<String, String>> {
        val results = ArrayList<Map<String, String>>()
        try {
            stmt = conn!!.createStatement()
            //println("pol:")
            resultset = stmt!!.executeQuery("SELECT * FROM $tableName LIMIT $limit OFFSET $offset;")
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



