package com.doc.gradient.bt.server.uses.ai.Java_BDG_Adapter;

import static com.doc.gradient.bt.server.uses.ai.Java_BDG_CommonDocUtils.ProjectConstantsJava.Project_TOTAL_ITEM_COUNT;
import static com.doc.gradient.bt.server.uses.ai.Java_BDG_CommonDocUtils.ProjectConstantsJava.Project_TOTAL_ITEM_THRESHOULD;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.doc.gradient.bt.server.uses.ai.BDG_Responce_Class.BDG_WithdrawalHistory.BDG_WithdrawHistoryItem;
import com.doc.gradient.bt.server.uses.ai.Java_BDG_Data_Scroller.PosterFetchMore_Listener;
import com.doc.gradient.bt.server.uses.ai.Java_BDG_Data_Scroller.PosterNextPageWatcher_Listener;
import com.doc.gradient.bt.server.uses.ai.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;

public class FundVaultAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public final int Fund_VIEW_TYPE_ITEM = 0;
    public final int Fund_VIEW_TYPE_LOADING = 1;
    public final int Fund_VIEW_TYPE_REFRESH = 2;
    public final String TAG = "FundVaultAdapter";
    public Context context;
    public ArrayList<BDG_WithdrawHistoryItem> Fund_miningHistoryList;
    public RecyclerView Fund_recyclerView;
    public PosterNextPageWatcher_Listener Fund_pageAppendListenerPoster;
    public PosterFetchMore_Listener Fund_mOnLoadMoreListenerPoster;
    public  int Fund_paginationCounter = 1;
    public int Fund_totalItemCount = 0;
    public int Fund_lastVisibleItem = 0;
    public boolean Fund_isLoading = true;
    public boolean Fund_startPaging = true;

    public FundVaultAdapter(Context context, ArrayList<BDG_WithdrawHistoryItem> Fund_miningHistoryList, RecyclerView Fund_recyclerView) {
        this.context = context;
        this.Fund_miningHistoryList = Fund_miningHistoryList != null ? Fund_miningHistoryList : new ArrayList<>();
        this.Fund_recyclerView = Fund_recyclerView;

        if (Fund_recyclerView != null) {
            LinearLayoutManager manager = (LinearLayoutManager) Fund_recyclerView.getLayoutManager();
            if (manager != null) {
                Fund_recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                    @Override
                    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                        super.onScrolled(recyclerView, dx, dy);
                        int visibleItemCount = manager.getChildCount();
                        int pastVisibleItems = manager.findFirstVisibleItemPosition();
                        if (pastVisibleItems + visibleItemCount >= Project_TOTAL_ITEM_COUNT) {
                            if (Fund_pageAppendListenerPoster != null) {
                                Fund_pageAppendListenerPoster.NextPageshowBottomToTop(true);
                            }
                        } else {
                            if (Fund_pageAppendListenerPoster != null) {
                                Fund_pageAppendListenerPoster.NextPageshowBottomToTop(false);
                            }
                        }
                        Fund_totalItemCount = manager.getItemCount();
                        Fund_lastVisibleItem = manager.findLastVisibleItemPosition();

                        if (!Fund_isLoading && Fund_totalItemCount <= Fund_lastVisibleItem + Project_TOTAL_ITEM_THRESHOULD) {
                            if (Fund_mOnLoadMoreListenerPoster != null) {
                                Fund_mOnLoadMoreListenerPoster.PosterFetchonLoadMore(Fund_paginationCounter, Fund_startPaging);
                            }
                            Fund_isLoading = true;
                        }
                    }
                });
            }
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case Fund_VIEW_TYPE_ITEM:
                View itemView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.both_history, parent, false);
                return new ItemHolder(itemView);
            case Fund_VIEW_TYPE_LOADING:
                View loadView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.paging_loading, parent, false);
                return new LoadingViewHolder(loadView);
            default:
                View appendView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.paging_refresh, parent, false);
                return new RefreshViewHolder(appendView);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ItemHolder) {
            BDG_WithdrawHistoryItem mininghistory = Fund_miningHistoryList.get(position);

            ((ItemHolder) holder).FundtxtNameMiningbdg.setText("Withdrawal History");

            try {
                String inputDateStr = mininghistory.getCreatedAt() != null ? mininghistory.getCreatedAt().toString() : "";
                SimpleDateFormat inputDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
                Date inputDate = inputDateFormat.parse(inputDateStr);

                SimpleDateFormat outputDateFormat = new SimpleDateFormat("dd MMM yyyy");
                String outputDateStr = outputDateFormat.format(inputDate);

                String inputDateStr1 = mininghistory.getCreatedAt() != null ? mininghistory.getCreatedAt().toString() : "";
                SimpleDateFormat inputDateFormat1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
                inputDateFormat1.setTimeZone(TimeZone.getTimeZone("UTC"));
                Date inputDate1 = inputDateFormat1.parse(inputDateStr1);

                SimpleDateFormat outputTimeFormat = new SimpleDateFormat("hh:mm a");
                outputTimeFormat.setTimeZone(TimeZone.getDefault());
                String outputTimeStr = outputTimeFormat.format(inputDate1).toUpperCase();

                ((ItemHolder) holder).FundtxtMiningDate.setText(outputDateStr);
                ((ItemHolder) holder).FundtxtMiningTimebdg.setText(outputTimeStr);

            } catch (Exception e) {
                e.printStackTrace();
            }

            String amount = mininghistory.getAmount() != null ? mininghistory.getAmount() : "";
            ((ItemHolder) holder).FundtxtMiningHistoryPoint.setText("+ " + amount);

        } else if (holder instanceof RefreshViewHolder) {
            Log.i(TAG, "onBindViewHolder: jsonListObj RefreshViewHolder : ");
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Fund_pageAppendListenerPoster != null) {
                        Fund_pageAppendListenerPoster.NextPageonPageAppendClick(Fund_paginationCounter);
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return Fund_miningHistoryList.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        this.Fund_recyclerView = recyclerView;
        super.onAttachedToRecyclerView(recyclerView);
    }

    public void setPageAppendListener(PosterNextPageWatcher_Listener pageAppendListenerPoster) {
        this.Fund_pageAppendListenerPoster = pageAppendListenerPoster;
    }

    public void setLoaded() {
        Fund_isLoading = false;
    }

    public void setOnLoadMoreListener(PosterFetchMore_Listener mOnLoadMoreListenerPoster) {
        this.Fund_mOnLoadMoreListenerPoster = mOnLoadMoreListenerPoster;
    }

    public class ItemHolder extends RecyclerView.ViewHolder {
        public TextView FundtxtMiningDate;
        public TextView FundtxtMiningTimebdg;
        public TextView FundtxtMiningHistoryPoint;
        public TextView FundtxtNameMiningbdg;

        public ItemHolder(View itemView) {
            super(itemView);
            FundtxtMiningHistoryPoint = itemView.findViewById(R.id.txtHistoryPointbdg);
            FundtxtMiningDate = itemView.findViewById(R.id.txtMiningDatebdg);
            FundtxtMiningTimebdg = itemView.findViewById(R.id.txtMiningTimebdg);
            FundtxtNameMiningbdg = itemView.findViewById(R.id.txtNameMiningbdg);
        }
    }

    public class LoadingViewHolder extends RecyclerView.ViewHolder {
        public ProgressBar loadMore;

        public LoadingViewHolder(View itemView) {
            super(itemView);
            loadMore = itemView.findViewById(R.id.loadMore);
        }
    }

    public class RefreshViewHolder extends RecyclerView.ViewHolder {
        public ImageView btnLoadMore;

        public RefreshViewHolder(View itemView) {
            super(itemView);
            btnLoadMore = itemView.findViewById(R.id.btnLoadMore);
        }
    }
}