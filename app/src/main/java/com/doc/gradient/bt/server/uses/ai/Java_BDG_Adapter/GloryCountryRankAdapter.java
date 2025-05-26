package com.doc.gradient.bt.server.uses.ai.Java_BDG_Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.doc.gradient.bt.server.uses.ai.Java_BDG_Responce_Class.BDG_Country.BDG_CountryDataItem;
import com.doc.gradient.bt.server.uses.ai.R;

import java.util.ArrayList;

public class GloryCountryRankAdapter extends RecyclerView.Adapter<GloryCountryRankAdapter.ViewHolder> {
    private final Context context;
    private final ArrayList<BDG_CountryDataItem> gloryCountryList;

    public GloryCountryRankAdapter(Context context, ArrayList<BDG_CountryDataItem> gloryCountryList) {
        this.context = context;
        this.gloryCountryList = gloryCountryList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.global_ranking_item, parent, false);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        view.setLayoutParams(layoutParams);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.gloryGlobalPoints.setVisibility(View.GONE);
        BDG_CountryDataItem data = gloryCountryList.get(position);
        int pos = position + 1;
        holder.gloryTxtUserCountryName.setText(data.getCountryName());

        if (pos == 1) {
            holder.gloryTxtUserPosition.setVisibility(View.GONE);
            holder.gloryImgForGlobal.setVisibility(View.VISIBLE);
            holder.gloryImgForGlobal.setImageResource(R.drawable.a_one);
        } else if (pos == 2) {
            holder.gloryTxtUserPosition.setVisibility(View.GONE);
            holder.gloryImgForGlobal.setVisibility(View.VISIBLE);
            holder.gloryImgForGlobal.setImageResource(R.drawable.a_two);
        } else if (pos == 3) {
            holder.gloryTxtUserPosition.setVisibility(View.GONE);
            holder.gloryImgForGlobal.setVisibility(View.VISIBLE);
            holder.gloryImgForGlobal.setImageResource(R.drawable.a_three);
        } else {
            holder.gloryImgForGlobal.setVisibility(View.GONE);
            holder.gloryTxtUserPosition.setVisibility(View.VISIBLE);
            holder.gloryTxtUserPosition.setText(pos + ".");
        }
    }

    @Override
    public int getItemCount() {
        return 50;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView gloryTxtUserPosition;
        TextView gloryTxtUserCountryName;
        TextView gloryGlobalPoints;
        ImageView gloryImgForGlobal;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            gloryTxtUserPosition = itemView.findViewById(R.id.globalNumberbdg);
            gloryTxtUserCountryName = itemView.findViewById(R.id.globalNamebdg);
            gloryGlobalPoints = itemView.findViewById(R.id.globalPointsbdg);
            gloryImgForGlobal = itemView.findViewById(R.id.imgForGlobal);
        }
    }
}
