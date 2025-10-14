package desktop.hambug.presentation.community

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import desktop.hambug.domain.model.Category
import desktop.hambug.presentation.community.component.RequiredFieldTitle
import desktop.hambug.presentation.ui.component.CustomContentTextField
import desktop.hambug.presentation.ui.component.CustomTitleTextField
import desktop.hambug.presentation.ui.icon.AppIcons
import desktop.hambug.presentation.ui.icon.appicons.BackDetail
import desktop.hambug.presentation.ui.icon.appicons.Camera
import desktop.hambug.presentation.ui.icon.appicons.CircleCross
import desktop.hambug.presentation.ui.theme.HambugTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PostWriteScreen(
    postWriteViewModel: PostWriteViewModel = hiltViewModel()
) {
    // 카테고리 목록 (자유잡담, 프랜차이즈, 수제버거, 맛집추천)
    val categoryList = postWriteViewModel.categoryList
    // 현재 선택된 카테고리
    val currentCategory by postWriteViewModel.currentCategory.collectAsStateWithLifecycle()

    val postTitle by postWriteViewModel.postTitle.collectAsStateWithLifecycle()
    val postContent by postWriteViewModel.postContent.collectAsStateWithLifecycle()

    Scaffold(
        containerColor = Color.White,
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "게시물 작성",
                        style = HambugTheme.typography.title02,
                        color = HambugTheme.colors.textHeadline
                    )
                },
                navigationIcon = {
                    Icon(
                        modifier = Modifier
                            .clickable {  }
                            .padding(16.dp),
                        imageVector = AppIcons.BackDetail,
                        contentDescription = null,
                        tint = HambugTheme.colors.iconDisabled,
                    )
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = HambugTheme.colors.bgWhite
                )
            )

        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(horizontal = 20.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                RequiredFieldTitle(title = "카테고리")

                Spacer(modifier = Modifier.height(12.dp))

                // 카테고리 목록
                CategoryButtonSection(
                    categoryList = categoryList,
                    currentCategory = currentCategory,
                    onClick = { categoryType -> postWriteViewModel.setCategory(categoryType) }
                )

                Spacer(modifier = Modifier.height(16.dp))

                // 제목 영역
                WriteTitleSection(
                    postTitle = postTitle,
                    postWriteViewModel = postWriteViewModel
                )

                Spacer(modifier = Modifier.height(16.dp))

                // 내용 영역
                WriteContentSection(
                    placeholder = currentCategory.placeholder,
                    postContent = postContent,
                    postWriteViewModel = postWriteViewModel
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                // 이미지 표시 영역
                WriteImageSection()

                Spacer(modifier = Modifier.height(16.dp))

                // 사진추가 버튼
                WriteImageAddButton()

                Spacer(modifier = Modifier.height(20.dp))

                // 등록 버튼
                WriteRegisterButton()

                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

@Composable
fun CategoryButtonSection(
    categoryList: List<Category>,
    currentCategory: Category,
    onClick: (Category) -> Unit
) {
    Row {
        categoryList.forEach { item ->
            val selected = (currentCategory == item)

            CategoryButtonItem(
                category = item,
                selected = selected,
                onClick = onClick
            )
            Spacer(Modifier.width(8.dp))
        }
    }
}

@Composable
fun CategoryButtonItem(
    category: Category,
    selected: Boolean,
    onClick: (Category) -> Unit
) {
    Box(
        modifier = Modifier
            .clickable { onClick(category) }
            .height(34.dp)
            .background(color = HambugTheme.colors.bgWhite)
            .border(
                width = 1.dp,
                color = if (selected) HambugTheme.colors.primRed else HambugTheme.colors.borderDisabled,
                shape = RoundedCornerShape(2.dp)
            )
            .padding(horizontal = 12.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = category.title,
            style = HambugTheme.typography.label02,
            color = if (selected) HambugTheme.colors.primRed else HambugTheme.colors.textDisabled
        )
    }
}

@Composable
fun WriteTitleSection(
    postTitle: String,
    postWriteViewModel: PostWriteViewModel
) {
    RequiredFieldTitle(title = "제목")

    Spacer(modifier = Modifier.height(16.dp))

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        CustomTitleTextField(
            value = postTitle,
            onValueChange = { newTitle -> postWriteViewModel.updatePostTitle(newTitle) }
        )
        Spacer(Modifier.height(4.dp))
        HorizontalDivider(thickness = 1.dp, color = HambugTheme.colors.borderDefault)
    }
}

@Composable
fun WriteContentSection(
    placeholder: String,
    postContent: String,
    postWriteViewModel: PostWriteViewModel
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
        // 플레이스홀더
        if (postContent.isEmpty()) {
            Text(
                text = placeholder,
                style = HambugTheme.typography.body03,
                color = HambugTheme.colors.borderDefault
            )
        }
        CustomContentTextField(
            value = postContent,
            onValueChange = { newContent -> postWriteViewModel.updatePostContent(newContent)}
        )
    }
}

@Composable
fun WriteImageSection() {
    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        for (idx in 0 until 5) {
            WriteImageItem()
            if (idx < 4) {
                Spacer(modifier = Modifier.width(8.dp))
            }
        }
    }
}

@Composable
fun WriteImageItem() {
    Box(
        modifier = Modifier
//            .size(100.dp)
            .size(60.dp)
            .background(color = HambugTheme.colors.bgDarker, shape = RoundedCornerShape(6.dp)),
        contentAlignment = Alignment.TopEnd
    ) {
        Icon(
            modifier = Modifier.offset(x = 4.dp, y = (-10).dp),
            imageVector = AppIcons.CircleCross,
            contentDescription = null,
            tint = HambugTheme.colors.iconDisabled
        )
        Spacer(modifier = Modifier.width(8.dp))
        Icon(
            modifier = Modifier.offset(x = 4.dp, y = (-10).dp),
            imageVector = AppIcons.CircleCross,
            contentDescription = null,
            tint = HambugTheme.colors.iconDisabled
        )
    }
}

@Composable
fun WriteImageAddButton() {
    Box(
        modifier = Modifier
            .clickable {  }
            .fillMaxWidth()
            .height(40.dp)
            .background(color = HambugTheme.colors.bgWhite)
            .border(width = 1.dp, color = HambugTheme.colors.primRed),
        contentAlignment = Alignment.Center
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = AppIcons.Camera,
                contentDescription = null,
                tint = HambugTheme.colors.primRed
            )
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = "사진추가 (2/5)",
                style = HambugTheme.typography.body03,
                color = HambugTheme.colors.primRed
            )
        }
    }
}

@Composable
fun WriteRegisterButton() {
    Box(
        modifier = Modifier
            .clickable {  }
            .fillMaxWidth()
            .height(52.dp)
            .background(color = HambugTheme.colors.primRed, shape = RoundedCornerShape(16.dp)),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "등록",
            style = HambugTheme.typography.title02,
            color = HambugTheme.colors.bgWhite
        )
    }
}

@Preview
@Composable
fun PostWriteScreenPreview() {
    HambugTheme {
        PostWriteScreen()
    }
}
