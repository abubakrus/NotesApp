package com.example.notesapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.notesapp.databinding.ActivitySimpleNoteDetailsBinding
import com.example.notesapp.db.Database
import com.example.notesapp.models.Note

class SimpleNoteDetailsActivity : AppCompatActivity() {
    private val binding:ActivitySimpleNoteDetailsBinding by lazy {
        ActivitySimpleNoteDetailsBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val note = intent.extras?.getSerializable(NOTE_KEY) as? Note

        val database = Database(this)
        binding.buttonSaveChanges.setOnClickListener{
            database.updateSimpleNote(
                oldNote = note!!,
                title = binding.titleEditView.text.toString(),
                description = binding.descriptionEditView.text.toString()
            )
        }
        binding.imageBackspace.setOnClickListener{navigateBackToMainActivity()}
    }
    private fun initViews(note: Note?){
        if (note == null) return
        binding.titleEditView.setText(note.title)
        binding.descriptionEditView.setText(note.description)
        binding.lastEditedTextView.text = "Last edited:" + note.lastEditedDate.toString()
    }
    private fun navigateBackToMainActivity(){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}

