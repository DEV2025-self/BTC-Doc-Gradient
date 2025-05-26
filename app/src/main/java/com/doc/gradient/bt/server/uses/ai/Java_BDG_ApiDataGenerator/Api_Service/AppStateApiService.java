package com.doc.gradient.bt.server.uses.ai.Java_BDG_ApiDataGenerator.Api_Service;


import com.doc.gradient.bt.server.uses.ai.Java_BDG_Responce_Class.BDG_Add_Plan.Addplanreq;
import com.doc.gradient.bt.server.uses.ai.Java_BDG_Responce_Class.BDG_Add_Plan.BDG_AddPlanResponse;
import com.doc.gradient.bt.server.uses.ai.Java_BDG_Responce_Class.BDG_AppSetting.BDG_AppSettingResponse;
import com.doc.gradient.bt.server.uses.ai.Java_BDG_Responce_Class.BDG_Country.BDG_CountryResponse;
import com.doc.gradient.bt.server.uses.ai.Java_BDG_Responce_Class.BDG_Country.Countryreq;
import com.doc.gradient.bt.server.uses.ai.Java_BDG_Responce_Class.BDG_DeleteUser.BDG_DeleteUserResponse;
import com.doc.gradient.bt.server.uses.ai.Java_BDG_Responce_Class.BDG_DeleteUser.Deletereq;
import com.doc.gradient.bt.server.uses.ai.Java_BDG_Responce_Class.BDG_GetPlan.BDG_GetPlanResponse;
import com.doc.gradient.bt.server.uses.ai.Java_BDG_Responce_Class.BDG_GetPlan.GetPlanreq;
import com.doc.gradient.bt.server.uses.ai.Java_BDG_Responce_Class.BDG_GetUser.BDG_GetAllUserResponse;
import com.doc.gradient.bt.server.uses.ai.Java_BDG_Responce_Class.BDG_GetUser.GetUserreq;
import com.doc.gradient.bt.server.uses.ai.Java_BDG_Responce_Class.BDG_Login.BDG_LoginResponse;
import com.doc.gradient.bt.server.uses.ai.Java_BDG_Responce_Class.BDG_Login.Logingreq;
import com.doc.gradient.bt.server.uses.ai.Java_BDG_Responce_Class.BDG_Logout.BDG_LogOutResponse;
import com.doc.gradient.bt.server.uses.ai.Java_BDG_Responce_Class.BDG_Logout.Forgetreq;
import com.doc.gradient.bt.server.uses.ai.Java_BDG_Responce_Class.BDG_MiningHistroy.BDG_MiningHistroyResponse;
import com.doc.gradient.bt.server.uses.ai.Java_BDG_Responce_Class.BDG_MiningHistroy.MiningHistoryreq;
import com.doc.gradient.bt.server.uses.ai.Java_BDG_Responce_Class.BDG_Plan.Allplanreq;
import com.doc.gradient.bt.server.uses.ai.Java_BDG_Responce_Class.BDG_Plan.BDG_PlanResponse;
import com.doc.gradient.bt.server.uses.ai.Java_BDG_Responce_Class.BDG_ReferralsHistory.BDG_ReferralsHistoryResponse;
import com.doc.gradient.bt.server.uses.ai.Java_BDG_Responce_Class.BDG_ReferralsHistory.Getreferhistoryreq;
import com.doc.gradient.bt.server.uses.ai.Java_BDG_Responce_Class.BDG_Signup.BDG_SignupModelClass;
import com.doc.gradient.bt.server.uses.ai.Java_BDG_Responce_Class.BDG_Signup.Registerreq;
import com.doc.gradient.bt.server.uses.ai.Java_BDG_Responce_Class.BDG_UserInfo.BDG_UserInfoResponse;
import com.doc.gradient.bt.server.uses.ai.Java_BDG_Responce_Class.BDG_UserInfo.GetUserinforeq;
import com.doc.gradient.bt.server.uses.ai.Java_BDG_Responce_Class.BDG_UserInfoEdit.BDG_UserInfoEditResponse;
import com.doc.gradient.bt.server.uses.ai.Java_BDG_Responce_Class.BDG_UserInfoEdit.Getusereditreq;
import com.doc.gradient.bt.server.uses.ai.Java_BDG_Responce_Class.BDG_Withdrawal.BDG_WithdrawalResponse;
import com.doc.gradient.bt.server.uses.ai.Java_BDG_Responce_Class.BDG_Withdrawal.Withdrawalreq;
import com.doc.gradient.bt.server.uses.ai.Java_BDG_Responce_Class.BDG_WithdrawalHistory.BDG_WithdrawalHistoryResponse;
import com.doc.gradient.bt.server.uses.ai.Java_BDG_Responce_Class.BDG_WithdrawalHistory.WithdrawalHistoryReq;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;


