package com.dheerajk1994.pine

import android.util.Log
import android.view.View

class Pine private constructor() {
    enum class LogLevel {
        NO_LOG,
        WTF,
        ERROR,
        WARN,
        DEBUG,
        STEP,
        INFO,
        VERBOSE,
    }
    companion object {
        private const val TAG = "Pine "
        private var currentLogLevel: LogLevel = LogLevel.ERROR
        private var currentStepLevel = 1

        /**
         * Allows you to send the min log level for the build.
         * Log levels of lesser value will be ignored.
         * @param level The minimum log level to listen in on.
         */
        fun setLogLevel(level : LogLevel) {
            this.currentLogLevel = level
        }

        /**
         * Function to keep track of the action taken in the app.
         * Will keep track of the number of the step and can also take in Views.
         * @param message Message to be printed
         * @param viewElement Optional view element -
         *sample use :
         * Pine.logStep("user pressed ", btn_send)
         */
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

        fun wtf(message : String) {
            printLog(LogLevel.WTF, message)
        }

        @Synchronized
        private fun printLog(requestedLevel : LogLevel, message : String) {
            val stackTrace = Thread.currentThread().stackTrace
            val caller = "${stackTrace[4].className}/${stackTrace[4].methodName}"
            val fullMessage = "$caller : $message"
            if(requestedLevel <= currentLogLevel) {
                when(requestedLevel) {
                    LogLevel.NO_LOG -> {}
                    LogLevel.WTF -> {
                        Log.wtf(TAG, fullMessage)
                    }
                    LogLevel.ERROR -> {
                        Log.e(TAG, fullMessage)
                    }
                    LogLevel.WARN -> {
                        Log.w(TAG, fullMessage)
                    }
                    LogLevel.DEBUG -> {
                        Log.d(TAG, fullMessage)
                    }
                    LogLevel.INFO -> {
                        Log.i(TAG, fullMessage)
                    }
                    LogLevel.VERBOSE -> {
                        Log.v(TAG, fullMessage)
                    }
                    else -> { }
                }
            }
        }
    }
}