package com.goldengears.gradeestimator.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.goldengears.gradeestimator.R;
import com.goldengears.gradeestimator.models.Grade;

import java.util.ArrayList;

public class GradesRecyclerAdapter extends RecyclerView.Adapter<GradesRecyclerAdapter.ViewHolder> {

    private ArrayList<Grade> mGradesList = new ArrayList<>();

    public GradesRecyclerAdapter(ArrayList<Grade> gradesList) {
        this.mGradesList = gradesList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_grade_list_item,
                parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.timestamp.setText(mGradesList.get(position).getTimestamp());
        holder.title.setText(mGradesList.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return mGradesList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView title, timestamp;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.grade_title);
            timestamp = itemView.findViewById(R.id.grade_timestamp);

        }
    }


}
