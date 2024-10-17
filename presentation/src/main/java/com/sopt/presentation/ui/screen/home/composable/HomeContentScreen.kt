package com.sopt.presentation.ui.screen.home.composable

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.sopt.presentation.ui.component.image.WavveLogoImage
import com.sopt.presentation.ui.component.text.SecondaryText
import com.sopt.presentation.ui.component.top.DefaultTopAppBar
import com.sopt.presentation.ui.screen.home.type.VideoType
import com.sopt.presentation.ui.state.CommonVideoOverviewsViewState
import com.sopt.presentation.ui.state.VideoOverviewViewState
import com.sopt.presentation.ui.theme.WavveTheme
import com.sopt.presentation.ui.util.noRippleClickable

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun HomeContentScreen(
    modifier: Modifier = Modifier,
    headVideoOverviews: List<VideoOverviewViewState>,
    commonVideoOverviews: List<CommonVideoOverviewsViewState>,
    topVideoOverviews: CommonVideoOverviewsViewState,
    onVideoTypeSelected: (VideoType) -> Unit,
) {

    val headDisplayPagerState = rememberPagerState { headVideoOverviews.size }

    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            DefaultTopAppBar(
                title = {
                    WavveLogoImage()
                }
            )
        }

        stickyHeader {
            VideoTypeTabRow(
                modifier = Modifier
                    .background(WavveTheme.colorScheme.background)
                    .padding(horizontal = 16.dp)
                    .padding(top = 4.dp, bottom = 12.dp),
                onVideoTypeSelected = onVideoTypeSelected
            )
        }

        item {
            HeadDisplayedVideoHorizontalPager(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(500.dp),
                state = headDisplayPagerState,
                videoOverviews = headVideoOverviews,
                onVideoClicked = { }
            )
        }

        items(count = commonVideoOverviews.size) {
            Spacer(modifier = Modifier.height(12.dp))
            CommonVideosHorizontalPager(
                modifier = Modifier.fillMaxWidth(),
                commonVideoOverview = commonVideoOverviews[it],
                onVideoClicked = { }
            )
        }

        item {
            Spacer(modifier = Modifier.height(12.dp))
            TodayTopVideosPager(
                modifier = Modifier.fillMaxWidth(),
                commonVideoOverview = topVideoOverviews,
                onVideoClicked = { }
            )
        }

        items(count = commonVideoOverviews.size) {
            Spacer(modifier = Modifier.height(12.dp))
            CommonVideosHorizontalPager(
                modifier = Modifier.fillMaxWidth(),
                commonVideoOverview = commonVideoOverviews[it],
                onVideoClicked = { }
            )
        }

        item {
            SecondaryText(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                text = """
    콘텐츠웨이브 주식회사 / 대표이사: 이태현
    주소: 서울특별시 영등포구 여의나루로 60 포스트타워 19층
    사업자등록번호: 220-88-38020
    
    
    통신판매업 신고번호: 제 2021-서울영등포-0585호
    통신판매업 정보 공개:
    http://www.ftc.go.kr/bizCommPop.do?wrkr_no=220-88-38020 호스팅 서비스 제공자: 마이크로포스트 유한회사, 구글클라우드코리아 유한회사, 아마존웹서비시즈코리아 유한회사
    
    
    고객센터: 1599-3709 (평일 00:00~18:00 / 점심시간 12:00~13:00 / 주말 및 공휴일 휴무)
    전자우편주소: helpdesk@wavve.com
    
    
    Copyright © 콘텐츠웨이브(주) All Rights Reserved.
    
""".trimIndent()
            )
        }
    }
}

@Composable
private fun VideoTypeTabRow(
    modifier: Modifier = Modifier,
    onVideoTypeSelected: (VideoType) -> Unit
) {
    Row(
        modifier = modifier.horizontalScroll(rememberScrollState()),
        horizontalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        VideoType.entries.forEach {
            SecondaryText(
                modifier = Modifier.noRippleClickable(onClick = { onVideoTypeSelected(it) }),
                text = stringResource(it.titleResId),
                style = WavveTheme.typography.bodyLarge,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}
