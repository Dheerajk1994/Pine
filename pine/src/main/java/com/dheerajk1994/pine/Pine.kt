package com.dheerajk1994.pine

import android.util.Log
import android.view.View

class Pine private constructor() {
    enum class LogLevel {
        NO_LOG,
        ERROR,
        WARN,
        DEBUG,
        STEP,
        INFO,
        VERBOSE,
    }
    companion object {
        private const val TAG = "Pine : "
        private var currentLogLevel: LogLevel = LogLevel.ERROR
        private var currentStepLevel = 1

        fun setLogLevel(level : LogLevel) {
            this.currentLogLevel = level
        }

        fun logStep(message: String, viewElement : View?) {
            if(currentLogLevel >= LogLevel.STEP) {
                val properName = viewElement?.context?.resources?.getResourceEntryName(viewElement.id) ?: ""
                Log.d(TAG, "Step ${currentStepLevel++} : $message $properName")
            }
        }

        fun v(message : String) {
            printLog(LogLevel.VERBOSE, message)
        }

        fun i(message : String) {
            printLog(LogLevel.INFO, message)
        }

        fun d(message : String) {
            printLog(LogLevel.DEBUG, message)
        }

        fun w(message : String) {
            printLog(LogLevel.WARN, message)
        }

        fun e(message : String) {
            printLog(LogLevel.ERROR, message)
        }

        private fun printLog(requestedLevel : LogLevel, message : String) {
            val stackTrace = Thread.currentThread().stackTrace[6]
            if(requestedLevel <= currentLogLevel) {
                when(requestedLevel) {
                    LogLevel.NO_LOG -> {}
                    LogLevel.ERROR -> {
                        Log.e(TAG, message + stackTrace)
                    }
                    LogLevel.WARN -> {
                        Log.w(TAG, message + stackTrace)
                    }
                    LogLevel.DEBUG -> {
                        Log.d(TAG, message + stackTrace)
                    }
                    LogLevel.INFO -> {
                        Log.i(TAG, message + stackTrace)
                    }
                    LogLevel.VERBOSE -> {
                        Log.v(TAG, message + stackTrace)
                    }
                    else -> { }
                }
            }
        }
    }
}