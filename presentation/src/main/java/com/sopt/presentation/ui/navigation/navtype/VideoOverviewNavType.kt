package com.sopt.presentation.ui.navigation.navtype

import android.net.Uri
import android.os.Bundle
import androidx.navigation.NavType
import com.sopt.presentation.ui.state.VideoOverviewViewState
import com.sopt.presentation.ui.util.getParcelableCompat
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

internal object VideoOverviewNavType : NavType<VideoOverviewViewState>(
    isNullableAllowed = false
) {
    override fun get(bundle: Bundle, key: String): VideoOverviewViewState {
        return bundle.getParcelableCompat<VideoOverviewViewState>(key) as VideoOverviewViewState
    }

    override fun parseValue(value: String): VideoOverviewViewState {
        return Json.decodeFromString<VideoOverviewViewState>(value)
    }

    override fun put(bundle: Bundle, key: String, value: VideoOverviewViewState) {
        bundle.putParcelable(key, value)
    }

    override fun serializeAsValue(value: VideoOverviewViewState): String {
        return Uri.encode(Json.encodeToString(value))
    }
}