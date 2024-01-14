package com.example.domain.repository

import com.example.data.mysql.mysql_models.address.AddressEntity
import com.example.data.mysql.mysql_models.person.PersonEntity
import com.example.data.mysql.mysql_models.person.PersonTable
import com.example.data.remote.dto.address.CreateAddressDto
import com.example.data.remote.dto.person.*
import com.example.data.remote.dto.person.FoundPersonWithAddressDto
import com.example.data.remote.dto.person.PersonId
import com.example.data.remote.dto.person.UpdatePersonDto
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.load
import org.jetbrains.exposed.dao.with
import org.jetbrains.exposed.sql.SizedCollection
import org.jetbrains.exposed.sql.transactions.transaction

class PersonRepository {

    fun findAll(): List<FoundPersonWithAddressDto> = transaction {
        PersonEntity
            .all()
            .with(PersonEntity::addresses)
            .map {
                FoundPersonWithAddressDto(
                    it.id.value,
                    it.name,
                    it.surname,
                    it.age,
                    it.addresses.map {
                        FoundPersonAddressDto(
                            it.street,
                            it.house,
                            it.apartment,
                            it.city,
                            it.postalCode
                        )
                    }
                )
            }
    }

    fun find(id: PersonId): FoundPersonWithAddressDto? = transaction {
        PersonEntity
            .findById(id)
            ?.load(PersonEntity::addresses)
            ?.let {
                FoundPersonWithAddressDto(
                    it.id.value,
                    it.name,
                    it.surname,
                    it.age,
                    it.addresses.map {
                        FoundPersonAddressDto(
                            it.street, it.house, it.apartment, it.city, it.postalCode
                        )
                    }
                )
            }
    }

    fun create(person: CreatePersonDto): PersonId = transaction {
        PersonEntity.new {
            name = person.name
            surname = person.surname
            age = person.age
        }.id.value
    }

    fun delete(id: PersonId): Unit = transaction {
        PersonEntity.findById(id)?.delete()
    }

    fun update(id: PersonId, person: UpdatePersonDto): Unit = transaction {
        PersonEntity.findById(id)?.let {
            it.name = person.name
            it.surname = person.surname
            it.age = person.age
        }
    }

    fun addAddress(personId: PersonId, address: CreateAddressDto) {
        transaction {
            PersonEntity.findById(personId)?.let {
                SizedCollection(
                    it.addresses + address.let {
                        AddressEntity.new {
                            city = address.city
                            house = address.house
                            street = address.street
                            postalCode = address.postalCode
                            apartment = address.apartment
                            this.personId = EntityID(personId, PersonTable)
                        }
                    }
                )
            }
        }
    }
}
