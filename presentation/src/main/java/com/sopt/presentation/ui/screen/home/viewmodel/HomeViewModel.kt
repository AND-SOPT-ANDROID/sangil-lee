package com.sopt.presentation.ui.screen.home.viewmodel

import androidx.lifecycle.ViewModel
import com.sopt.presentation.ui.state.CommonVideoOverviewsViewState
import com.sopt.presentation.ui.state.VideoOverviewViewState

class HomeViewModel: ViewModel() {

    /* TODO : 임시 데이터 */
    val headVideoOverviews: List<VideoOverviewViewState> = listOf(
        VideoOverviewViewState(
            id = 1,
            title = "나의 아저씨",
            titleImage = "https://image.tmdb.org/t/p/original/o5cBRMF6syh4yUBgZ2Ust8LvcxA.jpg",
            description = "나의 아저씨 아이유"
        ), VideoOverviewViewState(
            id = 1,
            title = "호텔델루나",
            titleImage = "https://i.namu.wiki/i/wIAb2jXT5mzO-tWlADEz5z47I1RoW3RzIULQDXNBj58yGKEgBLdZknJIm5tWgmLSnEKD6URFsFLo1lhCW7vNvg.webp",
            description = "ㅇㅇㅇㅇㅇ"
        ), VideoOverviewViewState(
            id = 1,
            title = "데드풀과 울버린",
            titleImage = "https://i.namu.wiki/i/Iw90hSU6JdUAOsAmuNqMQ56SSzubq8eAQEzZfziufEskrUh9ZidDjMhUzU96kCp8dKmSPGlcoP3eYutpU7Paww.webp",
            description = "데드풀과 울버린과 데드풀과 울버린과 데드풀"
        )
    )
    val commonVideoOverviews: List<CommonVideoOverviewsViewState> = listOf(
        CommonVideoOverviewsViewState(
            kindDescription = "믿고 보는 웨이브 에디터 추천작",
            videoOverviews = listOf(
                VideoOverviewViewState(
                    id = 1,
                    title = "조커2",
                    titleImage = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTx76yhwnvWtNwJBftU9PTiPUZypB8VRa7cgA&s",
                    description = "망"
                ), VideoOverviewViewState(
                    id = 1,
                    title = "나의 아저씨",
                    titleImage = "https://i.namu.wiki/i/wavbYsNgMzAeHOpD7MDDL9xYbxhkIi_xvNKEjiIVrUUnxmkY3peOAjF4NP5RDwgpIwse-LZfQooGtlP2YUBK_g.webp",
                    description = "나의 아저씨 아이유"
                ), VideoOverviewViewState(
                    id = 1,
                    title = "그 해 우리는",
                    titleImage = "https://assets.repress.co.kr/photos/14a22b926c857076a950ee259c839d66/original.jpg",
                    description = "ㅇㄹㅇㄹㅇㄹㅇㄹㄹ"
                ), VideoOverviewViewState(
                    id = 1,
                    title = "흑백요리사",
                    titleImage = "https://talkimg.imbc.com/TVianUpload/tvian/TViews/image/2024/08/20/9088a7a5-1715-4855-bf58-220a37ca4f46.jpg",
                    description = "even"
                ), VideoOverviewViewState(
                    id = 1,
                    title = "원피스",
                    titleImage = "https://i.namu.wiki/i/r52RyMBi9Fmclux64jb7AlQl6XgVIn2yz1_FJFfEyg-gqxyXYaLZjXCYlKvtvnHjt1Q0hAE6JQ6kGH05EE8Zwg.webp",
                    description = "ㄹㄷㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠ"
                ), VideoOverviewViewState(
                    id = 1,
                    title = "지옥",
                    titleImage = "https://i.namu.wiki/i/4pTc29uQid1LuQVUb22jBGCaqP1YOwPaHBzfAye5ZZ9lZLGhF3NjGyW4ZdhZ87IuDfmojU-9SCP52ccecErc4w.webp",
                    description = "ㅇㅈㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎ"
                ), VideoOverviewViewState(
                    id = 1,
                    title = "오징어게임",
                    titleImage = "https://i.namu.wiki/i/5rIzALAdaF2QDaYgzJSKkfsgWS9gTUlq-i9QnmGKRftr4ny_pUjZRy5_IA96zFL4MkJ4T2F8xVKSXY6HHVRaRw.webp",
                    description = "456456456"
                )
            )
        ), CommonVideoOverviewsViewState(
            kindDescription = "실시간 인기 콘텐츠",
            videoOverviews = listOf(
                VideoOverviewViewState(
                    id = 1,
                    title = "무한도전",
                    titleImage = "https://image.tmdb.org/t/p/w500/4Nn3JUVrB4O6Qqo5yze051OG07O.jpg",
                    description = "무한도전무한도전"
                ),
                VideoOverviewViewState(
                    id = 1,
                    title = "나혼자산다",
                    titleImage = "https://talkimg.imbc.com/TVianUpload/tvian/TViews/image/2022/03/12/U6O1e6h0J81C637826787433233791.jpg",
                    description = "ㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹ"
                ), VideoOverviewViewState(
                    id = 1,
                    title = "런닝맨",
                    titleImage = "https://i.namu.wiki/i/TbtnALr4A7RG9e5v8YxRAusQ23_cBzaEej_CVBRJp5wx_OrKM9BUESk0mIPbLvS76knR1KnR4YyT1s3Sp-RRdg.webp",
                    description = "ㅎㅀㅎㅎㅎㅎㅎㅎㅎㅎㅎ"
                ), VideoOverviewViewState(
                    id = 1,
                    title = "오은영의 금쪽상담소",
                    titleImage = "https://i.namu.wiki/i/1HYrJrBZCykBpCy49k6kUGCScyRaA8QCwC745PUXMBYZEplFt2PGOOXHa_yTNG0SX1MpRdLBXlEBXFDhGRakXQ.webp",
                    description = "금쪽이"
                ), VideoOverviewViewState(
                    id = 1,
                    title = "백종원의 골목식당",
                    titleImage = "https://dimg.donga.com/wps/NEWS/IMAGE/2019/01/28/93897544.2.jpg",
                    description = "ㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎ"
                ), VideoOverviewViewState(
                    id = 1,
                    title = "신서유기",
                    titleImage = "https://i.namu.wiki/i/0-RXBLtGoNqcyV9TEiLD-ftdqS42a9jc5r29EsDcqMGENwCpFShtSfHfjxx4jliGnuy-wtnKZPtx2mCpi-lXaw.webp",
                    description = "ㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇ"
                ), VideoOverviewViewState(
                    id = 1,
                    title = "쇼미더머니",
                    titleImage = "https://upload.wikimedia.org/wikipedia/ko/1/1f/Show_Me_the_Money_11.jpg",
                    description = "ㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹ"
                ), VideoOverviewViewState(
                    id = 1,
                    title = "아는형님",
                    titleImage = "https://file.mk.co.kr/meet/neds/2021/09/image_readtop_2021_924917_16328769694798426.jpg",
                    description = "형"
                ), VideoOverviewViewState(
                    id = 1,
                    title = "놀면뭐하니?",
                    titleImage = "https://i.namu.wiki/i/vjCVCHwKFR7zJm5GIxtEOfPzz8h0_r_nVXe4wfsFMgeKUoeLXDyfgyBho83Ws5nIA-J5CsRFZu5zYU8pENAcBA.webp",
                    description = "ㄴ"
                ), VideoOverviewViewState(
                    id = 1,
                    title = "유퀴즈온더블럭",
                    titleImage = "https://i.namu.wiki/i/ReeZ1gxKh-IZhcIFOsGT7KRDKqeN_F-DCaueY0XIgBRDGPa6YkK4iLHfV7kHA6YXBu-vsZW_8EVtdiZ-2duk-Q.webp",
                    description = "유퀴즈온더블럭"
                ), VideoOverviewViewState(
                    id = 1,
                    title = "어서와한국은처음이지",
                    titleImage = "https://m.mbcplus.com/upload/editor/20180508/20180508091207086336.png",
                    description = "ㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎ"
                )
            )
        ), CommonVideoOverviewsViewState(
            kindDescription = "오직 웨이브에서",
            videoOverviews = listOf(
                VideoOverviewViewState(
                    id = 1,
                    title = "무한도전",
                    titleImage = "https://image.tmdb.org/t/p/w500/4Nn3JUVrB4O6Qqo5yze051OG07O.jpg",
                    description = "무한도전무한도전"
                ),
                VideoOverviewViewState(
                    id = 1,
                    title = "나혼자산다",
                    titleImage = "https://talkimg.imbc.com/TVianUpload/tvian/TViews/image/2022/03/12/U6O1e6h0J81C637826787433233791.jpg",
                    description = "ㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹ"
                ), VideoOverviewViewState(
                    id = 1,
                    title = "런닝맨",
                    titleImage = "https://i.namu.wiki/i/TbtnALr4A7RG9e5v8YxRAusQ23_cBzaEej_CVBRJp5wx_OrKM9BUESk0mIPbLvS76knR1KnR4YyT1s3Sp-RRdg.webp",
                    description = "ㅎㅀㅎㅎㅎㅎㅎㅎㅎㅎㅎ"
                ), VideoOverviewViewState(
                    id = 1,
                    title = "오은영의 금쪽상담소",
                    titleImage = "https://i.namu.wiki/i/1HYrJrBZCykBpCy49k6kUGCScyRaA8QCwC745PUXMBYZEplFt2PGOOXHa_yTNG0SX1MpRdLBXlEBXFDhGRakXQ.webp",
                    description = "금쪽이"
                ), VideoOverviewViewState(
                    id = 1,
                    title = "백종원의 골목식당",
                    titleImage = "https://dimg.donga.com/wps/NEWS/IMAGE/2019/01/28/93897544.2.jpg",
                    description = "ㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎ"
                ), VideoOverviewViewState(
                    id = 1,
                    title = "신서유기",
                    titleImage = "https://i.namu.wiki/i/0-RXBLtGoNqcyV9TEiLD-ftdqS42a9jc5r29EsDcqMGENwCpFShtSfHfjxx4jliGnuy-wtnKZPtx2mCpi-lXaw.webp",
                    description = "ㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇ"
                ), VideoOverviewViewState(
                    id = 1,
                    title = "쇼미더머니",
                    titleImage = "https://upload.wikimedia.org/wikipedia/ko/1/1f/Show_Me_the_Money_11.jpg",
                    description = "ㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹ"
                ), VideoOverviewViewState(
                    id = 1,
                    title = "아는형님",
                    titleImage = "https://file.mk.co.kr/meet/neds/2021/09/image_readtop_2021_924917_16328769694798426.jpg",
                    description = "형"
                ), VideoOverviewViewState(
                    id = 1,
                    title = "놀면뭐하니?",
                    titleImage = "https://i.namu.wiki/i/vjCVCHwKFR7zJm5GIxtEOfPzz8h0_r_nVXe4wfsFMgeKUoeLXDyfgyBho83Ws5nIA-J5CsRFZu5zYU8pENAcBA.webp",
                    description = "ㄴ"
                ), VideoOverviewViewState(
                    id = 1,
                    title = "유퀴즈온더블럭",
                    titleImage = "https://i.namu.wiki/i/ReeZ1gxKh-IZhcIFOsGT7KRDKqeN_F-DCaueY0XIgBRDGPa6YkK4iLHfV7kHA6YXBu-vsZW_8EVtdiZ-2duk-Q.webp",
                    description = "유퀴즈온더블럭"
                ), VideoOverviewViewState(
                    id = 1,
                    title = "어서와한국은처음이지",
                    titleImage = "https://m.mbcplus.com/upload/editor/20180508/20180508091207086336.png",
                    description = "ㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎ"
                )
            ).reversed()
        )
    )

