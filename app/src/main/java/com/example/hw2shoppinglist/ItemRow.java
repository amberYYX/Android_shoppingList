package com.example.hw2shoppinglist;

import android.os.Parcel;
import android.widget.CheckBox;
import android.widget.ImageButton;

import java.io.Serializable;

public class ItemRow {
    private boolean mCheckBox;
    private String mImageCategory;
    private String mItemName;
    private String mBudget;
    private String mDescription;





    public void changetext(String text){
        mItemName = text;
    }


    public ItemRow(boolean checkBox,
                   String imageCategory,
                   String itemName,
                   String budget,
                   String description)
    {
        mCheckBox = checkBox;
        mImageCategory = imageCategory;
        mItemName = itemName;
        mBudget = budget;
        mDescription = description;
    }
//
//    public ItemRow(boolean checkBox,
//                   int imageCategory,
//                   String itemName,
//                   ImageButton details,
//                   ImageButton delete){
//        mCheckBox = checkBox;
//        mImageCategory = imageCategory;
//        mItemName = itemName;
//        mDetails = details;
//        mDelete = delete;
//    }



    public String getmImageCategoty(){return mImageCategory;}

    public boolean getmCheckBox(){
        return mCheckBox;
    }

    public String getmItemName(){
        return mItemName;
    }

    public String getmBudget(){return mBudget;}

    public String getmDescription(){return mDescription;}


}

