package com.example.plugins


import com.example.data.mysql.migrations.migration1
import com.example.data.mysql.seeders.eggCollectionsDemoData
import com.example.data.mysql.seeders.personDemoData
import io.ktor.server.application.*
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.transactions.transaction

fun Application.configureDataBase() {
    val database: Database = Database.connect(
        url ="jdbc:mysql://localhost:3306/organiks-ktor",
        user = "root",
        password = ""
    )

    transaction(database) {
        //migrations
        migration1()
        //seeders
        personDemoData()
        eggCollectionsDemoData()

    }

}