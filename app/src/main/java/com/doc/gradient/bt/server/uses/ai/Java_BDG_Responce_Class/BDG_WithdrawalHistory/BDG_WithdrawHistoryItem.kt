package com.doc.gradient.bt.server.uses.ai.BDG_Responce_Class.BDG_WithdrawalHistory

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class BDG_WithdrawHistoryItem(TYPE_REFRESH: Int) {
    @SerializedName("id")
    @Expose
    val id: Int? = null

    @SerializedName("user_id")
    @Expose
    val userId: Int? = null

    @SerializedName("amount")
    @Expose
    val amount: String? = null

    @SerializedName("address")
    @Expose
    val address: String? = null

    @SerializedName("time")
    @Expose
    val time: String? = null

    @SerializedName("status")
    @Expose
    val status: String? = null

    @SerializedName("coin_name")
    @Expose
    val coinName: String? = null

    @SerializedName("app_id")
    @Expose
    val appId: Int? = null

    @SerializedName("created_at")
    @Expose
    val createdAt: Any? = null

    @SerializedName("updated_at")
    @Expose
    val updatedAt: Any? = null

    @SerializedName("first_name")
    @Expose
    val firstName: String? = null

    @SerializedName("last_name")
    @Expose
    val lastName: String? = null

    @SerializedName("email")
    @Expose
    val email: String? = null

    override fun toString(): String {
        return "BDG_WithdrawHistoryItem(id=$id, userId=$userId, amount=$amount, address=$address, time=$time, status=$status, coinName=$coinName, appId=$appId, createdAt=$createdAt, updatedAt=$updatedAt, firstName=$firstName, lastName=$lastName, email=$email)"
    }

}