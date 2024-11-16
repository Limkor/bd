package com.example.plugins.table.sql

import com.example.model.order
import com.example.model.order_work
import com.example.model.order_work_id
import com.example.plugins.table.sql.MySQLDatabaseExampleKotlin.conn
import com.example.plugins.table.sql.MySQLDatabaseExampleKotlin.resultset
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
        if (work.status != "Не выбрано"){
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
            UPDATE $tableName SET ${updateFields.joinToString(", ")} WHERE id_orde='${ord.id_order}' AND id_work='${ord.id_work}' AND id_employee='${ord.id_employee}' AND id_equipment='${ord.id_equipment}' AND id_assortment='${ord.id_assortment}';
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
            INSERT INTO ${tableName} (count, orde_status, id_orde, id_work, id_equipment, id_employee, id_assortment, price, id_prese) 
            VALUES 
            ('${work.count}', '${work.status}', '${ord.id_order}', '${ord.id_work}', '${ord.id_equipment}', '${ord.id_employee}', '${ord.id_assortment}', '${work.price}', '${work.id_price}');
        """.trimIndent()
        stmt!!.execute(sql)
    }catch (e: SQLException) {
        e.printStackTrace()
    }
}
fun select_work_order(id: order_work_id, ord: order_work, tableName: String): ArrayList<Map<String, String>> {
    val results = ArrayList<Map<String, String>>()
    try {
        stmt = conn!!.createStatement()

            val updateFields = mutableListOf<String>()
            if (id.id_order.isNotEmpty()) updateFields.add("id_orde='${id.id_order}'")
            if (id.id_employee.isNotEmpty()) updateFields.add("id_employee='${id.id_employee}'")
            if (id.id_equipment.isNotEmpty()) updateFields.add("id_equipment='${id.id_equipment}'")
            if (id.id_work.isNotEmpty()) updateFields.add("id_work='${id.id_work}'")
            if (id.id_assortment.isNotEmpty()) updateFields.add("id_assortment='${id.id_assortment}'")
            if (ord.count.isNotEmpty()) updateFields.add("count='${ord.count}'")
            if (ord.status.isNotEmpty()) updateFields.add("status='${ord.status}'")
            if (ord.price.isNotEmpty()) updateFields.add("price='${ord.price}'")
            if (ord.id_price.isNotEmpty()) updateFields.add("id_prese='${ord.id_price}'")

            val sql = """
                SELECT * FROM $tableName WHERE ${updateFields.joinToString("AND ")};
            """.trimIndent()

        val resultset = stmt!!.executeQuery(sql)
        val metaData = resultset!!.metaData
        val columnCount = metaData.columnCount

        while (resultset!!.next()) {
            val row = mutableMapOf<String, String>()
            for (i in 1..columnCount) {
                row[metaData.getColumnName(i)] = resultset!!.getString(i)
            }
            //println(row.toString())
            results.add(row)
        }
        println(results.toString())

    } catch (e: SQLException) {
        e.printStackTrace()
    }

    return results
}
