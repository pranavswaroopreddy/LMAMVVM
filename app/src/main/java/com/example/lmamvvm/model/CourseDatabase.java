package com.example.lmamvvm.model;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Category.class, Course.class}, version = 1)
public abstract class CourseDatabase extends RoomDatabase {

    public abstract CategoryDAO categoryDAO();

    public abstract CourseDAO courseDAO();

    //singleton pattern
    private static CourseDatabase instance;

    public static synchronized CourseDatabase getInstance(Context context) {

        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            CourseDatabase.class,
                            "courses_database"
                    ).fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }

        return instance;
    }
    //callback

    private static RoomDatabase.Callback roomCallback =new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            //insert data when db is created

        }
    };

}
