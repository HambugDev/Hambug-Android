package desktop.hambug.presentation.ui.icon

import androidx.compose.ui.graphics.vector.ImageVector
import desktop.hambug.presentation.ui.icon.appicons.Apple
import desktop.hambug.presentation.ui.icon.appicons.Camera
import desktop.hambug.presentation.ui.icon.appicons.CircleCross
import desktop.hambug.presentation.ui.icon.appicons.Comment
import desktop.hambug.presentation.ui.icon.appicons.Heart
import desktop.hambug.presentation.ui.icon.appicons.Kakao
import kotlin.collections.List as ____KtList

public object AppIcons

private var __AllIcons: ____KtList<ImageVector>? = null

public val AppIcons.AllIcons: ____KtList<ImageVector>
  get() {
    if (__AllIcons != null) {
      return __AllIcons!!
    }
    __AllIcons= listOf(Apple, Camera, CircleCross, Comment, Heart, Kakao)
    return __AllIcons!!
  }
