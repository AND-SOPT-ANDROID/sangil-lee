package com.sopt.presentation.ui.screen.home.type

import androidx.annotation.StringRes
import com.sopt.presentation.R

enum class VideoType(
    @StringRes val titleResId: Int
) {
    NEW_CLASSIC(R.string.type_new_classic),
    DRAMA(R.string.type_drama),
    ENTERTAINMENT(R.string.type_entertainment),
    MOVIE(R.string.type_movie),
    ANIMATION(R.string.type_animation),
    ABROAD_SERIES(R.string.type_abroad_series),
    INFORMATION_CULTURE(R.string.type_information_culture),
    KIDS(R.string.type_kids),
    MOVIE_PLUS(R.string.type_movie_plus),
}