package com.example.notesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.notesapp.databinding.ActivityCheckNoteDetailsBinding
import com.example.notesapp.models.Note

class CheckNoteDetailsActivity : AppCompatActivity() {
    private val binding:ActivityCheckNoteDetailsBinding by lazy {
        ActivityCheckNoteDetailsBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val note = intent.extras?.getSerializable(NOTE_KEY) as? com.example.notesapp.models.Note
        initViews(note)
    }
    private fun initViews(note: Note?){
        if (note == null) return
        binding.titleTextView.text = note.title
        binding.lastEditedTextView.text = "last edited:" + note.lastEditedDate.toString()
        binding.firstCheckBox.isChecked = note.checkBoxIsCheckedList[0]
        binding.secondCheckBox.isChecked = note.checkBoxIsCheckedList[1]
        binding.thirdCheckBox.isChecked = note.checkBoxIsCheckedList[2]
        binding.fourCheckBox.isChecked = note.checkBoxIsCheckedList[3]
        binding.fifthCheckBox.isChecked = note.checkBoxIsCheckedList[4]
        binding.sixthCheckBox.isChecked = note.checkBoxIsCheckedList[5]
        binding.firstCheckBoxTitle.text = note.checkBoxTilesList[0]
        binding.secondCheckBoxTitle.text = note.checkBoxTilesList[1]
        binding.thirdCheckBoxTitle.text = note.checkBoxTilesList[2]
        binding.fourthCheckBoxTitle.text = note.checkBoxTilesList[3]
        binding.fifthCheckBoxTitle.text = note.checkBoxTilesList[4]
        binding.sixthCheckBoxTitle.text = note.checkBoxTilesList[5]
    }
}