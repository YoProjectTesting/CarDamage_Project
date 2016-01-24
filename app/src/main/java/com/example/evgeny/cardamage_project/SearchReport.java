package com.example.evgeny.cardamage_project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

public class SearchReport extends AppCompatActivity {
    report_car_DB repCarDB;
    static List<report_car> searchResList;
    EditText cusNameToSearch,carTypeToSearch,dateToSearch,userToSearch;
    String cusNameToSearchStr, carTypeToSearchStr, dateToSearchStr, userToSearchStr;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent mainInt=new Intent(getApplicationContext(),MainScreen.class);
        startActivity(mainInt);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_report);

        repCarDB=new report_car_DB(getApplicationContext(),"repCarDB",null,1);

        EditText carNumToSearch=(EditText)findViewById(R.id.carNumToSearch);
        Button searchCarNumButton=(Button)findViewById(R.id.searchCarNumButton);

        cusNameToSearch=(EditText)findViewById(R.id.cusNameToSearch);
        carTypeToSearch=(EditText)findViewById(R.id.carTypeToSearch);
        dateToSearch=(EditText)findViewById(R.id.dateToSearch);
        userToSearch=(EditText)findViewById(R.id.userToSearch);

        Button   searchQueryButton=(Button)findViewById(R.id.searchQueryButton);


        searchCarNumButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchResList=repCarDB.searchByQuery(""+cusNameToSearch.getText().toString(),"","","","");
                Intent resInt=new Intent(getApplicationContext(),SearchResult.class);
                startActivity(resInt);
            }
        });
        searchQueryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cusNameToSearchStr=cusNameToSearch.getText().toString();
                carTypeToSearchStr=carTypeToSearch.getText().toString();
                dateToSearchStr=dateToSearch.getText().toString();
                userToSearchStr=userToSearch.getText().toString();
                searchResList=repCarDB.searchByQuery("",cusNameToSearchStr,carTypeToSearchStr, dateToSearchStr, userToSearchStr);
                Intent resInt=new Intent(getApplicationContext(),SearchResult.class);
                startActivity(resInt);
            }
        });

        getSupportActionBar().hide();
    }
}
