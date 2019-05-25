package com.goldengears.gradeestimator.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.goldengears.gradeestimator.R;
import com.goldengears.gradeestimator.models.Grade;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class GradesRecyclerAdapter extends RecyclerView.Adapter<GradesRecyclerAdapter.ViewHolder> {

    private static final String TAG = "GradesRecyclerAdapter";

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

        Grade grade = mGradesList.get(position);

        holder.bind(grade);
        SimpleDateFormat format = new SimpleDateFormat("E, dd MMM yyyy hh:mm");
        Log.d(TAG, "onBindViewHolder: initial date " + grade.getDate());
//        Timestamp ts = new Timestamp(grade.getDate());
        Date date = new Date(grade.getDate());
        Log.d(TAG, "onBindViewHolder: initial date java " + date);
        Log.d(TAG, "onBindViewHolder: date format " + format.format(date));
//            date = format.parse(mGradesList.get(position).getDate());
        holder.timestamp.setText(format.format(date));
        holder.editTimestamp.setText(format.format(date));
        holder.title.setText(grade.getTitle());
        holder.editScore.setText(grade.getScore().toString());

        holder.itemView.setOnClickListener(v -> {
            // Get the current state of the item
            boolean expanded = grade.isExpanded();
            // Change the state
            grade.setExpanded(!expanded);
            // Notify the adapter that item has changed
            notifyItemChanged(position);
        });
    }

    @Override
    public int getItemCount() {
        return mGradesList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView title, timestamp;
        private EditText editScore, editTimestamp;
        private LinearLayout subItem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.grade_title);
            timestamp = itemView.findViewById(R.id.grade_timestamp);
            editScore = itemView.findViewById(R.id.edit_score);
            editTimestamp = itemView.findViewById(R.id.edit_timestamp);

            subItem = itemView.findViewById(R.id.sub_item);
        }

        public void bind(Grade grade) {
            // Get the state
            boolean expanded = grade.isExpanded();
            // Set the visibility based on state

            subItem.setVisibility(expanded ? View.VISIBLE : View.GONE);

        }
    }
}
