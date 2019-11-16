package io.orly.loginapp.data.local.room.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(
    tableName = "user",
    indices = [
        Index("email"),
        Index("password")
    ]
)
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    @field:SerializedName("id")
    val id: Int? = null,
    @field:SerializedName("email")
    var email: String? = null,
    @field:SerializedName("name")
    var name: String? = null,
    @field:SerializedName("password")
    var password: String? = null
)