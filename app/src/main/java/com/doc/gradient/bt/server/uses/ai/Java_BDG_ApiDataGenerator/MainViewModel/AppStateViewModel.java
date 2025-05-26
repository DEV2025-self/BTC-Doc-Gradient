package com.doc.gradient.bt.server.uses.ai.Java_BDG_ApiDataGenerator.MainViewModel;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.doc.gradient.bt.server.uses.ai.Java_BDG_ApiDataGenerator.Network_Result.OperationState;
import com.doc.gradient.bt.server.uses.ai.Java_BDG_ApiDataGenerator.Repository.AppStateRepository;
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

import dagger.hilt.android.lifecycle.HiltViewModel;


@HiltViewModel
public class AppStateViewModel extends ViewModel {

    private final MutableLiveData<OperationState<BDG_LoginResponse>> _loginResponse = new MutableLiveData<>();
    private final MutableLiveData<OperationState<BDG_SignupModelClass>> _registerResponse = new MutableLiveData<>();
    private final MutableLiveData<OperationState<BDG_LogOutResponse>> _LogOutResponse = new MutableLiveData<>();
    private final MutableLiveData<OperationState<BDG_UserInfoResponse>> _UserInfoResponse = new MutableLiveData<>();
    private final MutableLiveData<OperationState<BDG_PlanResponse>> _PlanResponse = new MutableLiveData<>();
    private final MutableLiveData<OperationState<BDG_GetPlanResponse>> _GetPlanResponse = new MutableLiveData<>();
    private final MutableLiveData<OperationState<BDG_LogOutResponse>> _GetlogoutResponse = new MutableLiveData<>();
    private final MutableLiveData<OperationState<BDG_AddPlanResponse>> _GetaddplanResponse = new MutableLiveData<>();
    private final MutableLiveData<OperationState<BDG_MiningHistroyResponse>> _GetMininghistoryResponse = new MutableLiveData<>();
    private final MutableLiveData<OperationState<BDG_WithdrawalResponse>> _GetWithdrawalResponse = new MutableLiveData<>();
    private final MutableLiveData<OperationState<BDG_WithdrawalHistoryResponse>> _GetWithdrawalhistoryResponse = new MutableLiveData<>();
    private final MutableLiveData<OperationState<BDG_UserInfoEditResponse>> _GetUserInfoEditResponse = new MutableLiveData<>();
    private final MutableLiveData<OperationState<BDG_ReferralsHistoryResponse>> _ReferralsHistoryResponse = new MutableLiveData<>();
    private final MutableLiveData<OperationState<BDG_GetAllUserResponse>> _GetuserApibdgResponse = new MutableLiveData<>();
    private final MutableLiveData<OperationState<BDG_CountryResponse>> _GetcountryApibdg = new MutableLiveData<>();
    private final MutableLiveData<OperationState<BDG_DeleteUserResponse>> _GetDeleteUserApibdg = new MutableLiveData<>();
    private final AppStateRepository repository;
    private final MutableLiveData<OperationState<BDG_AppSettingResponse>> _SettingResponse = new MutableLiveData<>();
    public LiveData<OperationState<BDG_AppSettingResponse>> settingResponse = _SettingResponse;

    @Inject
    public AppStateViewModel(AppStateRepository repository) {
        this.repository = repository;
    }

    public void dataAppSetting(HashMap<String, String> fields) {
        repository.settingResponse(fields, _SettingResponse);
    }

    public LiveData<OperationState<BDG_LoginResponse>> getLoginResponse() {
        return _loginResponse;
    }

    public void loginapi(Logingreq logingreq) {
        repository.Login(logingreq, _loginResponse);
    }

    public LiveData<OperationState<BDG_SignupModelClass>> getRegisterResponse() {
        return _registerResponse;
    }

    public void registerApibdg(Registerreq logingreq) {
        repository.registerApibdg(logingreq, _registerResponse);
    }

    public LiveData<OperationState<BDG_LogOutResponse>> getforgetResponse() {
        return _LogOutResponse;
    }

    public void forgetApibdg(Forgetreq logoutreq) {
        repository.forgetApibdg(logoutreq, _LogOutResponse);
    }

