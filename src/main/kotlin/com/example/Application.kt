package com.example

import com.example.plugins.*
import com.example.plugins.MySQLDatabaseExampleKotlin.getConnection
import com.example.plugins.MySQLDatabaseExampleKotlin.select
import io.ktor.server.application.*

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    configureTemplating()
    configureRouting()
    configureZapR()
}
