package com.example.data.mysql.migrations

import com.example.data.mysql.entity.UserEntity
import com.example.data.mysql.mysql_models.address.AddressTable
import com.example.data.mysql.mysql_models.person.PersonTable
import org.jetbrains.exposed.sql.SchemaUtils

fun migration1(){
        SchemaUtils.create(UserEntity)
        SchemaUtils.create(PersonTable)
        SchemaUtils.create(AddressTable)
}