    public LiveData<OperationState<BDG_UserInfoResponse>> getUserinfoResponse() {
        return _UserInfoResponse;
    }

    public void GetUserInfoApibdg(GetUserinforeq getUSerinfo) {
        repository.userinfoApibdg(getUSerinfo, _UserInfoResponse);
    }

    public LiveData<OperationState<BDG_PlanResponse>> getAllplanResponse() {
        return _PlanResponse;
    }

    public void AllplanResponseapi(Allplanreq allplan) {
        repository.Allplanapi(allplan, _PlanResponse);
    }

    public LiveData<OperationState<BDG_GetPlanResponse>> getplanResponse() {
        return _GetPlanResponse;
    }

    public void GetplanResponseapi(GetPlanreq getplan) {
        repository.Getplanapi(getplan, _GetPlanResponse);
    }

    public LiveData<OperationState<BDG_LogOutResponse>> getlogoutResponse() {
        return _GetlogoutResponse;
    }

    public void LogoutResponseapi(GetUserinforeq forgetreq) {
        repository.Getlogoutapi(forgetreq, _GetlogoutResponse);
    }

    public LiveData<OperationState<BDG_AddPlanResponse>> getaddplanResponse() {
        return _GetaddplanResponse;
    }

    public void AddPlanResponseapi(Addplanreq addplanreq) {
        repository.Getaddplanapi(addplanreq, _GetaddplanResponse);
    }

    public LiveData<OperationState<BDG_MiningHistroyResponse>> getMininghistoryResponse() {
        return _GetMininghistoryResponse;
    }

    public void MininghistoryResponseapi(MiningHistoryreq miningreq) {
        repository.Getminighistoryapi(miningreq, _GetMininghistoryResponse);
    }

    public LiveData<OperationState<BDG_WithdrawalResponse>> getWithdrawalResponse() {
        return _GetWithdrawalResponse;
    }

    public void WithdrawalApi(Withdrawalreq withdrawalreq) {
        repository.GetWithdrawalapi(withdrawalreq, _GetWithdrawalResponse);
    }

    public LiveData<OperationState<BDG_WithdrawalHistoryResponse>> getWithdrawalhistoryResponse() {
        return _GetWithdrawalhistoryResponse;
    }

    public void WithdrawalHistoryApi(WithdrawalHistoryReq  withdrawalreq) {
        repository.GetWithdrawalhistoryapi(withdrawalreq, _GetWithdrawalhistoryResponse);
    }

    public LiveData<OperationState<BDG_UserInfoEditResponse>> getUserInfoEditResponse() {
        return _GetUserInfoEditResponse;
    }

    public void UserInfoEditApi(Getusereditreq geteditinfo) {
        repository.GetUsereditApibdg(geteditinfo, _GetUserInfoEditResponse);
    }

    public LiveData<OperationState<BDG_ReferralsHistoryResponse>> GetreferhistoryApibdg() {
        return _ReferralsHistoryResponse;
    }

    public void GetreferhistoryApibdg(Getreferhistoryreq getreferhistory) {
        repository.getreferhistoryApibdg(getreferhistory, _ReferralsHistoryResponse);
    }

    public LiveData<OperationState<BDG_GetAllUserResponse>> getuserresponse() {
        return _GetuserApibdgResponse;
    }

    public void GetuserApibdg(GetUserreq getuserreq) {
        repository.GetuserApibdg(getuserreq, _GetuserApibdgResponse);
    }

    public LiveData<OperationState<BDG_CountryResponse>> GetcountryResponsebdg() {
        return _GetcountryApibdg;
    }

    public void GetcountryApibdg(Countryreq countryreq) {
        repository.GecountryApibdg(countryreq,_GetcountryApibdg);
    }

    public LiveData<OperationState<BDG_DeleteUserResponse>> GetDeleteUserbdg() {
        return _GetDeleteUserApibdg;
    }

    public void GetDeleteUserApibdg(Deletereq deletereq) {
        repository.GetDeleteApibdg(deletereq,_GetDeleteUserApibdg);
    }

}