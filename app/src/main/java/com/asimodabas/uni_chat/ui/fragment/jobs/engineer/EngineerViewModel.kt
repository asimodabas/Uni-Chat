package com.asimodabas.uni_chat.ui.fragment.jobs.engineer

import com.asimodabas.uni_chat.model.CategoryItem
import com.asimodabas.uni_chat.ui.fragment.jobs.base.JobCategoryViewModel

class EngineerViewModel : JobCategoryViewModel() {

    override fun getCategoryItems(): List<CategoryItem> {
        return listOf(
            CategoryItem("Computer Engineering", 1, 0, 0),
            CategoryItem("Chemical Engineering", 2, 0, 0),
            CategoryItem("Industry Engineering", 3, 0, 0),
            CategoryItem("Build Engineering", 4, 0, 0),
            CategoryItem("Food Engineering", 5, 0, 0),
            CategoryItem("Electric Engineering", 6, 0, 0),
            CategoryItem("Machine Engineering", 7, 0, 0)
        )
    }

    fun handleComputerMediaClick() = navigateToMediaChat(1)
    fun handleComputerMessageClick() = navigateToChat(1)

    fun handleChemicalMediaClick() = navigateToMediaChat(2)
    fun handleChemicalMessageClick() = navigateToChat(2)

    fun handleIndustryMediaClick() = navigateToMediaChat(3)
    fun handleIndustryMessageClick() = navigateToChat(3)

    fun handleBuildMediaClick() = navigateToMediaChat(4)
    fun handleBuildMessageClick() = navigateToChat(4)

    fun handleFoodMediaClick() = navigateToMediaChat(5)
    fun handleFoodMessageClick() = navigateToChat(5)

    fun handleElectricMediaClick() = navigateToMediaChat(6)
    fun handleElectricMessageClick() = navigateToChat(6)

    fun handleMachineMediaClick() = navigateToMediaChat(7)
    fun handleMachineMessageClick() = navigateToChat(7)
}