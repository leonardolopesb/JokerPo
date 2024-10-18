package com.leonardo.jokenpo

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class MatchHistory(context: Context): SQLiteOpenHelper(context, "match_history", null, 1) {
    companion object {
        const val TABLE_NAME = "match_history"
        const val COLUMN_ID = "id"
        const val COLUMN_USER_CHOICE = "user_choice"
        const val COLUMN_COMPUTER_CHOICE = "computer_choice"
        const val COLUMN_RESULT = "result"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = "CREATE TABLE $TABLE_NAME (" +
                "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "$COLUMN_USER_CHOICE TEXT, " +
                "$COLUMN_COMPUTER_CHOICE TEXT, " +
                "$COLUMN_RESULT TEXT)"
        db?.execSQL(createTable)
    }


    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun insertMatch(userChoice: String, computerChoice: String, result: String) {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_USER_CHOICE, userChoice)
            put(COLUMN_COMPUTER_CHOICE, computerChoice)
            put(COLUMN_RESULT, result)
        }
        db.insert(TABLE_NAME, null, values)
        db.close()
    }

    fun getAllMatches(): Cursor {
        val db = readableDatabase
        return db.rawQuery("SELECT * FROM $TABLE_NAME", null)
    }
}