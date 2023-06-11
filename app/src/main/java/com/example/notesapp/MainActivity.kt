package com.example.notesapp

import android.content.Intent
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.notesapp.databinding.ActivityMainBinding
import com.example.notesapp.db.Database
import com.example.notesapp.models.Note

class MainActivity() : AppCompatActivity(),
    SearchView.OnQueryTextListener, Parcelable {
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

    constructor(parcel: Parcel) : this() {
    }

    private val allNotesList by lazy {
        database.getAllNotes().toMutableList()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.searchView.setOnQueryTextListener(this)


        adapter.updateList(allNotesList)
        binding.mainFloatingActionButton.setOnClickListener {
            binding.checkboxFloatActionButton.isVisible =
                !binding.checkboxFloatActionButton.isVisible
            binding.simpleFloatActionButton.isVisible = !binding.simpleFloatActionButton.isVisible
        }
        binding.simpleFloatActionButton.setOnClickListener {
            handActionButtonaClick(
                isSimpleNote = true,
                allNotesList = allNotesList
            )
        }
        binding.checkboxFloatActionButton.setOnClickListener {
            handActionButtonaClick(
                isSimpleNote = false,
                allNotesList = allNotesList
            )
        }
        binding.recyclerView.adapter = adapter
    }

    private fun handActionButtonaClick(
        isSimpleNote: Boolean,
        allNotesList: MutableList<Note>,
    ) {
        val note = database.saveNewNote(isSimpleNote = isSimpleNote)
        allNotesList.add(note)
        adapter.updateList(allNotesList)
        if (isSimpleNote) navigateToSimpleDetailsScreen(note)
        else navigateToCheckBoxDetailsScreen(note)
    }

    private fun navigateToCheckBoxDetailsScreen(note: Note) {
        val intent = Intent(this, CheckNoteDetailsActivity::class.java)
        intent.putExtra(NOTE_KEY, note)
        startActivity(intent)
    }

    private fun navigateToSimpleDetailsScreen(note: Note) {
        val intent = Intent(this, SimpleNoteDetailsActivity::class.java)
        intent.putExtra(NOTE_KEY, note)
        startActivity(intent)
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MainActivity> {
        override fun createFromParcel(parcel: Parcel): MainActivity {
            return MainActivity(parcel)
        }

        override fun newArray(size: Int): Array<MainActivity?> {
            return arrayOfNulls(size)
        }
    }

    override fun onQueryTextSubmit(
        query: String?
    ): Boolean {

        val searchString = query ?: return false
        startSearch(searchString)
        return false
    }

    override fun onQueryTextChange(
        query: String?
    ): Boolean {
        val searchString = query ?: return false
        startSearch(searchString)
        return false
    }

    private fun startSearch(
        query: String
    ) {
        if (query.isBlank()) {
            adapter.updateList(allNotesList)
        }
        val sortedNoteList = allNotesList.filter { note: Note ->
            note.title.contains(query, ignoreCase = true)
        }
        adapter.updateList(sortedNoteList)
    }
}

const val NOTE_KEY = "NOTE_KEY"