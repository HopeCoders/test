package com.hussainalhaddad.friends;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

/**
 * Created by hussainalhaddad on 3/29/16.
 */
public class FriendsDataBase extends SQLiteOpenHelper {
    private static  final String TAG =FriendsDataBase.class.getSimpleName();
    private static  final String DATABASE_NAME ="friends.db";
    private static  final int DATABASE_VERSION = 2;
    private final Context mContext;

    interface tables{
        String FRIENDS="friends";

    }

    public  FriendsDataBase(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
        mContext=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+tables.FRIENDS+" ("
                + BaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + FriendsContract.FriendsColumns.FRIENDS_NAME+" TEXT NOT NULL,"
                        + FriendsContract.FriendsColumns.FRIENDS_EMAIL+" TEXT NOT NULL,"
                        + FriendsContract.FriendsColumns.FRIENDS_PHONE+" TEXT NOT NULL)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        int version=oldVersion;
        if(version==1){
            //add some extra fields to the database without deleting the existing data
            version=2;
        }

        if (version!=DATABASE_VERSION){
            db.execSQL("DROP TABLE IF EXISTS "+tables.FRIENDS);
            onCreate(db);

        }
    }

    public static void deleteDatabase(Context context){
        context.deleteDatabase(DATABASE_NAME);
    }
}
