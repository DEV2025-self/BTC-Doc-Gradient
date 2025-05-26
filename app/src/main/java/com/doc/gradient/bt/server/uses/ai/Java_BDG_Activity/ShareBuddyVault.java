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

import com.doc.gradient.bt.server.uses.ai.Java_BDG_Adapter.ShareBuddyVaultAdapter;
import com.doc.gradient.bt.server.uses.ai.Java_BDG_ApiDataGenerator.MainViewModel.AppStateViewModel;
import com.doc.gradient.bt.server.uses.ai.Java_BDG_CommonDocUtils.UserInteractionStatsJava;
import com.doc.gradient.bt.server.uses.ai.Java_BDG_Data_Scroller.PosterFetchMore_Listener;
import com.doc.gradient.bt.server.uses.ai.Java_BDG_Data_Scroller.PosterNextPageWatcher_Listener;
import com.doc.gradient.bt.server.uses.ai.Java_BDG_MainBase.AutoConnectNetwork;
import com.doc.gradient.bt.server.uses.ai.Java_BDG_MainBase.BaseCoreParentJava;
import com.doc.gradient.bt.server.uses.ai.Java_BDG_Responce_Class.BDG_ReferralsHistory.BDG_ReferralsHistoryItem;
import com.doc.gradient.bt.server.uses.ai.Java_BDG_Responce_Class.BDG_ReferralsHistory.Getreferhistoryreq;
import com.doc.gradient.bt.server.uses.ai.databinding.ActivityShareBuddyVaultBinding;

