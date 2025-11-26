package desktop.hambug.presentation.my

import android.content.Intent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import coil3.compose.AsyncImage
import desktop.hambug.domain.model.UserInfo
import desktop.hambug.presentation.my.component.NicknameUpdateDialog
import desktop.hambug.presentation.my.component.UserRemoveDialog
import desktop.hambug.presentation.my.component.UserRemoveSuccessDialog
import desktop.hambug.presentation.ui.icon.AppIcons
import desktop.hambug.presentation.ui.icon.appicons.Activity
import desktop.hambug.presentation.ui.icon.appicons.ArrowRight
import desktop.hambug.presentation.ui.icon.appicons.Logout
import desktop.hambug.presentation.ui.icon.appicons.Pen
import desktop.hambug.presentation.ui.icon.appicons.Remove
import desktop.hambug.presentation.ui.theme.HambugTheme
import desktop.hambug.presentation.ui.theme.RemoveRed

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MypageScreen(
    navController: NavHostController,
    mypageViewModel: MypageViewModel = hiltViewModel(
        viewModelStoreOwner = navController.getBackStackEntry("main_graph")
    )
) {
    val context = LocalContext.current
    val uiState by mypageViewModel.uiState.collectAsStateWithLifecycle()
    val nicknameState by mypageViewModel.nicknameState.collectAsStateWithLifecycle()

    var showBottomSheet by remember { mutableStateOf(false) }
    var showUserRemoveDialog by remember { mutableStateOf(false) }
    var showUserRemoveSuccessDialog by remember { mutableStateOf(false) }
    var showNicknameUpdateDialog by remember { mutableStateOf(false) }

    // Photo Picker 런처 등록
    val singlePhotoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia()
    ) { uri ->
        // Uri 결과 전달
        mypageViewModel.onImageSelected(uri)

        // Uri 접근권한 지속적으로 요청
        if (uri != null) {
            val flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
            context.contentResolver.takePersistableUriPermission(uri, flags)
        }
    }

    // Photo Picker 실행 요청
    LaunchedEffect(Unit) {
        mypageViewModel.mypageEvent.collect { event ->
            when (event) {
                is MypageEvent.LaunchPhotoPicker -> {
                    // Photo Picker 실행
                    singlePhotoPickerLauncher.launch(
                        input = PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                    )
                }
            }
        }
    }

    Scaffold(
        containerColor = HambugTheme.colors.bgWhite,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "마이페이지",
                        style = HambugTheme.typography.title02,
                        color = HambugTheme.colors.textHeadline
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = HambugTheme.colors.bgWhite
                )
            )
        }
    ) { paddingValues ->

        when (uiState) {
            is MyUiState.Loading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(40.dp),
                        color = HambugTheme.colors.primRed,
                        strokeWidth = 4.dp
                    )
                }
            }
            is MyUiState.Error -> {

            }
            is MyUiState.Success -> {
                val data = uiState as MyUiState.Success

                Column(
                    modifier = Modifier
                        .padding(paddingValues)
                        .fillMaxSize()
                ) {
                    Spacer(Modifier.height(20.dp))

                    // 상단 영역 (프로필이미지 + 닉네임)
                    MypageHeaderSection(
                        userInfo = data.userInfo,
                        onClick = { showBottomSheet = true }
                    )

                    Spacer(Modifier.height(40.dp))

                    // 메뉴 선택 영역
                    MypageMenuSection(
                        onActivityClick = { navController.navigate("my_activity") },
                        onUserRemove = { showUserRemoveSuccessDialog = true }
                    )
                }
            }
        }
    }

    // 프로필 클릭 시 바텀시트 표시 (닉네임, 프로필 이미지, 기본 이미지)
    if (showBottomSheet) {
        ModalBottomSheet(
            onDismissRequest = { showBottomSheet = false },
            dragHandle = null,
            containerColor = HambugTheme.colors.bgWhite,
            shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)
        ) {
            ProfileBottomSheetContent(
                onNicknameClick = {
                    showBottomSheet = false
                    showNicknameUpdateDialog = true
                },
                onProfileImageClick = {
                    showBottomSheet = false
                    mypageViewModel.onProfileImageClicked()
                },
                onDismiss = { showBottomSheet = false }
            )
        }
    }

    // 닉네임 변경 모달
    if (showNicknameUpdateDialog) {
        NicknameUpdateDialog(
            state = nicknameState,
            onValueChange = { newValue ->
                mypageViewModel.onNicknameChange(newValue)
            },
            onDismiss = { showNicknameUpdateDialog = false },
            onCancel = { showNicknameUpdateDialog = false },
            onConfirm = {
                mypageViewModel.updateUserNickname(
                    onSuccess = { showNicknameUpdateDialog = false }
                )
            }
        )
    }

    // 회원탈퇴 확인 모달창
    if (showUserRemoveDialog) {
        UserRemoveDialog(
            onDismiss = { showUserRemoveDialog = false },
            onCancel = { showUserRemoveDialog = false },
            onConfirm = {}
        )
    }

    // 회원탈퇴 완료 모달창
    if (showUserRemoveSuccessDialog) {
        UserRemoveSuccessDialog (
            onDismiss = { showUserRemoveSuccessDialog = false},
            onConfirm = { showUserRemoveSuccessDialog = false }
        )
    }
}