    val topVideoOverviews: CommonVideoOverviewsViewState = CommonVideoOverviewsViewState(
        kindDescription = "오늘의 TOP 20",
        videoOverviews = listOf(
            VideoOverviewViewState(
                id = 1,
                title = "나는 SOLO",
                titleImage = "https://ena.skylifetv.co.kr/data/file/variety/2887450690_bXmj3MDE_918901dbabb385cba070d53117d45fd0b3f6d2cc.jpg",
                description = "ㅇㅈㅇㅈㅇㅈㅇ"
            ),
            VideoOverviewViewState(
                id = 1,
                title = "무한도전",
                titleImage = "https://image.tmdb.org/t/p/w500/4Nn3JUVrB4O6Qqo5yze051OG07O.jpg",
                description = "무한도전무한도전"
            ),
            VideoOverviewViewState(
                id = 1,
                title = "환승연애",
                titleImage = "https://cdnimage.dailian.co.kr/news/202402/news_1706920016_1324836_m_1.jpeg",
                description = "ㅇㅈㅇㅈㅇ"
            ),
            VideoOverviewViewState(
                id = 1,
                title = "나혼자산다",
                titleImage = "https://talkimg.imbc.com/TVianUpload/tvian/TViews/image/2022/03/12/U6O1e6h0J81C637826787433233791.jpg",
                description = "ㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹ"
            ), VideoOverviewViewState(
                id = 1,
                title = "런닝맨",
                titleImage = "https://i.namu.wiki/i/TbtnALr4A7RG9e5v8YxRAusQ23_cBzaEej_CVBRJp5wx_OrKM9BUESk0mIPbLvS76knR1KnR4YyT1s3Sp-RRdg.webp",
                description = "ㅎㅀㅎㅎㅎㅎㅎㅎㅎㅎㅎ"
            ), VideoOverviewViewState(
                id = 1,
                title = "오은영의 금쪽상담소",
                titleImage = "https://i.namu.wiki/i/1HYrJrBZCykBpCy49k6kUGCScyRaA8QCwC745PUXMBYZEplFt2PGOOXHa_yTNG0SX1MpRdLBXlEBXFDhGRakXQ.webp",
                description = "금쪽이"
            ), VideoOverviewViewState(
                id = 1,
                title = "백종원의 골목식당",
                titleImage = "https://dimg.donga.com/wps/NEWS/IMAGE/2019/01/28/93897544.2.jpg",
                description = "ㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎ"
            ), VideoOverviewViewState(
                id = 1,
                title = "신서유기",
                titleImage = "https://i.namu.wiki/i/0-RXBLtGoNqcyV9TEiLD-ftdqS42a9jc5r29EsDcqMGENwCpFShtSfHfjxx4jliGnuy-wtnKZPtx2mCpi-lXaw.webp",
                description = "ㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇ"
            ), VideoOverviewViewState(
                id = 1,
                title = "쇼미더머니",
                titleImage = "https://upload.wikimedia.org/wikipedia/ko/1/1f/Show_Me_the_Money_11.jpg",
                description = "ㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹ"
            ), VideoOverviewViewState(
                id = 1,
                title = "아는형님",
                titleImage = "https://file.mk.co.kr/meet/neds/2021/09/image_readtop_2021_924917_16328769694798426.jpg",
                description = "형"
            ), VideoOverviewViewState(
                id = 1,
                title = "놀면뭐하니?",
                titleImage = "https://i.namu.wiki/i/vjCVCHwKFR7zJm5GIxtEOfPzz8h0_r_nVXe4wfsFMgeKUoeLXDyfgyBho83Ws5nIA-J5CsRFZu5zYU8pENAcBA.webp",
                description = "ㄴ"
            ), VideoOverviewViewState(
                id = 1,
                title = "유퀴즈온더블럭",
                titleImage = "https://i.namu.wiki/i/ReeZ1gxKh-IZhcIFOsGT7KRDKqeN_F-DCaueY0XIgBRDGPa6YkK4iLHfV7kHA6YXBu-vsZW_8EVtdiZ-2duk-Q.webp",
                description = "유퀴즈온더블럭"
            ), VideoOverviewViewState(
                id = 1,
                title = "어서와한국은처음이지",
                titleImage = "https://m.mbcplus.com/upload/editor/20180508/20180508091207086336.png",
                description = "ㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎ"
            ), VideoOverviewViewState(
                id = 1,
                title = "조커2",
                titleImage = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTx76yhwnvWtNwJBftU9PTiPUZypB8VRa7cgA&s",
                description = "망"
            ), VideoOverviewViewState(
                id = 1,
                title = "나의 아저씨",
                titleImage = "https://i.namu.wiki/i/wavbYsNgMzAeHOpD7MDDL9xYbxhkIi_xvNKEjiIVrUUnxmkY3peOAjF4NP5RDwgpIwse-LZfQooGtlP2YUBK_g.webp",
                description = "나의 아저씨 아이유"
            ), VideoOverviewViewState(
                id = 1,
                title = "그 해 우리는",
                titleImage = "https://assets.repress.co.kr/photos/14a22b926c857076a950ee259c839d66/original.jpg",
                description = "ㅇㄹㅇㄹㅇㄹㅇㄹㄹ"
            ), VideoOverviewViewState(
                id = 1,
                title = "흑백요리사",
                titleImage = "https://talkimg.imbc.com/TVianUpload/tvian/TViews/image/2024/08/20/9088a7a5-1715-4855-bf58-220a37ca4f46.jpg",
                description = "even"
            ), VideoOverviewViewState(
                id = 1,
                title = "원피스",
                titleImage = "https://i.namu.wiki/i/r52RyMBi9Fmclux64jb7AlQl6XgVIn2yz1_FJFfEyg-gqxyXYaLZjXCYlKvtvnHjt1Q0hAE6JQ6kGH05EE8Zwg.webp",
                description = "ㄹㄷㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠ"
            ), VideoOverviewViewState(
                id = 1,
                title = "지옥",
                titleImage = "https://i.namu.wiki/i/4pTc29uQid1LuQVUb22jBGCaqP1YOwPaHBzfAye5ZZ9lZLGhF3NjGyW4ZdhZ87IuDfmojU-9SCP52ccecErc4w.webp",
                description = "ㅇㅈㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎ"
            ), VideoOverviewViewState(
                id = 1,
                title = "오징어게임",
                titleImage = "https://i.namu.wiki/i/5rIzALAdaF2QDaYgzJSKkfsgWS9gTUlq-i9QnmGKRftr4ny_pUjZRy5_IA96zFL4MkJ4T2F8xVKSXY6HHVRaRw.webp",
                description = "456456456"
            )
        )
    )
}