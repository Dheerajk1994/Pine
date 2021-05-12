package com.dheerajk1994.pine

import android.util.Log

class Pine {
    enum class LogLevel {
        INFO,
        WARNING,
        ERROR
    }
    companion object {
        private var currentLogLevel: LogLevel = LogLevel.ERROR

        fun setLogLevel(level : LogLevel) {
            this.currentLogLevel = level
        }
    }
}