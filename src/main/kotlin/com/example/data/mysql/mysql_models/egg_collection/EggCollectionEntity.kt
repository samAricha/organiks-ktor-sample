package com.example.data.mysql.mysql_models.egg_collection

import com.example.data.mysql.mysql_models.address.AddressTable
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.sql.Table


class EggCollectionEntity(id: EntityID<Long>) : LongEntity(id) {
    companion object : LongEntityClass<EggCollectionEntity>(EggCollectionTable)

    var uuid by EggCollectionTable.uuid
    var qty by EggCollectionTable.qty
    var cracked by EggCollectionTable.cracked
    var eggTypeId by EggCollectionTable.eggTypeId
    var date by EggCollectionTable.date
    var isBackedUp by EggCollectionTable.isBackedUp
    var createdAt by EggCollectionTable.createdAt
}

