package com.example.notesapp.models

import java.util.Date

data class Note (
    //данные для симле айтем
    val title: String,
    val description: String,
    val lastEditedDate: Date,
    val isSimpleNote: Boolean,
    //данные для heck box note item
    val checkBoxIsCheckedList:List<Boolean>,
    val checkBoxTilesList:List<String>

):java.io.Serializable