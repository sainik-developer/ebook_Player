package com.sixfingers.bp.audio

import android.content.Context
import android.net.Uri
import android.os.Handler
import android.os.HandlerThread
import android.util.Pair
import com.google.android.exoplayer2.PlaybackParameters
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.upstream.DataSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util
import com.sixfingers.bp.cp.ContentProvider
import com.sixfingers.bp.model.Book
import java.io.File
import java.util.*

/***
 * This will be responsible to play
 */
class ExoPlayer2AudioEBook(context: Context, basePath: String, book: Book, contentProvider: ContentProvider) : AbstractAudioEBook(basePath, book) {


    private val simpleExoPlayer: SimpleExoPlayer = SimpleExoPlayer.Builder(context).setTrackSelector(DefaultTrackSelector(context)).build()
    private val dataSourceFactory: DataSource.Factory = DefaultDataSourceFactory(context, Util.getUserAgent(context, "eBookPlayer"))
    var selectedLanguage: Locale? = null
    @Volatile
    private var currentLocationInMilliBaseOne: Long = 0
    //    @Volatile
//    private var isHighLightEnable = false
//    @Volatile
//    private var isAnimalPlaying = false
    private var speed = 1.0f
    private var baseOneTimeLineVsParagraphIndexAndTimeLineIndex: TreeMap<Long, Pair<Int, Int>>? = null
    private var highLighter: HighLighter? = null
    private var calculatorHandler: Handler? = null
    private var handlerThread: HandlerThread? = null
    private var onFinishedAudio: OnFinishedAudio? = null

    init {
        simpleExoPlayer.addListener(object : Player.EventListener {
            override fun onPlayerStateChanged(playWhenReady: Boolean, playbackState: Int) {
                if (playWhenReady) {
                    if (playbackState == Player.STATE_ENDED) {
//                        if (isAnimalPlaying) {
//                            isAnimalPlaying = false
//                            simpleExoPlayer.prepare(prepareMediaSource())
//                            simpleExoPlayer.playWhenReady = false
//                            if (controlStatus.isControlPlaying) play()
//                        } else
                        if (isPlaying) {
                            if (onFinishedAudio != null) onFinishedAudio!!.onFinished()
                            simpleExoPlayer.playWhenReady = false
                            isPlaying = false
                            //                            wasPlaying = false;
                            currentLocationInMilliBaseOne = 0
                        }
                    }
                }
            }
        })
    }

    private fun prepareMediaSource(): ProgressiveMediaSource {
        return ProgressiveMediaSource.Factory(dataSourceFactory).createMediaSource(Uri.parse(textAudioFile))
    }

//    private fun prepareMediaSourceForAnimal() {
//        animalAudioMap = HashMap()
//        if (arbordalePage.Animals != null) for (animal in super.arbordalePage.Animals) {
//            val animalSoundPath = basePath + File.separator + animal.AnimalSound.replace(".mp3", ".a")
//            animalAudioMap[animal.AnimanlName] = factory.createMediaSource(Uri.parse(animalSoundPath))
//        }
//    }

    private val textAudioFile: String?
        private get() {
            when (selectedLanguage!!) {
                "ES" ->
                else {

                }

            }
            if (selectedLanguage.toUpperCase() == "EN") return basePath + File.separator + super.arbordalePage.leaves_en.backgroundImageName.replace(".jpg", mp3Extension) else if (selectedLanguage.toUpperCase() == "ES") return basePath + File.separator.toString() + getMp3Filename("ES", super.arbordalePage.leaves_es.backgroundImageName) else if (selectedLanguage.toUpperCase() == "CN") return basePath + File.separator.toString() + getMp3Filename("CN", super.arbordalePage.leaves_cn.backgroundImageName)
            return null
        }

//    private fun getMp3Filename(languageCode: String, imageName: String): String {
//        var imageName = imageName
//        if (!imageName.contains(languageCode)) {
//            val i = imageName.indexOf(" ")
//            imageName = imageName.substring(0, i) + languageCode + " " + imageName.substring(i + 1)
//        }
//        return imageName
//    }

    @Synchronized
    override fun pause() {
        if (simpleExoPlayer.playbackState == Player.STATE_READY && isPlaying) {
            currentLocationInMilliBaseOne = simpleExoPlayer.currentPosition
            speed = simpleExoPlayer.playbackParameters.speed
            simpleExoPlayer.playWhenReady = false
            stopHandler()
            isPlaying = false
            //            wasPlaying = false;
        }
    }

//    @Synchronized
//    override fun preemptAndPlayAudio(otherSound: String) {
//        if (isPlaying) pause()
//        simpleExoPlayer.prepare(animalAudioMap!![otherSound])
//        simpleExoPlayer.setPlaybackParameters(PlaybackParameters(1.0f, 1.0f))
//        simpleExoPlayer.playWhenReady = true
//        isAnimalPlaying = true
//    }

    @Synchronized
    override fun setSpeedAndPlay(newSpeed: Float): Boolean {
        if (speedInValidRange(newSpeed) && simpleExoPlayer.playbackState == Player.STATE_READY) {
            pause()
            speed = newSpeed
            play()
            return true
        }
        return false
    }

