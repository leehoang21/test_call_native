package com.example.flutter_test_call_native

import android.content.Context
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.BinaryMessenger
import io.flutter.plugin.common.MethodChannel
import java.lang.reflect.Method

class MainActivity: FlutterActivity() {
    private val METHOD_CHANNEL_NAME = "com.example.flutter_app/channel"
    private  val EVENT_CHANNEL = "com.example.flutter_app/eventChannel"

    private var methodChannel : MethodChannel? = null

    override fun configureFlutterEngine(flutterEngine: FlutterEngine) {
        super.configureFlutterEngine(flutterEngine)

        //setup channels
        setupChannel(this,flutterEngine.dartExecutor.binaryMessenger)
    }

    private  fun setupChannel(context:Context,mess:BinaryMessenger) {
        methodChannel = MethodChannel(mess,METHOD_CHANNEL_NAME)
        methodChannel!!.setMethodCallHandler { call, result ->
            if (call.method == "incrementCounter") {
                var couter:Int  = call.arguments<Int>() as Int
                couter++
                result.success(couter)
            }else{
                result.notImplemented()
            }
        }
    }
}
