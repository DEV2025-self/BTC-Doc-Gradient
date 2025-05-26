package com.doc.gradient.bt.server.uses.ai.Java_BDG_Activity;

import static com.doc.gradient.bt.server.uses.ai.Java_BDG_CommonDocUtils.ProjectConstantsJava.Project_APP_ID;

import android.content.Intent;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import androidx.lifecycle.ViewModelProvider;

import com.doc.gradient.bt.server.uses.ai.Java_BDG_ApiDataGenerator.MainViewModel.AppStateViewModel;
import com.doc.gradient.bt.server.uses.ai.Java_BDG_MainBase.BaseCoreParentJava;
import com.doc.gradient.bt.server.uses.ai.Java_BDG_Responce_Class.BDG_Signup.Registerreq;
import com.doc.gradient.bt.server.uses.ai.Java_BDG_Sessionary.SessionaryJava;
import com.doc.gradient.bt.server.uses.ai.R;
import com.doc.gradient.bt.server.uses.ai.databinding.ActivitySecureSignUpBinding;
import com.google.firebase.crashlytics.FirebaseCrashlytics;
import com.yesterselga.countrypicker.CountryPicker;
import com.yesterselga.countrypicker.Theme;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class SecureSignUpJava extends BaseCoreParentJava<ActivitySecureSignUpBinding> {
    private AppStateViewModel SignUpvModel;

    @Override
    protected ActivitySecureSignUpBinding DataBridge() {
        return ActivitySecureSignUpBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void NetBridge() {

    }

    @Override
    protected void RecallBindingProcess() {
        SignUpvModel = new ViewModelProvider(this).get(AppStateViewModel.class);
        AppStatusBarjava(this);

        allbindData.loader.loop(true);
        allbindData.loader.setAnimation(R.raw.loader);
        allbindData.loader.playAnimation();

        allbindData.privacypolicy.setOnClickListener(v -> {
            Intent intentx = new Intent(SecureSignUpJava.this, SafeDataInfo.class);
            intentx.putExtra("Flag", "Privacy");
            startActivity(intentx);
        });

        allbindData.termconditions.setOnClickListener(v -> {
            Intent intenty = new Intent(SecureSignUpJava.this, SafeDataInfo.class);
            intenty.putExtra("Flag", "Condition");
            startActivity(intenty);
        });

        allbindData.textSignin.setOnClickListener(v -> {
            startActivity(new Intent(SecureSignUpJava.this, SecureLoginJava.class));
            finish();
        });

        allbindData.closeeyepassword.setOnClickListener(v -> {
            if (allbindData.Passwordedittext.getTransformationMethod().equals(PasswordTransformationMethod.getInstance())) {
                allbindData.closeeyepassword.setImageResource(R.drawable.ic_eye);
                allbindData.Passwordedittext.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                allbindData.Passwordedittext.setSelection(allbindData.Passwordedittext.getText().length());
            } else {
                allbindData.closeeyepassword.setImageResource(R.drawable.ic_eye_slash);
                allbindData.Passwordedittext.setTransformationMethod(PasswordTransformationMethod.getInstance());
                allbindData.Passwordedittext.setSelection(allbindData.Passwordedittext.getText().length());
            }
        });

        allbindData.iceyeconfirmpass.setOnClickListener(v -> {
            if (allbindData.ConfirmpasswordInput.getTransformationMethod().equals(PasswordTransformationMethod.getInstance())) {
                allbindData.iceyeconfirmpass.setImageResource(R.drawable.ic_eye);
                allbindData.ConfirmpasswordInput.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                allbindData.ConfirmpasswordInput.setSelection(allbindData.ConfirmpasswordInput.getText().length());
            } else {
                allbindData.iceyeconfirmpass.setImageResource(R.drawable.ic_eye_slash);
                allbindData.ConfirmpasswordInput.setTransformationMethod(PasswordTransformationMethod.getInstance());
                allbindData.ConfirmpasswordInput.setSelection(allbindData.ConfirmpasswordInput.getText().length());
            }
        });

        allbindData.btnsignup.setOnClickListener(v -> {
            register();
        });

        allbindData.countrylay.setOnClickListener(v -> {
            try {
                CountryPicker picker = CountryPicker.newInstance("Select Country", Theme.DARK);
                picker.setListener((name, code, dialCode, flagDrawableResID) -> {
                    allbindData.Countrytext.setText(name);
                    picker.dismiss();
                });
                picker.show(getSupportFragmentManager(), "COUNTRY_PICKER");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

    }

    @Override
    protected void ModelSyncObserver() {
        SignUpvModel.getRegisterResponse().observe(this, result -> {
            if (result == null)
                return;
            switch (result.status) {
                case RAHJOVO:
                    allbindData.loader.setVisibility(View.VISIBLE);
                    allbindData.btnsignup.setVisibility(View.GONE);
                    break;
                case MALYU:
                    allbindData.loader.setVisibility(View.GONE);
                    allbindData.btnsignup.setVisibility(View.VISIBLE);

                    if (result.data.getData() != null && result.data.getData() != null && Boolean.TRUE.equals(result.data.getStatus())) {
                        SessionaryJava.getSessionaryinstance().SetSignUP(result.data);

                        Toast.makeText(SecureSignUpJava.this, "Account Created Successfully", Toast.LENGTH_SHORT).show();
                        goback();

                        allbindData.ReferralCodeInput.setText(null);
                        allbindData.FullNameedittext.setText(null);
                        allbindData.Countrytext.setText(null);
                        allbindData.Emailidedittext.setText(null);
                        allbindData.Passwordedittext.setText(null);
                        allbindData.ConfirmpasswordInput.setText(null);

                    } else {
                        FirebaseCrashlytics.getInstance().recordException(new Exception("SignUp Response Getting Null"));
                        try {
                            bothsnackBars(allbindData.getRoot(), result.data.getMessage(), 1);
                        } catch (Exception e) {
                            bothsnackBars(allbindData.getRoot(), "Something went wrong please try again", 1);
                        }
                    }
                    break;

                case NOMALYU:
                    allbindData.loader.setVisibility(View.GONE);
                    allbindData.btnsignup.setVisibility(View.VISIBLE);
                    bothsnackBars(allbindData.getRoot(), "Something went wrong please try again", 1);
                    break;
            }
        });
    }

    @Override
    protected void viewAds() {

    }


    private void register() {
        String fullname = allbindData.FullNameedittext.getText().toString();
        String email = allbindData.Emailidedittext.getText().toString();
        String country = allbindData.Countrytext.getText().toString();
        String password = allbindData.Passwordedittext.getText().toString();
        String confirmpassword = allbindData.ConfirmpasswordInput.getText().toString();
        String referralcode = allbindData.ReferralCodeInput.getText().toString();

        if (fullname.trim().isEmpty()) {
            bothsnackBars(allbindData.getRoot(), "Enter your full name", 1);
            return;
        }
        if (email.trim().isEmpty()) {
            bothsnackBars(allbindData.getRoot(), "Please Enter email to create account", 1);
            return;
        }
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            bothsnackBars(allbindData.getRoot(), "Please Enter Valid email to create account", 1);
            return;
        }
        if (country.trim().isEmpty()) {
            bothsnackBars(allbindData.getRoot(), "Enter your country", 1);
            return;
        }
        if (password.trim().isEmpty()) {
            bothsnackBars(allbindData.getRoot(), "Enter your password", 1);
            return;
        }
        if (confirmpassword.trim().isEmpty()) {
            bothsnackBars(allbindData.getRoot(), "Enter your Confirm password", 1);
            return;
        }
        if (!confirmpassword.trim().equals(password.trim())) {
            bothsnackBars(allbindData.getRoot(), "Password And Confirm password are not equal.", 1);
            return;
        }
        if (!allbindData.btnCheck.isChecked()) {
            bothsnackBars(allbindData.getRoot(), "Please Agree To The Terms And Conditions", 1);
        } else {
            View view = SecureSignUpJava.this.getCurrentFocus();
            if (view != null) {
                InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                if (imm != null) {
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }
            }
            allbindData.btnsignup.setVisibility(View.GONE);
            allbindData.loader.setVisibility(View.VISIBLE);

            SignUpvModel.registerApibdg(new Registerreq(
                    fullname,
                    email,
                    password,
                    confirmpassword,
                    country,
                    referralcode,
                    Project_APP_ID)
            );
        }
    }

    private void goback() {
        Intent intent = new Intent(SecureSignUpJava.this, SecureLoginJava.class);
        startActivity(intent);
        finishAffinity();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
