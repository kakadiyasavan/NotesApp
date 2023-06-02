package com.example.notesapp.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.notesapp.DataBase.RoomDB
import com.example.notesapp.MainActivity
import com.example.notesapp.Model.Notes
import com.example.notesapp.R

class NotesAdapter(noteList: ArrayList<Notes>) : Adapter<NotesAdapter.NotesHolder>() {

    var list = noteList
    lateinit var db : RoomDB
    lateinit var context : Context

    class NotesHolder(itemView: View) : ViewHolder(itemView){

        var txtTitle = itemView.findViewById<TextView>(R.id.txtTitle)
        var txtNote = itemView.findViewById<TextView>(R.id.txtNote)
        var cardNote = itemView.findViewById<TextView>(R.id.cardNote)
        var imgPin = itemView.findViewById<ImageView>(R.id.imgPin)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesHolder {
        context = parent.context
        return NotesHolder(LayoutInflater.from(parent.context).inflate(R.layout.note_item,parent,false))
    }

    override fun getItemCount(): Int {
       return list.size
    }

    override fun onBindViewHolder(holder: NotesHolder, position: Int) {

        db = RoomDB.getInstance(context)

        holder.apply {
            holder.txtTitle.text = list.get(position).title
            holder.txtNote.text = list.get(position).notes
            holder.cardNote.setBackgroundResource(list.get(position).color)

            if (list.get(position).pinned) {
                holder.imgPin.setImageResource(R.drawable.pin1)
            } else {
                holder.imgPin.setImageResource(R.drawable.pin2)
            }
        }
        holder.imgPin.setOnClickListener {

            if (list.get(position).pinned) {
                var data =Notes(list.get(position).title,list.get(position).notes,list.get(position).date,list.get(position).color,false)
                data.id = list.get(position).id
                db.notes().updateNote(data)
            }else{
                var data =Notes(list.get(position).title,list.get(position).notes,list.get(position).date,list.get(position).color,true)
                data.id = list.get(position).id
                db.notes().updateNote(data)
            }
            MainActivity.update()
        }
    }
}













