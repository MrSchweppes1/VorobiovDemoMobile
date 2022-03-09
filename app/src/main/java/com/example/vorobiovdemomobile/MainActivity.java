package com.example.vorobiovdemomobile;

import androidx.appcompat.app.AppCompatActivity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    DBHelper dBhelper;
    SQLiteDatabase database;
    ContentValues contentValues;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        dBhelper = new DBHelper(this);
        database = dBhelper.getWritableDatabase();
        UpdateTable();
    }

    public void UpdateTable() {
        Cursor cursor = database.query(DBHelper.TABLE_PRODUCT, null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            int idIndex = cursor.getColumnIndex(DBHelper.KEY_ID);
            int TitleIndex = cursor.getColumnIndex(DBHelper.KEY_Title);
            int PTIndex = cursor.getColumnIndex(DBHelper.KEY_ProductTypeId);
            int ArtIndex = cursor.getColumnIndex(DBHelper.KEY_ArticleNumber);
            int ImageIndex = cursor.getColumnIndex(DBHelper.KEY_Image);
            int PPCIndex = cursor.getColumnIndex(DBHelper.KEY_ProductionPersonCount);
            int PWNIndex = cursor.getColumnIndex(DBHelper.KEY_ProductionWorkshopNumber);
            int CostIndex = cursor.getColumnIndex(DBHelper.KEY_MinCostForAgent);


            TableLayout layOutput = findViewById(R.id.TabLay);
            layOutput.removeAllViews();
            do {
                TableRow TBrow = new TableRow(this);
                TBrow.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));

                TableRow.LayoutParams params = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT);

                TextView outputID = new TextView(this);
                params.weight = 1.0f;
                outputID.setLayoutParams(params);
                outputID.setText(cursor.getString(idIndex));
                TBrow.addView(outputID);

                TextView outputNm = new TextView(this);
                params.weight = 3.0f;
                outputNm.setLayoutParams(params);
                outputNm.setText(cursor.getString(TitleIndex));
                TBrow.addView(outputNm);


                TextView outPT = new TextView(this);
                params.weight = 1.0f;
                outPT.setLayoutParams(params);
                outPT.setText(cursor.getString(PTIndex));
                TBrow.addView(outPT);

                TextView outArt = new TextView(this);
                params.weight = 2.0f;
                outArt.setLayoutParams(params);
                outArt.setText(cursor.getString(ArtIndex));
                TBrow.addView(outArt);

                layOutput.addView(TBrow);

                TextView outCost = new TextView(this);
                params.weight = 1.0f;
                outCost.setLayoutParams(params);
                outCost.setText(cursor.getString(CostIndex));
                TBrow.addView(outCost);


            } while (cursor.moveToNext());
        }
        cursor.close();
    }
}