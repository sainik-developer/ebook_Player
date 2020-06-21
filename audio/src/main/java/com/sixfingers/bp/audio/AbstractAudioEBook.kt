package com.sixfingers.bp.audio

import com.sixfingers.bp.model.Book
import com.sixfingers.bp.model.Page

abstract class AbstractAudioEBook(basePath: String, book: Book) : AudioEBook {
//    abstract fun preemptAndPlayAudio(otherSound: String?)
//    abstract fun selectLanguage(language: String)

    init {
        assert( !basePath.isEmpty())
    }
}