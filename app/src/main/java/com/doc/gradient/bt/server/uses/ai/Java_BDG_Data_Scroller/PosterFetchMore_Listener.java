package com.doc.gradient.bt.server.uses.ai.Java_BDG_Data_Scroller;

public interface PosterFetchMore_Listener {
    void PosterFetchonLoadMore(int page, Boolean isLoadMore);

    void PosterFetchonPopularTagLoadMore(int page, Boolean isLoadMore);
}
