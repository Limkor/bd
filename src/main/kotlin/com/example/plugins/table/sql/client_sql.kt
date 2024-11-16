package com.example.plugins.table.sql

import com.example.model.bd_id
import com.example.model.client
import com.example.plugins.table.sql.MySQLDatabaseExampleKotlin.conn
import com.example.plugins.table.sql.MySQLDatabaseExampleKotlin.stmt
import java.sql.SQLException

fun into_client(client: client, tableName: String) {
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
fun update_client(clientId: bd_id, client: client, tableName: String) {
    try {
        val num: Int = clientId.bd_id.toInt()
        stmt = conn!!.createStatement()
        val updateFields = mutableListOf<String>()
        if (client.name.isNotEmpty()) {
            updateFields.add("name = '${client.name}'")
        }
        if (client.surname.isNotEmpty()) {
            updateFields.add("surname = '${client.surname}'")
        }
        if (client.orde != "Не выбрано") {
            updateFields.add("orde = '${client.orde}'")
        }
        if (client.result != "Не выбрано") {
            updateFields.add("result = '${client.result}'")
        }
        if (client.patronymic != "Если хотите поменяить отчество удалите") {
            updateFields.add("patronymic = '${client.patronymic}'")
        }
        val sql = """
            UPDATE $tableName SET ${updateFields.joinToString(", ")} WHERE id_$tableName = '${num}';
        """.trimIndent()
        stmt!!.executeUpdate(sql)
    } catch (e: SQLException) {
        e.printStackTrace()
    }
}