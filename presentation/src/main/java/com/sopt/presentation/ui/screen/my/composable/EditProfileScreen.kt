package com.sopt.presentation.ui.screen.my.composable

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sopt.presentation.R
import com.sopt.presentation.ui.component.button.FullWidthTextButton
import com.sopt.presentation.ui.component.textfield.FilledTextField
import com.sopt.presentation.ui.component.top.DefaultCenterAlignedTopAppBar
import com.sopt.presentation.ui.screen.my.viewmodel.EditProfileViewModel
import com.sopt.presentation.ui.screen.my.viewmodel.UpdateProfileUiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditProfileScreen(
    modifier: Modifier = Modifier,
    onCompleteEdit: () -> Unit,
    viewModel: EditProfileViewModel = hiltViewModel()
) {

    val context = LocalContext.current

    val passwordInput by viewModel.passwordInput.collectAsStateWithLifecycle()
    val hobbyInput by viewModel.hobbyInput.collectAsStateWithLifecycle()

    Column(
        modifier = modifier
    ) {
        DefaultCenterAlignedTopAppBar(
            title = {
                Text(stringResource(R.string.title_profile_edit))
            }
        )
        Column(
            modifier = Modifier
                .padding(horizontal = 24.dp)
                .weight(1f)
                .verticalScroll(rememberScrollState())
                .imePadding()
        ) {
            FilledTextField(
                value = passwordInput,
                onValueChange = viewModel::onPasswordInputChanged,
                placeholder = stringResource(R.string.edit_password_placeholder),
                modifier = Modifier.padding(top = 16.dp)
            )

            FilledTextField(
                value = hobbyInput,
                onValueChange = viewModel::onHobbyInputChanged,
                placeholder = stringResource(R.string.edit_hobby_placeholder),
                modifier = Modifier.padding(top = 32.dp)
            )
        }

        FullWidthTextButton(
            text = stringResource(R.string.save),
            onClick = viewModel::updateProfile,
        )
    }

    LaunchedEffect(Unit) {
        viewModel.updateProfileUiState.collect {
            when(it) {
                is UpdateProfileUiState.Success -> {
                    onCompleteEdit()
                }
                is UpdateProfileUiState.PasswordInputEmpty -> {
                    Toast.makeText(context, context.getString(R.string.require_password_input), Toast.LENGTH_SHORT).show()
                }
                is UpdateProfileUiState.HobbyInputEmpty -> {
                    Toast.makeText(context, context.getString(R.string.require_hobby_input), Toast.LENGTH_SHORT).show()
                }
                is UpdateProfileUiState.InvalidPassword -> {
                    Toast.makeText(context, context.getString(R.string.check_password_format), Toast.LENGTH_SHORT).show()
                }
                is UpdateProfileUiState.InvalidHobby -> {
                    Toast.makeText(context, context.getString(R.string.check_hobby_format), Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}