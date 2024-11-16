package com.example.plugins

import com.example.plugins.table.sql.MySQLDatabaseExampleKotlin.select
import com.example.plugins.table.sql.MySQLDatabaseExampleKotlin.select_w
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
                call.respond(ThymeleafContent("table/standart/client_table", mapOf("results" to client)))
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
                call.respond(ThymeleafContent("table/standart/order_table", mapOf("results" to order)))
            }
            /*
            get("/order_work"){//Запуск стронички с заказом и работой
                val order_work = select("orde_work")
                call.respond(ThymeleafContent("table/order_work_table", mapOf("results" to order_work)))

                //call.respond("order_work")
            }

             */
            get("/order_work") {
                val page = call.parameters["page"]?.toIntOrNull() ?: 1
                val limit = 3
                val offset = (page - 1) * limit
                val orderWorkData = select_w("orde_work", limit, offset)

                call.respond(ThymeleafContent("table/standart/order_work_table", mapOf(
                    "results" to orderWorkData,
                    "currentPage" to page
                )))
            }
            get("/assortment"){ //Запуск стронички с ассортиментом
                val assortment = select("assortment")
                call.respond(ThymeleafContent("table/standart/assortment_table", mapOf("results" to assortment)))
            }
            get("/equipment"){//Запуск стронички с оборудованием
                val equipment = select("equipment")
                call.respond(ThymeleafContent("table/standart/equipment_table", mapOf("results" to equipment)))
            }
            get("/work"){//Запуск стронички с работой
                val work = select("work")
                call.respond(ThymeleafContent("table/standart/work_table", mapOf("results" to work)))
            }
            get("/employee"){//Запуск стронички с работниками
                val employee = select("employee")
                call.respond(ThymeleafContent("table/standart/employee_table", mapOf("results" to employee)))
            }
            /*






             */
            get("/client_r"){ //Запуск стронички с клиентами
                val client = select("client")
                call.respond(ThymeleafContent("table/razrab/client_table", mapOf("results" to client)))
            }
            get("/order_r"){//Запуск стронички с заказами
                val order = select("orde")
                call.respond(ThymeleafContent("table/razrab/order_table", mapOf("results" to order)))
            }
            /*
            get("/order_work"){//Запуск стронички с заказом и работой
                val order_work = select("orde_work")
                call.respond(ThymeleafContent("table/order_work_table", mapOf("results" to order_work)))

                //call.respond("order_work")
            }

             */
            get("/order_work_r") {
                val page = call.parameters["page"]?.toIntOrNull() ?: 1
                val limit = 3
                val offset = (page - 1) * limit
                val orderWorkData = select_w("orde_work", limit, offset)

                call.respond(ThymeleafContent("table/razrab/order_work_table", mapOf(
                    "results" to orderWorkData,
                    "currentPage" to page
                )))
            }
            get("/assortment_r"){ //Запуск стронички с ассортиментом
                val assortment = select("assortment")
                call.respond(ThymeleafContent("table/razrab/assortment_table", mapOf("results" to assortment)))
            }
            get("/equipment_r"){//Запуск стронички с оборудованием
                val equipment = select("equipment")
                call.respond(ThymeleafContent("table/razrab/equipment_table", mapOf("results" to equipment)))
            }
            get("/work_r"){//Запуск стронички с работой
                val work = select("work")
                call.respond(ThymeleafContent("table/razrab/work_table", mapOf("results" to work)))
            }
            get("/employee_r"){//Запуск стронички с работниками
                val employee = select("employee")
                call.respond(ThymeleafContent("table/razrab/employee_table", mapOf("results" to employee)))
            }
            /*





             */
            get("/client_c"){ //Запуск стронички с клиентами
                val client = select("client")
                call.respond(ThymeleafContent("table/client/client_table", mapOf("results" to client)))
            }
            get("/order_c"){//Запуск стронички с заказами
                val order = select("orde")
                call.respond(ThymeleafContent("table/client/order_table", mapOf("results" to order)))
            }
            /*
            get("/order_work"){//Запуск стронички с заказом и работой
                val order_work = select("orde_work")
                call.respond(ThymeleafContent("table/order_work_table", mapOf("results" to order_work)))

                //call.respond("order_work")
            }

             */
            get("/order_work_c") {
                val page = call.parameters["page"]?.toIntOrNull() ?: 1
                val limit = 3
                val offset = (page - 1) * limit
                val orderWorkData = select_w("orde_work", limit, offset)

                call.respond(ThymeleafContent("table/client/order_work_table", mapOf(
                    "results" to orderWorkData,
                    "currentPage" to page
                )))
            }
            get("/assortment_c"){ //Запуск стронички с ассортиментом
                val assortment = select("assortment")
                call.respond(ThymeleafContent("table/client/assortment_table", mapOf("results" to assortment)))
            }
            get("/equipment_c"){//Запуск стронички с оборудованием
                val equipment = select("equipment")
                call.respond(ThymeleafContent("table/client/equipment_table", mapOf("results" to equipment)))
            }
            get("/work_c"){//Запуск стронички с работой
                val work = select("work")
                call.respond(ThymeleafContent("table/client/work_table", mapOf("results" to work)))
            }
            get("/employee_c"){//Запуск стронички с работниками
                val employee = select("employee")
                call.respond(ThymeleafContent("table/client/employee_table", mapOf("results" to employee)))
            }
            ///
            post("/exits_r"){
                call.respondRedirect("/razrab")

            }
            post("/exits"){
                call.respondRedirect("/")
            }
            post("/exits_c"){
                call.respondRedirect("/client_avr")

            }
        }
    }
//}