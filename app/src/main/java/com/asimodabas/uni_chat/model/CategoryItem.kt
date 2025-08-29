package com.asimodabas.uni_chat.model

data class CategoryItem(
    val name: String,
    val chatId: Int,
    val mediaButtonId: Int,
    val messageButtonId: Int
)

enum class NavigationType {
    CHAT,
    MEDIA_CHAT
}
