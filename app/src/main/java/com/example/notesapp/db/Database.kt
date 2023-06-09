package com.example.notesapp.db

import android.content.Context
import com.example.notesapp.models.Note
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import java.util.*
import kotlin.collections.ArrayList

private const val NOTES_SHARED_PREF_KEY = "NOTES_SHARED_PREF_KEY"
private const val ALL_NOTES_KEY = "ALL_NOTES_KEY"
class Database (
    private val context: Context
    ){ private val sharedPreferences =context.getSharedPreferences(
    NOTES_SHARED_PREF_KEY,
    Context.MODE_PRIVATE
    )
    fun getAllNotes():List<Note>{
        val gson = Gson()
        val json = sharedPreferences.getString(ALL_NOTES_KEY, null)
        val type : Type = object : TypeToken<ArrayList<Note?>?>() {}.type
        val list = gson.fromJson<List<Note>>(json, type)
        return list ?: emptyList()
    }
    fun saveNewNote(isSimpleNote:Boolean): Note {
        val note =Note(
            title = "",
            description = "",
            lastEditedDate = Date(),
            checkBoxTilesList = emptyList(),
            checkBoxIsCheckedList = emptyList(),
            isSimpleNote = isSimpleNote
        )
        val allNotes = getAllNotes().toMutableList()
        allNotes.add(0, note)
        val gson = Gson().toJson(allNotes)
        sharedPreferences
            .edit()
            .putString(ALL_NOTES_KEY, gson)
            .apply()
        return note
    }
}