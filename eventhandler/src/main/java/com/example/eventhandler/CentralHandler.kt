package com.example.eventhandler

import android.os.*
import java.lang.StringBuilder
import java.util.concurrent.Callable

/***
 *
 */
class CentralHandler private constructor() {

    // will be used for audio
    private val handlerThread: HandlerThread = HandlerThread("BookPlayer")
    val mainHandler: Handler = object : Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message) {
            callbacks.get(msg.what)?.callback(msg.data)
        }
    }

    val callbacks = HashMap<Int, Callback>()


    fun addCallback(code: Int, callback: Callback) {
        callbacks.put(code, callback)
    }

//    fun removeCallback(code: Int){
//
//    }

    enum class MessageCode(val code: Int) {
        PAGE_NUMBER(2020)
    }

    private object HOLDER {
        val INSTANCE = CentralHandler()
    }

    companion object {
        val instance: CentralHandler by lazy { HOLDER.INSTANCE }
    }

    fun start() {
        handlerThread.start();
    }

    fun destroy() {
        handlerThread.quit();
    }

    fun getHandlerThread(): HandlerThread {
        return handlerThread
    }

    fun setPageNumber(first: Int, second: Int = -1) {
        val message = mainHandler.obtainMessage()
        message.data = Bundle()
        message.what = MessageCode.PAGE_NUMBER.code
        message.data.putString("current_page", if (second != -1)
            StringBuilder().append(first).append("-").append(second).toString() else StringBuilder().append(first).toString())
        mainHandler.sendMessage(message)
    }

//    fun moveToNextPage() {
//
//    }
//
//    fun moveToPrevPage() {
//
//    }
//
//    fun changeLanguage(lang: String) {
//
//    }
//
//    fun changeSpeed(speed: Float) {
//
//    }
}