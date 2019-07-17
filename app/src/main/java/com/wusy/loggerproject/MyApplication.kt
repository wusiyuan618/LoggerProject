package com.wusy.loggerproject

import android.app.Application
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import com.orhanobut.logger.PrettyFormatStrategy
import com.orhanobut.logger.DiskLogAdapter
import com.orhanobut.logger.CsvFormatStrategy
import com.orhanobut.logger.FormatStrategy
import com.wusy.loggerproject.loggerExpand.MyDiskLogAdapter


class MyApplication : Application() {
    var isDebug:Boolean=false
    private val restartHandler = Thread.UncaughtExceptionHandler { _, ex ->
        Logger.e(ex, Thread.currentThread().toString() + "--APP异常捕获uncaughtException")
    }
    override fun onCreate() {
        super.onCreate()
        initLogger()
        Thread.setDefaultUncaughtExceptionHandler(restartHandler) // 程序崩溃时触发线程  以下用来捕获程序崩溃异常
    }
    private fun initLogger(){
        val formatStrategyForAndroid = PrettyFormatStrategy.newBuilder()
            .showThreadInfo(true)  // (Optional) Whether to show thread info or not. Default true
            .methodCount(5)         // (Optional) How many method line to show. Default 2
            .tag("wsy")   // (Optional) Global tag for every log. Default PRETTY_LOGGER
            .build()
        Logger.addLogAdapter(object : AndroidLogAdapter(formatStrategyForAndroid) {
            override fun isLoggable(priority: Int, tag: String?): Boolean {
                return BuildConfig.DEBUG
            }
        })
        Logger.addLogAdapter(MyDiskLogAdapter())
    }
}
