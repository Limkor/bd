package com.example.plugins.table.sql

import com.example.model.bd_id
import com.example.model.work
import com.example.plugins.table.sql.MySQLDatabaseExampleKotlin.conn
import com.example.plugins.table.sql.MySQLDatabaseExampleKotlin.stmt

fun into_work(work: work, tableName: String){
    stmt = conn!!.createStatement()
    val sql = """
        INSERT INTO $tableName (start_dt, end_dt) VALUES ('${work.start}', '${work.end}');
    """.trimIndent()
    stmt!!.execute(sql)
}
fun update_work(workid: bd_id, work: work, tableName: String){
    val num: Int = workid.bd_id.toInt()
    stmt = conn!!.createStatement()
    val updateFields = mutableListOf<String>()
    if(work.start.isNotEmpty()){
        updateFields.add("start_dt = '${work.start}'")
    }
    if(work.end.isNotEmpty()){
        updateFields.add("end_dt = '${work.end}'")
    }
    println("${work.start}")
    val sql = """
        UPDATE $tableName SET ${updateFields.joinToString(", ")} WHERE id_${tableName} = '${num}';
    """.trimIndent()
    stmt!!.execute(sql)
}