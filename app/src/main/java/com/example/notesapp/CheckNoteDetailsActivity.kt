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
        if (note.checkBoxIsCheckedList.isNotEmpty()){
            binding.firstCheckBox.isChecked = note.checkBoxIsCheckedList[0]
        }
        if (note.checkBoxIsCheckedList.isNotEmpty()){
            binding.secondCheckBox.isChecked = note.checkBoxIsCheckedList[1]
        }
        if (note.checkBoxIsCheckedList.isNotEmpty()){
            binding.thirdCheckBox.isChecked = note.checkBoxIsCheckedList[2]
        }
        if (note.checkBoxIsCheckedList.isNotEmpty()){
            binding.fourCheckBox.isChecked = note.checkBoxIsCheckedList[3]
        }
        if (note.checkBoxIsCheckedList.isNotEmpty()){
            binding.fifthCheckBox.isChecked = note.checkBoxIsCheckedList[4]
        }
        if (note.checkBoxIsCheckedList.isNotEmpty()){
            binding.sixthCheckBox.isChecked = note.checkBoxIsCheckedList[5]
        }
        if(note.checkBoxTilesList.isNotEmpty()){
            binding.firstCheckBoxTitle.text = note.checkBoxTilesList[0]
        }
        if(note.checkBoxTilesList.isNotEmpty()){
            binding.secondCheckBoxTitle.text = note.checkBoxTilesList[1]
        }
        if(note.checkBoxTilesList.isNotEmpty()){
            binding.thirdCheckBoxTitle.text = note.checkBoxTilesList[2]
        }
        if(note.checkBoxTilesList.isNotEmpty()){
            binding.fourthCheckBoxTitle.text = note.checkBoxTilesList[3]
        }
        if(note.checkBoxTilesList.isNotEmpty()){
            binding.fifthCheckBoxTitle.text = note.checkBoxTilesList[4]
        }
        if(note.checkBoxTilesList.isNotEmpty()){
            binding.sixthCheckBoxTitle.text = note.checkBoxTilesList[5]
        }
    }
}