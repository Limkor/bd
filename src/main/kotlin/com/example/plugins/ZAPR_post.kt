package com.example.plugins

import com.example.model.*
import com.example.plugins.table.sql.*
import com.example.plugins.table.sql.MySQLDatabaseExampleKotlin.dele
import com.example.plugins.table.sql.MySQLDatabaseExampleKotlin.getConnection
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.thymeleaf.*

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
                call.respondRedirect("/client_r")
            }
            if(action == "del"){
                dele(param_id, tablename)
                call.respondRedirect("/client_r")
            }
            if(action == "update"){
                update_client(param_id, param, tablename)
                call.respondRedirect("/client_r")
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
            call.respondRedirect("/employee_r")
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
            call.respondRedirect("/equipment_r")
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
            call.respondRedirect("/order_r")
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
            call.respondRedirect("/work_r")
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
            if(action == "select"){
                val allFieldsEmpty = listOf(
                    param_id.id_order,
                    param_id.id_work,
                    param_id.id_employee,
                    param_id.id_equipment,
                    param_id.id_assortment,
                    param.count,
                    param.status,
                    param.price,
                    param.id_price
                ).all { it.isEmpty() }
                println(allFieldsEmpty)
                if (allFieldsEmpty) {
                    call.respondRedirect("/order_work")
                } else{
                    val page = call.parameters["page"]?.toIntOrNull() ?: 1
                    val limit = 3
                    val offset = (page - 1) * limit
                    val k = select_work_order(param_id, param, tablename)
                    call.respond(
                        ThymeleafContent(
                            "table/client/order_work_table",
                            mapOf("results" to k, "currentPage" to page)
                        )
                    )
                }
            }
            call.respondRedirect("/order_work")
        }

        post("/order_work_r"){
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
            if(action == "select"){
                val allFieldsEmpty = listOf(
                    param_id.id_order,
                    param_id.id_work,
                    param_id.id_employee,
                    param_id.id_equipment,
                    param_id.id_assortment,
                    param.count,
                    param.status,
                    param.price,
                    param.id_price
                ).all { it.isEmpty() }
                println(allFieldsEmpty)
                if (allFieldsEmpty) {
                    call.respondRedirect("/order_work_r")
                } else{
                    val page = call.parameters["page"]?.toIntOrNull() ?: 1
                    val limit = 3
                    val offset = (page - 1) * limit
                    val k = select_work_order(param_id, param, tablename)
                    call.respond(
                        ThymeleafContent(
                            "table/razrab/order_work_table",
                            mapOf("results" to k, "currentPage" to page)
                        )
                    )
                }
            }
            call.respondRedirect("/order_work_r")
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
            call.respondRedirect("/assortment_r")
        }
        post("/regestr"){
            val tableName = "regestrachia"
            val registr = call.receiveParameters()
            val action = registr["action"]
            if (action == "input"){
                val avt = avtorizet(
                    registr["name"]?:"",
                    registr["password"]?:""
                )
                println(avt)
                getConnection()
                val exists = input_avtorizet(avt, tableName)
                if (exists.first){
                    if (exists.second == "client") {
                        call.respondRedirect("/client_avr")
                    }
                    if (exists.second == "razrab"){
                        call.respondRedirect("/razrab")
                    }
                }else{
                    call.respond("такого пользователя нет")
                }
            }
            if (action == "regestr") {
                call.respondRedirect("/regestr")
            }
            if(action=="regestr1"){
                val reg = regestrachia(
                    registr["name"]?:"",
                    registr["password"]?:"",
                    registr["password1"]?:""
                )
                getConnection()
                val status = reg_avtorizet(reg, tableName)
                println("lllllllllllllllllllllllllllllll")
                println(status)
                println("lllllllllllllllllllllllllllllll")
                if(status){
                    call.respondRedirect("/client_avr")
                }else{

                }
            }
        }
    }
}