package com.example.data.mysql.mysql_models.address

import com.example.data.mysql.mysql_models.person.PersonTable
import org.jetbrains.exposed.dao.id.IntIdTable

object AddressTable : IntIdTable("address") {
    val personId = reference("person_id", PersonTable.id)
    val street = text("street")
    val house = text("house")
    val apartment = text("apartment")
    val city = text("city")
    val postalCode = text("postal_code")
}