package com.kaising.commonui.components

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.padding
import com.kaising.commonui.model.TaskUiModel
import com.kaising.commonui.theme.Gray
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

@Composable
fun SwipeableCard(
    modifier: Modifier = Modifier,
    task: TaskUiModel,
    onSwipeLeft: (TaskUiModel) -> Unit,
    onSwipeRight: (TaskUiModel) -> Unit,
    swipeThresholdRatio: Float = 0.6f,
    cardColor: Color = Gray,
    content: @Composable ColumnScope.() -> Unit
) {
    val scope = rememberCoroutineScope()
    val offsetX = remember { Animatable(0f) }

    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val swipeThresholdPx = with(LocalDensity.current) { (screenWidth * swipeThresholdRatio).toPx() }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
            .pointerInput(Unit) {
                detectDragGestures(
                    onDragEnd = {
                        when {
                            offsetX.value <= -swipeThresholdPx -> {
                                scope.launch { offsetX.animateTo(0f) }
                                onSwipeLeft(task)
                            }

                            offsetX.value >= swipeThresholdPx -> {
                                scope.launch { offsetX.animateTo(0f) }
                                onSwipeRight(task)
                            }

                            else -> {
                                scope.launch {
                                    offsetX.animateTo(0f, animationSpec = tween(300))
                                }
                            }
                        }
                    },
                    onDrag = { change, dragAmount ->
                        change.consume()
                        scope.launch {
                            offsetX.snapTo(offsetX.value + dragAmount.x)
                        }
                    }
                )
            }
            .offset { IntOffset(offsetX.value.roundToInt(), 0) }
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = cardColor),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        content = content
    )
}