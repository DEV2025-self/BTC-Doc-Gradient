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

import com.doc.gradient.bt.server.uses.ai.BDG_Responce_Class.BDG_WithdrawalHistory.BDG_WithdrawHistoryItem;
import com.doc.gradient.bt.server.uses.ai.Java_BDG_Adapter.FundVaultAdapter;
import com.doc.gradient.bt.server.uses.ai.Java_BDG_ApiDataGenerator.MainViewModel.AppStateViewModel;
import com.doc.gradient.bt.server.uses.ai.Java_BDG_CommonDocUtils.UserInteractionStatsJava;
import com.doc.gradient.bt.server.uses.ai.Java_BDG_Data_Scroller.PosterFetchMore_Listener;
import com.doc.gradient.bt.server.uses.ai.Java_BDG_Data_Scroller.PosterNextPageWatcher_Listener;
import com.doc.gradient.bt.server.uses.ai.Java_BDG_MainBase.AutoConnectNetwork;
import com.doc.gradient.bt.server.uses.ai.Java_BDG_MainBase.BaseCoreParentJava;
import com.doc.gradient.bt.server.uses.ai.Java_BDG_Responce_Class.BDG_WithdrawalHistory.WithdrawalHistoryReq;
import com.doc.gradient.bt.server.uses.ai.databinding.ActivityFundVaultBinding;

