package com.dongze.ecart.model.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.dongze.ecart.model.local.DBConstants.PRICE
import com.dongze.ecart.model.local.DBConstants.PRODUCT_ID
import com.dongze.ecart.model.local.DBConstants.PRODUCT_NAME
import com.dongze.ecart.model.local.DBConstants.QTY
import com.dongze.ecart.model.local.DBConstants.TABLE_NAME
import com.dongze.ecart.model.local.DBConstants.USER_ID

@Entity(
    tableName = TABLE_NAME,
    primaryKeys = [PRODUCT_ID, USER_ID]
)
data class InCartItem(
    @ColumnInfo(name = PRODUCT_ID) val pid: String,
    @ColumnInfo(name = PRODUCT_NAME) val pname: String,
    @ColumnInfo(name = PRICE) val price: String,
    @ColumnInfo(name = QTY) val  qty: Int,

    @ColumnInfo(name = USER_ID) val userID: Int
)
