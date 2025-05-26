package com.doc.gradient.bt.server.uses.ai.Java_BDG_Activity;

import static com.doc.gradient.bt.server.uses.ai.Java_BDG_CommonDocUtils.ProjectConstantsJava.Project_APP_ID;
import static com.doc.gradient.bt.server.uses.ai.Java_BDG_CommonDocUtils.ProjectConstantsJava.Project_TOTAL_ITEM_COUNT;

import android.graphics.Color;
import android.view.View;
import android.widget.LinearLayout;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.doc.gradient.bt.server.uses.ai.Java_BDG_Adapter.GloryCountryRankAdapter;
import com.doc.gradient.bt.server.uses.ai.Java_BDG_Adapter.GloryRollRankAdapter;
import com.doc.gradient.bt.server.uses.ai.Java_BDG_ApiDataGenerator.MainViewModel.AppStateViewModel;
import com.doc.gradient.bt.server.uses.ai.Java_BDG_CommonDocUtils.ProjectConstantsJava;
import com.doc.gradient.bt.server.uses.ai.Java_BDG_CommonDocUtils.UserInteractionStatsJava;
import com.doc.gradient.bt.server.uses.ai.Java_BDG_Data_Scroller.PosterFetchMore_Listener;
import com.doc.gradient.bt.server.uses.ai.Java_BDG_Data_Scroller.PosterNextPageWatcher_Listener;
import com.doc.gradient.bt.server.uses.ai.Java_BDG_MainBase.AutoConnectNetwork;
import com.doc.gradient.bt.server.uses.ai.Java_BDG_MainBase.BaseCoreParentJava;
import com.doc.gradient.bt.server.uses.ai.Java_BDG_Responce_Class.BDG_Country.BDG_CountryDataItem;
import com.doc.gradient.bt.server.uses.ai.Java_BDG_Responce_Class.BDG_Country.Countryreq;
import com.doc.gradient.bt.server.uses.ai.Java_BDG_Responce_Class.BDG_GetUser.BDG_GetAllUserResponse;
import com.doc.gradient.bt.server.uses.ai.Java_BDG_Responce_Class.BDG_GetUser.BDG_GetAllUsersItem;
import com.doc.gradient.bt.server.uses.ai.Java_BDG_Responce_Class.BDG_GetUser.GetUserreq;
import com.doc.gradient.bt.server.uses.ai.R;
import com.doc.gradient.bt.server.uses.ai.databinding.ActivityGloryRollRankBinding;

