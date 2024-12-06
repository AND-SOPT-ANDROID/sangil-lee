package com.sopt.presentation.ui.screen.my.composable

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sopt.presentation.ui.component.surface.DefaultSurface
import com.sopt.presentation.ui.screen.my.viewmodel.MyViewModel

@Composable
fun MyScreen(
    modifier: Modifier = Modifier,
    onNavigateToEditProfile: () -> Unit,
    viewModel: MyViewModel = hiltViewModel()
) {

    val myHobby by viewModel.myHobby.collectAsStateWithLifecycle()

    DefaultSurface(
        modifier = modifier
            .fillMaxSize()
    ) {
        MyContentScreen(
            modifier = Modifier
                .fillMaxSize(),
            myHobby = myHobby,
            onNavigateToEditProfile = onNavigateToEditProfile
        )
    }
}