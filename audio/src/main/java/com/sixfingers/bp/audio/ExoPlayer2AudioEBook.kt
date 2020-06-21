package com.sixfingers.bp.audio

import android.content.Context
import android.net.Uri
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
import com.sixfingers.bp.model.Text
import java.io.File
import java.net.URI
import java.util.*

/***
 * This will be responsible to play
 */
class ExoPlayer2AudioEBook(context: Context, basePath: String, book: Book, private val contentProvider: ContentProvider<String>) : AbstractAudioEBook(basePath, book) {

    private val simpleExoPlayer: SimpleExoPlayer = SimpleExoPlayer.Builder(context).setTrackSelector(DefaultTrackSelector(context)).build()
    private val dataSourceFactory: DataSource.Factory = DefaultDataSourceFactory(context, Util.getUserAgent(context, "eBookPlayer"))
    var selectedLanguage: String? = null
    @Volatile
    private var currentLocationInMilliBaseOne: Long = 0

    private lateinit var tt: List<URI>
    private val textIdx = 0
    private var speed = 1.0f
    private var baseOneTimeLineVsParagraphIndexAndStartIndexAndEndIndex: TreeMap<Long, Triple<Int, Int, Int>>? = TreeMap()
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

                        if (onFinishedAudio != null) onFinishedAudio!!.onFinished()
                        simpleExoPlayer.playWhenReady = false
                        //                            wasPlaying = false;
                        currentLocationInMilliBaseOne = 0
                    }
                }
            }
        })
    }

    @Synchronized
    override fun pause() {
        if (simpleExoPlayer.playbackState == Player.STATE_READY && simpleExoPlayer.isPlaying) {
            currentLocationInMilliBaseOne = simpleExoPlayer.currentPosition
            speed = simpleExoPlayer.playbackParameters.speed
            simpleExoPlayer.playWhenReady = false
        }
    }

    @Synchronized
    override fun setSpeedAndPlay(speed: Float): Boolean {
        if (speed in 0.7f..1.2f && simpleExoPlayer.playbackState == Player.STATE_READY) {
            pause()
            this.speed = speed
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
        simpleExoPlayer.setPlaybackParameters(PlaybackParameters(speed, 1.0f))
        simpleExoPlayer.seekTo(currentLocationInMilliBaseOne)
        simpleExoPlayer.playWhenReady = true
    }

//    private fun mapTimeLineAsPerLanguage() {
//        if (selectedLanguage == "EN") {
//            createMapForTimeLine(super.arbordalePage.leaves_en)
//        } else if (selectedLanguage == "ES") {
//            createMapForTimeLine(super.arbordalePage.leaves_es)
//        }
//    }

    private fun createMap(pageIndex: Int) {
        val texts = contentProvider.provide(pageIndex).getBySubType(Text::class.java)
        for (textIdx in 0 until texts.size) {
            val text = texts[textIdx] as Text
            for (paraId in 0 until text.paragraphs.size) {
                val para = text.paragraphs[paraId]
                for (acSpanId in 0 until para.actionSpanables.size) {
                    val aSpanable = para!!.actionSpanables[acSpanId]
                    baseOneTimeLineVsParagraphIndexAndStartIndexAndEndIndex!![(aSpanable.audioTime * 1000L).toLong()] = Triple(paraId, aSpanable.start, aSpanable.end)
                }
            }
        }
    }
//
//    private fun createMapForTimeLine(leave: Leave) {
//        baseOneTimeLineVsParagraphIndexAndTimeLineIndex = TreeMap()
//        for (paragraphIndex in 0 until leave.paragraphs.size()) {
//            val currentParagraph: Paragraph = leave.paragraphs.get(paragraphIndex)
//            for (timeLineIndex in 0 until currentParagraph.timeLineArray.size()) {
//                val timeLine: TimeLine = currentParagraph.timeLineArray.get(timeLineIndex)
//                baseOneTimeLineVsParagraphIndexAndTimeLineIndex!![(timeLine.timeline * 1000)] = Pair(paragraphIndex, timeLineIndex)
//            }
//        }
//    }

//    private fun startHandler() {
//        handlerThread = HandlerThread("TimeLine Calculator")
//        handlerThread!!.start()
//        calculatorHandler = object : Handler(handlerThread!!.looper) {
//            override fun handleMessage(msg: Message) {
//                if (msg.what === pageIndex) {
//                    super.handleMessage(msg)
//                    val integerIntegerPair = msg.obj as Pair<Int, Int>
//                    highLighter!!.markHighLight(integerIntegerPair.first, integerIntegerPair.second)
//                    if (isHighLightEnable) scheduleNextCallbackForHighLight() else stopHandler()
//                }
//            }
//        }
//    }
//
//    private fun stopHandler() {
//        if (calculatorHandler != null && handlerThread != null) {
//            calculatorHandler!!.removeCallbacksAndMessages(null)
//            handlerThread!!.quit()
//        }
//    }

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
    fun selectLanguage(language: String) {
        selectedLanguage = language
    }

//    @Synchronized
//    fun setOnFinished(onFinished: OnFinishedAudio?) {
//        onFinishedAudio = onFinished
//    }

//    fun setPage(pageIndex: Int) {
//        pause()
//        prepareMediaSource()
//        prepareMediaSourceForAnimal()
//        mapTimeLineAsPerLanguage()
//        currentLocationInMilliBaseOne = 0
//        if (File(textAudioFile).exists()) simpleExoPlayer.prepare(audioTextMediaSource!!)
//    }

    private fun prepareMediaSource(): ProgressiveMediaSource {
        return ProgressiveMediaSource.Factory(dataSourceFactory).createMediaSource(Uri.parse(textAudioFile))
    }

    private fun getAllAudioFile(pageIndex: Int, nextPage: Int): List<String>? {
        val yy = contentProvider.provide(pageIndex).getBySubType(Text::class.java)?.map { any -> (any as Text).audioRes!! }
        if (nextPage != -1) {
            yy!! + contentProvider.provide(nextPage).getBySubType(Text::class.java).map { any -> (any as Text).audioRes!! }
        }
        return yy
    }

    fun setPages(firstPageIdx: Int, secondPageIdx: Int = -1) {
        pause()
        tt = getAllAudioFile(firstPageIdx, secondPageIdx)!!.map { audioPath: String -> contentProvider.getAssetUri(audioPath) }
        if (tt.isNotEmpty() && File(tt[0]).exists()) {
            currentLocationInMilliBaseOne = 0
            simpleExoPlayer.playWhenReady = false
            ProgressiveMediaSource.Factory(dataSourceFactory).createMediaSource(Uri.parse(tt[textIdx].toString()))
            createMap(firstPageIdx)
        }
    }
}