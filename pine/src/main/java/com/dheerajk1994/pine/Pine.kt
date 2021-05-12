package com.dheerajk1994.pine

import android.util.Log

class Pine {
    enum class LogLevel {
        NO_LOG,
        ERROR,
        WARN,
        DEBUG,
        INFO,
        VERBOSE,
    }
    companion object {
        private const val TAG = "PINE"
        private var currentLogLevel: LogLevel = LogLevel.ERROR

        fun setLogLevel(level : LogLevel) {
            this.currentLogLevel = level
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
                }
            }
        }
    }
}