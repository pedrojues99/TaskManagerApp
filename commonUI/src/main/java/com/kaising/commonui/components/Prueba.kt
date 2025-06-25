package com.kaising.commonui.components

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Checkbox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

/*
@Composable
fun Prueba() {
    var isMoved by remember { mutableStateOf(true) }
    val offsetX by animateDpAsState(
        targetValue = if (isMoved) 200.dp else 0.dp,
        animationSpec = tween(durationMillis = 2000)
    )

    Box(
        modifier = Modifier
            .offset(x = offsetX)
            .size(100.dp)
            .background(Color.Red)
    )
    Checkbox(isMoved, onCheckedChange = {
        isMoved = it
    })

}

 */