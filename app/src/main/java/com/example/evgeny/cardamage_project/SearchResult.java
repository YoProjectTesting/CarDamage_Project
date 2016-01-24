package com.example.evgeny.cardamage_project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableRow;

public class SearchResult extends AppCompatActivity {
    TableRow []trArr;
    Button []btnArr;
    int i;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent searchInt=new Intent(getApplicationContext(),SearchReport.class);
        startActivity(searchInt);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);
        LinearLayout LLResult=(LinearLayout)findViewById(R.id.LLResult);
        trArr=new TableRow[30];
        btnArr=new Button[30];


        for ( i = 0; i < SearchReport.searchResList.size(); i++) {
            trArr[i]=new TableRow(this);
            btnArr[i]=new Button(this);
            btnArr[i].setId(i);
            btnArr[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent choosedCar=new Intent(getApplicationContext(),MainActivity.class);
                    choosedCar.putExtra("carNum",SearchReport.searchResList.get(v.getId()).getCarNum());
                    choosedCar.putExtra("model",SearchReport.searchResList.get(v.getId()).getModel());
                    choosedCar.putExtra("cusName",SearchReport.searchResList.get(v.getId()).getCusName());
                    choosedCar.putExtra("customerID",SearchReport.searchResList.get(v.getId()).getCustomerID());
                    choosedCar.putExtra("cusPhone",SearchReport.searchResList.get(v.getId()).getCusPhone());
                    choosedCar.putExtra("customerEmail",SearchReport.searchResList.get(v.getId()).getCustomerEmail());
                    choosedCar.putExtra("appraiserName",SearchReport.searchResList.get(v.getId()).getAppraiserName());
                    choosedCar.putExtra("appraiserPhone",SearchReport.searchResList.get(v.getId()).getAppraiserPhone());
                    choosedCar.putExtra("appraiserMail",SearchReport.searchResList.get(v.getId()).getAppraiserMail());
                    choosedCar.putExtra("insuranceCompany",SearchReport.searchResList.get(v.getId()).getInsuranceCompany());
                    choosedCar.putExtra("insuranceName",SearchReport.searchResList.get(v.getId()).getInsuranceName());
                    choosedCar.putExtra("insurancePhone",SearchReport.searchResList.get(v.getId()).getInsurancePhone());
                    choosedCar.putExtra("levelDamage",SearchReport.searchResList.get(v.getId()).getLevelDamage());
                    choosedCar.putExtra("list_of_points_saved", SearchReport.searchResList.get(v.getId()).getList_of_points_saved());
                    choosedCar.putExtra("carDmgArray", SearchReport.searchResList.get(v.getId()).getCarDmgArray());
                    startActivity(choosedCar);
                }
            });
            btnArr[i].setText(SearchReport.searchResList.get(i).getCarNum()+" - "+SearchReport.searchResList.get(i).getCusName());
            trArr[i].addView(btnArr[i]);
            LLResult.addView(trArr[i]);
        }

        getSupportActionBar().hide();

    }
}
