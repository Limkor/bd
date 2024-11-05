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
            }else if(action == "del"){
                dele(param_id, tablename)
            }else if(action == "update"){
                update_client(param_id, param, tablename)
            } else if (action == "select"){

            }
            call.respondRedirect("/client")
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
            } else if (action == "update"){
                update_employee(param_id, param, tablename)
            } else if (action == "del"){
                dele(param_id, tablename)
            } else if (action == "select"){

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
            } else if (action == "update"){
                update_equipment(param_id, param, tablename)
            } else if (action == "del"){
                dele(param_id, tablename)
            } else if (action == "select"){

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
            )
            getConnection()
            if (action == "into"){

            }else if (action == "del") {
                dele(param_id, tablename)
            }else if(action == "update"){

            }
            println(param)
            call.respondRedirect("/order")
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
            }else if(action == "del"){
                dele(param_id, tablename)
            }else if(action == "update"){
                update_assortment(param_id, param, tablename)
            }else if(action == "select"){}
            call.respondRedirect("/assortment")
        }
    }
}