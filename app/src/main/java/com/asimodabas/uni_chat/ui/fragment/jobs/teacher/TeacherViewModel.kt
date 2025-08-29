package com.asimodabas.uni_chat.ui.fragment.jobs.teacher

import com.asimodabas.uni_chat.model.CategoryItem
import com.asimodabas.uni_chat.ui.fragment.jobs.base.JobCategoryViewModel

class TeacherViewModel : JobCategoryViewModel() {

    override fun getCategoryItems(): List<CategoryItem> {
        return listOf(
            CategoryItem("Physics", 8, 0, 0),
            CategoryItem("Literature", 9, 0, 0),
            CategoryItem("Chemical", 10, 0, 0),
            CategoryItem("Maths", 11, 0, 0),
            CategoryItem("Biology", 12, 0, 0),
            CategoryItem("English", 13, 0, 0),
            CategoryItem("Geography", 14, 0, 0),
            CategoryItem("History", 15, 0, 0)
        )
    }

    fun handlePhysicsMediaClick() = navigateToMediaChat(8)
    fun handlePhysicsMessageClick() = navigateToChat(8)

    fun handleLiteratureMediaClick() = navigateToMediaChat(9)
    fun handleLiteratureMessageClick() = navigateToChat(9)

    fun handleChemicalTeacherMediaClick() = navigateToMediaChat(10)
    fun handleChemicalTeacherMessageClick() = navigateToChat(10)

    fun handleMathsMediaClick() = navigateToMediaChat(11)
    fun handleMathsMessageClick() = navigateToChat(11)

    fun handleBiologyMediaClick() = navigateToMediaChat(12)
    fun handleBiologyMessageClick() = navigateToChat(12)

    fun handleEnglishMediaClick() = navigateToMediaChat(13)
    fun handleEnglishMessageClick() = navigateToChat(13)

    fun handleGeographyMediaClick() = navigateToMediaChat(14)
    fun handleGeographyMessageClick() = navigateToChat(14)

    fun handleHistoryMediaClick() = navigateToMediaChat(15)
    fun handleHistoryMessageClick() = navigateToChat(15)
}