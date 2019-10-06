package com.example.hw2shoppinglist;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class EditToDoActivity extends AppCompatActivity {
    public static final String KEY_TODO = "KEY_TODO";
    private EditText mItemName;
    private EditText mBudget;
    private EditText mDescription;
    private CheckBox mPurchaseState;
    private Spinner mCategory;
    private Button mSave;

    private ItemRow newItem;


    public static final String Tag = "SAVE-BUTTON";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);
        mSave = findViewById(R.id.id_edit_saveButton);
        mItemName = findViewById(R.id.id_edit_itemName);
        mBudget = findViewById(R.id.id_edit_budget);
        mDescription = findViewById(R.id.id_edit_description);
        mPurchaseState = findViewById(R.id.id_edit_checkBox);





        mSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String itemName = mItemName.getText().toString();
                String description = mDescription.getText().toString();
                String budget = mBudget.getText().toString();
                boolean purchaseState = mPurchaseState.isChecked();


                Intent intent = getIntent();
                Bundle bundle = intent.getExtras();
                bundle.putBoolean("purchase", purchaseState);
                bundle.putString("itemName",itemName);
                bundle.putString("budget",budget);
                bundle.putString("description",description);
                intent.putExtras(bundle);
                EditToDoActivity.this.setResult(100,intent);
                EditToDoActivity.this.finish();



            }
        });

    }



}
