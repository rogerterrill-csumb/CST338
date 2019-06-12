package com.example.week7;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText etID;
    Button btnSubmit;
    Button btnList;
    TextView tvResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etID = findViewById(R.id.etID);
        btnSubmit = findViewById(R.id.btnSubmit);
        btnList = findViewById(R.id.btnList);
        tvResults = findViewById(R.id.tvResults);
        tvResults.setText(getString(R.string.listOfClasses));


        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = getApplicationContext();
                String classString = etID.getText().toString().trim();

                if(classString == null || classString.trim().equals("")){
                    Toast.makeText(context, "Sorry you did't type anything", Toast.LENGTH_SHORT).show();
                    return;
                }
                int classID = Integer.parseInt(classString);

                String text;

                switch(classID)
                {
                    case 300:
                        text = getString(R.string.cst300);
                        break;
                    case 338:
                        text = getString(R.string.cst338);
                        break;
                    case 311:
                        text = getString(R.string.cst311);
                        break;
                    case 334:
                        text = getString(R.string.cst334);
                        break;
                    case 336:
                        text = getString(R.string.cst336);
                        break;
                    case 363:
                        text = getString(R.string.cst363);
                        break;
                    case 370:
                        text = getString(R.string.cst370);
                        break;
                    case 383:
                        text = getString(R.string.cst383);
                        break;
                    case 438:
                        text = getString(R.string.cst438);
                        break;
                    case 462:
                        text = getString(R.string.cst462);
                        break;
                    case 499:
                        text = getString(R.string.cst499);
                        break;
                    default:
                        text = "Error: you didn't choose a valid class id";
                        break;

                }
                etID.setText("");

                tvResults.setText(text);

            }
        });
        btnList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvResults.setText(getString(R.string.listOfClasses));
            }
        });
    }

}
