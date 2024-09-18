package com.dongze.ecart.model.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.dongze.ecart.model.local.DBConstants.TABLE_NAME

@Dao
interface InCartItemDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertInCartItem(inCartItem: InCartItem)

    @Delete
    fun deleteInCartItem(inCartItem: InCartItem)

    @Update
    fun updateInCartItem(inCartItem: InCartItem)

    @Query("SELECT * FROM $TABLE_NAME WHERE USER_ID = :userId ")
    fun getInCartItemList(userId: Int): List<InCartItem>

    @Query("DELETE FROM $TABLE_NAME")
    fun clear()
}