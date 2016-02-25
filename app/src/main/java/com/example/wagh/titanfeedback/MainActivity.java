package com.example.wagh.titanfeedback;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {



    Button b1;
    EditText et1;
    TextView tv3;
    String feedback;
    Integer a=0,b=0;
    RatingBar rb1;
    Float f1;

    SQLiteDatabase db=null;
    String tablename="Review";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b1=(Button)findViewById(R.id.b1);
        et1=(EditText)findViewById(R.id.et1);
        tv3=(TextView)findViewById(R.id.tv3);
        rb1=(RatingBar)findViewById(R.id.rb1);




        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (b>0) {
                    AlertDialog.Builder bb = new AlertDialog.Builder(MainActivity.this);
                    bb.setTitle("Confirmation").setMessage("Are You Sure?").setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            feedback = et1.getText().toString();
                            Toast.makeText(MainActivity.this, "Thank you for your time", Toast.LENGTH_SHORT).show();
                            database(feedback,f1);
                            dialog.dismiss();
                            a++;
                            tv3.setText(a.toString());
                            et1.setText("");
                            rb1.setRating(0.0f);
                            b=0;
                        }
                    }).show();
                }
                else {
                    Toast.makeText(MainActivity.this,"Give us some stars first",Toast.LENGTH_LONG).show();
                }


            }
        });

        rb1.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
             f1=rating;
               b++;
                if(f1==0.0)
                {
                    Toast.makeText(MainActivity.this,"Ohh!! Sorry!!",Toast.LENGTH_LONG).show();
                }

            }
        });
    }


    public void database(String feedback,Float f1)
    {
        try
        {
            db=this.openOrCreateDatabase("TitanFeeback",MODE_PRIVATE,null);

            db.execSQL("CREATE TABLE IF NOT EXISTS "+tablename+" (Feedback STRING, F1 FLOAT);");

            db.execSQL("INSERT INTO "
                    + tablename
                    + " (Feedback,f1)"
                    + " VALUES ('" + feedback + "',"+ f1 +");");
        }
        catch (Exception e)
        {
            Log.e("ERROR UPDATING","ERROR",e);
        }
    }
}
