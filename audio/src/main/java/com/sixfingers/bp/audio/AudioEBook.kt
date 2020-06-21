package com.sixfingers.bp.audio

interface AudioEBook {
    /***
     * Pause the Audio
     *
     * NOT_STARTED       no effect
     * PLAYING           keep the track of speed and current position
     * FINISHED_PLAYING  no effect
     */
    fun pause()

    /***
     * NOT_STARTED       Set the speed and play from staring
     * PLAYING           Set the speed and play from last saved position as per new speed
     * FINISHED_PLAYING  Set the speed and play from staring
     *
     * @param speed at which it should play
     */
    fun setSpeedAndPlay(speed: Float): Boolean

    /***
     * Speed to be set.
     * NOT_STARTED       when played next this speed will be used
     * PLAYING           paused the audio and set the speed and resume with new speed
     * FINISHED_PLAYING  set the speed when playing again new speed will be used
     *
     * @param speed to be used, it should be between 0.7 to 1.2
     */
    fun setSpeed(speed: Float)

    /***
     * Play audio again from start
     *
     * NOTE: not required may be
     */
    fun play()

//    /***
//     * Just enable high light of the text
//     *
//     * @param enable
//     */
//    fun setEnableHighlightCallback(enable: Boolean)
    //    /***
//     *
//     * @param highLighter
//     */
//    void setHighLighter(HighLighter highLighter);
//
//    /***
//     *
//     * @param onFinished
//     */
//    fun setOnFinished(onFinished: OnFinishedAudio);
}