package com.doc.gradient.bt.server.uses.ai.Java_BDG_ApiDataGenerator.Repository;

import static com.doc.gradient.bt.server.uses.ai.Java_BDG_CommonDocUtils.ProjectConstantsJava.Project_TOKEN;

import androidx.lifecycle.MutableLiveData;

import com.doc.gradient.bt.server.uses.ai.Java_BDG_ApiDataGenerator.Api_Service.AppStateApiService;
import com.doc.gradient.bt.server.uses.ai.Java_BDG_ApiDataGenerator.Network_Result.OperationState;
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

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AppStateRepository {
    private final AppStateApiService retrofit;

    @Inject
    public AppStateRepository(AppStateApiService retrofit) {
        this.retrofit = retrofit;
    }

    public void settingResponse(HashMap<String, String> appsettingreq, MutableLiveData<OperationState<BDG_AppSettingResponse>> liveData) {
        liveData.postValue(OperationState.loading());
        retrofit.AppSettingbdg(appsettingreq, Project_TOKEN).enqueue(new Callback<BDG_AppSettingResponse>() {
            @Override
            public void onResponse(Call<BDG_AppSettingResponse> call, Response<BDG_AppSettingResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    liveData.postValue(OperationState.success(response.body()));
                } else {
                    liveData.postValue(OperationState.failure("Response error or empty"));
                }
            }

            @Override
            public void onFailure(Call<BDG_AppSettingResponse> call, Throwable t) {
                liveData.postValue(OperationState.failure(t.getMessage() != null ? t.getMessage() : "Unknown error"));
            }
        });
    }

    public void Login(Logingreq logingreq, MutableLiveData<OperationState<BDG_LoginResponse>> liveData) {
        liveData.postValue(OperationState.loading());
        retrofit.LoginApibdg(logingreq, Project_TOKEN).enqueue(new Callback<BDG_LoginResponse>() {

            @Override
            public void onResponse(Call<BDG_LoginResponse> call, Response<BDG_LoginResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    liveData.postValue(OperationState.success(response.body()));
                } else {
                    liveData.postValue(OperationState.failure("Response error or empty"));
                }
            }

            @Override
            public void onFailure(Call<BDG_LoginResponse> call, Throwable t) {
                liveData.postValue(OperationState.failure(t.getMessage() != null ? t.getMessage() : "Unknown error"));
            }
        });
    }

    public void registerApibdg(Registerreq logingreq, MutableLiveData<OperationState<BDG_SignupModelClass>> liveData) {
        liveData.postValue(OperationState.loading());
        retrofit.registerApibdg(logingreq, Project_TOKEN).enqueue(new Callback<BDG_SignupModelClass>() {

            @Override
            public void onResponse(Call<BDG_SignupModelClass> call, Response<BDG_SignupModelClass> response) {
                if (response.isSuccessful() && response.body() != null) {
                    liveData.postValue(OperationState.success(response.body()));
                } else {
                    liveData.postValue(OperationState.failure("Response error or empty"));
                }
            }

            @Override
            public void onFailure(Call<BDG_SignupModelClass> call, Throwable t) {
                liveData.postValue(OperationState.failure(t.getMessage() != null ? t.getMessage() : "Unknown error"));
            }
        });
    }

    public void forgetApibdg(Forgetreq logoutreq, MutableLiveData<OperationState<BDG_LogOutResponse>> liveData) {
        liveData.postValue(OperationState.loading());
        retrofit.forgetApibdg(logoutreq, Project_TOKEN).enqueue(new Callback<BDG_LogOutResponse>() {

            @Override
            public void onResponse(Call<BDG_LogOutResponse> call, Response<BDG_LogOutResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    liveData.postValue(OperationState.success(response.body()));
                } else {
                    liveData.postValue(OperationState.failure("Response error or empty"));
                }
            }

            @Override
            public void onFailure(Call<BDG_LogOutResponse> call, Throwable t) {
                liveData.postValue(OperationState.failure(t.getMessage() != null ? t.getMessage() : "Unknown error"));
            }
        });
    }

    public void userinfoApibdg(GetUserinforeq getUSerinfo, MutableLiveData<OperationState<BDG_UserInfoResponse>> liveData) {
        liveData.postValue(OperationState.loading());
        retrofit.GetUserInfoApibdg(getUSerinfo, Project_TOKEN).enqueue(new Callback<BDG_UserInfoResponse>() {

            @Override
            public void onResponse(Call<BDG_UserInfoResponse> call, Response<BDG_UserInfoResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    liveData.postValue(OperationState.success(response.body()));
                } else {
                    liveData.postValue(OperationState.failure("Response error or empty"));
                }
            }

            @Override
            public void onFailure(Call<BDG_UserInfoResponse> call, Throwable t) {
                liveData.postValue(OperationState.failure(t.getMessage() != null ? t.getMessage() : "Unknown error"));
            }
        });
    }

    public void Allplanapi(Allplanreq allplan, MutableLiveData<OperationState<BDG_PlanResponse>> liveData) {
        liveData.postValue(OperationState.loading());
        retrofit.AllplanApibdg(allplan, Project_TOKEN).enqueue(new Callback<BDG_PlanResponse>() {

            @Override
            public void onResponse(Call<BDG_PlanResponse> call, Response<BDG_PlanResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    liveData.postValue(OperationState.success(response.body()));
                } else {
                    liveData.postValue(OperationState.failure("Response error or empty"));
                }
            }

            @Override
            public void onFailure(Call<BDG_PlanResponse> call, Throwable t) {
                liveData.postValue(OperationState.failure(t.getMessage() != null ? t.getMessage() : "Unknown error"));
            }
        });
    }

    public void Getplanapi(GetPlanreq getplan, MutableLiveData<OperationState<BDG_GetPlanResponse>> liveData) {
        liveData.postValue(OperationState.loading());
        retrofit.GetPlanApibdg(getplan, Project_TOKEN).enqueue(new Callback<BDG_GetPlanResponse>() {

            @Override
            public void onResponse(Call<BDG_GetPlanResponse> call, Response<BDG_GetPlanResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    liveData.postValue(OperationState.success(response.body()));
                } else {
                    liveData.postValue(OperationState.failure("Response error or empty"));
                }
            }

            @Override
            public void onFailure(Call<BDG_GetPlanResponse> call, Throwable t) {
                liveData.postValue(OperationState.failure(t.getMessage() != null ? t.getMessage() : "Unknown error"));
            }
        });
    }

    public void Getlogoutapi(GetUserinforeq forgetreq, MutableLiveData<OperationState<BDG_LogOutResponse>> liveData) {
        liveData.postValue(OperationState.loading());
        retrofit.LogoutApibdg(forgetreq, Project_TOKEN).enqueue(new Callback<BDG_LogOutResponse>() {

            @Override
            public void onResponse(Call<BDG_LogOutResponse> call, Response<BDG_LogOutResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    liveData.postValue(OperationState.success(response.body()));
                } else {
                    liveData.postValue(OperationState.failure("Response error or empty"));
                }
            }

            @Override
            public void onFailure(Call<BDG_LogOutResponse> call, Throwable t) {
                liveData.postValue(OperationState.failure(t.getMessage() != null ? t.getMessage() : "Unknown error"));
            }
        });
    }

    public void Getaddplanapi(Addplanreq addplanreq, MutableLiveData<OperationState<BDG_AddPlanResponse>> liveData) {
        liveData.postValue(OperationState.loading());
        retrofit.AddplanApibdg(addplanreq, Project_TOKEN).enqueue(new Callback<BDG_AddPlanResponse>() {

            @Override
            public void onResponse(Call<BDG_AddPlanResponse> call, Response<BDG_AddPlanResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    liveData.postValue(OperationState.success(response.body()));
                } else {
                    liveData.postValue(OperationState.failure("Response error or empty"));
                }
            }

            @Override
            public void onFailure(Call<BDG_AddPlanResponse> call, Throwable t) {
                liveData.postValue(OperationState.failure(t.getMessage() != null ? t.getMessage() : "Unknown error"));
            }
        });
    }

    public void Getminighistoryapi(MiningHistoryreq miningreq, MutableLiveData<OperationState<BDG_MiningHistroyResponse>> liveData) {
        liveData.postValue(OperationState.loading());
        retrofit.MiningHistoryApibdg(miningreq, Project_TOKEN).enqueue(new Callback<BDG_MiningHistroyResponse>() {

            @Override
            public void onResponse(Call<BDG_MiningHistroyResponse> call, Response<BDG_MiningHistroyResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    liveData.postValue(OperationState.success(response.body()));
                } else {
                    liveData.postValue(OperationState.failure("Response error or empty"));
                }
            }

            @Override
            public void onFailure(Call<BDG_MiningHistroyResponse> call, Throwable t) {
                liveData.postValue(OperationState.failure(t.getMessage() != null ? t.getMessage() : "Unknown error"));
            }
        });
    }

    public void GetWithdrawalapi(Withdrawalreq withdrawalreq, MutableLiveData<OperationState<BDG_WithdrawalResponse>> liveData) {
        liveData.postValue(OperationState.loading());
        retrofit.WithdrawalApibdg(withdrawalreq, Project_TOKEN).enqueue(new Callback<BDG_WithdrawalResponse>() {

            @Override
            public void onResponse(Call<BDG_WithdrawalResponse> call, Response<BDG_WithdrawalResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    liveData.postValue(OperationState.success(response.body()));
                } else {
                    liveData.postValue(OperationState.failure("Response error or empty"));
                }
            }

            @Override
            public void onFailure(Call<BDG_WithdrawalResponse> call, Throwable t) {
                liveData.postValue(OperationState.failure(t.getMessage() != null ? t.getMessage() : "Unknown error"));
            }
        });
    }

    public void GetWithdrawalhistoryapi(WithdrawalHistoryReq withdrawalreq, MutableLiveData<OperationState<BDG_WithdrawalHistoryResponse>> liveData) {
        liveData.postValue(OperationState.loading());
        retrofit.WithdrawalHistoryApibdg(withdrawalreq, Project_TOKEN).enqueue(new Callback<BDG_WithdrawalHistoryResponse>() {

            @Override
            public void onResponse(Call<BDG_WithdrawalHistoryResponse> call, Response<BDG_WithdrawalHistoryResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    liveData.postValue(OperationState.success(response.body()));
                } else {
                    liveData.postValue(OperationState.failure("Response error or empty"));
                }
            }

            @Override
            public void onFailure(Call<BDG_WithdrawalHistoryResponse> call, Throwable t) {
                liveData.postValue(OperationState.failure(t.getMessage() != null ? t.getMessage() : "Unknown error"));
            }
        });
    }

    public void GetUsereditApibdg(Getusereditreq geteditinfo, MutableLiveData<OperationState<BDG_UserInfoEditResponse>> liveData) {
        liveData.postValue(OperationState.loading());
        retrofit.GetUsereditApibdg(geteditinfo, Project_TOKEN).enqueue(new Callback<BDG_UserInfoEditResponse>() {

            @Override
            public void onResponse(Call<BDG_UserInfoEditResponse> call, Response<BDG_UserInfoEditResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    liveData.postValue(OperationState.success(response.body()));
                } else {
                    liveData.postValue(OperationState.failure("Response error or empty"));
                }
            }

            @Override
            public void onFailure(Call<BDG_UserInfoEditResponse> call, Throwable t) {
                liveData.postValue(OperationState.failure(t.getMessage() != null ? t.getMessage() : "Unknown error"));
            }
        });
    }

    public void getreferhistoryApibdg(Getreferhistoryreq getreferhistory, MutableLiveData<OperationState<BDG_ReferralsHistoryResponse>> liveData) {
        liveData.postValue(OperationState.loading());
        retrofit.GetreferhistoryApibdg(getreferhistory, Project_TOKEN).enqueue(new Callback<BDG_ReferralsHistoryResponse>() {

            @Override
            public void onResponse(Call<BDG_ReferralsHistoryResponse> call, Response<BDG_ReferralsHistoryResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    liveData.postValue(OperationState.success(response.body()));
                } else {
                    liveData.postValue(OperationState.failure("Response error or empty"));
                }
            }

            @Override
            public void onFailure(Call<BDG_ReferralsHistoryResponse> call, Throwable t) {
                liveData.postValue(OperationState.failure(t.getMessage() != null ? t.getMessage() : "Unknown error"));
            }
        });
    }


    public void GetuserApibdg(GetUserreq getuserreq, MutableLiveData<OperationState<BDG_GetAllUserResponse>> liveData) {
        liveData.postValue(OperationState.loading());
        retrofit.GetuserApibdg(getuserreq, Project_TOKEN).enqueue(new Callback<BDG_GetAllUserResponse>() {

            @Override
            public void onResponse(Call<BDG_GetAllUserResponse> call, Response<BDG_GetAllUserResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    liveData.postValue(OperationState.success(response.body()));
                } else {
                    liveData.postValue(OperationState.failure("Response error or empty"));
                }
            }

            @Override
            public void onFailure(Call<BDG_GetAllUserResponse> call, Throwable t) {
                liveData.postValue(OperationState.failure(t.getMessage() != null ? t.getMessage() : "Unknown error"));
            }
        });
    }
    public void GecountryApibdg(Countryreq countryreq, MutableLiveData<OperationState<BDG_CountryResponse>> liveData) {
        liveData.postValue(OperationState.loading());
        retrofit.GetcountryApibdg(countryreq,Project_TOKEN).enqueue(new Callback<BDG_CountryResponse>() {

            @Override
            public void onResponse(Call<BDG_CountryResponse> call, Response<BDG_CountryResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    liveData.postValue(OperationState.success(response.body()));
                } else {
                    liveData.postValue(OperationState.failure("Response error or empty"));
                }
            }

            @Override
            public void onFailure(Call<BDG_CountryResponse> call, Throwable t) {
                liveData.postValue(OperationState.failure(t.getMessage() != null ? t.getMessage() : "Unknown error"));
            }
        });
    }


    public void GetDeleteApibdg(Deletereq deletereq, MutableLiveData<OperationState<BDG_DeleteUserResponse>> liveData) {
        liveData.postValue(OperationState.loading());
        retrofit.DeleteApibdg(deletereq,Project_TOKEN).enqueue(new Callback<BDG_DeleteUserResponse>() {

            @Override
            public void onResponse(Call<BDG_DeleteUserResponse> call, Response<BDG_DeleteUserResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    liveData.postValue(OperationState.success(response.body()));
                } else {
                    liveData.postValue(OperationState.failure("Response error or empty"));
                }
            }

            @Override
            public void onFailure(Call<BDG_DeleteUserResponse> call, Throwable t) {
                liveData.postValue(OperationState.failure(t.getMessage() != null ? t.getMessage() : "Unknown error"));
            }
        });
    }

}
