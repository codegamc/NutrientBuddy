package edu.cwru.students.nutrientbuddy;


import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.content.Context;
import android.database.Cursor;

public class DatabaseHelper extends SQLiteOpenHelper{

    public static final String TABLE_USERS = "users";

    public static final String COL_ID = BaseColumns._ID;

    public static final String COL_NAME = "name";

    public static final String COL_INGREDIENTS = "ingredients";

    public static final String COL_DIRECTIONS = "directions";

    public static final String DATABASE_NAME = "recipe.db";

    private static final int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("CREATE TABLE " + TABLE_USERS + " ("

            + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"

            + COL_NAME + " TEXT,"

            + COL_INGREDIENTS + " TEXT,"

            + COL_DIRECTIONS + " TEXT"

            + ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS + ";");

        onCreate(db);
    }

    public long insert(String tableName, ContentValues values){
        return getWritableDatabase().insert(tableName, null, values);
    }

    public int update(String tableName, long id, ContentValues values){
        String selection = COL_ID + " = ?";
        String[] selectionArgs = {String.valueOf(id)};
        return getWritableDatabase().update(tableName, values, selection, selectionArgs);
    }

    public int delete(String tableName, long id){
        String selection = COL_ID + " = ?";
        String[] selectionArgs = {String.valueOf(id)};

        return getWritableDatabase().delete(tableName, selection, selectionArgs);
    }

    public Cursor query (String tableName, String orderedBy){
        String[] projection = {COL_ID, COL_NAME, COL_INGREDIENTS, COL_DIRECTIONS};

        return getReadableDatabase().query(tableName, projection, null, null, null, null, orderedBy);
    }



}
