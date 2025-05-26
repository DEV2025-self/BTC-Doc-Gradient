package com.doc.gradient.bt.server.uses.ai.Java_BDG_Activity;

import static com.doc.gradient.bt.server.uses.ai.Java_BDG_CommonDocUtils.ProjectConstantsJava.Project_APP_ID;
import static com.doc.gradient.bt.server.uses.ai.Java_BDG_CommonDocUtils.ProjectConstantsJava.Project_TOTAL_ITEM_COUNT;
import static com.doc.gradient.bt.server.uses.ai.Java_BDG_CommonDocUtils.ProjectConstantsJava.Project_TYPE_REFRESH;
import static com.doc.gradient.bt.server.uses.ai.Java_BDG_CommonDocUtils.ProjectConstantsJava.Project_USER_KEY;

import android.view.View;
import android.widget.LinearLayout;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.doc.gradient.bt.server.uses.ai.Java_BDG_Adapter.DayVaultAdpter;
import com.doc.gradient.bt.server.uses.ai.Java_BDG_ApiDataGenerator.MainViewModel.AppStateViewModel;
import com.doc.gradient.bt.server.uses.ai.Java_BDG_CommonDocUtils.UserInteractionStatsJava;
import com.doc.gradient.bt.server.uses.ai.Java_BDG_Data_Scroller.PosterFetchMore_Listener;
import com.doc.gradient.bt.server.uses.ai.Java_BDG_Data_Scroller.PosterNextPageWatcher_Listener;
import com.doc.gradient.bt.server.uses.ai.Java_BDG_MainBase.AutoConnectNetwork;
import com.doc.gradient.bt.server.uses.ai.Java_BDG_MainBase.BaseCoreParentJava;
import com.doc.gradient.bt.server.uses.ai.Java_BDG_Responce_Class.BDG_MiningHistroy.BDG_DailyMiningHistoryItem;
import com.doc.gradient.bt.server.uses.ai.Java_BDG_Responce_Class.BDG_MiningHistroy.MiningHistoryreq;
import com.doc.gradient.bt.server.uses.ai.databinding.ActivityDayVaultBinding;