public interface AppStateApiService {

    @POST("setting")
    Call<BDG_AppSettingResponse> AppSettingbdg(@Body HashMap<String, String> fields, @Header("token") String authHeader);


    @POST("login")
    Call<BDG_LoginResponse> LoginApibdg(
            @Body Logingreq logingreq,
            @Header("token") String authHeader
    );

    @POST("sign_up")
    Call<BDG_SignupModelClass> registerApibdg(
            @Body Registerreq registerreq,
            @Header("token") String authHeader
    );

    @POST("forgot_password")
    Call<BDG_LogOutResponse> forgetApibdg(
            @Body Forgetreq logoutreq,
            @Header("token") String authHeader
    );

    @POST("mining_history")
    Call<BDG_MiningHistroyResponse> MiningHistoryApibdg(
            @Body MiningHistoryreq miningreq,
            @Header("token") String authHeader
    );

    @POST("get_user")
    Call<BDG_GetAllUserResponse> GetuserApibdg(
            @Body GetUserreq getuserreq,
            @Header("token") String authHeader
    );

    @POST("plan")
    Call<BDG_PlanResponse> AllplanApibdg(
            @Body Allplanreq allplan,
            @Header("token") String authHeader
    );

    @POST("plan/get")
    Call<BDG_GetPlanResponse> GetPlanApibdg(
            @Body GetPlanreq getplan,
            @Header("token") String authHeader
    );

    @POST("plan/add")
    Call<BDG_AddPlanResponse> AddplanApibdg(
            @Body Addplanreq addplanreq,
            @Header("token") String authHeader
    );

    @POST("user_info")
    Call<BDG_UserInfoResponse> GetUserInfoApibdg(
            @Body GetUserinforeq getUSerinfo,
            @Header("token") String authHeader
    );

    @POST("referrals_history")
    Call<BDG_ReferralsHistoryResponse> GetreferhistoryApibdg(
            @Body Getreferhistoryreq getreferhistory,
            @Header("token") String authHeader
    );

    @POST("user_info_edit")
    Call<BDG_UserInfoEditResponse> GetUsereditApibdg(
            @Body Getusereditreq geteditinfo,
            @Header("token") String authHeader
    );

    @POST("logout")
    Call<BDG_LogOutResponse> LogoutApibdg(
            @Body GetUserinforeq forgetreq,
            @Header("token") String authHeader
    );

    @POST("countries")
    Call<BDG_CountryResponse> GetcountryApibdg(
            @Body Countryreq countryreq,
            @Header("token") String authHeader);

    @POST("withdraw")
    Call<BDG_WithdrawalResponse> WithdrawalApibdg(
            @Body Withdrawalreq withdrawalreq,
            @Header("token") String authHeader
    );

    @POST("withdraw_history")
    Call<BDG_WithdrawalHistoryResponse> WithdrawalHistoryApibdg(
            @Body WithdrawalHistoryReq withdrawalreq,
            @Header("token") String authHeader
    );

    @POST("user/delete")
    Call<BDG_DeleteUserResponse> DeleteApibdg(
            @Body Deletereq deletereq,
            @Header("token") String authHeader
    );
}
