package com.example.plugins

import com.example.model.*
import com.example.plugins.MySQLDatabaseExampleKotlin.dele
import com.example.plugins.MySQLDatabaseExampleKotlin.getConnection
import com.example.plugins.MySQLDatabaseExampleKotlin.into
import com.example.plugins.MySQLDatabaseExampleKotlin.select
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.thymeleaf.Thymeleaf
import io.ktor.server.thymeleaf.ThymeleafContent
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver

fun Application.configureTemplating() {
    install(Thymeleaf) {
        setTemplateResolver(ClassLoaderTemplateResolver().apply {
            prefix = "templates/thymeleaf/"
            suffix = ".html"
            characterEncoding = "utf-8"
        })
    }
        routing {
            get("/client"){//Запуск стронички с клиентами
                val client = select("client")
                call.respond(ThymeleafContent("table/client_table", mapOf("results" to client)))
            }
            /*
            post {
                val front = call.receiveParameters()
                val action = front["action"]
                if (action == "count") {
                    val param = Client(
                        name = front["name"] ?: "",
                        surname = front["surname"] ?: "",
                        patronymic = front["patronymic"] ?: "",
                        orde = front["orde"] ?: "",
                        result = front["result"] ?: ""
                    )
                    getConnection()
                    println(param.toString())
                    into(param)
                    call.respondRedirect("/client")
                    //нужно будет убрать в templating
                }
            }

             */
            post("/client"){
                val front = call.receiveParameters()
                val action = front["action"]
                if (action == "del") {
                    val param = bd_id(
                        front["id"] ?: "")
                    getConnection()
                    dele(param, "client")
                    call.respondRedirect("/client")
                }
            }
            get("/order"){//Запуск стронички с заказами
                val order = select("orde")
                call.respond(ThymeleafContent("table/order_table", mapOf("results" to order)))
            }
            post("/order"){
                val order = call.receiveParameters()
                val action = order["action"]
                if (action == "del") {
                    val param = bd_id(
                        order["id"] ?: ""
                    )
                    getConnection()
                    dele(param, "orde")
                    call.respondRedirect("/order")
                }
            }
            get("/order_work"){//Запуск стронички с заказом и работой
                val order_work = select("orde_work")
                call.respond(ThymeleafContent("table/order_work_table", mapOf("results" to order_work)))
                //call.respond("order_work")
            }
            get("/assortment"){ //Запуск стронички с ассортиментом
                val assortment = select("assortment")
                call.respond(ThymeleafContent("table/assortment_table", mapOf("results" to assortment)))
            }
            get("/equipment"){//Запуск стронички с оборудованием
                val equipment = select("equipment")
                call.respond(ThymeleafContent("table/equipment_table", mapOf("results" to equipment)))
            }
            post("/equipment"){}
            get("/work"){//Запуск стронички с работой
                val work = select("work")
                call.respond(ThymeleafContent("table/work_table", mapOf("results" to work)))
            }
            post("/work"){}
            get("/employee"){//Запуск стронички с работниками
                val employee = select("employee")
                call.respond(ThymeleafContent("table/employee_table", mapOf("results" to employee)))
            }
            post("/employee"){}
            post("/exits"){
                call.respondRedirect("/")

            }
        }
    }
//}