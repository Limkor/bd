package com.example.plugins


import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import java.sql.ResultSet
import java.sql.SQLException
import java.sql.Statement

object MySQLDatabaseExampleKotlin1 {
    private var dataSource: HikariDataSource? = null
    private val username = "root" // укажите имя пользователя
    private val password = "" // укажите соответствующий пароль

    init {
        configureHikariCP() // Настраиваем пул соединений при инициализации
    }

    // Настройка HikariCP
    private fun configureHikariCP() {
        val config = HikariConfig().apply {
            jdbcUrl = "jdbc:mysql://localhost:3306/clothes" // URL к базе данных
            username = this@MySQLDatabaseExampleKotlin1.username
            password = this@MySQLDatabaseExampleKotlin1.password
            driverClassName = "com.mysql.cj.jdbc.Driver"
            maximumPoolSize = 10 // Настраиваем максимальное количество соединений
            minimumIdle = 2 // Минимальное количество простаивающих соединений
            idleTimeout = 10000 // Таймаут простаивающего соединения
            connectionTimeout = 30000 // Таймаут подключения
            maxLifetime = 1800000 // Максимальное время жизни соединения
        }

        dataSource = HikariDataSource(config) // Создаем пул соединений
    }

    // Метод для выполнения запроса и вывода списка таблиц
    fun executeMySQLQuery() {
        var stmt: Statement? = null
        var resultset: ResultSet? = null
        try {
            dataSource?.connection?.use { conn ->
                stmt = conn.createStatement()
                println("lol:")
                resultset = stmt!!.executeQuery("SHOW TABLES") // Запрашиваем список таблиц
                while (resultset!!.next()) {
                    println(resultset!!.getString(1)) // Выводим имя каждой таблицы
                }
            }
        } catch (ex: SQLException) {
            ex.printStackTrace() // Обрабатываем ошибки
        } finally {
            resultset?.close()
            stmt?.close()
        }
    }

    // Метод для выборки данных из таблицы и возврата их в виде списка карт
    fun select(): List<Map<String, String>> {
        val results = mutableListOf<Map<String, String>>()
        var stmt: Statement? = null
        var resultset: ResultSet? = null
        try {
            dataSource?.connection?.use { conn ->
                val tableName = "client"
                stmt = conn.createStatement()
                resultset = stmt!!.executeQuery("SELECT * FROM $tableName")
                val metaData = resultset!!.metaData
                val columnCount = metaData.columnCount

                while (resultset!!.next()) {
                    val row = mutableMapOf<String, String>()
                    for (i in 1..columnCount) {
                        println(resultset!!.getString(i))
                        row[metaData.getColumnName(i)] = resultset!!.getString(i)
                    }
                    results.add(row)
                }
            }
        } catch (ex: SQLException) {
            ex.printStackTrace() // Обрабатываем ошибки
        } finally {
            resultset?.close()
            stmt?.close()
        }
        return results
    }

    // Не требуется отдельный метод getConnection, т.к. соединения теперь управляются пулом
}
