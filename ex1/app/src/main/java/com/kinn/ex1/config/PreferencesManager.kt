package com.kinn.ex1.config

import android.content.Context
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class PreferencesManager(private val context: Context) {
    companion object {
        // Keep the string key for backward compatibility
        val BACKGROUND_COLOR_STRING = stringPreferencesKey("background_color")
        // New key for storing as Long
        val BACKGROUND_COLOR_LONG = longPreferencesKey("background_color_long")
    }

    suspend fun saveBackgroundColor(color: Color) {
        context.dataStore.edit { preferences ->
            // Remove old string-based value if it exists
            preferences.remove(BACKGROUND_COLOR_STRING)
            // Save as long value
            preferences[BACKGROUND_COLOR_LONG] = color.toArgb().toLong()
        }
    }

    val backgroundColorFlow: Flow<Color> = context.dataStore.data
        .map { preferences ->
            // Try to read the long value first
            val colorLong = preferences[BACKGROUND_COLOR_LONG]
            if (colorLong != null) {
                return@map Color(colorLong.toInt())
            }

            // Fall back to string value if it exists
            val colorString = preferences[BACKGROUND_COLOR_STRING]
            if (colorString != null) {
                try {
                    return@map Color(android.graphics.Color.parseColor(colorString))
                } catch (e: IllegalArgumentException) {
                    // Parsing failed, return default color
                }
            }

            // Default color if no saved preference
            Color.White
        }
}