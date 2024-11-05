package com.example.plugins.table.sql

import com.example.model.assortment
import com.example.model.bd_id
import com.example.plugins.table.sql.MySQLDatabaseExampleKotlin.conn
import com.example.plugins.table.sql.MySQLDatabaseExampleKotlin.stmt
import java.sql.SQLException

fun update_assortment(assortId: bd_id, assort: assortment, tableName: String) {
    try {
        val num: Int = assortId.bd_id.toInt()
        stmt = conn!!.createStatement()
        if (assort.name != "" && assort.description != "") {
            val sql =
                """UPDATE $tableName SET name = '${assort.name}', description = '${assort.description}' WHERE id_$tableName = '${num}';"""
            stmt!!.executeUpdate(sql)
        }else if (assort.name != ""){
            val sql = """UPDATE $tableName SET name = '${assort.name}' WHERE id_$tableName = '${num}';"""
            stmt!!.executeUpdate(sql)
            //println("dddddddddddddddddddddddddddddddddd")
        }else if(assort.description != ""){
            val sql = """UPDATE $tableName SET description = '${assort.description}' WHERE id_$tableName = '${num}';"""
            stmt!!.executeUpdate(sql)
        }
    }catch (ex: SQLException){
        ex.printStackTrace()
    }
}
fun into_assortment(assort: assortment, tableName: String) {
    try {
        stmt = conn!!.createStatement()
        val sql = """INSERT INTO $tableName (name, description) VALUES ('${assort.name}', '${assort.description}');"""
        stmt!!.executeUpdate(sql)
    }catch (ex: SQLException){
        ex.printStackTrace()
    }

}