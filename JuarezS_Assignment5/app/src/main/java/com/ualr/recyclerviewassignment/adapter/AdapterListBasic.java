package com.ualr.recyclerviewassignment.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ualr.recyclerviewassignment.R;
import com.ualr.recyclerviewassignment.Utils.DataGenerator;
import com.ualr.recyclerviewassignment.model.Inbox;

import java.util.List;

public class AdapterListBasic extends RecyclerView.Adapter {
    //Variable that contains data
    private List<Inbox> mItems;
    private Context mContext;

    //Define Listener interface
    private OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener{
        void onItemClick(View view, Inbox obj, int position);
    }

    public void setOnItemClickListener(final OnItemClickListener mItemClickListener){
        this.mOnItemClickListener = mItemClickListener;

    }

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
        viewHolder.from_btn.setText(i.getFrom());


    }

    @Override
    public int getItemCount() {
        //Returns number of items in data collection
        return this.mItems.size();
    }

    public void addItem(int position, Inbox i){
        mItems.add(position, i);
        notifyItemInserted(position);
    }

    public void removeItem(int position){
        //check position is in list
        if(position>=mItems.size()){
            return;
        }
        mItems.remove(position); //Remove item from dataset
        notifyItemRemoved(position); //Notify adapter item was removed
        notifyItemRangeChanged(position, getItemCount()); //Notify adapter set of items has changed
    }

    //Define ViewHolder class as an inner class
    public class InboxViewHolder extends RecyclerView.ViewHolder{
        //Ea instance refers to IV or TV of an associated row item
        //public ImageView image;
        public TextView from;
        public TextView email;
        public TextView message;
        public TextView date;
        public Button from_btn;
        public View lyt_parent;

        public InboxViewHolder(View itemView){
            super(itemView);
            //image =v.findViewById(R.id.circleIV);
            date = itemView.findViewById(R.id.dateTV);
            from = itemView.findViewById(R.id.nameTV);
            email = itemView.findViewById(R.id.emailTV);
            message = itemView.findViewById(R.id.messageTV);
            lyt_parent =itemView.findViewById(R.id.lyt_parent);
            from_btn = itemView.findViewById(R.id.initialBtn);

            lyt_parent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    from_btn.setText("X");
                    mOnItemClickListener.onItemClick(view, mItems.get(getLayoutPosition()),getLayoutPosition());

                }
            });

        }
    }
}
