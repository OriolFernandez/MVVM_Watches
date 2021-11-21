package com.uriolus.mvvmpractice.domain.model

import android.util.Log
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.util.*

class Watch private constructor(
    val id: WatchId,
    private val timeInterval: Long = 1000,
    private val speedFactor: Float = 1.0f
) {
    companion object {
        fun newWatch(): Watch {
            return Watch(WatchId(UUID.randomUUID().toString()))
        }
    }

    enum class State {
        CREATED,
        RUNNING,
        PAUSED,
        ENDED
    }

    private var state: State = State.CREATED
    private var currentTimeAccumulated: Long = 0
    private var started: Long? = null
    private var ended: Long? = null
    private var lastTimeUpdated: Long? = null

    fun start() {
        onStateChanged(State.RUNNING) {
            started = System.currentTimeMillis()
            lastTimeUpdated = started
            currentTimeAccumulated = 0
        }
    }

    fun pause() {
        onStateChanged(State.PAUSED) {
            updateTime()
        }
    }

    fun resume() {
        onStateChanged(State.RUNNING) {
            lastTimeUpdated = System.currentTimeMillis()
        }
    }

    fun stop() {
        onStateChanged(State.ENDED) {
            ended = System.currentTimeMillis()
            updateTime()
        }
    }

    fun getTime(): Long {
        if (state == State.RUNNING) {
            updateTime()
        }
        return currentTimeAccumulated
    }

    private fun onStateChanged(newState: State, onSuccess: () -> Unit) {
        when (val stateResult = updateState(newState)) {
            is ChangeStateResult.Correct -> {
                onSuccess()
                state = stateResult.state
            }
            is ChangeStateResult.NewStateException -> Log.e(
                "TIMER",
                "NEW STATE ERROR ${stateResult.reason}"
            )
        }
    }

    private fun updateTime() {
        currentTimeAccumulated = ((System.currentTimeMillis() - (lastTimeUpdated ?: 0)) * speedFactor).toLong()
    }

    private fun updateState(newState: State): ChangeStateResult {
        return when (state) {
            State.CREATED -> when (newState) {
                State.CREATED -> ChangeStateResult.NewStateException("Already in CREATED")
                State.RUNNING -> ChangeStateResult.Correct(State.RUNNING)
                State.PAUSED -> ChangeStateResult.Correct(State.PAUSED)
                State.ENDED -> ChangeStateResult.Correct(State.ENDED)
            }
            State.RUNNING -> when (newState) {
                State.CREATED -> ChangeStateResult.NewStateException("Already CREATED")
                State.RUNNING -> ChangeStateResult.NewStateException("Already in RUNNING")
                State.PAUSED -> ChangeStateResult.Correct(State.PAUSED)
                State.ENDED -> ChangeStateResult.Correct(State.ENDED)
            }
            State.PAUSED -> when (newState) {
                State.CREATED -> ChangeStateResult.NewStateException("Already CREATED")
                State.RUNNING -> ChangeStateResult.Correct(State.RUNNING)
                State.PAUSED -> ChangeStateResult.Correct(State.PAUSED)
                State.ENDED -> ChangeStateResult.Correct(State.ENDED)
            }
            State.ENDED -> when (newState) {
                State.CREATED -> ChangeStateResult.NewStateException("Already CREATED")
                State.RUNNING -> ChangeStateResult.NewStateException("It was ENDED")
                State.PAUSED -> ChangeStateResult.NewStateException("It was ENDED")
                State.ENDED -> ChangeStateResult.NewStateException("It was ENDED")
            }
        }
    }

    fun getTimeFlow(): Flow<Long> {
        return flow {
            while (true) {
                val time = getTime()
                emit(time)
                delay(timeInterval)
            }
        }
    }

    sealed class ChangeStateResult {
        class Correct(val state: State) : ChangeStateResult()
        class NewStateException(val reason: String) : ChangeStateResult()
    }
}

@JvmInline
value class WatchId(private val s: String)

