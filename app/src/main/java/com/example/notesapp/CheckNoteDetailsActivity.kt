package com.example.notesapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.notesapp.databinding.ActivityCheckNoteDetailsBinding
import com.example.notesapp.db.Database
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
        val database = Database(this)
        binding.checkNoteButtonSaveChanges.setOnClickListener{
            database.updateCheckNote(
                oldNote = note!!,
                title = binding.titleEditView.text.toString(),
                isCheckedList = listOf(
                    binding.firstCheckBox.isChecked,
                    binding.secondCheckBox.isChecked,
                    binding.thirdCheckBox.isChecked,
                    binding.fourthCheckBox.isChecked,
                    binding.fifthCheckBox.isChecked,
                    binding.firstCheckBox.isChecked,
                ),
                titles = listOf(
                    binding.firstCheckBoxEdit.text.toString(),
                    binding.secondCheckBoxEdit.text.toString(),
                    binding.thirdCheckBoxEdit.text.toString(),
                    binding.fourthCheckBoxEdit.text.toString(),
                    binding.fifthCheckBoxEdit.text.toString(),
                    binding.sixthCheckBoxEdit.text.toString(),
                )
            )
        }
        binding.imageBackspace.setOnClickListener { navigateBackToMainActivity() }
    }
    private fun initViews(note: Note?){
        if (note == null) return
        binding.titleEditView.setText(note.title)
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
            binding.fourthCheckBox.isChecked = note.checkBoxIsCheckedList[3]
        }
        if (note.checkBoxIsCheckedList.isNotEmpty()){
            binding.fifthCheckBox.isChecked = note.checkBoxIsCheckedList[4]
        }
        if (note.checkBoxIsCheckedList.isNotEmpty()){
            binding.sixthCheckBox.isChecked = note.checkBoxIsCheckedList[5]
        }
        if(note.checkBoxTilesList.isNotEmpty()){
            binding.firstCheckBoxEdit.setText(note.checkBoxTilesList[0])
        }
        if(note.checkBoxTilesList.isNotEmpty()){
            binding.secondCheckBoxEdit.setText(note.checkBoxTilesList[1])
        }
        if(note.checkBoxTilesList.isNotEmpty()){
            binding.thirdCheckBoxEdit.setText(note.checkBoxTilesList[2])
        }
        if(note.checkBoxTilesList.isNotEmpty()){
            binding.fourthCheckBoxEdit.setText(note.checkBoxTilesList[3])
        }
        if(note.checkBoxTilesList.isNotEmpty()){
            binding.fifthCheckBoxEdit.setText(note.checkBoxTilesList[4])
        }
        if(note.checkBoxTilesList.isNotEmpty()){
            binding.sixthCheckBoxEdit.text = note.checkBoxTilesList[5]
        }
    }
    private fun navigateBackToMainActivity(){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}