package com.example.notesapp

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.notesapp.databinding.ActivityMainBinding
import com.example.notesapp.db.Database
import com.example.notesapp.models.Note
import java.util.*

class MainActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private val database by lazy {
        Database(this)
    }
    private val adapter = NotesAdapter(
        navigateToCheckBoxDetailsScreen = ::navigateToCheckBoxDetailsScreen,
        navigateToSimpleDetailsScreen = ::navigateToSimpleDetailsScreen
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        val allNotesList = database.getAllNotes().toMutableList()
        Log.i("AbuAcademy", "allNotesList = ${allNotesList.size}")
        adapter.updateList(allNotesList)
        binding.simpleFloatActionButton.setOnClickListener{
            handActionButtonaClick(
                isSimpleNote = true,
                allNotesList = allNotesList
            )
        }
        binding.checkboxFloatActionButton.setOnClickListener{
            handActionButtonaClick(
                isSimpleNote = false,
                allNotesList = allNotesList
            )
        }
        binding.recyclerView.adapter = adapter
    }
    private fun handActionButtonaClick(
        isSimpleNote:Boolean,
        allNotesList: MutableList<Note>,
    ){
        val note = database.saveNewNote(isSimpleNote = isSimpleNote)
        allNotesList.add(note)
        adapter.updateList(allNotesList)
        if (isSimpleNote) navigateToSimpleDetailsScreen(note)
        else navigateToCheckBoxDetailsScreen(note)
    }
    private fun navigateToCheckBoxDetailsScreen (note: Note){
        val intent = Intent(this, CheckNoteDetailsActivity::class.java)
        intent.putExtra(NOTE_KEY, note)
        startActivity(intent)
    }
    private fun navigateToSimpleDetailsScreen(note: Note){
        val intent = Intent(this, SimpleNoteDetailsActivity::class.java)
        intent.putExtra(NOTE_KEY, note)
        startActivity(intent)
    }

}
const val NOTE_KEY = "NOTE_KEY"