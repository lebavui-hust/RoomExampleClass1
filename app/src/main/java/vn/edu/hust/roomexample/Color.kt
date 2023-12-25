package vn.edu.hust.roomexample

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "colors")
data class Color(
    @PrimaryKey(autoGenerate = true)
    val _id: Int = 0,
    val name: String,
    @ColumnInfo(name = "hex_color")
    val hex: String
)
