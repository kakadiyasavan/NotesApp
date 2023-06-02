package com.example.notesapp

import android.os.Build
import android.os.Bundle
import android.os.PersistableBundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.notesapp.DataBase.RoomDB
import com.example.notesapp.Model.Notes
import com.example.notesapp.databinding.AddNoteBinding
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class AddNote : AppCompatActivity() {

    lateinit var binding : AddNoteBinding
    var selectColor = 0

    lateinit var db : RoomDB

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {

        super.onCreate(savedInstanceState)
        binding = AddNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = RoomDB.getInstance(this)

        binding.cardAdd.setOnClickListener {

            val  formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss a")
            val  current = LocalDateTime.now().format(formatter)

             var note = Notes(
                 binding.edtTitles.text.toString(),
                 binding.edtNotes.text.toString(),current,selectColor,false
             )
            db.notes().addNote(note)
            finish()
        }

    }
}