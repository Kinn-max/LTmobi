package com.example.nodemanager.repository

import com.example.nodemanager.models.NoteItem
import retrofit2.http.GET

interface Api  {
    suspend fun login(username:String,password: String):Boolean
    suspend fun loadNotes():List<NoteItem>
    suspend fun addNote(title: String, content:String)
    suspend fun editNote(id: Long, title: String, content:String)
    suspend fun deleteNote(dt: Long)
}