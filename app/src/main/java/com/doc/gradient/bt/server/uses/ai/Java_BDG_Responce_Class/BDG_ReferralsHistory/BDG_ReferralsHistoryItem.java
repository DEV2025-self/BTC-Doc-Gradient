package com.doc.gradient.bt.server.uses.ai.Java_BDG_Responce_Class.BDG_ReferralsHistory;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class BDG_ReferralsHistoryItem implements Serializable {

    // Optional custom field, not serialized
    private final int typeRefresh;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("user_id")
    @Expose
    private Integer userId;
    @SerializedName("ref_user_id")
    @Expose
    private Integer refUserId;
    @SerializedName("created_at")
    @Expose
    private Object createdAt;
    @SerializedName("updated_at")
    @Expose
    private Object updatedAt;
    @SerializedName("point")
    @Expose
    private Integer point;
    @SerializedName("first_name")
    @Expose
    private String firstName;
    @SerializedName("last_name")
    @Expose
    private String lastName;
    @SerializedName("email")
    @Expose
    private String email;

    public BDG_ReferralsHistoryItem(int typeRefresh) {
        this.typeRefresh = typeRefresh;
    }

    // Getters and setters (or use Lombok if available)
    public Integer getId() {
        return id;
    }

    public Integer getUserId() {
        return userId;
    }

    public Integer getRefUserId() {
        return refUserId;
    }

    public Object getCreatedAt() {
        return createdAt;
    }

    public Object getUpdatedAt() {
        return updatedAt;
    }

    public Integer getPoint() {
        return point;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public int getTypeRefresh() {
        return typeRefresh;
    }

    @Override
    public String toString() {
        return "BDG_ReferralsHistoryItem{" +
                "id=" + id +
                ", userId=" + userId +
                ", refUserId=" + refUserId +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", point=" + point +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", typeRefresh=" + typeRefresh +
                '}';
    }
}
