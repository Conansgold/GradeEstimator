package com.goldengears.gradeestimator.adapters;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.goldengears.gradeestimator.GradesActivity;
import com.goldengears.gradeestimator.R;
import com.goldengears.gradeestimator.models.Grade;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
        SimpleDateFormat dateFormat = new SimpleDateFormat("E, dd MMM yyyy");
        SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm");
        Log.d(TAG, "onBindViewHolder: initial date " + grade.getDate());
//        Timestamp ts = new Timestamp(grade.getDate());
        Date date = new Date(grade.getDate());
        Log.d(TAG, "onBindViewHolder: initial date java " + date);
        Log.d(TAG, "onBindViewHolder: date format " + dateFormat.format(date));
//            date = format.parse(mGradesList.get(position).getDate());
        holder.timestamp.setText(dateFormat.format(date));
        holder.editDate.setText(dateFormat.format(date));
        holder.editTime.setText(timeFormat.format(date));
        holder.title.setText(grade.getTitle());
        holder.editScore.setText(grade.getScore().toString());


        TextView displayDate = holder.editDate;
        Calendar cal = Calendar.getInstance();
        DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                cal.set(Calendar.YEAR, year);
                cal.set(Calendar.MONTH, monthOfYear);
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                displayDate.setText(dateFormat.format(cal.getTime()));
            }
        };

        displayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog dialog = new DatePickerDialog(GradesActivity.getmGradesActivityContext(),
                        datePickerListener, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH),
                        cal.get(Calendar.DAY_OF_MONTH));
                dialog.show();
            }
        });


        TextView displayTime = holder.editTime;
        TimePickerDialog.OnTimeSetListener timePickerListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                cal.set(Calendar.HOUR_OF_DAY, hourOfDay);
                cal.set(Calendar.MINUTE, minute);
                displayTime.setText(timeFormat.format(cal.getTime()));
            }
        };

        displayTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog timeDialog = new TimePickerDialog(GradesActivity.getmGradesActivityContext(),
                        timePickerListener, 01, 02, false);
                timeDialog.show();
            }
        });




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

        private TextView title, timestamp, editDate, editTime;
        private EditText editScore;
        private LinearLayout subItem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.grade_title);
            timestamp = itemView.findViewById(R.id.grade_timestamp);
            editScore = itemView.findViewById(R.id.edit_score);
            editDate = itemView.findViewById(R.id.edit_date);
            editTime = itemView.findViewById(R.id.edit_time);

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
