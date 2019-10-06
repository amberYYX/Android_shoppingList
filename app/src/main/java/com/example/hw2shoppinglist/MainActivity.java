package com.example.hw2shoppinglist;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    private ArrayList<ItemRow> itemRows;
    private String itemName;

    private ItemRow NewItemRow;


    private RecyclerView mRecyclerView;
//    private RecyclerView.Adapter mAdapter;
    private ItemAdapter mAdapter; //click change

    private RecyclerView.LayoutManager mLayoutManager;

    private Button buttonAddNewItem;
    private Button buttonDelete;

    public static final String Tag = "MainActivity";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        creatItemRowsList();
        buildRecyclerView();

        buttonAddNewItem = findViewById(R.id.id_bt_insert);
        buttonDelete = findViewById(R.id.id_bt_deleteAll);


        buttonAddNewItem.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
//                insertItem(position); //insert a new item
/*----------------This is for an dialog.*/
//                showDialog();
                Intent intent = new Intent();

                intent.setClass(MainActivity.this,EditToDoActivity.class);
                Bundle bundle = new Bundle();
                intent.putExtras(bundle);
                startActivityForResult(intent,66);


                Log.d(Tag,"insert button clicked.");
            }
        });





        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = itemRows.size() -1;
                deleteItem(position);

            }
        });

    }



    public void deleteItem(int position){
        itemRows.remove(position);
        mAdapter.notifyDataSetChanged();
    }

    public void insertItem(int position, ItemRow itemRow){
//        itemRows.add(position, new ItemRow(true,R.drawable.category1,itemName));
//        itemRows.add(position, new ItemRow(true,R.drawable.category1,"add one"));

        itemRows.add(position, itemRow);

//        itemRows.add(position,new ItemRow(true,R.drawable.category1,"new one"));
//        itemRows.add(position,new ItemRow(true,R.drawable.category1,"new one"));
        mAdapter.notifyDataSetChanged();
    }

    //click
    public void changeItem(int position,String text){
        itemRows.get(position).changetext("clicked");
        mAdapter.notifyItemChanged(position);
    }



    public void creatItemRowsList(){
        itemRows = new ArrayList<>();
    }

    public void buildRecyclerView(){
        mRecyclerView = findViewById(R.id.id_recyclerView);
        mRecyclerView.setHasFixedSize(true); //
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new ItemAdapter(itemRows);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new ItemAdapter.onItemClickListener() {
            @Override
            public void onItemLick(int position) {
                changeItem(position,"Clicked");
            }

            @Override
            public void onDeleteClick(int position) {
                deleteItem(position);
            }
        });
    }



    //customer dialog
//    protected void showDialog(){
//        AlertDialog.Builder newbuilder = new AlertDialog.Builder(this);
//        // Get the layout inflater
//        LayoutInflater inflater = this.getLayoutInflater();
//
////        Spinner spinner;
////        ArrayAdapter <String> adapter;
////        ArrayList<String> list;
////
////        list = null;
////        list.add("1");
////        list.add("2");
////
////
////
////            spinner=(Spinner)findViewById(R.id.spinner);
////
////            adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,list);
////            spinner.setAdapter(adapter);
////
////            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
////                @Override
////                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//////                    Toast.makeText(MainActivity.this,"您选择的月份是："+list.get(position),Toast.LENGTH_SHORT).show();
////                    Log.d(Tag, "clicked"+ position);
////                }
////
////                @Override
////                public void onNothingSelected(AdapterView<?> parent) {
////
////                    Log.d(Tag, "clicked"+ "nothing");
////                }
////            });
//
//
//
//
//
//
//        // Inflate and set the layout for the dialog
//        // Pass null as the parent view because its going in the dialog layout
//        newbuilder.setView(inflater.inflate(R.layout.activity_edit_item, null))
//                // Add action buttons
//                .setPositiveButton("SAVE", new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int id) {
//                        Dialog f = (Dialog) dialog;
//
//
//                        /* ERROR HERE! */
//                        EditText newItemName = (EditText) f.findViewById(R.id.id_edit_itemName);
//                        String newItem = newItemName.getText().toString();
//                        int position = itemRows.size() ;
//
//
//
//                        addNewItem(position,"category2",newItem);
//
//
//                        Log.d(Tag,newItem + "clicked");
//
//
//                    }
//                })
//                .setNegativeButton("CANCLE",  new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int id) {
//                        dialog.dismiss();
//                    }
//                });
//
//        newbuilder.create().show();
//    }


    public void addNewItem(int mPosition,
                           boolean mCheckBox,
                           String mImageCategory,
                           String mItemName,
                           String mBudget,
                           String mDescription){
        int position = mPosition;

        boolean checkBox = mCheckBox;

        String imageName = mImageCategory;
        int resID = getResources().getIdentifier(imageName, "drawable", "com.example.hw2shoppinglist");


        String newItemName = mItemName;
        String budget = mBudget;
        String description = mDescription;

        itemRows.add(position, new ItemRow(checkBox,imageName,newItemName,budget,description));

        mAdapter.notifyDataSetChanged();
    }

//    public void addNewItem(int mPosition,ItemRow itemRow){
//        int position = mPosition;
//
//        itemRows.add(position, itemRow);
//
//        mAdapter.notifyDataSetChanged();
//    }


    /**
     * 为了得到传回的数据，必须在前面的Activity中（指MainActivity类）重写onActivityResult方法
     *
     * requestCode 请求码，即调用startActivityForResult()传递过去的值
     * resultCode 结果码，结果码用于标识返回数据来自哪个新Activity
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bundle bundle = data.getExtras();
        Boolean checkbox_new = bundle.getBoolean("purchase");
        String itemName_new = bundle.getString("itemName");
        String budget_new = bundle.getString("budget");
        String description_new = bundle.getString("description");
        int position = itemRows.size();
        addNewItem(position,checkbox_new,"category2",itemName_new,budget_new,description_new);
        mAdapter.notifyItemChanged(position);
        Log.d(Tag, "transformation finished"+ checkbox_new);
    }



}
