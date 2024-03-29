package com.example

import com.example.plugins.*
import io.github.cdimascio.dotenv.dotenv
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*

val dotenv = dotenv {
    ignoreIfMissing = true
}
fun main() {
    val env = applicationEngineEnvironment {
        envConfig()
    }
    embeddedServer(Netty, env)
        .start(wait = true)
}

fun Application.module() {
    configureDataBase()
    configureSerialization()
    configureRouting()
}

fun ApplicationEngineEnvironmentBuilder.envConfig() {
    module {
        module()
    }
    connector {
        host = "0.0.0.0"
        port = 8080
    }
}