import java.util.ArrayList;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class DayVault extends BaseCoreParentJava<ActivityDayVaultBinding> implements PosterFetchMore_Listener {
    private final ArrayList<BDG_DailyMiningHistoryItem> DayminingJsonList = new ArrayList<>();
    private AutoConnectNetwork autoconnectnetwork = null;
    private AppStateViewModel dayvModel;
    private DayVaultAdpter dayvaltadapter = null;
    private boolean dayisOfflineAppend = false;
    private LinearLayout daybannerview = null;
    private int Daypage = 0;


    @Override
    protected ActivityDayVaultBinding DataBridge() {
        return ActivityDayVaultBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void NetBridge() {
        autoconnectnetwork = new AutoConnectNetwork(this);
        calldayvalt();
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
        dayvModel = new ViewModelProvider(this).get(AppStateViewModel.class);

        if (DayminingJsonList != null) {
            DayminingJsonList.clear();
        }
        refreshView();
        AppStatusBarjava(this);

        allbindData.btnbackbdg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        allbindData.btnBottomTopbdg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allbindData.miningbdg.smoothScrollToPosition(0);
            }
        });

    }

    @Override
    protected void ModelSyncObserver() {
        dayvModel.getMininghistoryResponse().observe(this, result -> {
            if (result == null)
                return;
            switch (result.status) {
                case RAHJOVO:

                    break;
                case MALYU:
                    try {
                        if (result.data != null && result.data.getMessage() != null) {
                            if ("User not found.".equals(result.data.getMessage())) {
                                userDeleted(this);
                                return;
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    hidePageLoadView();
                    hidePageAppendView();

                    if (UserInteractionStatsJava.isValidContext(DayVault.this)) {
                        if (result.data != null && result.data.getData() != null) {
                            if (result.data.getData().getIsNextPage() != null) {
                                if (result.data.getData().getMiningHistory() != null && !result.data.getData().getMiningHistory().isEmpty()) {
                                    dayvaltadapter.setLoaded();
                                    ArrayList<BDG_DailyMiningHistoryItem> uniqueList = new ArrayList<>(
                                            getUniqueJsonList(result.data.getData().getMiningHistory())
                                    );

                                    if (Daypage == 1) {
                                        if (uniqueList != null && !uniqueList.isEmpty()) {
                                            DayminingJsonList.addAll(uniqueList);
                                            dayvaltadapter.notifyItemInserted(dayvaltadapter.getItemCount());
                                        } else {
                                            appendOfflineData(Daypage, result.data.getData().getIsNextPage());
                                        }
                                    } else {
                                        DayminingJsonList.addAll(uniqueList);
                                        dayvaltadapter.notifyItemInserted(dayvaltadapter.getItemCount());
                                    }
                                } else {
                                    appendOfflineData(Daypage, result.data.getData().getIsNextPage());
                                }

                                if (result.data.getData().getIsNextPage()) {
                                    dayvaltadapter.DaypaginationCounter = Daypage + 1;
                                    dayvaltadapter.DaystartPaging = true;
                                } else {
                                    dayvaltadapter.DaystartPaging = false;
                                    addOfflineData();
                                    dayisOfflineAppend = true;
                                }
                            }
                        } else {
                            bothsnackBars(allbindData.getRoot(), "Something went wrong please try again", 1);
                        }
                    } else {
                        bothsnackBars(allbindData.getRoot(), "Something went wrong please try again", 1);
                    }

                    if (DayminingJsonList != null && !DayminingJsonList.isEmpty()) {
                        allbindData.layMining11bdg.setVisibility(View.VISIBLE);
                        allbindData.notFoundbdg.setVisibility(View.GONE);
                    } else {
                        allbindData.layMining11bdg.setVisibility(View.GONE);
                        allbindData.notFoundbdg.setVisibility(View.VISIBLE);
                    }

                    HideSpin();
                    break;

                case NOMALYU:
                    allbindData.layMining11bdg.setVisibility(View.GONE);
                    allbindData.notFoundbdg.setVisibility(View.VISIBLE);
                    bothsnackBars(allbindData.getRoot(), "Something went wrong please try again", 1);
                    HideSpin();
                    break;
            }
        });
    }

    @Override
    protected void viewAds() {
        daybannerview = findViewById(com.doc.gradient.bt.server.uses.ai.ads.R.id.hideView);
        showBannerAds(daybannerview, DayVault.this);
    }

    private void calldayvalt() {
        ShowSpin(DayVault.this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(DayVault.this, LinearLayoutManager.VERTICAL, false);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        allbindData.miningbdg.setLayoutManager(linearLayoutManager);

        dayvaltadapter = new DayVaultAdpter(this, DayminingJsonList, allbindData.miningbdg);
        allbindData.miningbdg.setAdapter(dayvaltadapter);

        dayvaltadapter.setOnLoadMoreListener(this);

        dayvaltadapter.setPageAppendListener(new PosterNextPageWatcher_Listener() {
            @Override
            public void NextPageonPageAppendClick(int page) {
                allbindData.miningbdg.post(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            DayminingJsonList.remove(DayminingJsonList.size() - 1);
                            dayvaltadapter.notifyItemRemoved(DayminingJsonList.size());
                            PosterFetchonLoadMore(page, true);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }

            @Override
            public void NextonPageAppendClick() {
                // Empty implementation
            }

            @Override
            public void NextPageshowBottomToTop(boolean isShow) {
                if (isShow) {
                    allbindData.btnBottomTopbdg.setVisibility(View.VISIBLE);
                } else {
                    allbindData.btnBottomTopbdg.setVisibility(View.GONE);
                }
            }
        });
    }

    private void refreshView() {
        if (DayminingJsonList != null) {
            DayminingJsonList.clear();
        }
        dayisOfflineAppend = false;
        if (dayvaltadapter != null) {
            dayvaltadapter.notifyDataSetChanged();
        }
        getAllSample(1);
    }

    private void getAllSample(int page) {
        this.Daypage = page;
        ShowSpin(this);
        dayvModel.MininghistoryResponseapi(new MiningHistoryreq(Project_USER_KEY, String.valueOf(page), String.valueOf(Project_TOTAL_ITEM_COUNT),Project_APP_ID));
    }


    @Override
    public void PosterFetchonLoadMore(int page, Boolean isLoadMore) {
        allbindData.miningbdg.post(new Runnable() {
            @Override
            public void run() {
                try {
                    DayminingJsonList.add(null);
                    dayvaltadapter.notifyItemInserted(DayminingJsonList.size() - 1);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        if (isLoadMore != null && isLoadMore) {
            getAllSample(page);
        } else {
            allbindData.miningbdg.post(new Runnable() {
                @Override
                public void run() {
                    try {
                        DayminingJsonList.remove(DayminingJsonList.size() - 1);
                        dayvaltadapter.notifyItemRemoved(DayminingJsonList.size());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    @Override
    public void PosterFetchonPopularTagLoadMore(int page, Boolean isLoadMore) {
        // Empty implementation
    }

    private ArrayList<BDG_DailyMiningHistoryItem> getUniqueJsonList(ArrayList<BDG_DailyMiningHistoryItem> newJsonList) {
        ArrayList<BDG_DailyMiningHistoryItem> tempList = new ArrayList<>();
        if (DayminingJsonList.size() == 0) {
            tempList.clear();
            tempList.addAll(newJsonList);
        } else if (newJsonList != null && !newJsonList.isEmpty()) {
            for (BDG_DailyMiningHistoryItem newList : newJsonList) {
                Integer jsonId = newList.getId();
                boolean isMatch = false;
                for (BDG_DailyMiningHistoryItem oldList : DayminingJsonList) {
                    if (oldList != null && oldList.getId() == jsonId) {
                        isMatch = true;
                        break;
                    }
                }
                if (!isMatch) {
                    tempList.add(newList);
                }
            }
        }
        return tempList;
    }

    private void hidePageAppendView() {
        // Remove append item on success
        try {
            if (DayminingJsonList.size() > 0 &&
                    DayminingJsonList.get(DayminingJsonList.size() - 1) != null &&
                    DayminingJsonList.get(DayminingJsonList.size() - 1).getId() == Project_TYPE_REFRESH) {
                DayminingJsonList.remove(DayminingJsonList.size() - 1);
                dayvaltadapter.notifyItemRemoved(DayminingJsonList.size());
                // GdLog.e(TAG, "Remove Page Indicator.");
            } else if (DayminingJsonList.size() > 1 &&
                    DayminingJsonList.get(DayminingJsonList.size() - 2) != null &&
                    DayminingJsonList.get(DayminingJsonList.size() - 2).getId() == Project_TYPE_REFRESH) {
                DayminingJsonList.remove(DayminingJsonList.size() - 2);
                dayvaltadapter.notifyItemRemoved(DayminingJsonList.size());
                // GdLog.e(TAG, "Remove Page Indicator from second last position.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addOfflineData() {
        hidePageLoadView();
        hidePageAppendView();
        if (!dayisOfflineAppend) {
            ArrayList<BDG_DailyMiningHistoryItem> local = new ArrayList<>();
            if (!local.isEmpty()) {
                if (DayminingJsonList != null) {
                    DayminingJsonList.addAll(local);
                    dayvaltadapter.notifyItemRangeInserted(
                            DayminingJsonList.size() - local.size(), local.size());
                    dayisOfflineAppend = true;
                }
            }
        }
    }

    private void appendOfflineData(int page, boolean isNextPage) {
        hidePageLoadView();
        hidePageAppendView();
        if (page == 1 && (DayminingJsonList == null || DayminingJsonList.isEmpty())) {
            ArrayList<BDG_DailyMiningHistoryItem> local = new ArrayList<>();
            if (!local.isEmpty()) {
                if (DayminingJsonList != null) {
                    DayminingJsonList.addAll(local);
                    dayvaltadapter.notifyItemRangeInserted(
                            DayminingJsonList.size() - local.size(), local.size());
                    dayisOfflineAppend = true;
                }
            }
        }
        if (isNextPage) {
            showLoadMoreButton();
        }
    }

    private void showLoadMoreButton() {
        dayvaltadapter.setLoaded();
        allbindData.miningbdg.post(() -> {
            try {
                if (DayminingJsonList != null) {
                    int size = DayminingJsonList.size();
                    if (size == 0 || (DayminingJsonList.get(size - 1) != null &&
                            DayminingJsonList.get(size - 1).getId() != Project_TYPE_REFRESH)) {
                        DayminingJsonList.add(new BDG_DailyMiningHistoryItem(Project_TYPE_REFRESH));
                        dayvaltadapter.notifyItemInserted(DayminingJsonList.size() - 1);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private void hidePageLoadView() {
        if (DayminingJsonList != null && !DayminingJsonList.isEmpty() &&
                DayminingJsonList.get(DayminingJsonList.size() - 1) == null) {
            try {
                int removeIndex = DayminingJsonList.size() - 1;
                DayminingJsonList.remove(removeIndex);
                dayvaltadapter.notifyItemRemoved(removeIndex);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
