package com.example.plugins.table.sql

import com.example.model.avtorizet
import com.example.model.regestrachia
import com.example.plugins.table.sql.MySQLDatabaseExampleKotlin.conn
import com.example.plugins.table.sql.MySQLDatabaseExampleKotlin.stmt

fun input_avtorizet(avt: avtorizet, tableName: String): Pair<Boolean, String?> {
    var exists = false
    var p = ""
    var idcl: String? = null
    val results = ArrayList<Map<String, String>>()
    stmt = conn!!.createStatement()
    val sql = """
        SELECT EXISTS(SELECT 1 FROM ${tableName} WHERE name='${avt.username}' AND password='${avt.password}') ;
    """.trimIndent()
    val resultset = stmt!!.executeQuery(sql)
    if (resultset.next()) {
        exists = resultset.getBoolean(1)
        val sql1 = """
        SELECT * FROM ${tableName} WHERE name='${avt.username}' AND password='${avt.password}';
    """.trimIndent()
        val res = stmt!!.executeQuery(sql1)
        val metaData = res!!.metaData
        val columnCount = metaData.columnCount
        while (res!!.next()) {
            val row = mutableMapOf<String, String>()
            for (i in 1..columnCount) {
                row[metaData.getColumnName(i)] = res!!.getString(i)
            }
            results.add(row)
        }
        println(results.toString())
        val index = 0 // Укажите индекс строки, которую хотите вывести
        if (index in results.indices) {
            p = results[index].toString()
            val regex = """statys=([A-Za-z]+)""".toRegex()
            val m = regex.find(p)
            idcl = m?.groupValues?.get(1)
            println(idcl)
            println(results[index])
            println(p)
        } else {
            println("Индекс вне диапазона.")
        }
        //println(results.toString())
    }
    return Pair(exists, idcl)
}

fun reg_avtorizet(reg: regestrachia, tableName: String): Boolean {
    var exists = false
    var status = false
    stmt = conn!!.createStatement()
    val sql = """
        SELECT EXISTS(SELECT 1 FROM ${tableName} WHERE name='${reg.username}' AND password='${reg.password}') ;
    """.trimIndent()
    val resultSet = stmt!!.executeQuery(sql)
    if (resultSet.next()) {
        exists = resultSet.getBoolean(1)
    }
    if (exists) {
        status = false
    } else {
        val sql1 = """
            INSERT INTO ${tableName} (name, password, statys) VALUES ('${reg.username}', '${reg.password}', 'client')
        """.trimIndent()
        stmt!!.executeUpdate(sql1)
        status = true

    }
    return status
}
