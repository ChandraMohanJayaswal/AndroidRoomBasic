package com.chronelab.roombasic.database

import android.content.Context
import com.chronelab.roombasic.database.repository.CategoriesRepository
import com.chronelab.roombasic.database.repository.CategoryiesRepositoryInterface
import com.chronelab.roombasic.database.repository.NotesRepository
import com.chronelab.roombasic.database.repository.NotesRepositoryInterface


interface DatabaseContainer {
    val notesRepositoryInterface : NotesRepositoryInterface
    val categoryiesRepositoryInterface: CategoryiesRepositoryInterface
}

class DatabaseDataContainer(private val context: Context) : DatabaseContainer {
    /**
     * Implementation for [ItemsRepositoryInterface]
     */

    override val notesRepositoryInterface: NotesRepositoryInterface by lazy {
        NotesRepository(NoteDatabase.getDatabase(context).noteDao())
    }

    override val categoryiesRepositoryInterface: CategoryiesRepositoryInterface by lazy {
        CategoriesRepository(NoteDatabase.getDatabase(context).categoryDao())
    }
}