@Composable
fun MypageHeaderSection(
    userInfo: UserInfo,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .clickable(onClick = onClick)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        MypageProfileImage(
            profileImageUrl = userInfo.profileImageUrl
        )
        Spacer(Modifier.height(24.dp))
        Text(
            text = userInfo.nickname,
            style = HambugTheme.typography.body02,
            color = HambugTheme.colors.textBody
        )
    }
}

@Composable
fun MypageProfileImage(
    profileImageUrl: String
) {
    Box(
        modifier = Modifier.size(140.dp)
    ) {
        // border를 별도 Box로 분리 (문제 해결)
        // - 부모 Box에 border를 그리면, Modifier 특성상 자식 요소들 위에 그려짐
        // - 이 때문에 border가 작은 Box(아이콘 영역) 위를 덮는 문제 발생
        Box(
            modifier = Modifier
                .size(140.dp)
                .border(width = 2.dp, color = HambugTheme.colors.primRed, shape = CircleShape)
        )

        AsyncImage(
            modifier = Modifier
                .size(130.dp)
                .align(Alignment.Center)
                .clip(CircleShape),
            model = profileImageUrl,
            contentDescription = null,
            contentScale = ContentScale.Crop
        )

        Box(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .offset(x = (-9).dp, y = (-5).dp)
                .size(30.dp)
                .background(color = HambugTheme.colors.primRed, shape = CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = AppIcons.Pen,
                contentDescription = null,
                tint = HambugTheme.colors.bgWhite
            )
        }
    }
}

@Composable
fun MypageMenuSection(
    onActivityClick: () -> Unit,
    onUserRemove: () -> Unit
) {
    Column(
        modifier = Modifier
            .padding(horizontal = 20.dp)
            .fillMaxWidth()
    ) {
        MypageMenuButton(
            menuIcon = AppIcons.Activity,
            menuText = "활동 내역",
            onClick = { onActivityClick() },
            modifier = Modifier
                .background(color = HambugTheme.colors.bgNormal, shape = RoundedCornerShape(12.dp))
                .padding(20.dp)
        )

        Spacer(Modifier.height(24.dp))

        MypageMenuButton(
            menuIcon = AppIcons.Logout,
            menuText = "로그아웃",
            onClick = {},
            modifier = Modifier
                .background(color = HambugTheme.colors.bgNormal, shape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp))
                .padding(start = 20.dp, end = 20.dp, top = 20.dp, bottom = 10.dp)
        )

        MypageMenuButton(
            menuIcon = AppIcons.Remove,
            menuText = "탈퇴하기",
            onClick = { onUserRemove() },
            modifier = Modifier
                .background(color = HambugTheme.colors.bgNormal, shape = RoundedCornerShape(bottomStart = 12.dp, bottomEnd = 12.dp))
                .padding(start = 20.dp, end = 20.dp, top = 10.dp, bottom = 20.dp)
        )
    }
}

@Composable
fun MypageMenuButton(
    menuIcon: ImageVector,
    menuText: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .clickable(onClick = onClick)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = menuIcon,
                contentDescription = null,
                tint = if (menuText == "탈퇴하기") RemoveRed else HambugTheme.colors.iconDisabled
            )
            Spacer(Modifier.width(8.dp))
            Text(
                text = menuText,
                style = HambugTheme.typography.body02,
                color = if (menuText == "탈퇴하기") RemoveRed else HambugTheme.colors.textBody
            )
        }

        Icon(
            modifier = Modifier.padding(end = 6.dp),
            imageVector = AppIcons.ArrowRight,
            contentDescription = null,
            tint = HambugTheme.colors.iconDefault
        )
    }
}

@Composable
fun ProfileBottomSheetContent(
    onNicknameClick: () -> Unit,
    onProfileImageClick: () -> Unit,
    onDismiss: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        MypageBottomSheetButton(
            buttonText = "닉네임 변경",
            onClick = { onNicknameClick() },
            modifier = Modifier
                .background(color = HambugTheme.colors.bgNormal, shape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp))
                .padding(16.dp)
        )

        HorizontalDivider(thickness = 0.5.dp, color = HambugTheme.colors.bgDarker)

        MypageBottomSheetButton(
            buttonText = "프로필 이미지 변경",
            onClick = { onProfileImageClick() },
            modifier = Modifier
                .background(color = HambugTheme.colors.bgNormal)
                .padding(16.dp)
        )

        HorizontalDivider(thickness = 0.5.dp, color = HambugTheme.colors.bgDarker)

        MypageBottomSheetButton(
            buttonText = "기본 이미지 적용",
            onClick = {},
            modifier = Modifier
                .background(color = HambugTheme.colors.bgNormal, shape = RoundedCornerShape(bottomStart = 12.dp, bottomEnd = 12.dp))
                .padding(16.dp)
        )

        Spacer(Modifier.height(12.dp))

        Box(
            modifier = Modifier
                .clickable { onDismiss() }
                .fillMaxWidth()
                .background(color = HambugTheme.colors.bgNormal, RoundedCornerShape(12.dp))
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "취소",
                style = HambugTheme.typography.body02Prominent,
                color = HambugTheme.colors.textBody
            )
        }
    }
}

@Composable
fun MypageBottomSheetButton(
    buttonText: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .clickable(onClick = onClick)
            .fillMaxWidth()
    ) {
        Text(
            text = buttonText,
            style = HambugTheme.typography.body02,
            color = HambugTheme.colors.textBody
        )
    }
}

@Preview
@Composable
fun MypageScreenPreview() {
    HambugTheme {
//        MypageScreen()
    }
}
