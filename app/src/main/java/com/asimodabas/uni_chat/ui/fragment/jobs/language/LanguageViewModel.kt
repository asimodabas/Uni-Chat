package com.asimodabas.uni_chat.ui.fragment.jobs.language

import com.asimodabas.uni_chat.model.CategoryItem
import com.asimodabas.uni_chat.ui.fragment.jobs.base.JobCategoryViewModel

class LanguageViewModel : JobCategoryViewModel() {

    override fun getCategoryItems(): List<CategoryItem> {
        return listOf(
            CategoryItem("English", 24, 0, 0),
            CategoryItem("Deutsch", 25, 0, 0),
            CategoryItem("French", 26, 0, 0),
            CategoryItem("Russian", 27, 0, 0),
            CategoryItem("Japanese", 28, 0, 0)
        )
    }

    fun handleLanguageEnglishMediaClick() = navigateToMediaChat(24)
    fun handleLanguageEnglishMessageClick() = navigateToChat(24)

    fun handleDeutschMediaClick() = navigateToMediaChat(25)
    fun handleDeutschMessageClick() = navigateToChat(25)

    fun handleFrenchMediaClick() = navigateToMediaChat(26)
    fun handleFrenchMessageClick() = navigateToChat(26)

    fun handleRussianMediaClick() = navigateToMediaChat(27)
    fun handleRussianMessageClick() = navigateToChat(27)

    fun handleJapaneseMediaClick() = navigateToMediaChat(28)
    fun handleJapaneseMessageClick() = navigateToChat(28)
}