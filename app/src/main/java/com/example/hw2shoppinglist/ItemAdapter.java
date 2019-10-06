package com.example.hw2shoppinglist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ItemAdapter extends RecyclerView.Adapter {
    private ArrayList<ItemRow> mItemRows;

    // for click
    //private onItemClickListener mListener ;
    private onItemClickListener mListener;
    // for click
    public interface onItemClickListener{
        void onItemLick(int position);
        void onDeleteClick(int position);
    }

    public void setOnItemClickListener(onItemClickListener listener){
        mListener = listener;

    }



    public static class ItemAdapterHolder extends RecyclerView.ViewHolder{

        public CheckBox mCheckBox;
        public ImageView mImageViewCategory;
        public TextView mItemName;

        // delete
        public ImageButton mDeleteBt;

        public ItemAdapterHolder(View itemView, final onItemClickListener listener){
            super(itemView);
            mCheckBox = itemView.findViewById(R.id.id_row_checkBox);
            mImageViewCategory = itemView.findViewById(R.id.id_row_categoryImage);
            mItemName = itemView.findViewById(R.id.id_row_itemName);
            mDeleteBt = itemView.findViewById(R.id.id_row_delete);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            listener.onItemLick(position);
                        }
                    }
                }
            });

            //delete one row
            mDeleteBt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            listener.onDeleteClick(position);
                        }
                    }
                }
            });
        }

    }

    public ItemAdapter(ArrayList<ItemRow> itemRows){
        mItemRows = itemRows;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row,parent,false);
        ItemAdapterHolder evh = new ItemAdapterHolder(v, mListener);
        return evh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ItemRow currentItem = mItemRows.get(position);

        // has to be like this format
//        ((ItemAdapterHolder)holder).mImageViewCategory.setImageResource(currentItem.getmImageCategoty());
        ((ItemAdapterHolder)holder).mImageViewCategory.getResources().getIdentifier(currentItem.getmImageCategoty(), "drawable", "com.example.hw2shoppinglist");
//        ((ItemAdapterHolder)holder).mCheckBox.setActivated(currentItem.getmCheckBox());
        ((ItemAdapterHolder)holder).mCheckBox.setChecked(currentItem.getmCheckBox());
        ((ItemAdapterHolder)holder).mItemName.setText(currentItem.getmItemName());


    }


    public int getItemCount() {
        return mItemRows.size();
    }



}

