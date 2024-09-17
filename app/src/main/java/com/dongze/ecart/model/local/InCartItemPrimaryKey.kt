package com.dongze.ecart.model.local

import androidx.room.ColumnInfo
import com.dongze.ecart.model.local.DBConstants.PRODUCT_ID
import com.dongze.ecart.model.local.DBConstants.USER_ID

data class InCartItemPrimaryKey(
    @ColumnInfo(name = PRODUCT_ID) val pid: String,
    @ColumnInfo(name = USER_ID) val userID: Int
)
