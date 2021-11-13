package com.uriolus.mvvmpractice.domain.model

import com.uriolus.mvvmpractice.domain.timeutils.Formatter.Companion.formatToSeconds
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class WatchTest {
    private lateinit var watch: Watch

    @BeforeEach
    fun setup() {
        watch = Watch()
    }

    @Test
    fun `When Timer is started then the getTime returns 0`() {
        watch.start()
        val time = watch.getTime().formatToSeconds()
        assertEquals("0", time)
    }

    @Test
    fun `When Timer is started and one second elapsed then the getTime returns 1`() {
        watch.start()
        Thread.sleep(1000)
        val time = watch.getTime().formatToSeconds()
        assertEquals("1", time)
    }

    @Test
    fun `Timer started, paused for 1s, resumed, elapsed 1s then getTime is 1`() {
        watch.start()
        watch.pause()
        Thread.sleep(1000)
        watch.resume()
        Thread.sleep(1000)
        val time = watch.getTime().formatToSeconds()
        assertEquals("1", time)
    }

    @Test
    fun `Timer started, elapsed 1s , stopped,elapsed half s then getTime is 1`() {
        watch.start()
        Thread.sleep(1000)
        watch.stop()
        Thread.sleep(500)
        val time = watch.getTime().formatToSeconds()
        assertEquals("1", time)
    }

}