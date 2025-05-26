package com.doc.gradient.bt.server.uses.ai.Java_BDG_Adapter;

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

import com.doc.gradient.bt.server.uses.ai.Java_BDG_CommonDocUtils.ProjectConstantsJava;
import com.doc.gradient.bt.server.uses.ai.Java_BDG_Data_Scroller.PosterFetchMore_Listener;
import com.doc.gradient.bt.server.uses.ai.Java_BDG_Data_Scroller.PosterNextPageWatcher_Listener;
import com.doc.gradient.bt.server.uses.ai.Java_BDG_Responce_Class.BDG_ReferralsHistory.BDG_ReferralsHistoryItem;
import com.doc.gradient.bt.server.uses.ai.R;

import java.util.ArrayList;

public class ShareBuddyVaultAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    static final int Buddy_VIEW_TYPE_ITEM = 0;
    static final int Buddy_VIEW_TYPE_LOADING = 1;
    static final int Buddy_VIEW_TYPE_REFRESH = 2;
    public boolean Buddy_startPaging = false;
    public int Buddy_paginationCounter = 1;
    Context context;
    ArrayList<BDG_ReferralsHistoryItem> BuddyreferralList;
    RecyclerView BuddyrecyclerView;
    PosterFetchMore_Listener Buddy_mOnLoadMoreListenerPoster;
    int Buddy_totalItemCount = 0;
    int Buddy_lastVisibleItem = 0;
    boolean Buddy_isLoading = true;
    PosterNextPageWatcher_Listener Buddy_pageAppendListenerPoster;

    public ShareBuddyVaultAdapter(Context context, ArrayList<BDG_ReferralsHistoryItem> BuddyreferralList, RecyclerView BuddyrecyclerView) {
        this.context = context;
        this.BuddyreferralList = BuddyreferralList;
        this.BuddyrecyclerView = BuddyrecyclerView;

        if (BuddyrecyclerView != null) {
            LinearLayoutManager manager = (LinearLayoutManager) BuddyrecyclerView.getLayoutManager();
            if (manager != null) {
                BuddyrecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                    @Override
                    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                        super.onScrolled(recyclerView, dx, dy);
                        int visibleItemCount = manager.getChildCount();
                        int pastVisibleItems = manager.findFirstVisibleItemPosition();
                        if (pastVisibleItems + visibleItemCount >= ProjectConstantsJava.Project_TOTAL_ITEM_COUNT) {
                            if (Buddy_pageAppendListenerPoster != null) {
                                Buddy_pageAppendListenerPoster.NextPageshowBottomToTop(true);
                            }
                        } else {
                            if (Buddy_pageAppendListenerPoster != null) {
                                Buddy_pageAppendListenerPoster.NextPageshowBottomToTop(false);
                            }
                        }
                        Buddy_totalItemCount = manager.getItemCount();
                        Buddy_lastVisibleItem = manager.findLastVisibleItemPosition();

                        if (!Buddy_isLoading && Buddy_totalItemCount <= Buddy_lastVisibleItem + ProjectConstantsJava.Project_TOTAL_ITEM_THRESHOULD) {
                            if (Buddy_mOnLoadMoreListenerPoster != null) {
                                Buddy_mOnLoadMoreListenerPoster.PosterFetchonLoadMore(
                                        Buddy_paginationCounter,
                                        Buddy_startPaging
                                );
                            }
                            Buddy_isLoading = true;
                        }
                    }
                });
            }
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        this.BuddyrecyclerView = recyclerView;
        super.onAttachedToRecyclerView(recyclerView);
    }

    public void setPageAppendListener(PosterNextPageWatcher_Listener pageAppendListenerPoster) {
        this.Buddy_pageAppendListenerPoster = pageAppendListenerPoster;
    }

    public void setLoaded() {
        Buddy_isLoading = false;
    }

    public void setOnLoadMoreListener(PosterFetchMore_Listener mOnLoadMoreListenerPoster) {
        this.Buddy_mOnLoadMoreListenerPoster = mOnLoadMoreListenerPoster;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case Buddy_VIEW_TYPE_ITEM:
                View itemView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.sharebuddy_item, parent, false);
                return new ItemHolder(itemView);
            case Buddy_VIEW_TYPE_LOADING:
                View loadView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.paging_loading, parent, false);
                return new LoadingViewHolder(loadView);
            default:
                View appendView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.paging_refresh, parent, false);
                return new RefreshViewHolder(appendView);
        }
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ItemHolder) {
            BDG_ReferralsHistoryItem pojoClass = BuddyreferralList.get(position);
            int pos = position + 1;
            ((ItemHolder) holder).BuddyReferralPositionbdg.setText(pos + ".");
            ((ItemHolder) holder).BuddyReferralNamebdg.setText(pojoClass.getFirstName());
            ((ItemHolder) holder).BuddyReferralGmailbdg.setText(pojoClass.getEmail());
            try {
                long value = pojoClass.getPoint().longValue();
                float floatValue = value / 100000000.0f;

                @SuppressLint("DefaultLocale")
                String formattedValue = String.format("%.8f", floatValue);
                ((ItemHolder) holder).BuddyReferralpointbdg.setText("+" + formattedValue);
            } catch (Exception e) {
                ((ItemHolder) holder).BuddyReferralpointbdg.setText("+0.00000100");
            }
        } else if (holder instanceof RefreshViewHolder) {
            holder.itemView.setOnClickListener(v -> {
                if (Buddy_pageAppendListenerPoster != null) {
                    Buddy_pageAppendListenerPoster.NextPageonPageAppendClick(Buddy_paginationCounter);
                }
            });
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (BuddyreferralList.get(position) == null) {
            return Buddy_VIEW_TYPE_LOADING;
        } else if (BuddyreferralList.get(position) != null &&
                BuddyreferralList.get(position).getId().equals(ProjectConstantsJava.Project_TYPE_REFRESH)) {
            return Buddy_VIEW_TYPE_REFRESH;
        } else {
            return Buddy_VIEW_TYPE_ITEM;
        }
    }

    @Override
    public int getItemCount() {
        return BuddyreferralList.size();
    }

    class ItemHolder extends RecyclerView.ViewHolder {
        TextView BuddyReferralpointbdg;
        TextView BuddyReferralNamebdg;
        TextView BuddyReferralGmailbdg;
        TextView BuddyReferralPositionbdg;

        ItemHolder(View itemView) {
            super(itemView);
            BuddyReferralpointbdg = itemView.findViewById(R.id.Referralpointbdg);
            BuddyReferralNamebdg = itemView.findViewById(R.id.ReferralNamebdg);
            BuddyReferralGmailbdg = itemView.findViewById(R.id.ReferralGmailbdg);
            BuddyReferralPositionbdg = itemView.findViewById(R.id.ReferralPositionbdg);
        }
    }

    class LoadingViewHolder extends RecyclerView.ViewHolder {
        ProgressBar loadMore;

        LoadingViewHolder(View itemView) {
            super(itemView);
            loadMore = itemView.findViewById(R.id.loadMore);
        }
    }

    class RefreshViewHolder extends RecyclerView.ViewHolder {
        ImageView btnLoadMore;

        RefreshViewHolder(View itemView) {
            super(itemView);
            btnLoadMore = itemView.findViewById(R.id.btnLoadMore);
        }
    }
}