package com.mobilecomputing.newsapp

import android.accessibilityservice.AccessibilityService
import android.speech.tts.TextToSpeech
import android.view.accessibility.AccessibilityEvent
import java.util.Locale
class MyAccessibilityService : AccessibilityService() {
    private lateinit var tts: TextToSpeech

    override fun onServiceConnected() {
        super.onServiceConnected()

        // Initialize TextToSpeech
        tts = TextToSpeech(this) { status ->
            if (status != TextToSpeech.ERROR) {
                tts.language = Locale.US
            }
        }
    }

    override fun onAccessibilityEvent(event: AccessibilityEvent) {
        // Get the source's text
        val text = event.text.joinToString(" ")

        // Speak the text
        tts.speak(text, TextToSpeech.QUEUE_FLUSH, null, "")
    }

    override fun onInterrupt() {
        // Handle interruptions here
    }

    override fun onDestroy() {
        // Clean up TextToSpeech
        if (tts.isSpeaking) {
            tts.stop()
        }
        tts.shutdown()

        super.onDestroy()
    }
}