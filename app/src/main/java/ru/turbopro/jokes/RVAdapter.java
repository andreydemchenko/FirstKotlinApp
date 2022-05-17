package ru.turbopro.jokes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RVAdapter extends RecyclerView.Adapter<JokeViewHolder>{
    private List<String> jokes;
    private Context context;
    private LayoutInflater mLayoutInflater;

    public RVAdapter(Context context, List<String> data ) {
        this.context = context;
        this.jokes = data;
        this.mLayoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public JokeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate view from card_view.xml
        View recyclerViewItem = mLayoutInflater.inflate(R.layout.cardview, parent, false);

        recyclerViewItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleRecyclerItemClick( (RecyclerView)parent, v);
            }
        });
        return new JokeViewHolder(recyclerViewItem);
    }

    @Override
    public void onBindViewHolder(@NonNull JokeViewHolder holder, int position) {
        // Set joke in jokes via position
        String joke = this.jokes.get(position);
        holder.textView.setText(joke);
    }

    @Override
    public int getItemCount() {
        return this.jokes.size();
    }

    // Click on RecyclerView Item.
    private void handleRecyclerItemClick(RecyclerView recyclerView, View itemView) {
        int itemPosition = recyclerView.getChildLayoutPosition(itemView);
        String joke  = this.jokes.get(itemPosition);

        Toast.makeText(this.context, joke, Toast.LENGTH_LONG).show();
    }
}