    private fun calculateLocation(oldSpeed: Float, newSpeed: Float, locationInMilli: Long): Long {
        return (locationInMilli * oldSpeed / newSpeed).toLong()
    }

    override fun setSpeed(speed: Float) {
        if (speed in 0.7f..1.2f) this.speed = speed
    }

    @Synchronized
    override fun play() {
        if (!isPlaying && File(textAudioFile).exists()) {
            simpleExoPlayer.setPlaybackParameters(PlaybackParameters(speed, 1.0f))
            simpleExoPlayer.seekTo(currentLocationInMilliBaseOne)
            simpleExoPlayer.playWhenReady = true
            //TODO to be enabled
//            if (isHighLightEnable) {
//                startHandler()
//                scheduleNextCallbackForHighLight()
//            }
            simpleExoPlayer.isPlaying
            isPlaying = true
        }
    }

    private fun mapTimeLineAsPerLanguage() {
        if (selectedLanguage.toUpperCase() == "EN") {
            createMapForTimeLine(super.arbordalePage.leaves_en)
        } else if (selectedLanguage.toUpperCase() == "ES") {
            createMapForTimeLine(super.arbordalePage.leaves_es)
        } else if (selectedLanguage.toUpperCase() == "CN") {
            createMapForTimeLine(super.arbordalePage.leaves_cn)
        }
    }

    private fun createMap(book: Book){
        val pages= book.getPagesByLanguage(Locale.ENGLISH)
        for (page in 0 until pages.size){

        }
    }

    private fun createMapForTimeLine(leave: Leave) {
        baseOneTimeLineVsParagraphIndexAndTimeLineIndex = TreeMap()
        for (paragraphIndex in 0 until leave.paragraphs.size()) {
            val currentParagraph: Paragraph = leave.paragraphs.get(paragraphIndex)
            for (timeLineIndex in 0 until currentParagraph.timeLineArray.size()) {
                val timeLine: TimeLine = currentParagraph.timeLineArray.get(timeLineIndex)
                baseOneTimeLineVsParagraphIndexAndTimeLineIndex!![(timeLine.timeline * 1000)] = Pair(paragraphIndex, timeLineIndex)
            }
        }
    }

    private fun startHandler() {
        handlerThread = HandlerThread("TimeLine Calculator")
        handlerThread!!.start()
        calculatorHandler = object : Handler(handlerThread!!.looper) {
            override fun handleMessage(msg: Message) {
                if (msg.what === pageIndex) {
                    super.handleMessage(msg)
                    val integerIntegerPair = msg.obj as Pair<Int, Int>
                    highLighter!!.markHighLight(integerIntegerPair.first, integerIntegerPair.second)
                    if (isHighLightEnable) scheduleNextCallbackForHighLight() else stopHandler()
                }
            }
        }
    }

    private fun stopHandler() {
        if (calculatorHandler != null && handlerThread != null) {
            calculatorHandler!!.removeCallbacksAndMessages(null)
            handlerThread!!.quit()
        }
    }

    // TODO to be used
//    private fun scheduleNextCallbackForHighLight() {
//        currentLocationInMilliBaseOne = simpleExoPlayer.currentPosition
//        val longPairEntry = baseOneTimeLineVsParagraphIndexAndTimeLineIndex
//                .ceilingEntry(currentLocationInMilliBaseOne)
//        if (longPairEntry != null) {
//            val integerIntegerPair = longPairEntry.value
//            calculatorHandler!!.sendMessageDelayed(Message.obtain(calculatorHandler, pageIndex, integerIntegerPair), calculateLocation(1.0f, speed, longPairEntry.key)
//                    - calculateLocation(1.0f, speed, currentLocationInMilliBaseOne))
//        }
//    }


    // TODO to be used
//    @Synchronized
//    override fun setEnableHighlightCallback(enable: Boolean) {
//        if (isPlaying && !enable && isHighLightEnable) {
//            stopHandler()
//        } else if (isPlaying && !isHighLightEnable && enable) {
//            startHandler()
//            scheduleNextCallbackForHighLight()
//        }
//        isHighLightEnable = enable
//    }

//    @Synchronized
//    fun setHighLighter(highLighter: HighLighter?) {
//        assert(highLighter != null)
//        this.highLighter = highLighter
//    }

    @Synchronized
    override fun selectLanguage(language: String) {
        selectedLanguage = language
        setPage(arbordalePage, pageIndex)
        if (controlStatus.isControlPlaying) play()
    }

    @Synchronized
    fun setOnFinished(onFinished: OnFinishedAudio?) {
        onFinishedAudio = onFinished
    }

    @Synchronized
    override fun isPlaying(): Boolean {
        return isPlaying
    }

    override fun setPage(pageIndex: Int) {
        pause()
        super.arbordalePage = page
        prepareMediaSource()
        prepareMediaSourceForAnimal()
        mapTimeLineAsPerLanguage()
        currentLocationInMilliBaseOne = 0
        if (File(textAudioFile).exists()) simpleExoPlayer.prepare(audioTextMediaSource!!)
        simpleExoPlayer.playWhenReady = false
    }
}