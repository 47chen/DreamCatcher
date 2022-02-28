package edu.vt.cs.cs5254.dreamcatcher

import androidx.lifecycle.ViewModel

class DreamListViewModel : ViewModel() {
//    val dreams = mutableListOf<Dream>()
//    init {
//        for (i in 0 until 100) {
//            val dream = Dream()
//            dream.title = "Crime #$i"
//            dream.isFulfilled = i % 2 == 0
//            dreams += dream
//        }
//    }
    // now we got our repository, so we don't need above code, we just need to access repo

    private val dreamRepository = DreamRepository.get()
    val dreams = dreamRepository.getDreams()

}