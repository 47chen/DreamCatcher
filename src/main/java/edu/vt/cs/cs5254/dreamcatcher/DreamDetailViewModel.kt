package edu.vt.cs.cs5254.dreamcatcher

import androidx.lifecycle.ViewModel
import java.lang.IllegalArgumentException
import java.util.*

class DreamDetailViewModel : ViewModel() {

//    val dream = Dream()
    private val dreamRepository = DreamRepository.get()

    lateinit var dreamWithEntries: DreamWithEntries

    fun loadDreamWithEntries(dreamId: UUID) {
        dreamWithEntries = dreamRepository.getDreamWithEntries(dreamId)
            ?: throw IllegalArgumentException("Dream with ID $dreamId not found")
    }

//    dream = dreamRepository.getDreamWithEntries(dreamId = UUID.randomUUID())
    // TODO: args for uuid
}