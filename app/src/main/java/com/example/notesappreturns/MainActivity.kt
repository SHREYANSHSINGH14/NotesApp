package com.example.notesappreturns

import android.content.Intent
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.notesappreturns.adapter.MainAdapter
import com.example.notesappreturns.database.NotesDatabase
import com.example.notesappreturns.repository.Repository
import com.example.notesappreturns.viewmodel.MainViewModel
import com.example.notesappreturns.viewmodel.MainViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val database = NotesDatabase.getDatabase(this)
        val repository = Repository(database)
        mainViewModel = ViewModelProvider(this,MainViewModelFactory(repository))[MainViewModel::class.java]
        val insertBtn = findViewById<ImageButton>(R.id.addbtn)
        val recycleView = findViewById<RecyclerView>(R.id.mainRecycleView)
        val adapter = MainAdapter()
        val emptyTxt = findViewById<TextView>(R.id.emptyText)

        recycleView.layoutManager = LinearLayoutManager(this)
        recycleView.adapter = adapter
        insertBtn.setOnClickListener {
            val intent = Intent(this@MainActivity,InsertActivity::class.java)
            startActivity(intent)
        }

        mainViewModel.notes.observe(this) {
            Log.i("NOTESFETCH", it.toString())
            if(it.isEmpty()){
                emptyTxt.visibility = View.VISIBLE
            }
            adapter.submitList(it)
        }

    }
}