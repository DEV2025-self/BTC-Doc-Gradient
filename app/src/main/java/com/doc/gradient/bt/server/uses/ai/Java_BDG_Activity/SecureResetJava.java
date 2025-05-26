package com.doc.gradient.bt.server.uses.ai.Java_BDG_Activity;

import static com.doc.gradient.bt.server.uses.ai.Java_BDG_CommonDocUtils.ProjectConstantsJava.Project_APP_ID;

import android.util.Patterns;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import androidx.lifecycle.ViewModelProvider;

import com.doc.gradient.bt.server.uses.ai.Java_BDG_ApiDataGenerator.MainViewModel.AppStateViewModel;
import com.doc.gradient.bt.server.uses.ai.Java_BDG_MainBase.BaseCoreParentJava;
import com.doc.gradient.bt.server.uses.ai.Java_BDG_Responce_Class.BDG_Logout.Forgetreq;
import com.doc.gradient.bt.server.uses.ai.R;
import com.doc.gradient.bt.server.uses.ai.databinding.ActivitySecureResetBinding;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class SecureResetJava extends BaseCoreParentJava<ActivitySecureResetBinding> {

    private AppStateViewModel ResetvModel;

    @Override
    protected ActivitySecureResetBinding DataBridge() {
        return ActivitySecureResetBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void NetBridge() {

    }

    @Override
    protected void RecallBindingProcess() {
        ResetvModel = new ViewModelProvider(this).get(AppStateViewModel.class);
        AppStatusBarjava(this); // Assuming AppStatusBar is a method that modifies status bar

        allbindData.loader.loop(true);
        allbindData.loader.setAnimation(R.raw.loader);
        allbindData.loader.playAnimation();

        allbindData.textsigbninbdg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        allbindData.btnsubmitbdg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = allbindData.Emailidedittext.getText().toString().trim();
                if (email.isEmpty()) {
                    bothsnackBars(allbindData.getRoot(), "Enter email to reset password", 1);
                } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    bothsnackBars(allbindData.getRoot(), "Enter Valid Email", 1);
                } else {
                    SendLink(email);
                }
            }
        });

    }

    @Override
    protected void ModelSyncObserver() {
        ResetvModel.getforgetResponse().observe(this, result -> {
            if (result == null)
                return;
            switch (result.status) {
                case RAHJOVO:

                    break;
                case MALYU:

                    if (result.data != null && Boolean.TRUE.equals(result.data.getStatus())) {
                        try {
                            if (result.data.getMessage() != null) {
                                if ("User not found.".equals(result.data.getMessage())) {
                                    userDeleted(this);
                                    return;
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }


                        try {
                            bothsnackBars(allbindData.getRoot(), result.data.getMessage(), 2);
                            allbindData.loader.setVisibility(View.GONE);
                            allbindData.btnsubmitbdg.setVisibility(View.VISIBLE);
                            allbindData.Emailidedittext.setText(null);
                        } catch (Exception e) {
                            bothsnackBars(allbindData.getRoot(), "Check Email In Spam List.Password Reset Link sent.", 2);
                            allbindData.loader.setVisibility(View.GONE);
                            allbindData.btnsubmitbdg.setVisibility(View.VISIBLE);
                        }

                    } else {
                        try {
                            allbindData.loader.setVisibility(View.GONE);
                            allbindData.btnsubmitbdg.setVisibility(View.VISIBLE);
                            bothsnackBars(allbindData.getRoot(), "Something went wrong please try again", 1);
                        } catch (Exception e) {
                            bothsnackBars(allbindData.getRoot(), "Something went wrong please try again", 1);
                        }
                    }

                    break;

                case NOMALYU:

                    allbindData.loader.setVisibility(View.GONE);
                    allbindData.btnsubmitbdg.setVisibility(View.VISIBLE);
                    bothsnackBars(allbindData.getRoot(), "Something went wrong please try again", 1);

                    break;
            }
        });
    }

    @Override
    protected void viewAds() {

    }

    private void SendLink(String email) {
        View view = getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            if (imm != null) {
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        }
        allbindData.btnsubmitbdg.setVisibility(View.GONE);
        allbindData.loader.setVisibility(View.VISIBLE);

        ResetvModel.forgetApibdg(new Forgetreq(email, Project_APP_ID)); // Assuming viewModel is already initialized
    }
}
