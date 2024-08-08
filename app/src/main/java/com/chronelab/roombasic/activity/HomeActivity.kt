package com.chronelab.roombasic.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.chronelab.roombasic.database.entity.Note
import com.chronelab.roombasic.ui.theme.RoomBasicTheme
import com.chronelab.roombasic.ui.view.ViewHome
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class HomeActivity : ComponentActivity() {
    companion object {
        private  val TAG = com.chronelab.roombasic.activity.HomeActivity::class.java.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val roomApp = application as com.chronelab.roombasic.RoomApp
        val loggedUser = roomApp.loggedUser()
        Log.i(com.chronelab.roombasic.activity.HomeActivity.Companion.TAG, "${loggedUser}")
        setContent {
            RoomBasicTheme {
                var notes by remember { mutableStateOf(listOf<Note>()) }
                LaunchedEffect(Unit) {
                    val roomApp = application as com.chronelab.roombasic.RoomApp
                    val flowNotes: Flow<List<Note>> = roomApp.dbContainer.notesRepositoryInterface.getAllNotesStream()
                    flowNotes.collect { notesList ->
                        notes = notesList
                    }
                }
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val coroutineScope = rememberCoroutineScope()

                    ViewHome(notes = notes, leftBtnAction = {
                        btnAddNoteAction()
                    }, rightBtnAction = {
                        logout()
                    }, onDelete = {note: Note ->
                        coroutineScope.launch {
                            deleteNoteAction(note)
                        }
                    }, onEdit = {note: Note ->
                        editNoteAction(note)
                    }
                    )
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        Log.i("HomeActivity", "onResume called.")
    }

    private fun editNoteAction(note: Note) {
        println("Edit note: ${note.title}")
    }

    private suspend fun deleteNoteAction(note: Note) {
        val roomApp = application as com.chronelab.roombasic.RoomApp
        roomApp.dbContainer.notesRepositoryInterface.deleteNote(note)
    }

    private fun btnAddNoteAction() {
        var intent = Intent(this, com.chronelab.roombasic.activity.NoteAddActivity::class.java)
        startActivity(intent)
    }

    private fun logout() {
        val roomApp = application as com.chronelab.roombasic.RoomApp
        roomApp.logout()
        finish()
    }
}

