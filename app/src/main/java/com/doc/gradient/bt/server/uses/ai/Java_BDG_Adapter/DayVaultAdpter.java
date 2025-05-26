package com.doc.gradient.bt.server.uses.ai.Java_BDG_Adapter;

import static com.doc.gradient.bt.server.uses.ai.Java_BDG_CommonDocUtils.ProjectConstantsJava.Project_TOTAL_ITEM_COUNT;
import static com.doc.gradient.bt.server.uses.ai.Java_BDG_CommonDocUtils.ProjectConstantsJava.Project_TOTAL_ITEM_THRESHOULD;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.doc.gradient.bt.server.uses.ai.Java_BDG_Data_Scroller.PosterFetchMore_Listener;
import com.doc.gradient.bt.server.uses.ai.Java_BDG_Data_Scroller.PosterNextPageWatcher_Listener;
import com.doc.gradient.bt.server.uses.ai.Java_BDG_Responce_Class.BDG_MiningHistroy.BDG_DailyMiningHistoryItem;
import com.doc.gradient.bt.server.uses.ai.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class DayVaultAdpter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public final int Day_VIEW_TYPE_ITEM = 0;
    public final int Day_VIEW_TYPE_LOADING = 1;
    public final int VIEW_TYPE_REFRESH = 2;
    public final String TAG = "DayVaultAdpter";
    public Context context;
    public ArrayList<BDG_DailyMiningHistoryItem> DayminingHistoryList;
    public RecyclerView DayrecyclerView;
    public PosterNextPageWatcher_Listener DaypageAppendListenerPoster;
    public PosterFetchMore_Listener DaymOnLoadMoreListenerPoster;
    public int DaypaginationCounter = 1;
    public int DaytotalItemCount = 0;
    public int DaylastVisibleItem = 0;
    public boolean DayisLoading = true;
    public boolean DaystartPaging = true;

    public DayVaultAdpter(Context context, ArrayList<BDG_DailyMiningHistoryItem> DayminingHistoryList, RecyclerView DayrecyclerView) {
        this.context = context;
        this.DayminingHistoryList = DayminingHistoryList != null ? DayminingHistoryList : new ArrayList<>();
        this.DayrecyclerView = DayrecyclerView;

        if (DayrecyclerView != null) {
            LinearLayoutManager manager = (LinearLayoutManager) DayrecyclerView.getLayoutManager();
            if (manager != null) {
                DayrecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                    @Override
                    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                        super.onScrolled(recyclerView, dx, dy);
                        int visibleItemCount = manager.getChildCount();
                        int pastVisibleItems = manager.findFirstVisibleItemPosition();
                        if (pastVisibleItems + visibleItemCount >= Project_TOTAL_ITEM_COUNT) {
                            if (DaypageAppendListenerPoster != null) {
                                DaypageAppendListenerPoster.NextPageshowBottomToTop(true);
                            }
                        } else {
                            if (DaypageAppendListenerPoster != null) {
                                DaypageAppendListenerPoster.NextPageshowBottomToTop(false);
                            }
                        }
                        DaytotalItemCount = manager.getItemCount();
                        DaylastVisibleItem = manager.findLastVisibleItemPosition();

                        if (!DayisLoading && DaytotalItemCount <= DaylastVisibleItem + Project_TOTAL_ITEM_THRESHOULD) {
                            if (DaymOnLoadMoreListenerPoster != null) {
                                DaymOnLoadMoreListenerPoster.PosterFetchonLoadMore(DaypaginationCounter, DaystartPaging);
                            }
                            DayisLoading = true;
                        }
                    }
                });
            }
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case Day_VIEW_TYPE_ITEM:
                View itemView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.both_history, parent, false);
                return new ItemHolder(itemView);
            case Day_VIEW_TYPE_LOADING:
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
            BDG_DailyMiningHistoryItem mininghistory = DayminingHistoryList.get(position);

            try {
                String inputDateStr = mininghistory.getDate();
                if (inputDateStr != null && !inputDateStr.isEmpty()) {
                    // Define the input date format
                    SimpleDateFormat inputDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault());
                    inputDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));

                    // Parse the input date string to a Date object
                    Date parsedDate = inputDateFormat.parse(inputDateStr);

                    if (parsedDate != null) {
                        // Define the desired output date format
                        SimpleDateFormat outputDateFormat = new SimpleDateFormat("dd MMM yyyy", Locale.getDefault());
                        String outputDateStr = outputDateFormat.format(parsedDate);

                        // Define the desired output time format
                        SimpleDateFormat outputTimeFormat = new SimpleDateFormat("hh:mm a", Locale.getDefault());
                        outputTimeFormat.setTimeZone(TimeZone.getDefault());
                        String outputTimeStr = outputTimeFormat.format(parsedDate).toUpperCase();

                        // Set the formatted date and time to the respective TextViews
                        ((ItemHolder) holder).DaytxtMiningDate.setText(outputDateStr);
                        ((ItemHolder) holder).DaytxtMiningTimebdg.setText(outputTimeStr);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }


            @SuppressLint("DefaultLocale")
            String formatted = String.format("%.8f", mininghistory.getMining());
            ((ItemHolder) holder).DaytxtMiningHistoryPoint.setText("+" + formatted);

        } else if (holder instanceof RefreshViewHolder) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (DaypageAppendListenerPoster != null) {
                        DaypageAppendListenerPoster.NextPageonPageAppendClick(DaypaginationCounter);
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return DayminingHistoryList.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        this.DayrecyclerView = recyclerView;
        super.onAttachedToRecyclerView(recyclerView);
    }

    public void setPageAppendListener(PosterNextPageWatcher_Listener pageAppendListenerPoster) {
        this.DaypageAppendListenerPoster = pageAppendListenerPoster;
    }

    public void setLoaded() {
        DayisLoading = false;
    }

    public void setOnLoadMoreListener(PosterFetchMore_Listener mOnLoadMoreListenerPoster) {
        this.DaymOnLoadMoreListenerPoster = mOnLoadMoreListenerPoster;
    }

    public class ItemHolder extends RecyclerView.ViewHolder {
        public TextView DaytxtMiningDate;
        public TextView DaytxtMiningTimebdg;
        public TextView DaytxtMiningHistoryPoint;

        public ItemHolder(View itemView) {
            super(itemView);
            DaytxtMiningHistoryPoint = itemView.findViewById(R.id.txtHistoryPointbdg);
            DaytxtMiningDate = itemView.findViewById(R.id.txtMiningDatebdg);
            DaytxtMiningTimebdg = itemView.findViewById(R.id.txtMiningTimebdg);
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