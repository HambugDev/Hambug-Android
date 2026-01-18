package desktop.hambug.presentation.community

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import desktop.hambug.presentation.community.component.RequiredFieldTitle
import desktop.hambug.presentation.component.CustomContentTextField
import desktop.hambug.presentation.component.CustomSnackbar
import desktop.hambug.presentation.component.CustomTitleTextField
import desktop.hambug.presentation.component.HambugLoadingIndicator
import desktop.hambug.presentation.ui.icon.AppIcons
import desktop.hambug.presentation.ui.icon.appicons.Back
import desktop.hambug.presentation.ui.theme.HambugTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReportScreen(
    navController: NavHostController,
    reportViewModel: ReportViewModel = hiltViewModel()
) {
    val uiState by reportViewModel.uiState.collectAsStateWithLifecycle()
    val reportTitle by reportViewModel.reportTitle.collectAsStateWithLifecycle()
    val reportContent by reportViewModel.reportContent.collectAsStateWithLifecycle()

    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
        // 신고 완료 이벤트 처리
        launch {
            reportViewModel.eventFlow.collect { event ->
                when(event) {
                    is ReportEvent.NavigateToDetail -> {
                        // 이전 화면(BoardDetailScreen)의 SavedStateHandle에 데이터 기록
                        navController.previousBackStackEntry
                            ?.savedStateHandle
                            ?.set("report_success", true)
                        navController.popBackStack()
                    }
                }
            }
        }

        // 스낵바 메시지 처리
        launch {
            reportViewModel.snackbarMessage.collect { message ->
                snackbarHostState.showSnackbar(
                    message = message.message,
                    duration = SnackbarDuration.Short
                )
            }
        }
    }

    Scaffold(
        containerColor = Color.White,
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "신고",
                        style = HambugTheme.typography.title02,
                        color = HambugTheme.colors.textHeadline
                    )
                },
                navigationIcon = {
                    Icon(
                        modifier = Modifier
                            .clickable { navController.popBackStack() }
                            .padding(16.dp),
                        imageVector = AppIcons.Back,
                        contentDescription = null,
                        tint = HambugTheme.colors.iconDisabled,
                    )
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = HambugTheme.colors.bgWhite
                )
            )
        },
        snackbarHost = {
            SnackbarHost(
                hostState = snackbarHostState,
                modifier = Modifier.padding(bottom = 100.dp)
            ) { data ->
                CustomSnackbar(snackbarData = data)
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(horizontal = 20.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            // 제목 + 내용
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Spacer(Modifier.height(20.dp))
                ReportTitleSection(
                    title = reportTitle,
                    reportViewModel = reportViewModel
                )
                Spacer(modifier = Modifier.height(16.dp))
                ReportContentSection(
                    reportContent = reportContent,
                    reportViewModel = reportViewModel
                )
            }

            // 신고 버튼
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Box(
                    modifier = Modifier
                        .clickable(
                            enabled = !uiState.isReporting,
                            onClick = { reportViewModel.submitReport() }
                        )
                        .fillMaxWidth()
                        .height(52.dp)
                        .background(color = HambugTheme.colors.primRed, shape = RoundedCornerShape(16.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "신고 등록",
                        style = HambugTheme.typography.title02,
                        color = HambugTheme.colors.bgWhite
                    )
                }

                Spacer(Modifier.height(16.dp))
            }
        }

        if (uiState.isReporting) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                HambugLoadingIndicator()
            }
        }
    }
}

@Composable
fun ReportTitleSection(
    title: String,
    reportViewModel: ReportViewModel
) {
    RequiredFieldTitle(title = "제목")

    Spacer(modifier = Modifier.height(16.dp))

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        CustomTitleTextField(
            value = title,
            onValueChange = { newTitle -> reportViewModel.updateReportTitle(newTitle) }
        )
        Spacer(Modifier.height(4.dp))
        HorizontalDivider(thickness = 1.dp, color = HambugTheme.colors.borderDefault)
    }
}

@Composable
fun ReportContentSection(
    reportContent: String,
    reportViewModel: ReportViewModel
) {
    RequiredFieldTitle(title = "내용")

    Spacer(modifier = Modifier.height(12.dp))

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(220.dp)
            .background(color = HambugTheme.colors.bgLighter, shape = RoundedCornerShape(6.dp))
            .border(width = 1.dp, color = HambugTheme.colors.borderDefault, shape = RoundedCornerShape(6.dp))
            .padding(12.dp),
    ) {
        if (reportContent.isEmpty()) {
            Text(
                text = "신고하시는 이유를 구체적으로 적어주시면 검토에 도움이 됩니다",
                style = HambugTheme.typography.body03,
                color = HambugTheme.colors.borderDefault
            )
        }
        CustomContentTextField(
            value = reportContent,
            onValueChange = { newContent -> reportViewModel.updateReportContent(newContent) }
        )
    }
}

@Preview
@Composable
fun ReportScreenPreview() {
    HambugTheme {
//        ReportScreen()
    }
}
