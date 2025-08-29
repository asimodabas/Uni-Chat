package com.asimodabas.uni_chat.ui.fragment.jobs.health

import com.asimodabas.uni_chat.model.CategoryItem
import com.asimodabas.uni_chat.ui.fragment.jobs.base.JobCategoryViewModel

class HealthViewModel : JobCategoryViewModel() {

    override fun getCategoryItems(): List<CategoryItem> {
        return listOf(
            CategoryItem("Medicine", 16, 0, 0),
            CategoryItem("Dentist", 17, 0, 0),
            CategoryItem("Nurse", 18, 0, 0),
            CategoryItem("Psychology", 19, 0, 0),
            CategoryItem("Pharmacy", 20, 0, 0),
            CategoryItem("Veterinary", 21, 0, 0),
            CategoryItem("Dietetics", 22, 0, 0),
            CategoryItem("Rehabilitation", 23, 0, 0)
        )
    }

    fun handleMedicineMediaClick() = navigateToMediaChat(16)
    fun handleMedicineMessageClick() = navigateToChat(16)

    fun handleDentistMediaClick() = navigateToMediaChat(17)
    fun handleDentistMessageClick() = navigateToChat(17)

    fun handleNurseMediaClick() = navigateToMediaChat(18)
    fun handleNurseMessageClick() = navigateToChat(18)

    fun handlePsychologyMediaClick() = navigateToMediaChat(19)
    fun handlePsychologyMessageClick() = navigateToChat(19)

    fun handlePharmacyMediaClick() = navigateToMediaChat(20)
    fun handlePharmacyMessageClick() = navigateToChat(20)

    fun handleVeterinaryMediaClick() = navigateToMediaChat(21)
    fun handleVeterinaryMessageClick() = navigateToChat(21)

    fun handleDieteticsMediaClick() = navigateToMediaChat(22)
    fun handleDieteticsMessageClick() = navigateToChat(22)

    fun handleRehabilitationMediaClick() = navigateToMediaChat(23)
    fun handleRehabilitationMessageClick() = navigateToChat(23)
}