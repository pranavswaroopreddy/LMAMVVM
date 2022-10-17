package com.example.lmamvvm;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lmamvvm.databinding.CourseListItemBinding;
import com.example.lmamvvm.model.Course;

import java.util.ArrayList;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CourseViewHolder> {

    private onItemClickListener listener;
    private ArrayList<Course> courses =new ArrayList<>();

    @NonNull
    @Override
    public CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull CourseViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }


    class CourseViewHolder extends RecyclerView.ViewHolder{
        private CourseListItemBinding courseListItemBinding;

        public CourseViewHolder(@NonNull View itemView, CourseListItemBinding courseListItemBinding) {
            super(itemView);
            this.courseListItemBinding = courseListItemBinding;

            courseListItemBinding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int clickedPosition = getAdapterPosition();
                    if(listener!= null && clickedPosition !=RecyclerView.NO_POSITION){
                        listener.onItemClick(courses.get(clickedPosition));

                    }
                }
            });
        }
    }

    public interface onItemClickListener{
        void onItemClick(Course course);


    }

    public void setListener(onItemClickListener listener){
        this.listener=listener;
    }

}