import java.util.ArrayList;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class FundVault extends BaseCoreParentJava<ActivityFundVaultBinding> implements PosterFetchMore_Listener {
    AppStateViewModel fundVaultViewModel;
    AutoConnectNetwork autoconnectnetwork = null;
    FundVaultAdapter fundvaultadapter = null;
    boolean fundVaultisOfflineAppend = false;
    ArrayList<BDG_WithdrawHistoryItem> FundVaultminingJsonList = new ArrayList<>();
    int FundVaultpage = 0;
    private LinearLayout fundVaultBannerView = null;

    @Override
    protected ActivityFundVaultBinding DataBridge() {
        return ActivityFundVaultBinding.inflate(getLayoutInflater());
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
        fundVaultViewModel = new ViewModelProvider(this).get(AppStateViewModel.class);

        AppStatusBarjava(this);

        if (FundVaultminingJsonList != null) {
            FundVaultminingJsonList.clear();
        }

        refreshView();

        allbindData.btnbackbdg.setOnClickListener(v -> finish());

        allbindData.btnBottomTopbdg.setOnClickListener(v -> allbindData.miningbdg.smoothScrollToPosition(0));
    }

    @Override
    protected void ModelSyncObserver() {
        fundVaultViewModel.getWithdrawalhistoryResponse().observe(this, result -> {
            if (result == null)
                return;
            switch (result.status) {
                case RAHJOVO:

                    break;
                case MALYU:
                    try {
                        if (result.data.getData() != null && result.data.getMessage() != null) {
                            if (result.data.getMessage().equals("User not found.")) {
                                userDeleted(this);
                                return;
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    hidePageLoadView();
                    hidePageAppendView();

                    if (UserInteractionStatsJava.isValidContext(FundVault.this)) {
                        if (result.data.getData() != null && result.data.getData() != null) {
                            if (result.data.getData().getIsNextPage() != null) {
                                if (result.data.getData() != null && result.data.getData().getWithdrawHistory() != null
                                        && !result.data.getData().getWithdrawHistory().isEmpty()) {
                                    fundvaultadapter.setLoaded();
                                    ArrayList<BDG_WithdrawHistoryItem> uniqueList = new ArrayList<>(getUniqueJsonList(result.data.getData().getWithdrawHistory()));
                                    if (FundVaultpage == 1) {
                                        if (uniqueList != null && !uniqueList.isEmpty()) {
                                            FundVaultminingJsonList.addAll(uniqueList);
                                            fundvaultadapter.notifyItemInserted(fundvaultadapter.getItemCount());
                                        } else {
                                            appendOfflineData(FundVaultpage, result.data.getData().getIsNextPage());
                                        }
                                    } else {
                                        FundVaultminingJsonList.addAll(uniqueList);
                                        fundvaultadapter.notifyItemInserted(fundvaultadapter.getItemCount());
                                    }
                                } else {
                                    appendOfflineData(FundVaultpage, result.data.getData().getIsNextPage());
                                }
                                if (result.data.getData().getIsNextPage()) {
                                    fundvaultadapter.Fund_paginationCounter = FundVaultpage + 1;
                                    fundvaultadapter.Fund_startPaging = true;
                                } else {
                                    fundvaultadapter.Fund_startPaging = false;
                                    addOfflineData();
                                    fundVaultisOfflineAppend = true;
                                }
                            }
                        } else {
                            bothsnackBars(allbindData.getRoot(), "Something went wrong please try again", 1);
                        }
                    } else {
                        bothsnackBars(allbindData.getRoot(), "Something went wrong please try again", 1);
                    }

                    if (!FundVaultminingJsonList.isEmpty() && FundVaultminingJsonList != null) {
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
                    HideSpin();
                    bothsnackBars(allbindData.getRoot(), "Something went wrong please try again", 1);
                    break;
            }
        });
    }

    @Override
    protected void viewAds() {
        fundVaultBannerView = findViewById(com.doc.gradient.bt.server.uses.ai.ads.R.id.hideView);
        showBannerAds(fundVaultBannerView, FundVault.this);
    }
    private void calldayvalt() {
        ShowSpin(FundVault.this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(
                FundVault.this, LinearLayoutManager.VERTICAL, false
        );
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        allbindData.miningbdg.setLayoutManager(linearLayoutManager);

        fundvaultadapter = new FundVaultAdapter(FundVault.this, FundVaultminingJsonList, allbindData.miningbdg);
        allbindData.miningbdg.setAdapter(fundvaultadapter);

        fundvaultadapter.setOnLoadMoreListener(this);

        fundvaultadapter.setPageAppendListener(new PosterNextPageWatcher_Listener() {
            @Override
            public void NextPageonPageAppendClick(final int page) {
                allbindData.miningbdg.post(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            FundVaultminingJsonList.remove(FundVaultminingJsonList.size() - 1);
                            fundvaultadapter.notifyItemRemoved(FundVaultminingJsonList.size());
                            PosterFetchonLoadMore(page, true);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }

            @Override
            public void NextonPageAppendClick() {
                // No implementation
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
        if (FundVaultminingJsonList != null) {
            FundVaultminingJsonList.clear();
        }
        fundVaultisOfflineAppend = false;
        if (fundvaultadapter != null) {
            fundvaultadapter.notifyDataSetChanged();
        }
        getAllSample(1);
    }

    private void getAllSample(int page) {
        this.FundVaultpage = page;
        ShowSpin(this);
        fundVaultViewModel.WithdrawalHistoryApi(new WithdrawalHistoryReq(Project_USER_KEY, String.valueOf(page), String.valueOf(Project_TOTAL_ITEM_COUNT),Project_APP_ID));
    }

    @Override
    public void PosterFetchonLoadMore(final int page, final Boolean isLoadMore) {
        allbindData.miningbdg.post(new Runnable() {
            @Override
            public void run() {
                try {
                    FundVaultminingJsonList.add(null);
                    fundvaultadapter.notifyItemInserted(FundVaultminingJsonList.size() - 1);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        if (Boolean.TRUE.equals(isLoadMore)) {
            getAllSample(page);
        } else {
            allbindData.miningbdg.post(new Runnable() {
                @Override
                public void run() {
                    try {
                        FundVaultminingJsonList.remove(FundVaultminingJsonList.size() - 1);
                        fundvaultadapter.notifyItemRemoved(FundVaultminingJsonList.size());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    @Override
    public void PosterFetchonPopularTagLoadMore(int page, Boolean isLoadMore) {
        // No implementation
    }

    private ArrayList<BDG_WithdrawHistoryItem> getUniqueJsonList(ArrayList<BDG_WithdrawHistoryItem> newJsonList) {
        ArrayList<BDG_WithdrawHistoryItem> tempList = new ArrayList<>();
        if (FundVaultminingJsonList != null) {
            if (FundVaultminingJsonList.size() == 0) {
                tempList.clear();
                if (newJsonList != null) {
                    tempList.addAll(newJsonList);
                }
            } else if (newJsonList != null && newJsonList.size() != 0) {
                for (BDG_WithdrawHistoryItem newItem : newJsonList) {
                    int jsonId = newItem.getId();
                    boolean isMatch = false;
                    for (BDG_WithdrawHistoryItem oldItem : FundVaultminingJsonList) {
                        if (oldItem != null && oldItem.getId() == jsonId) {
                            isMatch = true;
                            break;
                        }
                    }
                    if (!isMatch) {
                        tempList.add(newItem);
                    }
                }
            }
        }
        return tempList;
    }

    private void hidePageAppendView() {
        try {
            int size = FundVaultminingJsonList != null ? FundVaultminingJsonList.size() : 0;
            if (size > 0 &&
                    FundVaultminingJsonList.get(size - 1) != null &&
                    FundVaultminingJsonList.get(size - 1).getId() == Project_TYPE_REFRESH) {

                FundVaultminingJsonList.remove(size - 1);
                fundvaultadapter.notifyItemRemoved(FundVaultminingJsonList.size());

            } else if (size > 1 &&
                    FundVaultminingJsonList.get(size - 2) != null &&
                    FundVaultminingJsonList.get(size - 2).getId() == Project_TYPE_REFRESH) {

                FundVaultminingJsonList.remove(size - 2);
                fundvaultadapter.notifyItemRemoved(FundVaultminingJsonList.size());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void addOfflineData() {
        hidePageLoadView();
        hidePageAppendView();

        if (!fundVaultisOfflineAppend) {
            ArrayList<BDG_WithdrawHistoryItem> local = new ArrayList<>();
            if (local != null && local.size() > 0) {
                FundVaultminingJsonList.addAll(local);
                fundvaultadapter.notifyItemInserted(fundvaultadapter.getItemCount());
                fundVaultisOfflineAppend = true;
            }
        }
    }

    private void appendOfflineData(int page, boolean isNextPage) {
        hidePageLoadView();
        hidePageAppendView();

        if (page == 1 && (FundVaultminingJsonList == null || FundVaultminingJsonList.size() == 0)) {
            ArrayList<BDG_WithdrawHistoryItem> local = new ArrayList<>();
            if (local != null && local.size() > 0) {
                FundVaultminingJsonList.addAll(local);
                fundvaultadapter.notifyItemInserted(fundvaultadapter.getItemCount());
                fundVaultisOfflineAppend = true;
            }
        }

        if (isNextPage) {
            showLoadMoreButton();
        }
    }

    private void showLoadMoreButton() {
        fundvaultadapter.setLoaded();

        allbindData.miningbdg.post(new Runnable() {
            @Override
            public void run() {
                try {
                    if (FundVaultminingJsonList.size() == 0 ||
                            (FundVaultminingJsonList.get(FundVaultminingJsonList.size() - 1) != null &&
                                    FundVaultminingJsonList.get(FundVaultminingJsonList.size() - 1).getId() != Project_TYPE_REFRESH)) {

                        FundVaultminingJsonList.add(new BDG_WithdrawHistoryItem(Project_TYPE_REFRESH));
                        fundvaultadapter.notifyItemInserted(FundVaultminingJsonList.size() - 1);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void hidePageLoadView() {
        if (FundVaultminingJsonList != null &&
                FundVaultminingJsonList.size() > 0 &&
                FundVaultminingJsonList.get(FundVaultminingJsonList.size() - 1) == null) {
            try {
                FundVaultminingJsonList.remove(FundVaultminingJsonList.size() - 1);
                fundvaultadapter.notifyItemRemoved(FundVaultminingJsonList.size());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
