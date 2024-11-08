package com.example.plugins

import com.example.plugins.table.sql.MySQLDatabaseExampleKotlin.select
import io.ktor.server.application.*
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
            get("/order"){//Запуск стронички с заказами
                val order = select("orde")
                call.respond(ThymeleafContent("table/order_table", mapOf("results" to order)))
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
            get("/work"){//Запуск стронички с работой
                val work = select("work")
                call.respond(ThymeleafContent("table/work_table", mapOf("results" to work)))
            }
            get("/employee"){//Запуск стронички с работниками
                val employee = select("employee")
                call.respond(ThymeleafContent("table/employee_table", mapOf("results" to employee)))
            }
            post("/exits"){
                call.respondRedirect("/")

            }
        }
    }
//}