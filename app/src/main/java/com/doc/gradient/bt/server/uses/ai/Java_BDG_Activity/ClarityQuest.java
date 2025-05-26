package com.doc.gradient.bt.server.uses.ai.Java_BDG_Activity;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.lifecycle.Observer;

import com.doc.gradient.bt.server.uses.ai.Java_BDG_MainBase.AutoConnectNetwork;
import com.doc.gradient.bt.server.uses.ai.Java_BDG_MainBase.BaseCoreParentJava;
import com.doc.gradient.bt.server.uses.ai.R;
import com.doc.gradient.bt.server.uses.ai.databinding.ActivityClarityQuestBinding;

public class ClarityQuest extends BaseCoreParentJava<ActivityClarityQuestBinding> {
    AutoConnectNetwork autoconnectnetwork = null;
    private LinearLayout Questbannerview = null;

    @Override
    protected ActivityClarityQuestBinding DataBridge() {
        return ActivityClarityQuestBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void NetBridge() {
        autoconnectnetwork = new AutoConnectNetwork(this);
        autoconnectnetwork.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean hasConnection) {
                if (Boolean.FALSE.equals(hasConnection)) {
                    noDataDialog();
                } else {
                    hideDataDialog();
                }
            }
        });
    }

    @Override
    protected void RecallBindingProcess() {
        AppStatusBarjava(this);

        allbindData.btnbackbdg.setOnClickListener(v -> {
            finish();

            for (int i = 1; i < 11; i++) {
                int id1 = getResources().getIdentifier(
                        "des" + i,
                        "id",
                        ClarityQuest.this.getPackageName()
                );
                int id2 = getResources().getIdentifier(
                        "imageView" + i,
                        "id",
                        ClarityQuest.this.getPackageName()
                );

                TextView view1 = allbindData.getRoot().findViewById(id1);
                ImageView view2 = allbindData.getRoot().findViewById(id2);

                FaqcloseAll(view1, view2);
            }
        });

        allbindData.btnstartcomputing.setOnClickListener(v ->
                FaqcheckVisiblity(allbindData.des1, allbindData.imageView1)
        );

        allbindData.btncomputingwork.setOnClickListener(v ->
                FaqcheckVisiblity(allbindData.des2, allbindData.imageView2)
        );

        allbindData.btnreferafriend.setOnClickListener(v ->
                FaqcheckVisiblity(allbindData.des3, allbindData.imageView3)
        );

        allbindData.btnmineBTC.setOnClickListener(v ->
                FaqcheckVisiblity(allbindData.des4, allbindData.imageView4)
        );

        allbindData.btnrealBitcoin.setOnClickListener(v ->
                FaqcheckVisiblity(allbindData.des5, allbindData.imageView5)
        );

        allbindData.btnpaidcontract.setOnClickListener(v ->
                FaqcheckVisiblity(allbindData.des6, allbindData.imageView6)
        );

        allbindData.btnspendinganymoney.setOnClickListener(v ->
                FaqcheckVisiblity(allbindData.des7, allbindData.imageView7)
        );

        allbindData.btndropsuddenly.setOnClickListener(v ->
                FaqcheckVisiblity(allbindData.des8, allbindData.imageView8)
        );

        allbindData.btnlightningnetwork.setOnClickListener(v ->
                FaqcheckVisiblity(allbindData.des9, allbindData.imageView9)
        );

        allbindData.btnAPRcalculated.setOnClickListener(v ->
                FaqcheckVisiblity(allbindData.des10, allbindData.imageView10)
        );

    }

    @Override
    protected void ModelSyncObserver() {

    }

    @Override
    protected void viewAds() {
        Questbannerview = findViewById(com.doc.gradient.bt.server.uses.ai.ads.R.id.hideView);
        showBannerAds(Questbannerview, ClarityQuest.this);
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    public void FaqcheckVisiblity(View tvData, ImageView arrowView) {
        if (tvData.getVisibility() == View.VISIBLE) {
            tvData.setVisibility(View.GONE);
            arrowView.setImageDrawable(getResources().getDrawable(R.drawable.ic_dropdown));
        } else {
            tvData.setVisibility(View.VISIBLE);
            arrowView.setImageDrawable(getResources().getDrawable(R.drawable.ic_dropup));
        }
    }

    public void FaqcloseAll(View id, ImageView idd) {
        id.setVisibility(View.GONE);
        idd.setImageDrawable(getResources().getDrawable(R.drawable.ic_dropdown));
    }

}
