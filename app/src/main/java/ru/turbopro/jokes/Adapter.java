package ru.turbopro.jokes;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {
    private List<Joke> messageList;
    private Activity activity;

    public Adapter(List<Joke> messageList, Activity activity) {
        this.messageList = messageList;
        this.activity = activity;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       // View view = LayoutInflater.from(activity).inflate(R.layout.adapter_message_one, parent, false);
        return null;//new MyViewHolder(view);
    }

    @Override public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        String message = messageList.get(position).getText();
        holder.messageJoke.setText(message);
    }

    @Override public int getItemCount() {
        return messageList.size();
    }

    public void filter(String text) {
        List<Joke> temp = new ArrayList();
        for(Joke d: messageList)
            if(d.getText().contains(text)) temp.add(d);

        messageList = temp;
        notifyDataSetChanged();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView messageJoke;
        RecyclerView recyclerView;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            messageJoke = itemView.findViewById(R.id.tv_joke_text);
            recyclerView = itemView.findViewById(R.id.recyclerView);
        }
    }
}
