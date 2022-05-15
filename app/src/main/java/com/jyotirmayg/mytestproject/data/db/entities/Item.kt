package com.jyotirmayg.mytestproject.data.db.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey

/**
 * @author jyoti
 * @created on 14-05-2022
 * This class is responsible for creating database entity for Item table.
 */
@Entity
data class Item(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val itemName: String? = null,
    val qty: String? = null,
    val rate: Double? = null,
    val gst: Double? = null
)
