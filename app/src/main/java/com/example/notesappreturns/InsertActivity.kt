package com.example.notesappreturns

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import android.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import com.example.notesappreturns.database.Notes
import com.example.notesappreturns.database.NotesDatabase
import com.example.notesappreturns.repository.Repository
import com.example.notesappreturns.viewmodel.InsertViewModel
import com.example.notesappreturns.viewmodel.InsertViewModelFactory
import com.example.notesappreturns.viewmodel.MainViewModel
import com.example.notesappreturns.viewmodel.MainViewModelFactory

class InsertActivity : AppCompatActivity() {
    private lateinit var mainViewModel:MainViewModel
    lateinit var insertViewModel: InsertViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insert)

        val dao = NotesDatabase.getDatabase(applicationContext)
        val repository = Repository(dao)
        insertViewModel = ViewModelProvider(this, InsertViewModelFactory(repository))[InsertViewModel::class.java]
        mainViewModel = ViewModelProvider(this, MainViewModelFactory(repository))[MainViewModel::class.java]
        val btn = findViewById<Button>(R.id.insertBtn)
        val title = findViewById<EditText>(R.id.inTitle)
        val descp = findViewById<EditText>(R.id.inDescription)
        val backBtn = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolBar)

        setSupportActionBar(backBtn)
        if(supportActionBar != null){
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
        backBtn.setNavigationOnClickListener {
            onBackPressed()
        }



        btn.setOnClickListener {
            insertViewModel.insert(Notes(0,title.text.toString(), descp.text.toString()))
            Toast.makeText(applicationContext,"Inserted", Toast.LENGTH_SHORT)
            title.text.clear()
            descp.text.clear()

        }
    }
    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this@InsertActivity,MainActivity::class.java)
        startActivity(intent)
    }
}