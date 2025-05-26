package com.doc.gradient.bt.server.uses.ai.Java_BDG_Activity;

import static com.doc.gradient.bt.server.uses.ai.Java_BDG_CommonDocUtils.ProjectConstantsJava.Project_APP_ID;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import androidx.lifecycle.ViewModelProvider;

import com.doc.gradient.bt.server.uses.ai.Java_BDG_ApiDataGenerator.MainViewModel.AppStateViewModel;
import com.doc.gradient.bt.server.uses.ai.Java_BDG_MainBase.BaseCoreParentJava;
import com.doc.gradient.bt.server.uses.ai.Java_BDG_Responce_Class.BDG_Login.Logingreq;
import com.doc.gradient.bt.server.uses.ai.Java_BDG_Sessionary.SessionaryJava;
import com.doc.gradient.bt.server.uses.ai.R;
import com.doc.gradient.bt.server.uses.ai.databinding.ActivitySecureLoginBinding;
import com.google.firebase.crashlytics.FirebaseCrashlytics;
import com.google.firebase.messaging.FirebaseMessaging;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class SecureLoginJava extends BaseCoreParentJava<ActivitySecureLoginBinding> {
    private String device_token = "";
    private AppStateViewModel LoginvModel;
    private boolean userDoubleBackToExitPressedOnce = false;

    @Override
    protected ActivitySecureLoginBinding DataBridge() {
        return ActivitySecureLoginBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void NetBridge() {

    }

    @Override
    protected void RecallBindingProcess() {
        LoginvModel = new ViewModelProvider(this).get(AppStateViewModel.class);
        AppStatusBarjava(this);

        try {
            FirebaseMessaging.getInstance().getToken()
                    .addOnSuccessListener(token -> {
                        if (token != null && !token.isEmpty()) {
                            device_token = token;
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }

        allbindData.loader.loop(true);
        allbindData.loader.setAnimation(R.raw.loader);
        allbindData.loader.playAnimation();

        allbindData.btnsignin.setOnClickListener(view -> {
            login();
        });

        allbindData.textsignup.setOnClickListener(view -> {
            startActivity(new Intent(SecureLoginJava.this, SecureSignUpJava.class));
        });

        allbindData.textForgotPassword.setOnClickListener(view -> {
            startActivity(new Intent(SecureLoginJava.this, SecureResetJava.class));
        });

        allbindData.closeeyepassword.setOnClickListener(view -> {
            if (allbindData.Passwordedittext.getTransformationMethod().equals(
                    PasswordTransformationMethod.getInstance()
            )) {
                allbindData.closeeyepassword.setImageResource(R.drawable.ic_eye);
                // Show Password
                allbindData.Passwordedittext.setTransformationMethod(
                        HideReturnsTransformationMethod.getInstance());
                allbindData.Passwordedittext.setSelection(allbindData.Passwordedittext.length());
            } else {
                allbindData.closeeyepassword.setImageResource(R.drawable.ic_eye_slash);
                // Hide Password
                allbindData.Passwordedittext.setTransformationMethod(
                        PasswordTransformationMethod.getInstance());
                allbindData.Passwordedittext.setSelection(allbindData.Passwordedittext.length());
            }
        });
    }

    @Override
    protected void ModelSyncObserver() {

        LoginvModel.getLoginResponse().observe(this, result -> {
            if (result == null)
                return;
            switch (result.status) {
                case RAHJOVO:
                    allbindData.loader.setVisibility(View.VISIBLE);
                    allbindData.btnsignin.setVisibility(View.GONE);
                    break;
                case MALYU:
                    allbindData.btnsignin.setVisibility(View.VISIBLE);
                    allbindData.loader.setVisibility(View.GONE);

                    if (result.data.getData() != null && result.data.getData() != null && Boolean.TRUE.equals(result.data.getStatus())) {
                        SessionaryJava.getSessionaryinstance().SetLoginApi(result.data);
                        SessionaryJava.getSessionaryinstance().setUserLogOut(false);
                        GotoNextProcess();
                        Toast.makeText(SecureLoginJava.this, "Login SuccessFully", Toast.LENGTH_SHORT).show();
                    } else {
                        FirebaseCrashlytics.getInstance().recordException(new Exception("Login Response Getting Null"));
                        try {
                            bothsnackBars(allbindData.getRoot(), result.data.getMessage(), 1);
                        } catch (Exception e) {
                            bothsnackBars(allbindData.getRoot(), "Something went wrong please try again", 1);
                        }
                    }
                    break;

                case NOMALYU:
                    allbindData.loader.setVisibility(View.GONE);
                    allbindData.btnsignin.setVisibility(View.VISIBLE);
                    bothsnackBars(allbindData.getRoot(), "Something went wrong please try again", 1);
                    break;
            }
        });
    }

    @Override
    protected void viewAds() {

    }

    private void login() {
        String email = allbindData.Emailidedittext.getText().toString();
        String password = allbindData.Passwordedittext.getText().toString();

        if (email.trim().isEmpty() && password.trim().isEmpty()) {
            bothsnackBars(allbindData.getRoot(), "Enter details to login", 1);
            return;
        }
        if (email.trim().isEmpty()) {
            bothsnackBars(allbindData.getRoot(), "Enter email to login", 1);
            return;
        }
        if (password.trim().isEmpty()) {
            bothsnackBars(allbindData.getRoot(), "Enter your password", 1);
        } else {
            View view = SecureLoginJava.this.getCurrentFocus();
            if (view != null) {
                InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                if (imm != null) {
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }
            }
            allbindData.loader.setVisibility(View.VISIBLE);
            allbindData.btnsignin.setVisibility(View.GONE);
            LoginvModel.loginapi(new Logingreq(email, password, device_token, Project_APP_ID));
        }
    }

    private void GotoNextProcess() {
        Intent intent = new Intent(SecureLoginJava.this, StartDashboardAnalytics.class);
        startActivity(intent);
        finishAffinity();
    }

    @Override
    public void onBackPressed() {

        if (userDoubleBackToExitPressedOnce) {
            super.onBackPressed();
            finishAffinity();
            return;
        }

        userDoubleBackToExitPressedOnce = true;

        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                userDoubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }
}
