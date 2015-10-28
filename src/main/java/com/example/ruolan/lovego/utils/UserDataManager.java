package com.example.ruolan.lovego.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ruolan on 2015/10/28.
 */
public class UserDataManager {


    private static final String DB_NAME = "user_name";
    private static final int VERSION = 2;
    private static final String TABLE_NAME = "users";
    private static final String ID = "_id";
    private static final String USER_NAME = "user_name";
    private static final String USER_PASSWORD = "user_password";

    private Context mContext = null;

    private DatabaseManagerHelper managerHelper;
    private SQLiteDatabase mSQLiteDatabase;

    private static final String DB_CREATE = "create table" + TABLE_NAME  + "(" + ID + "integer primary key," +
            USER_NAME + "varchar," + USER_PASSWORD + "varchar" + ")";

    private static class DatabaseManagerHelper extends SQLiteOpenHelper{

        public DatabaseManagerHelper(Context context) {
            super(context, DB_NAME, null, 2);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("drop table if exists" + TABLE_NAME + ";");
            db.execSQL(DB_CREATE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            onCreate(db);
        }
    }



    public UserDataManager (Context context){
        mContext = context;
    }

    public void openDatabase(){
        managerHelper = new DatabaseManagerHelper(mContext);
        mSQLiteDatabase = managerHelper.getWritableDatabase();
    }

    public void closeDatabase(){
        mSQLiteDatabase.close();
    }

    public long insertUserData(UserData userData){
        String userName = userData.getUserName();
        String userPassword = userData.getUserPassword();
        ContentValues values = new ContentValues();
        values.put(USER_NAME,userName);
        values.put(USER_PASSWORD,userPassword);
        return mSQLiteDatabase.insert(TABLE_NAME,ID,values);
    }

    public int findUsername(String userName){
        int result = 0;
        Cursor cursor = mSQLiteDatabase.query(TABLE_NAME,null,USER_NAME + "=" + userName,null,null,null,null);
        if (cursor != null){
            result =cursor.getCount();
            cursor.close();
        }
        return result;
    }

    public int findUserNameAndPwd(String userName,String pwd){
        int result = 0;
        Cursor cursor = mSQLiteDatabase.query(TABLE_NAME, null, USER_NAME + "=" + userName + "and" + USER_PASSWORD + "=" + pwd, null, null, null, null);

        if (cursor != null){
            result = cursor.getCount();
            cursor.close();
        }
        return result;
    }
}
