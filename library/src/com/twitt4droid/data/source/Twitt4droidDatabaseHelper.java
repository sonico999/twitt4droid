package com.twitt4droid.data.source;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;

public class Twitt4droidDatabaseHelper extends SQLiteOpenHelper {

    private static final int CURRENT_VERSION = 1;
    private static final String TAG = Twitt4droidDatabaseHelper.class.getSimpleName();
    private static final String SCHEMA_FILE_FORMAT = "db/schema-v%s.sql";
    private static final String NAME = "twitt4droid";
    
    private final int version;
    private final Context context;

    public Twitt4droidDatabaseHelper(Context context) {
        super(context, NAME, null, CURRENT_VERSION);
        this.context = context;
        version = CURRENT_VERSION;
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        try {
            Log.v(TAG, "Creating database version " + version + "...");
            InputStream fileStream = context.getAssets().open(String.format(SCHEMA_FILE_FORMAT, version));
            String[] statements = SQLFileParser.getSqlStatements(fileStream);
            for (String statement : statements) {
                Log.d(TAG, statement);
                database.execSQL(statement);
            }
        } catch (IOException ex) {
            Log.e(TAG, "Unable read schema", ex);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        Log.v(TAG, "Destroying version " + oldVersion + "...");
        destroyDb(context);
        onCreate(database);
    }
    
    public static void destroyDb(Context context) {
        context.deleteDatabase(NAME);
    }
}