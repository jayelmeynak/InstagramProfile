package com.example.instagramprofile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.random.Random

class MainViewModel : ViewModel() {

    private val initialList = mutableListOf<InstagramModel>().apply {
        repeat(100) {
            add(
                InstagramModel(
                    id = it,
                    title = "Title $it",
                    followed = Random.nextBoolean()
                )
            )
        }
    }

    private val _models = MutableLiveData<List<InstagramModel>>(initialList)
    val models: LiveData<List<InstagramModel>> = _models

    fun changeFollowingStatus(model: InstagramModel) {
        val modifiedList = _models.value?.toMutableList() ?: mutableListOf()
        modifiedList.replaceAll {
            if (it == model) {
                it.copy(followed = !it.followed)
            } else {
                it
            }
        }
        _models.value = modifiedList
    }

    fun delete(model: InstagramModel){
        val modifiedList = _models.value?.toMutableList() ?: mutableListOf()
        modifiedList.remove(model)
        _models.value = modifiedList
    }
}