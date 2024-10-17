package com.sopt.presentation.ui.screen.search.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.sopt.presentation.ui.state.VideoOverviewViewState

class SearchViewModel(
    val savedStateHandle: SavedStateHandle
): ViewModel() {

    val searchQuery = savedStateHandle.getStateFlow(SEARCH_QUERY, "")

    val displayedVideoOverviews = listOf(
            VideoOverviewViewState(
                id = 1,
                title = "나는 SOLO",
                titleImage = "https://i.namu.wiki/i/naUet4gLa7vaSW9sEH3miT1LNxdoD_CxrjCBYR5BAmEmSDOWNf-rELb9ioqiaMyW7IBEpG0DxRLHvYWhBCkw6Q.webp",
                description = "sdsds"
            ),
            VideoOverviewViewState(
                id = 1,
                title = "라디오스타",
                titleImage = "https://img.imbc.com/template/2023/09/program_36b7867b-0798-44b9-827c-e27b2bcc79c2.jpg",
                description = "ㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹ"
            ), VideoOverviewViewState(
                id = 1,
                title = "고딩엄빠",
                titleImage = "https://img.mbn.co.kr/program/895/2022/02/23/164557455315.jpg",
                description = "ㅎㅀㅎㅎㅎㅎㅎㅎㅎㅎㅎ"
            ), VideoOverviewViewState(
                id = 1,
                title = "골 때리는 그녀들",
                titleImage = "https://img2.sbs.co.kr/img/sbs_cms/WE/2023/08/17/6uv1692260719254.jpg",
                description = "금쪽ㅇㅈㅇㅈ이"
            ), VideoOverviewViewState(
                id = 1,
                title = "백종원의 골목식당",
                titleImage = "https://img2.sbs.co.kr/img/sbs_cms/WE/2021/05/11/enN1620692371424.jpg",
                description = "ㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎ"
            ), VideoOverviewViewState(
                id = 1,
                title = "지옥에서 온 판사",
                titleImage = "https://img2.sbs.co.kr/img/sbs_cms/WE/2024/09/02/ejm1725238865329-640-360.jpg",
                description = "ㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇ"
            ), VideoOverviewViewState(
                id = 1,
                title = "개소리",
                titleImage = "https://programres.kbs.co.kr/t2024-0471/2024/8/28/1724810940534_536600.jpg",
                description = "ㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹ"
            ), VideoOverviewViewState(
                id = 1,
                title = "런닝맨",
                titleImage = "https://img2.sbs.co.kr/img/sbs_cms/WE/2024/08/16/eWM1723784366763.jpg",
                description = "ㅈㅇㅈㅇ"
            ), VideoOverviewViewState(
                id = 1,
                title = "나 혼자 산다",
                titleImage = "https://i.namu.wiki/i/jUG8Huo01jsCzBPmr7GvebPacH3ULreD9rvSfc1L7hhFwREWVreTtnYTS_z-BD-7vvnSNjh_lSkb4bjtz_ZN9g.webp",
                description = "ㄴ"
            ), VideoOverviewViewState(
                id = 1,
                title = "이토록 친밀한 배신자",
                titleImage = "https://img.imbc.com/template/2024/09/program_c5f4923a-7720-450d-88a4-990e455e81ab.jpg",
                description = "ㄴㅇㄴㅇ"
            ), VideoOverviewViewState(
                id = 1,
                title = "강철부대",
                titleImage = "https://image.tving.com/ntgs/contents/CTC/caip/CAIP1500/ko/20240920/0511/P001761839.jpg/dims/resize/1280",
                description = "ㅗㅗㅗㅗㅗ"
            )
        )


    fun onSearchQueryChanged(query: String) {
        savedStateHandle[SEARCH_QUERY] = query
    }
    
    companion object {
        const val SEARCH_QUERY = "searchQuery"
    }
}