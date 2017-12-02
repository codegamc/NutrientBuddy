package edu.cwru.students.nutrientbuddy;


import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.content.Context;
import android.database.Cursor;

public class ShoppingDatabaseHelper extends SQLiteOpenHelper{

    public static final String TABLE_USERS = "users";

    public static final String COL_ID = BaseColumns._ID;

    public static final String COL_NAME = "name";

    public static final String COL_QUANTITY = "quantity";

    public static final String COL_COST = "cost";

    public static final String DATABASE_NAME = "shopping.db";

    private static final int DATABASE_VERSION = 1;

    public ShoppingDatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("CREATE TABLE " + TABLE_USERS + " ("

                + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"

                + COL_NAME + " TEXT,"

                + COL_QUANTITY + " TEXT,"

                + COL_COST + " TEXT"

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

    public int removeAll(String tableName){
        return getWritableDatabase().delete(tableName, null, null);
    }

    public Cursor query (String tableName, String orderedBy){
        String[] projection = {COL_ID, COL_NAME, COL_QUANTITY, COL_COST};

        return getReadableDatabase().query(tableName, projection, null, null, null, null, orderedBy);
    }



}