import java.util.ArrayList;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class ShareBuddyVault extends BaseCoreParentJava<ActivityShareBuddyVaultBinding> implements PosterFetchMore_Listener {
    private final ArrayList<BDG_ReferralsHistoryItem> sampleJsonList = new ArrayList<>();
    private AutoConnectNetwork autoConnectNetwork = null;
    private AppStateViewModel vModel;
    private boolean isOfflineAppend = false;
    private int page = 0;
    private LinearLayout hideView = null;
    private ShareBuddyVaultAdapter referralroyaletimeadapter = null;

    @Override
    protected ActivityShareBuddyVaultBinding DataBridge() {
        return ActivityShareBuddyVaultBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void NetBridge() {
        autoConnectNetwork = new AutoConnectNetwork(this);
        GetReferAdapter();
        autoConnectNetwork.observe(this, new Observer<Boolean>() {
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
        vModel = new ViewModelProvider(this).get(AppStateViewModel.class);
        AppStatusBarjava(this);
        Allrefresh();

        allbindData.btnbackbdg.setOnClickListener(v -> finish());

        allbindData.btnBottomTopbdg.setOnClickListener(v ->
                allbindData.referbdg.smoothScrollToPosition(0)
        );

    }

    @Override
    protected void ModelSyncObserver() {
        vModel.GetreferhistoryApibdg().observe(this, result -> {
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

                    if (UserInteractionStatsJava.isValidContext(ShareBuddyVault.this)) {
                        if (result.data.getData() != null && result.data.getData() != null) {
                            if (result.data.getData().getIsNextPage() != null) {
                                if (result.data.getData() != null && result.data.getData().getReferralsHistory() != null
                                        && result.data.getData().getReferralsHistory().size() > 0) {
                                    if (referralroyaletimeadapter != null) {
                                        referralroyaletimeadapter.setLoaded();
                                    }
                                    ArrayList<BDG_ReferralsHistoryItem> uniqueList = new ArrayList<>(
                                            getUniqueJsonList(result.data.getData().getReferralsHistory())
                                    );

                                    if (page == 1) {
                                        if (uniqueList != null && uniqueList.size() > 0) {
                                            sampleJsonList.addAll(uniqueList);
                                            if (referralroyaletimeadapter != null) {
                                                referralroyaletimeadapter.notifyItemInserted(referralroyaletimeadapter.getItemCount());
                                            }
                                        } else {
                                            appendOfflineData(page, result.data.getData().getIsNextPage());
                                        }
                                    } else {
                                        sampleJsonList.addAll(uniqueList);
                                        if (referralroyaletimeadapter != null) {
                                            referralroyaletimeadapter.notifyItemInserted(referralroyaletimeadapter.getItemCount());
                                        }
                                    }
                                } else {
                                    appendOfflineData(page, result.data.getData().getIsNextPage());
                                }

                                if (result.data.getData().getIsNextPage()) {
                                    if (referralroyaletimeadapter != null) {
                                        referralroyaletimeadapter.Buddy_paginationCounter = page + 1;
                                        referralroyaletimeadapter.Buddy_startPaging = true;
                                    }
                                } else {
                                    if (referralroyaletimeadapter != null) {
                                        referralroyaletimeadapter.Buddy_startPaging = false;
                                    }
                                    addOfflineData();
                                    isOfflineAppend = true;
                                }
                            }
                        } else {
                            bothsnackBars(allbindData.getRoot(), "Something went wrong please try again", 1);
                        }
                    } else {
                        bothsnackBars(allbindData.getRoot(), "Something went wrong please try again", 1);
                    }

                    if (!sampleJsonList.isEmpty() && sampleJsonList != null) {
                        allbindData.layMining11bdg.setVisibility(View.VISIBLE);
                        allbindData.notFoundbdg.setVisibility(View.GONE);
                    } else {
                        allbindData.layMining11bdg.setVisibility(View.GONE);
                        allbindData.notFoundbdg.setVisibility(View.VISIBLE);
                    }

                    HideSpin();
                    break;

                case NOMALYU:
                    HideSpin();
                    allbindData.layMining11bdg.setVisibility(View.GONE);
                    allbindData.notFoundbdg.setVisibility(View.VISIBLE);
                    bothsnackBars(allbindData.getRoot(), "Something went wrong please try again", 1);
                    break;
            }
        });

    }

    @Override
    protected void viewAds() {
        hideView = findViewById(com.doc.gradient.bt.server.uses.ai.ads.R.id.hideView);
        showBannerAds(hideView, ShareBuddyVault.this);
    }

    private void Allrefresh() {
        sampleJsonList.clear();
        isOfflineAppend = false;
        if (referralroyaletimeadapter != null) {
            referralroyaletimeadapter.notifyDataSetChanged();
        }
        Getreferhistory(1);
    }

    @Override
    public void PosterFetchonLoadMore(int page, Boolean isLoadMore) {
        allbindData.referbdg.post(() -> {
            try {
                sampleJsonList.add(null);
                referralroyaletimeadapter.notifyItemInserted(sampleJsonList.size() - 1);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        if (isLoadMore) {
            Getreferhistory(page);
        } else {
            allbindData.referbdg.post(() -> {
                try {
                    sampleJsonList.remove(sampleJsonList.size() - 1);
                    referralroyaletimeadapter.notifyItemRemoved(sampleJsonList.size());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
    }


    @Override
    public void PosterFetchonPopularTagLoadMore(int page, Boolean isLoadMore) {
        // Empty method as per the original Kotlin code
    }

    private void GetReferAdapter() {
        ShowSpin(this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(
                ShareBuddyVault.this, LinearLayoutManager.VERTICAL, false
        );
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        allbindData.referbdg.setLayoutManager(linearLayoutManager);
        referralroyaletimeadapter = new ShareBuddyVaultAdapter(ShareBuddyVault.this, sampleJsonList, allbindData.referbdg);
        allbindData.referbdg.setAdapter(referralroyaletimeadapter);
        referralroyaletimeadapter.setOnLoadMoreListener(this);
        referralroyaletimeadapter.setPageAppendListener(new PosterNextPageWatcher_Listener() {
            @Override
            public void NextPageonPageAppendClick(int page) {
                allbindData.referbdg.post(() -> {
                    try {
                        sampleJsonList.remove(sampleJsonList.size() - 1);
                        referralroyaletimeadapter.notifyItemRemoved(sampleJsonList.size());
                        PosterFetchonLoadMore(page, true);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
            }

            @Override
            public void NextonPageAppendClick() {
                // Empty as per original
            }

            @Override
            public void NextPageshowBottomToTop(boolean isShow) {
                if (isShow) {
                    if (allbindData.btnBottomTopbdg.getVisibility() != View.VISIBLE) {
                        allbindData.btnBottomTopbdg.setVisibility(View.VISIBLE);
                    }
                } else {
                    if (allbindData.btnBottomTopbdg.getVisibility() != View.GONE) {
                        allbindData.btnBottomTopbdg.setVisibility(View.GONE);
                    }
                }
            }
        });
    }

    public void Getreferhistory(int page) {
        this.page = page;

        vModel.GetreferhistoryApibdg(new Getreferhistoryreq(Project_USER_KEY, String.valueOf(page), String.valueOf(Project_TOTAL_ITEM_COUNT),Project_APP_ID));

        hidePageAppendView();
        if (referralroyaletimeadapter != null) {
            referralroyaletimeadapter.Buddy_startPaging = false;
        }
    }

    private void addOfflineData() {
        hidePageLoadView();
        hidePageAppendView();
        if (!isOfflineAppend) {
            ArrayList<BDG_ReferralsHistoryItem> local = new ArrayList<>();
            if (local != null && local.size() > 0) {
                sampleJsonList.addAll(local);
                referralroyaletimeadapter.notifyItemInserted(referralroyaletimeadapter.getItemCount());
                isOfflineAppend = true;
            }
        }
    }

    private void hidePageAppendView() {
        // Remove append item on success
        try {
            if (sampleJsonList.size() > 0 && sampleJsonList.get(sampleJsonList.size() - 1) != null
                    && sampleJsonList.get(sampleJsonList.size() - 1).getId().equals(Project_TYPE_REFRESH)) {
                sampleJsonList.remove(sampleJsonList.size() - 1);
                referralroyaletimeadapter.notifyItemRemoved(sampleJsonList.size());
                // GdLog.e(TAG, "Remove Page Indicator.");
            } else if (sampleJsonList.size() > 1 && sampleJsonList.get(sampleJsonList.size() - 2) != null
                    && sampleJsonList.get(sampleJsonList.size() - 2).getId().equals(Project_TYPE_REFRESH)) {
                sampleJsonList.remove(sampleJsonList.size() - 2);
                referralroyaletimeadapter.notifyItemRemoved(sampleJsonList.size());
                // GdLog.e(TAG, "Remove Page Indicator from second last position.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void hidePageLoadView() {
        // Remove loading item on success
        if (sampleJsonList.size() > 0 && sampleJsonList.get(sampleJsonList.size() - 1) == null) {
            try {
                sampleJsonList.remove(sampleJsonList.size() - 1);
                referralroyaletimeadapter.notifyItemRemoved(sampleJsonList.size());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private ArrayList<BDG_ReferralsHistoryItem> getUniqueJsonList(ArrayList<BDG_ReferralsHistoryItem> newJsonList) {
        ArrayList<BDG_ReferralsHistoryItem> tempList = new ArrayList<>();
        if (sampleJsonList.size() == 0) {
            tempList.clear();
            tempList.addAll(newJsonList);
        } else if (newJsonList != null && newJsonList.size() != 0) {
            for (BDG_ReferralsHistoryItem newList : newJsonList) {
                int jsonId = newList.getId();
                boolean isMatch = false;
                for (BDG_ReferralsHistoryItem oldList : sampleJsonList) {
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

    private void appendOfflineData(int page, boolean isNextPage) {
        hidePageLoadView();
        hidePageAppendView();
        if (page == 1 && (sampleJsonList == null || sampleJsonList.size() == 0)) {
            ArrayList<BDG_ReferralsHistoryItem> local = new ArrayList<>();
            if (local != null && local.size() > 0) {
                sampleJsonList.addAll(local);
                referralroyaletimeadapter.notifyItemInserted(referralroyaletimeadapter.getItemCount());
                isOfflineAppend = true;
            }
        }
        if (isNextPage) {
            showLoadMoreButton();
        }
    }
    private void showLoadMoreButton() {
        referralroyaletimeadapter.setLoaded();
        allbindData.referbdg.post(() -> {
            try {
                if (sampleJsonList.size() == 0 ||
                        (sampleJsonList.get(sampleJsonList.size() - 1) != null &&
                                !sampleJsonList.get(sampleJsonList.size() - 1).getId().equals(Project_TYPE_REFRESH))) {
                    sampleJsonList.add(new BDG_ReferralsHistoryItem(Project_TYPE_REFRESH));
                    referralroyaletimeadapter.notifyItemInserted(sampleJsonList.size() - 1);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
