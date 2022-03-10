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

import java.io.IOException;
import java.io.InputStream;
import java.sql.Array;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.NClob;
import java.sql.PreparedStatement;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Savepoint;
import java.sql.Statement;
import java.sql.Struct;
import java.util.Map;
import java.util.Properties;

public class MainActivity extends AppCompatActivity {

    DBHelper dBhelper;
    SQLiteDatabase db;
    SQLiteDatabase database;
    ContentValues contentValues;
    Button btn;
    TextView tv_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dBhelper = new DBHelper(this);
        database = dBhelper.getWritableDatabase();

        btn = (Button) findViewById(R.id.Btn);
        tv_text = (TextView) findViewById(R.id.TV);

        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String text = "";
                try {
                    InputStream is = getAssets().open("product.txt");
                    int size = is.available();
                    byte[] buffer = new byte[size];
                    is.read(buffer);
                    is.close();
                    text = new String(buffer);



                    while((text = buffer.toString()) != null)
                    {
                        String[] tokens = text.split(";");
                        int KEY_ID = Integer.parseInt(tokens[0]);
                        String KEY_Title = tokens[1];
                        int KEY_ProductTypeId = Integer.parseInt(tokens[2]);
                        int KEY_ArticleNumber = Integer.parseInt(tokens[3]);
                        String KEY_Image = tokens[4];
                        int KEY_ProductionPersonCount = Integer.parseInt(tokens[5]);
                        int KEY_ProductionWorkshopNumber = Integer.parseInt(tokens[6]);
                        int KEY_MinCostForAgent = Integer.parseInt(tokens[7]);

                        String insertString = "insert into product(" + KEY_ID + ", " + KEY_Title + ", " +
                                KEY_ProductTypeId + ", " + KEY_ArticleNumber + ", " + KEY_Image +", " +
                                KEY_ProductionPersonCount +", " + KEY_ProductionWorkshopNumber +", " + KEY_MinCostForAgent +") values (?,?,?,?,?)";
                        Cursor cursor = database.query(DBHelper.TABLE_PRODUCT,null,null,null,null,null,null);

                    }
                }catch (IOException ex){
                    ex.printStackTrace();
                }
                tv_text.setText(text);
            }
        });


        UpdateTable();
    }

    public void UpdateTable() {

        Cursor cursor = database.query(DBHelper.TABLE_PRODUCT, null, null, null, null, null, null);
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
}