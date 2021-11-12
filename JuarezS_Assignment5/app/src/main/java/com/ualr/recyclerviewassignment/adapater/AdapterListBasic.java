package com.ualr.recyclerviewassignment.adapater;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ualr.recyclerviewassignment.R;
import com.ualr.recyclerviewassignment.model.Inbox;

import java.util.List;

public class AdapterListBasic extends RecyclerView.Adapter {
    //Variable that contains data
    private List<Inbox> mItems;
    private Context mContext;

    public AdapterListBasic(Context context, List<Inbox> items){
        this.mItems = items;
        this.mContext = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Implement the onCreateViewHolder method
        RecyclerView.ViewHolder vh;
        //Inflate the view for a row item
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_message,parent,false);
        //Viewholder to hold view references inside the view
        vh = new InboxViewHolder(itemView);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        InboxViewHolder viewHolder = (InboxViewHolder)holder;
        //Load information of a specific message from collection
        Inbox i = mItems.get(position);
        //Populate the corresponding TextView
        viewHolder.from.setText(i.getFrom());
        viewHolder.email.setText(i.getEmail());
        viewHolder.message.setText(i.getMessage());
        viewHolder.date.setText(i.getDate());

    }

    @Override
    public int getItemCount() {
        //Returns number of items in data collection
        return this.mItems.size();
    }

    //Define ViewHolder class as an inner class
    public class InboxViewHolder extends RecyclerView.ViewHolder{
        //Ea instance refers to IV or TV of an associated row item
        //public ImageView image;
        public TextView from;
        public TextView email;
        public TextView message;
        public TextView date;
        public View lyt_parent;

        public InboxViewHolder(View v){
            super(v);
            //image =v.findViewById(R.id.circleIV);
            from = v.findViewById(R.id.nameTV);
            email = v.findViewById(R.id.emailTV);
            message = v.findViewById(R.id.messageTV);
            lyt_parent =v.findViewById(R.id.lyt_parent);
        }
    }
}
