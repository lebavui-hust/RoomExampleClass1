package vn.edu.hust.roomexample

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface ColorDao {
    @Query("select * from colors")
    fun getAllColors(): Array<Color>

    @Insert
    fun insert(vararg color: Color): Array<Long>

    @Update
    fun update(color: Color): Int

    @Delete
    fun delete(color: Color): Int

    @Query("delete from colors where _id = :id")
    fun deleteById(id: Int)
}