package com.kaising.taskmanagerapp.mapper

import com.kaising.commonui.model.TaskUiModel
import com.kaising.domain.model.Task

fun Task.toUiModel(): TaskUiModel {
    return TaskUiModel(
        id = id,
        title = title,
        isCompleted = isCompleted,
        description = description
    )
}

fun TaskUiModel.toDomain(): Task {
    return Task(
        id = id,
        title = title,
        isCompleted = isCompleted,
        description = description

    )
}