package com.gaurav.light_sensor_event_channel_flutter

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.EventChannel
import io.flutter.plugins.GeneratedPluginRegistrant
import java.util.logging.StreamHandler


class MainActivity: FlutterActivity() {

    companion object {
        private const val PLATFORM_CHANNEL = "com.gaurav.lightsensor/platform"
    }

    private lateinit var sensorManager: SensorManager
    private var lightSensor: Sensor? = null
    private var sensorEventListener: SensorEventListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT)

        GeneratedPluginRegistrant.registerWith(FlutterEngine(applicationContext))

        EventChannel(flutterEngine?.dartExecutor?.binaryMessenger, PLATFORM_CHANNEL).setStreamHandler(object : StreamHandler(), EventChannel.StreamHandler {
            override fun onListen(arguments: Any?, events: EventChannel.EventSink) {
                createLightSensorListener(events)
                subscribeToSensor()
            }

            override fun onCancel(arguments: Any?) {
                unsubscribeFromSensor()
            }
        })
    }

    fun createLightSensorListener(events: EventChannel.EventSink) {
        sensorEventListener = object : SensorEventListener {
            override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}

            override fun onSensorChanged(event: SensorEvent) {
                val luxValue = event.values?.get(0)?.toDouble()
                events.success(luxValue)
            }
        }
    }

    private fun subscribeToSensor() {
        sensorManager.registerListener(sensorEventListener, lightSensor, SensorManager.SENSOR_DELAY_FASTEST)
    }

    private fun unsubscribeFromSensor() {
        sensorManager.unregisterListener(sensorEventListener)
    }
}
