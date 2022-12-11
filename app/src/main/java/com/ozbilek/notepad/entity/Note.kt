package com.ozbilek.notepad.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity("Note")
data class Note(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "Note_ID") @NotNull var NoteID:Int,
    @ColumnInfo(name = "Note_Title") @NotNull var NoteTitle:String,
    @ColumnInfo(name = "Note_Text") @NotNull var NoteText:String,
    @ColumnInfo(name= "Is_Favourite") @NotNull var isFavourite:Int,
    @ColumnInfo(name = "Note_Tag") @NotNull var NoteTag:String
)
