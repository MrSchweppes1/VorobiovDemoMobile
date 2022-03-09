package com.example.vorobiovdemomobile;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "productDb";
    public static final String TABLE_PRODUCT = "product";

    public static final String KEY_ID = "ID";
    public static final String KEY_Title = "Title";
    public static final String KEY_ProductTypeId = "ProductTypeId";
    public static final String KEY_ArticleNumber = "ArticleNumber";
    public static final String KEY_Image = "Image";
    public static final String KEY_ProductionPersonCount = "ProductionPersonCount";
    public static final String KEY_ProductionWorkshopNumber = "ProductionWorkshopNumber";
    public static final String KEY_MinCostForAgent = "MinCostForAgent";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_PRODUCT + "(" + KEY_ID
                + " integer primary key," + KEY_Title + " text," + KEY_ProductTypeId + " integer," + KEY_ArticleNumber +" integer,"
                + KEY_Image + " text," + KEY_ProductionPersonCount + " integer," + KEY_ProductionWorkshopNumber + " integer,"+ KEY_MinCostForAgent + " integer"+ ")");

        db.execSQL("insert into " + TABLE_PRODUCT + "(" + "KEY_ID" + ","          ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop table if exists " + TABLE_PRODUCT);
        onCreate(db);
    }

}