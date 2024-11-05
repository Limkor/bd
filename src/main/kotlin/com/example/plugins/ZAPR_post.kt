package com.example.plugins

import com.example.model.assortment
import com.example.model.bd_id
import com.example.plugins.MySQLDatabaseExampleKotlin.dele
import com.example.plugins.MySQLDatabaseExampleKotlin.getConnection
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureZapR() {
    routing{
        post("/assortment"){
            val assor = call.receiveParameters()
            val action = assor["action"]
            val tablename = "assortment"
            getConnection()
            val param_id = bd_id(
                assor["id"]?: ""
            )
            val param = assortment(
                assor["name"]?:"",
                assor["description"]?: ""
            )
            /*

             */
            if (action == "into"){
                into_assortment(param, tablename)
            }else if(action == "del"){
                dele(param_id, tablename)
            }else if(action == "update"){
                update_assortment(param_id, param, tablename)
            }else if(action == "select"){}
            call.respondRedirect("/assortment")
        }
    }
}