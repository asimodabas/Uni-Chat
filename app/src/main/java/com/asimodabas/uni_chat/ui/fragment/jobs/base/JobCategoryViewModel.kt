package com.asimodabas.uni_chat.ui.fragment.jobs.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.asimodabas.uni_chat.model.CategoryItem
import com.asimodabas.uni_chat.model.NavigationType

abstract class JobCategoryViewModel : ViewModel() {

    private val _navigationEvent = MutableLiveData<Pair<NavigationType, Int>>()
    val navigationEvent: LiveData<Pair<NavigationType, Int>> = _navigationEvent

    abstract fun getCategoryItems(): List<CategoryItem>

    fun navigateToChat(chatId: Int) {
        _navigationEvent.value = Pair(NavigationType.CHAT, chatId)
    }

    fun navigateToMediaChat(chatId: Int) {
        _navigationEvent.value = Pair(NavigationType.MEDIA_CHAT, chatId)
    }

    fun resetNavigation() {
        _navigationEvent.value = null
    }
}
