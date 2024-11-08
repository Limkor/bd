package com.example.plugins

import com.example.model.*
import com.example.plugins.table.sql.*
import com.example.plugins.table.sql.MySQLDatabaseExampleKotlin.dele
import com.example.plugins.table.sql.MySQLDatabaseExampleKotlin.getConnection
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureZapR() {
    routing{
        post("/client"){
            var cli = call.receiveParameters()
            val action = cli["action"]
            val tablename = "client"
            getConnection()
            val param_id = bd_id(
                cli["id"]?: ""
            )
            val param = client(
                cli["name"]?: "",
                cli["surname"]?: "",
                cli["patronymic"]?: "",
                cli["orde"]?:"",
                cli["result"]?: ""
            )
            if (action == "into"){
                into_client(param, tablename)
                call.respondRedirect("/client")
            }
            if(action == "del"){
                dele(param_id, tablename)
                call.respondRedirect("/client")
            }
            if(action == "update"){
                update_client(param_id, param, tablename)
                call.respondRedirect("/client")
            }
            if (action == "select"){
                call.respondRedirect("/info")
            }

        }
        post("/employee"){
            var emplo = call.receiveParameters()
            val action = emplo["action"]
            val tablename = "employee"
            var param_id = bd_id(
                emplo["id"]?: ""
            )
            val param = employee(
                emplo["name"]?: "",
                emplo["surname"]?: "",
                emplo["patronymic"]?: "",
                emplo["phone"]?: "",
            )
            getConnection()
            if (action == "into"){
                println(param)
                into_employee(param, tablename)
            }
            if (action == "update"){
                update_employee(param_id, param, tablename)
            }
            if (action == "del"){
                dele(param_id, tablename)
            }
            if (action == "select"){

            }
            call.respondRedirect("/employee")
        }
        post("/equipment"){
            var equi = call.receiveParameters()
            val action = equi["action"]
            val tablename = "equipment"
            var param_id = bd_id(
                equi["id"]?: ""
            )
            val param = equipment(
                equi["purchase"]?: "",
                equi["service"]?: "",
                equi["number"]?: "",
            )
            getConnection()
            if (action == "into"){
                println(param)
                into_equipment(param, tablename)
            }
            if (action == "update"){
                update_equipment(param_id, param, tablename)
            }
            if (action == "del"){
                dele(param_id, tablename)
            }
            call.respondRedirect("/equipment")
        }
        post("/order"){
            val orde = call.receiveParameters()
            val action = orde["action"]
            val tablename = "orde"
            val param_id = bd_id(
                orde["id"] ?: ""
            )
            val param = order(
                orde["start_dt"]?:"",
                orde["end_dt"]?:"",
                orde["client"]?:""
            )
            getConnection()
            if (action == "into"){
                into_order(param, tablename)
            }
            if (action == "del") {
                dele(param_id, tablename)
            }
            if(action == "update"){
                update_order(param_id, param, tablename)
            }
            call.respondRedirect("/order")
        }
        post("/work"){
            val wor = call.receiveParameters()
            val action = wor["action"]
            val tablename = "work"
            val param_id = bd_id(
                wor["id"] ?: ""
            )
            val param = work(
                wor["start"]?:"",
                wor["end"]?:"",
            )
            getConnection()
            if (action == "into"){
                into_work(param, tablename)
            }
            if (action == "del") {
                dele(param_id, tablename)
            }
            if(action == "update"){
                println(param)
                update_work(param_id, param, tablename)
            }
            call.respondRedirect("/work")
        }
        post("/order_work"){
            val wor = call.receiveParameters()
            val action = wor["action"]
            val tablename = "orde_work"
            val param_id = order_work_id(
                wor["id_order"] ?: "",
                wor["id_work"]?: "",
                wor["id_equipment"]?: "",
                wor["id_employee"]?: "",
                wor["id_assortment"]?: "",
            )
            val param = order_work(
                wor["count"] ?: "",
                wor["status"]?: "",
                wor["price"]?: "",
                wor["id_price"]?: ""
            )
            getConnection()
            if (action == "into"){
                into_order_work(param_id, param, tablename)
            }
            if (action == "del") {
                del_order_work(param_id, tablename)
            }
            if(action == "update"){
                update_order_work(param_id, param, tablename)
            }
            call.respondRedirect("/order_work")
        }
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
            }
            if(action == "del"){
                dele(param_id, tablename)
            }
            if(action == "update"){
                update_assortment(param_id, param, tablename)
            }
            if(action == "select"){}
            call.respondRedirect("/assortment")
        }
    }
}