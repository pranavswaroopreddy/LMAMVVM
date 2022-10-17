package com.example.lmamvvm.model;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
            InitializeDate();

        }
    };

    private static void InitializeDate() {
        CourseDAO courseDAO = instance.courseDAO();
        CategoryDAO categoryDAO = instance.categoryDAO();

        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(new Runnable() {
            @Override
            public void run() {
                //Categories
                Category category1= new Category();
                category1.setCategoryName("Front End");
                category1.setCategoryDescription("Web development Interface");

                Category category2= new Category();
                category2.setCategoryName("Back End");
                category2.setCategoryDescription("Web development Databases");

                categoryDAO.insert(category1);
                categoryDAO.insert(category2);

                //Courses
                Course course1= new Course();
                course1.setCourseName("HTML");
                course1.setUnitPrice("100$");
                course1.setCategoryId(1);

                Course course2= new Course();
                course2.setCourseName("CSS");
                course2.setUnitPrice("100$");
                course2.setCategoryId(1);

                Course course3= new Course();
                course3.setCourseName("PHP");
                course3.setUnitPrice("400$");
                course3.setCategoryId(2);

                Course course4= new Course();
                course4.setCourseName("AJAX");
                course4.setUnitPrice("200$");
                course4.setCategoryId(2);

                courseDAO.insert(course1);
                courseDAO.insert(course2);
                courseDAO.insert(course3);
                courseDAO.insert(course4);

            }
        });
    }

}
