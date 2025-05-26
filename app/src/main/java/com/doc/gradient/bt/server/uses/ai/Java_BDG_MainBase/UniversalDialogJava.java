package com.doc.gradient.bt.server.uses.ai.Java_BDG_MainBase;

import static com.doc.gradient.bt.server.uses.ai.Java_BDG_MainBase.BaseCoreParentJava.STATUS_ACCOUNT_REMOVED;
import static com.doc.gradient.bt.server.uses.ai.Java_BDG_MainBase.BaseCoreParentJava.STATUS_FUNDS_WITHDRAW;
import static com.doc.gradient.bt.server.uses.ai.Java_BDG_MainBase.BaseCoreParentJava.STATUS_ITEM_PURCHASED;
import static com.doc.gradient.bt.server.uses.ai.Java_BDG_MainBase.BaseCoreParentJava.STATUS_OFFLINE_MODE;
import static com.doc.gradient.bt.server.uses.ai.Java_BDG_MainBase.BaseCoreParentJava.STATUS_SESSION_TERMINATED;
import static com.doc.gradient.bt.server.uses.ai.Java_BDG_MainBase.BaseCoreParentJava.STATUS_TRANSACTION_ERROR;
import static com.doc.gradient.bt.server.uses.ai.Java_BDG_MainBase.BaseCoreParentJava.STATUS_TRANSACTION_PENDING;
import static com.doc.gradient.bt.server.uses.ai.Java_BDG_MainBase.BaseCoreParentJava.STATUS_TRANSACTION_SUCCESSFUL;
import static nl.dionsegijn.konfetti.emitters.StreamEmitter.INDEFINITE;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.doc.gradient.bt.server.uses.ai.Java_BDG_Activity.SafeDataInfo;
import com.doc.gradient.bt.server.uses.ai.Java_BDG_Activity.StartDashboardAnalytics;
import com.doc.gradient.bt.server.uses.ai.Java_BDG_CommonDocUtils.UserInteractionStatsJava;
import com.doc.gradient.bt.server.uses.ai.R;
import com.doc.gradient.bt.server.uses.ai.databinding.UniversalDialogBinding;

import nl.dionsegijn.konfetti.models.Shape;
import nl.dionsegijn.konfetti.models.Size;


public class UniversalDialogJava extends DialogFragment {

    private final int value;
    private final Context context;
    private final DialogDecisionCallback decisionCallback;
    private UniversalDialogBinding bindData;

    public UniversalDialogJava(int val1, Context context, DialogDecisionCallback callback) {
        this.value = val1;
        this.context = context;
        this.decisionCallback = callback;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        bindData = UniversalDialogBinding.inflate(getLayoutInflater());
        return bindData.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            if (getDialog() != null && getDialog().getWindow() != null) {
                getDialog().getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                getDialog().getWindow().setStatusBarColor(
                        ContextCompat.getColor(requireContext(), R.color.transparent_color));
                getDialog().getWindow().setNavigationBarColor(
                        ContextCompat.getColor(requireContext(), R.color.transparent_color));
            }
        }

