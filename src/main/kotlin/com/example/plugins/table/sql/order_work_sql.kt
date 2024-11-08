package com.example.plugins.table.sql

import com.example.model.order
import com.example.model.order_work
import com.example.model.order_work_id
import com.example.plugins.table.sql.MySQLDatabaseExampleKotlin.conn
import com.example.plugins.table.sql.MySQLDatabaseExampleKotlin.stmt
import java.sql.SQLException

fun del_order_work(ord: order_work_id, tableName: String) {
    try {
        stmt = conn!!.createStatement()
        val sql = """
        DELETE FROM $tableName WHERE id_orde='${ord.id_order}' AND id_work='${ord.id_work}' AND id_employee='${ord.id_employee}' AND id_equipment='${ord.id_equipment}' AND id_assortment='${ord.id_assortment}'; 
    """.trimIndent()
        stmt!!.execute(sql)
    } catch (e: SQLException) {
        e.printStackTrace()
    }
}
fun update_order_work(ord: order_work_id, work: order_work, tableName: String) {
    try {
        val updateFields = mutableListOf<String>()
        stmt = conn!!.createStatement()
        if (work.status != "не выбрано"){
            updateFields.add("orde_status='${work.status}'")
        }
        if (work.count.isNotEmpty()){
            updateFields.add("count='${work.count}'")
        }
        if (work.price.isNotEmpty()){
            updateFields.add("price='${work.price}'")
        }
        if (work.id_price.isNotEmpty()){
            updateFields.add("id_prese='${work.id_price}'")
        }
        val sql = """
            UPDATE $tableName SET '${updateFields.joinToString(", ")}' WHERE id_orde='${ord.id_order}' AND id_work='${ord.id_work}' AND id_employee='${ord.id_employee}' AND id_equipment='${ord.id_equipment}' AND id_assortment='${ord.id_assortment}';
        """.trimIndent()
        stmt!!.execute(sql)
    }catch (e: SQLException) {
        e.printStackTrace()
    }
}
fun into_order_work(ord: order_work_id, work: order_work, tableName: String) {
    try {
        stmt = conn!!.createStatement()
        val sql = """
            INSERT INTO ${tableName} VALUES (count, orde_status, id_orde, id_work, id_equipment, id_employee, id_assortment, price, id_prese) 
            VALUES 
            ('${work.count}', '${work.status}', '${ord.id_order}', '${ord.id_work}', '${ord.id_equipment}', '${ord.id_employee}', '${ord.id_assortment}', '${work.price}', '${work.price}');
        """.trimIndent()
        stmt!!.execute(sql)
    }catch (e: SQLException) {
        e.printStackTrace()
    }
}