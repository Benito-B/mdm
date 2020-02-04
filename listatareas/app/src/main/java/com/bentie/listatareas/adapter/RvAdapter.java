package com.bentie.listatareas.adapter;

import android.content.Context;
import android.graphics.PorterDuff;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bentie.listatareas.R;
import com.bentie.listatareas.model.Task;

import java.util.Date;
import java.util.List;

public class RvAdapter extends RecyclerView.Adapter<RvAdapter.TaskViewHolder> {

    private List<Task> tasks;
    private Context context;

    public RvAdapter(List<Task> tasks, Context context){
        this.tasks = tasks;
        this.context = context;
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card, parent, false);
        TaskViewHolder h = new TaskViewHolder(v);
        return h;
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        Task t = tasks.get(position);
        holder.tvId.setText(String.valueOf(t.getId()));
        holder.tvName.setText(t.getName());
        holder.tvDesc.setText(t.getDescription());
        String totalTime = "";
        if(t.getFinishDate() != null) {
            long days = (t.getFinishDate().getTime() - new Date().getTime()) / DateUtils.DAY_IN_MILLIS;
            long remainderFromDays = t.getFinishDate().getTime() % DateUtils.DAY_IN_MILLIS;
            long hours = remainderFromDays / DateUtils.HOUR_IN_MILLIS;
            long remainderFromHours = remainderFromDays % DateUtils.HOUR_IN_MILLIS;
            long minutes = remainderFromHours / DateUtils.MINUTE_IN_MILLIS;
            long remainderFromMinutes = remainderFromHours % DateUtils.MINUTE_IN_MILLIS;
            long seconds = remainderFromMinutes / 1000;
            totalTime += days > 0 ? String.valueOf(days) + " días, ":"";
            totalTime += hours + " horas, ";
            totalTime += minutes + " minutos, ";
            totalTime += seconds + " segundos.";
        }
        holder.tvDate.setText(context.getString(R.string.time_left, totalTime));
        holder.ivUrgency.setImageResource(t.getUrgency());
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    public static class TaskViewHolder extends RecyclerView.ViewHolder{

        TextView tvId, tvName, tvDesc, tvDate;
        ImageView ivUrgency;

        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);
            tvId = itemView.findViewById(R.id.card_id);
            tvName = itemView.findViewById(R.id.card_name);
            tvDesc = itemView.findViewById(R.id.card_description);
            ivUrgency = itemView.findViewById(R.id.card_color);
            tvDate = itemView.findViewById(R.id.card_date);
        }
    }

}
