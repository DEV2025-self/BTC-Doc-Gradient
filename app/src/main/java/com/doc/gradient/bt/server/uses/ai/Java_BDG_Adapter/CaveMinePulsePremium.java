package com.doc.gradient.bt.server.uses.ai.Java_BDG_Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.CircularProgressDrawable;

import com.bumptech.glide.Glide;
import com.doc.gradient.bt.server.uses.ai.Java_BDG_CommonDocUtils.ProjectConstantsJava;
import com.doc.gradient.bt.server.uses.ai.Java_BDG_Responce_Class.BDG_Plan.BDG_PlanData;
import com.doc.gradient.bt.server.uses.ai.R;

import java.util.ArrayList;

public class CaveMinePulsePremium extends RecyclerView.Adapter<CaveMinePulsePremium.ViewHolder> {

    Context context;
    ArrayList<BDG_PlanData> pulsePlanList;
    OnTextureForIndexChangeListener pulseListener;

    public CaveMinePulsePremium(Context context, ArrayList<BDG_PlanData> pulsePlanList, OnTextureForIndexChangeListener pulseListener) {
        this.context = context;
        this.pulsePlanList = pulsePlanList;
        this.pulseListener = pulseListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.planhorizontal_itembdg, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint({"LongLogTag", "SetTextI18n", "ResourceAsColor"})
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BDG_PlanData details = pulsePlanList.get(position);

        if (details.getPlanName().equals(ProjectConstantsJava.Project_CURRENT_PLAN_SHOW)) {
            holder.linearSpeed0.setBackgroundResource(R.drawable.startbtn_bg);
            holder.pulseTxtForPlanName.setText("MANAGE");
            holder.pulseTxtForPlanName.setTextColor(context.getResources().getColor(R.color.white));
        } else {
            holder.linearSpeed0.setBackgroundResource(R.drawable.allbtnradius100_bg);
            holder.pulseTxtForPlanName.setText(details.getPlanName());
            holder.pulseTxtForPlanName.setTextColor(context.getResources().getColor(R.color.black));
        }

        CircularProgressDrawable circularProgressDrawable = new CircularProgressDrawable(context);
        circularProgressDrawable.setColorSchemeColors(Color.parseColor("#ffffff"));
        circularProgressDrawable.setStrokeWidth(5f);
        circularProgressDrawable.setCenterRadius(20f);
        circularProgressDrawable.start();

        Glide.with(context)
                .load(details.getImagePath())
                .placeholder(circularProgressDrawable)
                .into(holder.pulseImagePlanDetails);

        int category = Integer.parseInt(details.getCategory());
        if (category == 2) {
            String price = details.getPrice();
            holder.pulseMainPrice.setText("₹" + price);
            holder.pulseMainPrice.setVisibility(View.VISIBLE);
        } else {
            if (details.getPriceInApp() != null && !details.getPriceInApp().isEmpty() && !details.getPriceInApp().equals("null")) {
                try {
                    String value = details.getPriceInApp();
                    String cleanedValue = value.replaceAll("\\.00$", "");
                    holder.pulseMainPrice.setText(cleanedValue);
                } catch (Exception e) {
                    holder.pulseMainPrice.setText(details.getPriceInApp());
                }
                holder.pulseMainPrice.setVisibility(View.VISIBLE);
            }
        }

        holder.pulseTxtForPlanName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                if (pulseListener != null && position != RecyclerView.NO_POSITION) {
                    pulseListener.onUserPlanSelect(position, pulsePlanList.get(position));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return pulsePlanList != null ? pulsePlanList.size() : 0;
    }

    public interface OnTextureForIndexChangeListener {
        void onUserPlanSelect(int position, BDG_PlanData planItem);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView pulseMainPrice;
        public TextView pulseTxtForPlanName;
        public ImageView pulseImagePlanDetails;
        public RelativeLayout linearSpeed0;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            pulseTxtForPlanName = itemView.findViewById(R.id.txtPlanNamebdg);
            pulseMainPrice = itemView.findViewById(R.id.txtPlanPricebdg);
            pulseImagePlanDetails = itemView.findViewById(R.id.imgplanbdg);
            linearSpeed0 = itemView.findViewById(R.id.linearSpeed0);
        }
    }
}