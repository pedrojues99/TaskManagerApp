package com.kaising.domain.model

data class Task(val id: Int, val title: String, val isCompleted: Boolean, val description: String? = null)