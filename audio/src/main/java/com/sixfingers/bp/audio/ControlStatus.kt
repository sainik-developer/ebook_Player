package com.sixfingers.bp.audio

interface ControlStatus {
    /***
     * It return control view's Play/Pause button status.
     * @return true if control is in playing mode , false if control is in paused
     */
    val isControlPlaying: Boolean
}