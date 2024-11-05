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
            call.respond(ThymeleafContent("index", emptyMap()))
        }
        // Пример маршрута для отображения HTML файла
        post {
            //нужно будет убрать в templating
            val front = call.receiveParameters()
            val action = front["action"]
             if (action == "client") {
                getConnection()
                call.respondRedirect("/client")
            } else if(action == "order") {
                getConnection()
                call.respondRedirect("/order")
            } else if(action == "assortment") {
                getConnection()
                call.respondRedirect("/assortment")
            } else if(action == "order_work") {
                getConnection()
                call.respondRedirect("/order_work")
            } else if(action == "employee") {
                getConnection()
                call.respondRedirect("/employee")
            } else if(action == "equipment") {
                getConnection()
                call.respondRedirect("/equipment")
            } else if(action == "work") {
                getConnection()
                call.respondRedirect("/work")
            }
        }

        staticResources("/static", "static")
    }
}


/** * Программа для вывода списка баз данных в MySQL с использованием Kotlin */





