package com.example.notesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.notesapp.databinding.ActivitySimpleNoteDetailsBinding
import com.example.notesapp.models.Note

class SimpleNoteDetailsActivity : AppCompatActivity() {
    private val binding:ActivitySimpleNoteDetailsBinding by lazy {
        ActivitySimpleNoteDetailsBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val note = intent.extras?.getSerializable(NOTE_KEY) as? Note
    }
    private fun initViews(note: Note?){
        if (note == null) return
        binding.titleTextView.text = note.title
        binding.descripitionTextView.text = note.description
        binding.lastEditedTextView.text = "Last edited:" + note.lastEditedDate.toString()
    }

}

