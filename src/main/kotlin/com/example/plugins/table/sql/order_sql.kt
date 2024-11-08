package com.example.plugins.table.sql

import com.example.model.bd_id
import com.example.model.order
import com.example.plugins.table.sql.MySQLDatabaseExampleKotlin.conn
import com.example.plugins.table.sql.MySQLDatabaseExampleKotlin.stmt
import java.sql.SQLException

fun into_order(order: order, tableName: String){
    try{
        stmt = conn!!.createStatement()
        val sql = """
            INSERT INTO $tableName (start_dt, end_dt, id_client) VALUES ('${order.start_dt}', '${order.end_dt}', '${order.id_client}');}
        """.trimIndent()
        stmt!!.execute(sql)
    }catch(e:SQLException){
        e.printStackTrace()
    }
}
fun update_order(orderId: bd_id, order: order, tableName: String){
    try {
        val num: Int = orderId.bd_id.toInt()
        val updateFields = mutableListOf<String>()
        stmt = conn!!.createStatement()
        if (order.start_dt.isNotEmpty()){
            updateFields.add("start_dt = ${order.start_dt}")
        }
        if(order.end_dt.isNotEmpty()){
            updateFields.add("end_dt = ${order.end_dt}")
        }
        if(order.id_client.isNotEmpty()){
            updateFields.add("id_client = ${order.id_client}")
        }
        val sql = """
            UPDATE $tableName SET ${updateFields.joinToString(", ")} WHERE id_${tableName} = ${num}
        """.trimIndent()
        stmt!!.execute(sql)
    }catch(e:SQLException){
        e.printStackTrace()
    }
}