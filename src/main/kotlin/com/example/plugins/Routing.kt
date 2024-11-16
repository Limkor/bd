package com.example.plugins


import com.example.plugins.table.sql.MySQLDatabaseExampleKotlin.getConnection
import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.thymeleaf.*



fun Application.configureRouting() {
    routing {
        // Главная страница - выводим HTML файл index.html
        get("/") {
            call.respond(ThymeleafContent("table/standart/index", emptyMap()))
        }
        // Пример маршрута для отображения HTML файла
        post {
            //нужно будет убрать в templating
            val front = call.receiveParameters()
            val action = front["action"]
             if (action == "client") {
                getConnection()
                call.respondRedirect("/client")
            }
            if(action == "order") {
                getConnection()
                call.respondRedirect("/order")
            }
            if(action == "assortment") {
                getConnection()
                call.respondRedirect("/assortment")
            }
            if(action == "order_work") {
                getConnection()
                call.respondRedirect("/order_work")
            }
            if(action == "employee") {
                getConnection()
                call.respondRedirect("/employee")
            }
            if(action == "equipment") {
                getConnection()
                call.respondRedirect("/equipment")
            }
            if(action == "work") {
                getConnection()
                call.respondRedirect("/work")
            }
        }

        /*

         */
        get("/client_avr") {
            call.respond(ThymeleafContent("table/client/index", emptyMap()))
        }
        post ("/c"){
            //нужно будет убрать в templating
            val front = call.receiveParameters()
            val action = front["action"]
            if (action == "client") {
                getConnection()
                call.respondRedirect("/client_c")
            }
            if(action == "order") {
                getConnection()
                call.respondRedirect("/order_c")
            }
            if(action == "assortment") {
                getConnection()
                call.respondRedirect("/assortment_c")
            }
            if(action == "order_work") {
                getConnection()
                call.respondRedirect("/order_work_c")
            }
            if(action == "employee") {
                getConnection()
                call.respondRedirect("/employee_c")
            }
            if(action == "equipment") {
                getConnection()
                call.respondRedirect("/equipment_c")
            }
            if(action == "work") {
                getConnection()
                call.respondRedirect("/work_c")
            }
        }
        /*

         */
        get("/regestr") {
            call.respond(ThymeleafContent("reg", emptyMap()))
        }
        /*

         */
        get("/razrab") {
            call.respond(ThymeleafContent("table/razrab/index", emptyMap()))
        }
        // Пример маршрута для отображения HTML файла
        post ("/r"){
            //нужно будет убрать в templating
            val front = call.receiveParameters()
            val action = front["action"]
            if (action == "client") {
                getConnection()
                call.respondRedirect("/client_r")
            }
            if(action == "order") {
                getConnection()
                call.respondRedirect("/order_r")
            }
            if(action == "assortment") {
                getConnection()
                call.respondRedirect("/assortment_r")
            }
            if(action == "order_work") {
                getConnection()
                call.respondRedirect("/order_work_r")
            }
            if(action == "employee") {
                getConnection()
                call.respondRedirect("/employee_r")
            }
            if(action == "equipment") {
                getConnection()
                call.respondRedirect("/equipment_r")
            }
            if(action == "work") {
                getConnection()
                call.respondRedirect("/work_r")
            }
        }
        //
        staticResources("/static", "static")
    }
}


/** * Программа для вывода списка баз данных в MySQL с использованием Kotlin */





