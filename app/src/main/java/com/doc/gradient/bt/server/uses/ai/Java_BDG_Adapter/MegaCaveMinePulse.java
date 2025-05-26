package com.doc.gradient.bt.server.uses.ai.Java_BDG_Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.CircularProgressDrawable;

import com.bumptech.glide.Glide;
import com.doc.gradient.bt.server.uses.ai.Java_BDG_CommonDocUtils.ProjectConstantsJava;
import com.doc.gradient.bt.server.uses.ai.Java_BDG_CommonDocUtils.UserInteractionStatsJava;
import com.doc.gradient.bt.server.uses.ai.Java_BDG_Responce_Class.BDG_Plan.BDG_PlanData;
import com.doc.gradient.bt.server.uses.ai.R;
import com.google.firebase.crashlytics.FirebaseCrashlytics;

import java.util.ArrayList;

public class MegaCaveMinePulse extends RecyclerView.Adapter<MegaCaveMinePulse.ViewHolder> {

    Context context;
    ArrayList<BDG_PlanData> megaplanList;
    OnTextureForIndexChangeListener megaListener;

    public MegaCaveMinePulse(Context context, ArrayList<BDG_PlanData> megaplanList, OnTextureForIndexChangeListener megaListener) {
        this.context = context;
        this.megaplanList = megaplanList;
        this.megaListener = megaListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.planvertical_itembdg, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint({"SetTextI18n", "ResourceAsColor"})
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        BDG_PlanData details = megaplanList.get(position);

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            holder.megaGreenOriginal.setTextSize(24f);
            holder.megaWhiteOriginal.setTextSize(17f);
            holder.megaTxtPlanName.setTextSize(17f);
        }

        if (details.getPlanName().equals(ProjectConstantsJava.Project_CURRENT_PLAN_SHOW)) {
            holder.megaSuperMainBtn.setBackgroundResource(R.drawable.startbtn_bg);
            holder.megaTxtPlanName.setText("MANAGE");
            holder.megaTxtPlanName.setTextColor(context.getResources().getColor(R.color.white));
        } else {
            holder.megaSuperMainBtn.setBackgroundResource(R.drawable.allbtnradius100_bg);
            holder.megaTxtPlanName.setText(details.getPlanName());
            holder.megaTxtPlanName.setTextColor(context.getResources().getColor(R.color.black));
        }

        CircularProgressDrawable circularProgressDrawable = new CircularProgressDrawable(context);
        circularProgressDrawable.setColorSchemeColors(Color.parseColor("#ffffff"));
        circularProgressDrawable.setStrokeWidth(5f);
        circularProgressDrawable.setCenterRadius(20f);
        circularProgressDrawable.start();

        Glide.with(context)
                .load(details.getImagePath())
                .placeholder(circularProgressDrawable)
                .into(holder.megaSuperMainImg);

        if (details.getPriceInApp() != null && !details.getPriceInApp().isEmpty() && !details.getPriceInApp().equals("null")) {
            try {
                String value = details.getPriceInApp();
                String cleanedValue = value.replaceAll("\\.00$", "");
                holder.megaGreenOriginal.setText(cleanedValue);
            } catch (Exception e) {
                holder.megaGreenOriginal.setText(details.getPriceInApp());
            }

            try {
                if (details.getDiscount() != null  && !details.getDiscount().equals("null")) {
                    String numericPart = details.getPriceInAppInNumber().replaceAll("[^\\d.]", "");
                    double price = Double.parseDouble(numericPart);

                    holder.megaLayOfferTag.setVisibility(View.VISIBLE);
                    holder.megaLayTextOfferTag.setVisibility(View.VISIBLE);

                    String discount = details.getDiscount();
                    holder.megaTxtOffer.setText(discount + "% OFF");
                    try {
                        holder.megaWhiteOriginal.setText(details.getPriceInApp().replaceAll("[0-9.,]+", "") +
                                UserInteractionStatsJava.calculateOriginalPrice(price, Integer.parseInt(discount)));
                    } catch (Exception e) {
                        holder.megaWhiteOriginal.setText(String.valueOf(
                                UserInteractionStatsJava.calculateOriginalPrice(price, Integer.parseInt(discount))));
                    }
                } else {
                    holder.megaLayOfferTag.setVisibility(View.GONE);
                    holder.megaLayTextOfferTag.setVisibility(View.GONE);
                }
            } catch (Exception e) {
                try {
                    FirebaseCrashlytics.getInstance().recordException(
                            new Exception(e.getMessage() + " In-App Price Value " + details.getPriceInApp() +
                                    " / priceInAppInNumber = " + details.getPriceInAppInNumber()));
                } catch (Exception ex) {
                    FirebaseCrashlytics.getInstance().recordException(
                            new Exception(ex.getMessage() + " In-App Price Value"));
                }
                holder.megaLayOfferTag.setVisibility(View.GONE);
                holder.megaLayTextOfferTag.setVisibility(View.GONE);
                e.printStackTrace();
            }
        }

        holder.megaSuperMainBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = holder.getAdapterPosition();
                if (megaListener != null && pos != RecyclerView.NO_POSITION) {
                    megaListener.onUserPlanSelect(pos, megaplanList.get(pos));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return megaplanList != null ? megaplanList.size() : 0;
    }

    public interface OnTextureForIndexChangeListener {
        void onUserPlanSelect(int position, BDG_PlanData planItem);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView megaGreenOriginal;
        public TextView megaWhiteOriginal;
        public RelativeLayout megaSuperMainBtn;
        public TextView megaTxtPlanName;
        public TextView megaTxtOffer;
        public ImageView megaSuperMainImg;
        public RelativeLayout megaLayTextOfferTag;
        public ImageView megaLayOfferTag;

        public ViewHolder(View itemView) {
            super(itemView);
            megaSuperMainImg = itemView.findViewById(R.id.boosterimgbdg);
            megaGreenOriginal = itemView.findViewById(R.id.txtgreenOrignalPrice);
            megaWhiteOriginal = itemView.findViewById(R.id.txtwhiteOrignalPrice);
            megaSuperMainBtn = itemView.findViewById(R.id.btnboosterbdg);
            megaTxtPlanName = itemView.findViewById(R.id.txtPlanNamebdg);
            megaTxtOffer = itemView.findViewById(R.id.txtOfferbdg);
            megaLayTextOfferTag = itemView.findViewById(R.id.laytextOfferTag);
            megaLayOfferTag = itemView.findViewById(R.id.layOfferTag);
        }
    }
}
