package com.doc.gradient.bt.server.uses.ai.Java_BDG_Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.doc.gradient.bt.server.uses.ai.Java_BDG_CommonDocUtils.ProjectConstantsJava;
import com.doc.gradient.bt.server.uses.ai.Java_BDG_Data_Scroller.PosterFetchMore_Listener;
import com.doc.gradient.bt.server.uses.ai.Java_BDG_Data_Scroller.PosterNextPageWatcher_Listener;
import com.doc.gradient.bt.server.uses.ai.Java_BDG_Responce_Class.BDG_GetUser.BDG_GetAllUsersItem;
import com.doc.gradient.bt.server.uses.ai.R;

import java.util.ArrayList;

public class GloryRollRankAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
     static final int ROLL_VIEW_TYPE_ITEM = 0;
     static final int ROLL_VIEW_TYPE_LOADING = 1;
     static final int ROLL_VIEW_TYPE_REFRESH = 2;

    public Context context;
    public ArrayList<BDG_GetAllUsersItem> rollRankUserDataList;
    public RecyclerView rollRankRecyclerView;
    public PosterFetchMore_Listener rollMOnLoadMoreListenerBitcoinPoster;
    public int RollpaginationCounter = 1;
    public int rollTotalItemCount = 0;
    public int rollLastVisibleItem = 0;
    public boolean rollIsLoading = true;
    public boolean Roll_startPaging = false;
    public PosterNextPageWatcher_Listener rollPageAppendListenerBitcoinPoster;

    public GloryRollRankAdapter(Context context, ArrayList<BDG_GetAllUsersItem> rollRankUserDataList, RecyclerView rollRankRecyclerView) {
        this.context = context;
        this.rollRankUserDataList = rollRankUserDataList;
        this.rollRankRecyclerView = rollRankRecyclerView;

        if (rollRankRecyclerView != null) {
            LinearLayoutManager manager = (LinearLayoutManager) rollRankRecyclerView.getLayoutManager();
            if (manager != null) {
                rollRankRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                    @Override
                    public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                        super.onScrolled(recyclerView, dx, dy);
                        int visibleItemCount = manager.getChildCount();
                        int pastVisibleItems = manager.findFirstVisibleItemPosition();

                        if (pastVisibleItems + visibleItemCount >= ProjectConstantsJava.Project_TOTAL_ITEM_COUNT) {
                            if (rollPageAppendListenerBitcoinPoster != null) {
                                rollPageAppendListenerBitcoinPoster.NextPageshowBottomToTop(true);
                            }
                        } else {
                            if (rollPageAppendListenerBitcoinPoster != null) {
                                rollPageAppendListenerBitcoinPoster.NextPageshowBottomToTop(false);
                            }
                        }

                        rollTotalItemCount = manager.getItemCount();
                        rollLastVisibleItem = manager.findLastVisibleItemPosition();

                        if (!rollIsLoading && rollTotalItemCount <= rollLastVisibleItem + ProjectConstantsJava.Project_TOTAL_ITEM_THRESHOULD) {
                            if (rollMOnLoadMoreListenerBitcoinPoster != null) {
                                rollMOnLoadMoreListenerBitcoinPoster.PosterFetchonLoadMore(RollpaginationCounter, Roll_startPaging);
                            }
                            rollIsLoading = true;
                        }
                    }
                });
            }
        }
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        this.rollRankRecyclerView = recyclerView;
        super.onAttachedToRecyclerView(recyclerView);
    }

    public void setPageAppendListener(PosterNextPageWatcher_Listener pageAppendListenerBitcoinPoster) {
        this.rollPageAppendListenerBitcoinPoster = pageAppendListenerBitcoinPoster;
    }

    public void setLoaded() {
        rollIsLoading = false;
    }

    public void setOnLoadMoreListener(PosterFetchMore_Listener mOnLoadMoreListenerBitcoinPoster) {
        this.rollMOnLoadMoreListenerBitcoinPoster = mOnLoadMoreListenerBitcoinPoster;
    }

    @Override
    public int getItemViewType(int position) {
        BDG_GetAllUsersItem item = rollRankUserDataList.get(position);
        if (item == null) {
            return ROLL_VIEW_TYPE_LOADING;
        } else if (item.getId() == ProjectConstantsJava.Project_TYPE_REFRESH) {
            return ROLL_VIEW_TYPE_REFRESH;
        } else {
            return ROLL_VIEW_TYPE_ITEM;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case ROLL_VIEW_TYPE_ITEM:
                View itemView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.global_ranking_item, parent, false);
                return new ItemHolder(itemView);
            case ROLL_VIEW_TYPE_LOADING:
            case ROLL_VIEW_TYPE_REFRESH:
                View loadView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.paging_loading, parent, false);
                return new LoadingViewHolder(loadView);
            default:
                View defaultView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.paging_loading, parent, false);
                return new LoadingViewHolder(defaultView);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        if (viewHolder instanceof ItemHolder) {
            ItemHolder holder = (ItemHolder) viewHolder;
            BDG_GetAllUsersItem data = rollRankUserDataList.get(position);
            int pos = position + 1;

            if (pos == 1) {
                holder.rollTxtGlobalPosition.setVisibility(View.GONE);
                holder.rollImgForGlobal.setVisibility(View.VISIBLE);
                holder.rollImgForGlobal.setImageResource(R.drawable.a_one);
            } else if (pos == 2) {
                holder.rollTxtGlobalPosition.setVisibility(View.GONE);
                holder.rollImgForGlobal.setVisibility(View.VISIBLE);
                holder.rollImgForGlobal.setImageResource(R.drawable.a_two);
            } else if (pos == 3) {
                holder.rollTxtGlobalPosition.setVisibility(View.GONE);
                holder.rollImgForGlobal.setVisibility(View.VISIBLE);
                holder.rollImgForGlobal.setImageResource(R.drawable.a_three);
            } else {
                holder.rollImgForGlobal.setVisibility(View.GONE);
                holder.rollTxtGlobalPosition.setVisibility(View.VISIBLE);
                holder.rollTxtGlobalPosition.setText(pos + ".");
            }

            String nameCopy = data.getFirstName() + data.getLastName();
            String name = nameCopy.substring(0, 1);
            holder.rollTxtGlobalPlayerName.setText(name + "*******");

            if (data.getMiningPoint() == null || data.getMiningPoint().equals("0")) {
                holder.rollTxtGlobalWinning.setText("0.00000000");
            } else {
                holder.rollTxtGlobalWinning.setText(data.getMiningPoint());
            }
        } else if (viewHolder instanceof RefreshViewHolder) {
            RefreshViewHolder refreshHolder = (RefreshViewHolder) viewHolder;
            refreshHolder.itemView.setOnClickListener(v -> {
                if (rollPageAppendListenerBitcoinPoster != null) {
                    rollPageAppendListenerBitcoinPoster.NextPageonPageAppendClick(RollpaginationCounter);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return rollRankUserDataList.size();
    }

    class ItemHolder extends RecyclerView.ViewHolder {
        TextView rollTxtGlobalPosition;
        TextView rollTxtGlobalPlayerName;
        TextView rollTxtGlobalWinning;
        ImageView rollImgForGlobal;

        ItemHolder(View itemView) {
            super(itemView);
            rollTxtGlobalPosition = itemView.findViewById(R.id.globalNumberbdg);
            rollTxtGlobalPlayerName = itemView.findViewById(R.id.globalNamebdg);
            rollTxtGlobalWinning = itemView.findViewById(R.id.globalPointsbdg);
            rollImgForGlobal = itemView.findViewById(R.id.imgForGlobal);
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