        setupUI();
    }

    private void setupUI() {
        if (bindData.layContainer.getViewTreeObserver() != null) {
            bindData.layContainer.getViewTreeObserver().addOnGlobalLayoutListener(() -> {
                YoYo.with(Techniques.ZoomIn)
                        .pivot(Float.MAX_VALUE, Float.MAX_VALUE)
                        .duration(500)
                        .delay(0)
                        .repeat(0)
                        .playOn(bindData.layContainer);
            });
        }

        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            bindData.viewKonfetti.reset();
            bindData.viewKonfetti.build()
                    .addColors(
                            context.getResources().getColor(R.color.color_1),
                            context.getResources().getColor(R.color.color_2),
                            context.getResources().getColor(R.color.color_3),
                            context.getResources().getColor(R.color.color_4)
                    )
                    .setDirection(359.0)
                    .setSpeed(1.0f, 5.0f)
                    .setFadeOutEnabled(true)
                    .setTimeToLive(1500)
                    .addShapes(Shape.Square.INSTANCE, Shape.Circle.INSTANCE)
                    .addSizes(new Size(10, 5.0f))
                    .setPosition(
                            -50.0f,
                            Float.valueOf((bindData.viewKonfetti.getWidth()) + 50.0f),
                            -50.0f,
                            Float.valueOf(-50.0f)
                    )
                    .streamFor(150, INDEFINITE);
        }, 100);

        handleDialogType();
    }

    private void handleDialogType() {
        switch (value) {
            case STATUS_OFFLINE_MODE:
                setupOfflineMode();
                break;
            case STATUS_ITEM_PURCHASED:
                setupItemPurchased();
                break;
            case STATUS_FUNDS_WITHDRAW:
                setupFundsWithdraw();
                break;
            case STATUS_ACCOUNT_REMOVED:
                setupAccountRemoved();
                break;
            case STATUS_SESSION_TERMINATED:
                setupSessionTerminated();
                break;
            case STATUS_TRANSACTION_SUCCESSFUL:
                setupTransactionSuccessful();
                break;
            case STATUS_TRANSACTION_ERROR:
                setupTransactionError();
                break;
            case STATUS_TRANSACTION_PENDING:
                setupTransactionPending();
                break;
        }
    }

    private void setupOfflineMode() {
        try {
            bindData.viewKonfetti.setVisibility(View.GONE);
            bindData.idtxtTitle.setText("No Internet Connection");
            bindData.idimgDialog.setImageResource(R.drawable.ic_no_internet);
            bindData.descriptionOfuniversal.setText(
                    "Your BTC rigs are running on the cloud. For keeping it run, you must have internet connection.");

            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    0,  // Width = 0 so weight controls it
                    LinearLayout.LayoutParams.MATCH_PARENT  // Height remains wrap_content
            );
            layoutParams.weight = 1.6f; // Set weight
            bindData.chaloaagal.setLayoutParams(layoutParams);
            bindData.chaloaagal.setText("Try Again");
            bindData.chaloaagal.setOnClickListener(v -> {
                try {
                    if (getDialog() != null && getDialog().isShowing()) {
                        if (UserInteractionStatsJava.connectedInternet(this.context)) {
                            getDialog().dismiss();
                        } else {
                            Toast.makeText(
                                    context,
                                    "Your Internet Is Not Available, Please try again later",
                                    Toast.LENGTH_SHORT
                            ).show();
                        }
                    }
                } catch (Exception e) {
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setupItemPurchased() {
        try {
            bindData.viewKonfetti.setVisibility(View.VISIBLE);
            bindData.idtxtTitle.setText("Purchase Successful");
            bindData.idimgDialog.setImageResource(R.drawable.ic_purchasecart);
            bindData.descriptionOfuniversal.setText(
                    "Well done! Your payment has been successfully processed, and you can now proceed access and use the features offered by more cloud booster.");

            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    0,  // Width = 0 so weight controls it
                    LinearLayout.LayoutParams.MATCH_PARENT  // Height remains wrap_content
            );
            layoutParams.weight = 1.6f; // Set weight
            bindData.chaloaagal.setLayoutParams(layoutParams);
            bindData.chaloaagal.setText("Home");
            bindData.chaloaagal.setOnClickListener(v -> {
                try {
                    if (getDialog() != null && getDialog().isShowing()) {
                        getDialog().dismiss();
                        Intent intent = new Intent(getActivity(), StartDashboardAnalytics.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        requireActivity().finish();
                    }
                } catch (Exception e) {
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setupFundsWithdraw() {
        try {
            bindData.viewKonfetti.setVisibility(View.VISIBLE);
            bindData.idtxtTitle.setText("Withdraw Successful");
            bindData.idimgDialog.setImageResource(R.drawable.ic_wallet);
            bindData.descriptionOfuniversal.setText(
                    "Congratulations! The withdrawal amount will be credited to your account within 72 hours.");

            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    0,  // Width = 0 so weight controls it
                    LinearLayout.LayoutParams.MATCH_PARENT  // Height remains wrap_content
            );
            layoutParams.weight = 1.6f;// Set weight
            bindData.chaloaagal.setLayoutParams(layoutParams);
            bindData.chaloaagal.setText("Home");
            bindData.chaloaagal.setOnClickListener(v -> {
                try {
                    if (getDialog() != null && getDialog().isShowing()) {
                        getDialog().dismiss();
                        Intent intent = new Intent(getActivity(), StartDashboardAnalytics.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        requireActivity().finish();
                    }
                } catch (Exception e) {
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setupAccountRemoved() {
        try {
            bindData.tvPolicy.setVisibility(View.VISIBLE);
            bindData.viewKonfetti.setVisibility(View.GONE);
            bindData.idtxtTitle.setText("Delete Account");
            bindData.idimgDialog.setImageResource(R.drawable.ic_delete);
            bindData.descriptionOfuniversal.setText("Are you sure want to delete your account?");
            bindData.chaloaagal.setVisibility(View.VISIBLE);
            bindData.nathijavu.setVisibility(View.VISIBLE);
            bindData.layDelete.setVisibility(View.VISIBLE);
            bindData.chaloaagal.setText("Yes");
            bindData.DeletionPolicy.setVisibility(View.VISIBLE);

            bindData.DeletionPolicy.setOnClickListener(v -> {
                Intent intent = new Intent(requireActivity(), SafeDataInfo.class);
                intent.putExtra("Flag", "DeletePolicy");
                startActivity(intent);
            });

            bindData.nathijavu.setOnClickListener(v -> {
                try {
                    if (getDialog() != null && getDialog().isShowing()) {
                        getDialog().dismiss();
                        decisionCallback.onDecision(false);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

            bindData.chaloaagal.setOnClickListener(v -> {
                if (bindData.btnCheck.isChecked()) {
                    try {
                        if (getDialog() != null && getDialog().isShowing()) {
                            getDialog().dismiss();
                            decisionCallback.onDecision(true);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(
                            requireActivity(), "Please verify check box", Toast.LENGTH_SHORT
                    ).show();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setupSessionTerminated() {
        try {
            bindData.viewKonfetti.setVisibility(View.GONE);
            bindData.idtxtTitle.setText("Logout Account");
            bindData.idimgDialog.setImageResource(R.drawable.ic_logout);
            bindData.descriptionOfuniversal.setText("Are you sure want to logout your account?");
            bindData.layDelete.setVisibility(View.VISIBLE);
            bindData.nathijavu.setVisibility(View.VISIBLE);
            bindData.chaloaagal.setVisibility(View.VISIBLE);

            bindData.nathijavu.setOnClickListener(v -> {
                try {
                    if (getDialog() != null && getDialog().isShowing()) {
                        getDialog().dismiss();
                        decisionCallback.onDecision(false);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

            bindData.chaloaagal.setOnClickListener(v -> {
                try {
                    if (getDialog() != null && getDialog().isShowing()) {
                        getDialog().dismiss();
                        decisionCallback.onDecision(true);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setupTransactionSuccessful() {
        try {
            bindData.viewKonfetti.setVisibility(View.VISIBLE);
            bindData.idtxtTitle.setText("Payment Successful");
            bindData.idtxtTitle.setTextColor(this.getResources().getColor(R.color.green));
            // bindData.idimgDialog.setImageResource(R.drawable.ic_purchase_successful);
            bindData.descriptionOfuniversal.setText("Congratulation! Your payment has been completed successfully.");
            bindData.chaloaagal.setText("Home");

            bindData.chaloaagal.setOnClickListener(v -> {
                try {
                    if (getDialog() != null && getDialog().isShowing()) {
                        getDialog().dismiss();
                        Intent intent = new Intent(getActivity(), StartDashboardAnalytics.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        requireActivity().finish();
                    }
                } catch (Exception e) {
                }
            });

            bindData.DeletionPolicy.setOnClickListener(v -> {
                Intent intent = new Intent(requireActivity(), SafeDataInfo.class);
                intent.putExtra("Flag", "deletionpolicy");
                startActivity(intent);
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setupTransactionError() {
        try {
            bindData.viewKonfetti.setVisibility(View.GONE);
            bindData.idtxtTitle.setText("Payment Failed");
            bindData.idtxtTitle.setTextColor(this.getResources().getColor(R.color.red));
            // bindData.idimgDialog.setImageResource(R.drawable.ic_payment_failed);
            bindData.descriptionOfuniversal.setText(
                    "Your payment has been failed. Please review your payment information and try again.");
            bindData.chaloaagal.setText("Pay Again");

            bindData.chaloaagal.setOnClickListener(v -> {
                try {
                    if (getDialog() != null && getDialog().isShowing()) {
                        getDialog().dismiss();
                    }
                } catch (Exception e) {
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setupTransactionPending() {
        try {
            bindData.viewKonfetti.setVisibility(View.GONE);
            bindData.idtxtTitle.setText("Payment Pending");
            // bindData.idimgDialog.setImageResource(R.drawable.ic_payment_pending);
            bindData.descriptionOfuniversal.setText(
                    "Currently processing your request, the next information will be provided within 24 hours.");
            bindData.chaloaagal.setText("Home");

            bindData.chaloaagal.setOnClickListener(v -> {
                try {
                    if (getDialog() != null && getDialog().isShowing()) {
                        getDialog().dismiss();
                        // startActivity(new Intent(requireActivity(), PayHisClass.class));
                    }
                } catch (Exception e) {
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void show(FragmentManager manager, String tag) {
        try {
            FragmentTransaction ft = manager.beginTransaction();
            ft.add(this, tag);
            ft.commitAllowingStateLoss();
        } catch (Exception th) {
            th.printStackTrace();
        }
    }

    @SuppressLint("UseRequireInsteadOfGet")
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        try {
            if ((getDialog() != null) && (getDialog().getWindow() != null) &&
                    UserInteractionStatsJava.isValidContext(getActivity())) {
                getDialog().getWindow().getAttributes().windowAnimations = R.style.PurchaseDialogAnimation;
                getDialog().getWindow().setBackgroundDrawable(
                        new ColorDrawable(
                                ContextCompat.getColor(getActivity(), R.color.transparent_color)
                        )
                );
                getDialog().getWindow().clearFlags(1024);
                getDialog().setCancelable(false);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Interface for callback
    public interface DialogDecisionCallback {
        void onDecision(boolean decision);
    }
}