import java.util.ArrayList;
import java.util.Objects;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class GloryRollRank extends BaseCoreParentJava<ActivityGloryRollRankBinding> implements PosterFetchMore_Listener {

    final ArrayList<BDG_CountryDataItem> RollRankcountryArrayList = new ArrayList<>();
    final ArrayList<BDG_GetAllUsersItem> RollRankuserDataList = new ArrayList<>();
    public AppStateViewModel RollRankvModel;
     GloryRollRankAdapter gloryrollrank;
    boolean RollRankisOfflineAppend = false;
    GloryCountryRankAdapter userByRegionAdapter;
    LinearLayout RollRankhideView;
    AutoConnectNetwork autoconnectnetwork;
    int RollRankpage = 1;
    boolean RollRankisShow = true;
    @Override
    protected ActivityGloryRollRankBinding DataBridge() {
        return ActivityGloryRollRankBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void NetBridge() {
        callAllUsers();
        autoconnectnetwork = new AutoConnectNetwork(this);
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
        AppStatusBarjava(this);
        RollRankvModel = new ViewModelProvider(this).get(AppStateViewModel.class);
        allbindData.GlobalRankingbdg.setOnClickListener(v -> {
            allbindData.UserByRegionlaybdg.setVisibility(View.GONE);
            allbindData.GlobalRankinglaybdg.setVisibility(View.VISIBLE);
            allbindData.GlobalRankingbdg.setBackgroundResource(R.drawable.leaderbtn_bg);
            allbindData.UserByRegionbdg.setBackground(null);
            allbindData.UserByRegionbdg.setTextColor(Color.WHITE);
            allbindData.GlobalRankingbdg.setTextColor(Color.BLACK);
            RollRankisShow = true;
        });

        allbindData.UserByRegionbdg.setOnClickListener(v -> {
            allbindData.GlobalRankingbdg.setBackground(null);
            allbindData.UserByRegionbdg.setBackgroundResource(R.drawable.leaderbtn_bg);
            allbindData.UserByRegionbdg.setTextColor(Color.BLACK);
            allbindData.GlobalRankingbdg.setTextColor(Color.WHITE);
            allbindData.UserByRegionlaybdg.setVisibility(View.VISIBLE);
            allbindData.GlobalRankinglaybdg.setVisibility(View.GONE);
            allbindData.btnBottomTopbdg.setVisibility(View.GONE);
            RollRankisShow = false;
        });

        refreshView();
        AddDataForUserByFRegion();

        allbindData.btnbackbdg.setOnClickListener(v -> finish());
        allbindData.btnBottomTopbdg.setOnClickListener(v -> allbindData.userForRecyclerViewbdg.smoothScrollToPosition(0));
    }

    @Override
    protected void ModelSyncObserver() {

        RollRankvModel.getuserresponse().observe(this, result -> {
            if (result == null)
                return;
            switch (result.status) {
                case RAHJOVO:

                    break;
                case MALYU:
                    allbindData.playernamesbdg.setVisibility(View.VISIBLE);
                    allbindData.userForRecyclerViewbdg.setVisibility(View.VISIBLE);

                    RollRankhidePageLoadView();
                    RollRankhidePageAppendView();
                    if (UserInteractionStatsJava.isValidContext(GloryRollRank.this)) {

                        if (result.data.getData() != null) {
                            BDG_GetAllUserResponse data = result.data;
                            if (data.getStatus() && data.getData().getIsNextPage()!= null) {
                                if (data.getData().getUser() != null && data.getData().getUser().size() > 0) {
                                    gloryrollrank.setLoaded();
                                    ArrayList<BDG_GetAllUsersItem> uniqueList = RollRankgetUniqueJsonList(data.getData().getUser()); // Corrected cast
                                    if (RollRankpage == 1) {
                                        if (uniqueList.size() > 0) {
                                            RollRankuserDataList.addAll(uniqueList);
                                            gloryrollrank.notifyItemInserted(gloryrollrank.getItemCount());
                                        } else {
                                            RollRankappendOfflineData(RollRankpage, data.getData().getIsNextPage());
                                        }
                                    } else {
                                        RollRankuserDataList.addAll(uniqueList);
                                        gloryrollrank.notifyItemInserted(gloryrollrank.getItemCount());
                                    }
                                } else {
                                    RollRankappendOfflineData(RollRankpage, data.getData().getIsNextPage());
                                }
                                if (data.getData().getIsNextPage()) {
                                    gloryrollrank.RollpaginationCounter = RollRankpage + 1;
                                    gloryrollrank.Roll_startPaging = true;
                                } else {
                                    gloryrollrank.Roll_startPaging = false;
                                    RollRankaddOfflineData();
                                    RollRankisOfflineAppend = true;
                                }
                            } else {
                                bothsnackBars(allbindData.getRoot(), "Something went wrong please try again", 1);
                            }
                        } else {
                            bothsnackBars(allbindData.getRoot(), "Something went wrong please try again", 1);
                        }
                        HideSpin();
                    }
                    HideSpin();
                    break;

                case NOMALYU:
                    bothsnackBars(allbindData.getRoot(), "Something went wrong please try again", 1);
                    HideSpin();
                    break;
            }
        });
        RollRankvModel.GetcountryResponsebdg().observe(this, result -> {
            if (result == null)
                return;
            switch (result.status) {
                case RAHJOVO:

                    break;
                case MALYU:
                    if (result.data != null && result.data.getData() != null) {
                        RollRankcountryArrayList.clear();
                        RollRankcountryArrayList.addAll(result.data.getData());
                        intAdapter();
                    } else {
                        bothsnackBars(allbindData.getRoot(), "Something went wrong please try again", 1);
                    }
                    HideSpin();
                    break;

                case NOMALYU:
                   HideSpin();
                    bothsnackBars(allbindData.getRoot(), "Something went wrong please try again", 1);
                    break;
            }
        });

    }

    @Override
    protected void viewAds() {
        RollRankhideView = findViewById(com.doc.gradient.bt.server.uses.ai.ads.R.id.hideView);
        showBannerAds(RollRankhideView, GloryRollRank.this);
    }

    private void callAllUsers() {
        ShowSpin(GloryRollRank.this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(GloryRollRank.this, RecyclerView.VERTICAL, false);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        allbindData.userForRecyclerViewbdg.setLayoutManager(linearLayoutManager);

        gloryrollrank = new GloryRollRankAdapter(this, RollRankuserDataList, allbindData.userForRecyclerViewbdg); // Pass Activity context
        allbindData.userForRecyclerViewbdg.setAdapter(gloryrollrank);

        gloryrollrank.setOnLoadMoreListener(this);

        gloryrollrank.setPageAppendListener(new PosterNextPageWatcher_Listener() {
            @Override
            public void NextPageonPageAppendClick(int page) {
                allbindData.userForRecyclerViewbdg.post(() -> {
                    try {
                        RollRankuserDataList.remove(RollRankuserDataList.size() - 1);
                        gloryrollrank.notifyItemRemoved(RollRankuserDataList.size());
                        PosterFetchonLoadMore(page, true);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
            }

            @Override
            public void NextonPageAppendClick() {
            }

            @Override
            public void NextPageshowBottomToTop(boolean isShow) {
                if (isShow) {
                    if (RollRankisShow) {
                        allbindData.btnBottomTopbdg.setVisibility(View.VISIBLE);
                    }
                } else {
                    allbindData.btnBottomTopbdg.setVisibility(View.GONE);
                }
            }
        });
    }

    private void AddDataForUserByFRegion() {
        RollRankvModel.GetcountryApibdg(new Countryreq(Project_APP_ID));
    }

    private void refreshView() {
        RollRankuserDataList.clear();
        RollRankisOfflineAppend = false;
        if (gloryrollrank != null) {
            gloryrollrank.notifyDataSetChanged();
        }
        getAllSample(1);
    }

    private void getAllSample(int page) {
        this.RollRankpage = page;
        RollRankhidePageAppendView();
        if (gloryrollrank != null) {
            gloryrollrank.Roll_startPaging = false;
        }
        ShowSpin(this);
        RollRankvModel.GetuserApibdg( new GetUserreq(page, Project_TOTAL_ITEM_COUNT,Project_APP_ID));
    }

    private void RollRankhidePageAppendView() {
        try {
            if (RollRankuserDataList.size() > 0 && RollRankuserDataList.get(RollRankuserDataList.size() - 1) != null && Objects.equals(RollRankuserDataList.get(RollRankuserDataList.size() - 1).getId(), ProjectConstantsJava.Project_TYPE_REFRESH)) {
                RollRankuserDataList.remove(RollRankuserDataList.size() - 1);
                gloryrollrank.notifyItemRemoved(RollRankuserDataList.size());
            } else if (RollRankuserDataList.size() > 1 && RollRankuserDataList.get(RollRankuserDataList.size() - 2) != null && Objects.equals(RollRankuserDataList.get(RollRankuserDataList.size() - 2).getId(), ProjectConstantsJava.Project_TYPE_REFRESH)) {
                RollRankuserDataList.remove(RollRankuserDataList.size() - 2);
                gloryrollrank.notifyItemRemoved(RollRankuserDataList.size());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private ArrayList<BDG_GetAllUsersItem> RollRankgetUniqueJsonList(ArrayList<BDG_GetAllUsersItem> newJsonList) {
        ArrayList<BDG_GetAllUsersItem> tempList = new ArrayList<>();
        if (RollRankuserDataList.size() == 0) {
            tempList.clear();
            tempList.addAll(newJsonList);
        } else if (newJsonList != null && newJsonList.size() != 0) {
            for (BDG_GetAllUsersItem newList : newJsonList) {
                String jsonId = String.valueOf(newList.getId());
                boolean isMatch = false;
                for (BDG_GetAllUsersItem oldList : RollRankuserDataList) {
                    if (oldList != null && Objects.equals(oldList.getId(), jsonId)) {
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

    private void RollRankshowLoadMoreButton() {
        gloryrollrank.setLoaded();
        allbindData.userForRecyclerViewbdg.post(() -> {
            try {
                if (RollRankuserDataList.size() == 0 || (RollRankuserDataList.get(RollRankuserDataList.size() - 1) != null && !Objects.equals(RollRankuserDataList.get(RollRankuserDataList.size() - 1).getId(), ProjectConstantsJava.Project_TYPE_REFRESH))) {
                    RollRankuserDataList.add(new BDG_GetAllUsersItem(ProjectConstantsJava.Project_TYPE_REFRESH));
                    gloryrollrank.notifyItemInserted(RollRankuserDataList.size() - 1);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private void RollRankaddOfflineData() {
        RollRankhidePageLoadView();
        RollRankhidePageAppendView();
        if (!RollRankisOfflineAppend) {
            ArrayList<BDG_GetAllUsersItem> local = new ArrayList<>(); // Corrected type
            if (local.size() > 0) {
                RollRankuserDataList.addAll(local);
                gloryrollrank.notifyItemInserted(gloryrollrank.getItemCount());
                RollRankisOfflineAppend = true;
            }
        }
    }

    private void RollRankappendOfflineData(int page, Boolean isNextPage) { // Changed Boolean isNextPage
        RollRankhidePageLoadView();
        RollRankhidePageAppendView();
        if (page == 1 && (RollRankuserDataList.size() == 0)) {
            ArrayList<BDG_GetAllUsersItem> local = new ArrayList<>();  // Corrected type
            if (local.size() > 0) {
                RollRankuserDataList.addAll(local);
                gloryrollrank.notifyItemInserted(gloryrollrank.getItemCount());
                RollRankisOfflineAppend = true;
            }
        }
        if (isNextPage) {
            RollRankshowLoadMoreButton();
        }
    }

    private void RollRankhidePageLoadView() {
        if (RollRankuserDataList.size() > 0 && RollRankuserDataList.get(RollRankuserDataList.size() - 1) == null) {
            try {
                RollRankuserDataList.remove(RollRankuserDataList.size() - 1);
                gloryrollrank.notifyItemRemoved(RollRankuserDataList.size());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void intAdapter() {
        if (!RollRankcountryArrayList.isEmpty() && RollRankcountryArrayList != null) { // added null check
            LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(this);
            linearLayoutManager1.setOrientation(LinearLayoutManager.VERTICAL);
            allbindData.recyclerviewUserByRegionbdg.setLayoutManager(linearLayoutManager1);
            allbindData.recyclerviewUserByRegionbdg.setLayoutManager(linearLayoutManager1);
            allbindData.recyclerviewUserByRegionbdg.setHasFixedSize(true);
            allbindData.recyclerviewUserByRegionbdg.setDrawingCacheEnabled(true);
            allbindData.recyclerviewUserByRegionbdg.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
            userByRegionAdapter = new GloryCountryRankAdapter(this, RollRankcountryArrayList);
            allbindData.recyclerviewUserByRegionbdg.setAdapter(userByRegionAdapter);
        }
    }

    @Override
    public void PosterFetchonLoadMore(int page, Boolean isLoadMore) { // Changed Boolean isLoadMore
        allbindData.userForRecyclerViewbdg.post(() -> {
            try {
                RollRankuserDataList.add(null);
                gloryrollrank.notifyItemInserted(RollRankuserDataList.size() - 1);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        if (isLoadMore) {
            getAllSample(page);
        } else {
            allbindData.userForRecyclerViewbdg.post(() -> {
                try {
                    RollRankuserDataList.remove(RollRankuserDataList.size() - 1);
                    gloryrollrank.notifyItemRemoved(RollRankuserDataList.size());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
    }

    @Override
    public void PosterFetchonPopularTagLoadMore(int page, Boolean isLoadMore) {
    }
}
