package com.example.plugins.table.sql

import com.example.model.bd_id
import com.example.model.equipment
import com.example.plugins.table.sql.MySQLDatabaseExampleKotlin.conn
import com.example.plugins.table.sql.MySQLDatabaseExampleKotlin.stmt
import java.sql.SQLException

fun into_equipment(equi: equipment, tableName: String) {
    try {
        stmt = conn!!.createStatement()
        val sql = """
            INSERT INTO ${tableName} (purchase_dt, service_life, model_number) VALUES ('${equi.purchase}', '${equi.service}', '${equi.number}');
        """.trimIndent()
        stmt!!.execute(sql)
    } catch (e: SQLException) {
        e.printStackTrace()
    }
}
fun update_equipment(equiID: bd_id, equi: equipment, tableName: String) {
    try {
        val updateFields = mutableListOf<String>()
        val num: Int = equiID.bd_id.toInt()
        stmt = conn!!.createStatement()
        if (equi.number.isNotEmpty()){
            updateFields.add("model_number = '${equi.number}'")
        }
        if (equi.service.isNotEmpty()){
            updateFields.add("service_life = '${equi.service}'")
        }
        if(equi.purchase.isNotEmpty()){
            updateFields.add("purchase_dt = '${equi.purchase}'")
        }
        val sql = """
            UPDATE ${tableName} SET ${updateFields.joinToString(", ")} WHERE id_${tableName} = '${num}';
        """.trimIndent()
        stmt!!.execute(sql)
    } catch (e: SQLException) {
        e.printStackTrace()
    }
}