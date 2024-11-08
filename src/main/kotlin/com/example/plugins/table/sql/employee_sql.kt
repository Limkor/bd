package com.example.plugins.table.sql

import com.example.model.bd_id
import com.example.model.employee
import com.example.plugins.table.sql.MySQLDatabaseExampleKotlin.conn
import com.example.plugins.table.sql.MySQLDatabaseExampleKotlin.stmt
import java.sql.SQLException

fun into_employee(empl: employee, tableName: String) {
    try{
        stmt = conn!!.createStatement()
        val sql = """
            INSERT INTO ${tableName} (name, surname, patronymic, phone_number) VALUES ('${empl.name}', '${empl.surname}', '${empl.patronymic}', '${empl.phone}');
        """.trimIndent()
        stmt!!.execute(sql)
    } catch (e: SQLException) {
        e.printStackTrace()
    }
}
fun update_employee(empl_id: bd_id, empl: employee, tableName: String) {
    try {
        val num: Int = empl_id.bd_id.toInt()
        val updateFields = mutableListOf<String>()
        stmt = conn!!.createStatement()
        if (empl.name.isNotEmpty()) {
            updateFields.add("name = '${empl.name}'")
        }
        if (empl.surname.isNotEmpty()) {
            updateFields.add("surname = '${empl.surname}'")
        }
        if (empl.phone.isNotEmpty()) {
            updateFields.add("phone_number = '${empl.phone}'")
        }
        if (empl.patronymic != "Если хотите поменяить отчество удалите"){
            updateFields.add("patronymic = '${empl.patronymic}'")
        }
        val sql = """UPDATE ${tableName} SET ${updateFields.joinToString(", ")} WHERE id_${tableName} = '${num}';
        """.trimIndent()
        stmt!!.execute(sql)
    } catch (e: SQLException) {
        e.printStackTrace()
    }
}