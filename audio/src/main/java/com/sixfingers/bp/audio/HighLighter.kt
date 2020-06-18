package com.sixfingers.bp.audio

interface HighLighter {
    /***
     *
     * @param paragraphIndex
     * @param timeLineArrayIndex
     */
    fun markHighLight(paragraphIndex: Int, timeLineArrayIndex: Int)
}