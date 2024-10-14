package com.sopt.presentation.ui.screen.signup.composable

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sopt.presentation.R
import com.sopt.presentation.ui.component.icon.PrimaryIcon
import com.sopt.presentation.ui.component.surface.WavveDefaultSurface
import com.sopt.presentation.ui.component.top.DefaultCenterAlignedTopAppBar
import com.sopt.presentation.ui.screen.signup.viewmodel.SignUpViewModel
import com.sopt.presentation.util.noRippleClickable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(
    modifier: Modifier = Modifier,
    onActionIconClicked: () -> Unit = {},
    onSignUpComplete: () -> Unit,
    viewModel: SignUpViewModel = viewModel(),
) {

    var emailInput = viewModel.email.collectAsStateWithLifecycle()
    var passwordInput = viewModel.password.collectAsStateWithLifecycle()

    Scaffold(
        modifier = modifier,
        topBar = {
            DefaultCenterAlignedTopAppBar(
                title = {
                    Text(stringResource(R.string.title_sign_up))
                },
                modifier = Modifier,
                actions = {
                    PrimaryIcon(
                        modifier = Modifier.size(28.dp).padding(end = 8.dp).noRippleClickable(onClick = onActionIconClicked),
                        painter = painterResource(R.drawable.ic_close),
                        contentDescription = stringResource(R.string.close_content_description)
                    )
                }
            )
        }
    ) { innerPadding ->
        WavveDefaultSurface(
            modifier = Modifier.padding(innerPadding)
        ) {
            SignUpContentScreen(
                modifier = Modifier.padding(horizontal = 12.dp),
                emailInput = emailInput.value,
                passwordInput = passwordInput.value,
                onEmailInputChanged = viewModel::onEmailInputChanged,
                onPasswordInputChanged = viewModel::onPasswordInputChanged,
                onSignUpComplete = onSignUpComplete
            )
        }
    }
}