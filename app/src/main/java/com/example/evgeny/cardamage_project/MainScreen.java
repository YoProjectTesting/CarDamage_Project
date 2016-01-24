package com.example.evgeny.cardamage_project;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainScreen extends AppCompatActivity {
    public AlertDialog alert;
    Activity thisActivity;
    user_data_DB us_db;
    report_car_DB repCarDB;
    TextView helloUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
        thisActivity = this;
        us_db = new user_data_DB(getApplicationContext(), "userDB", null, 1);

        helloUser = (TextView) findViewById(R.id.helloUser);
        RelativeLayout RLMain = (RelativeLayout) findViewById(R.id.RLMain);
        Button changeUserButton = (Button) findViewById(R.id.changeUserButton);
        Button NewReportButton = (Button) findViewById(R.id.NewReportButton);
        Button searchReport = (Button) findViewById(R.id.searchReport);

        NewReportButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent mainInt = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(mainInt);
            }
        });

        changeUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                us_db.deleteUser();
                Authorization_dialogView(thisActivity);
            }
        });

        searchReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent searchInt=new Intent(getApplicationContext(),SearchReport.class);
                startActivity(searchInt);
            }
        });

        Button []B = new Button[5];
        TableRow []TR=new TableRow[5];
        TR[0] = (TableRow) findViewById(R.id.TR1);
        TR[1] = (TableRow) findViewById(R.id.TR2);
        TR[2] = (TableRow) findViewById(R.id.TR3);
        TR[3] = (TableRow) findViewById(R.id.TR4);
        TR[4] = (TableRow) findViewById(R.id.TR5);

        repCarDB=new report_car_DB(getApplicationContext(),"repCarDB",null,1);
        final List<report_car> searchResultList=repCarDB.searchAllCarReports();

        int min5=(searchResultList.size()<5)?searchResultList.size():5;
        for (int i = searchResultList.size()-1,trIndex=0; i > searchResultList.size()-1-min5; i--,trIndex++) {
            B[i] = new Button(this);
            B[i].setId(i);
            B[i].setText(""+searchResultList.get(i).getCarNum()+" - "+searchResultList.get(i).getCusName());
            B[i].setBackgroundResource(R.drawable.transparent);
            B[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent choosedCar=new Intent(getApplicationContext(),MainActivity.class);
                    choosedCar.putExtra("carNum",searchResultList.get(v.getId()).getCarNum());
                    choosedCar.putExtra("model",searchResultList.get(v.getId()).getModel());
                    choosedCar.putExtra("cusName",searchResultList.get(v.getId()).getCusName());
                    choosedCar.putExtra("customerID",searchResultList.get(v.getId()).getCustomerID());
                    choosedCar.putExtra("cusPhone",searchResultList.get(v.getId()).getCusPhone());
                    choosedCar.putExtra("customerEmail",searchResultList.get(v.getId()).getCustomerEmail());
                    choosedCar.putExtra("appraiserName",searchResultList.get(v.getId()).getAppraiserName());
                    choosedCar.putExtra("appraiserPhone",searchResultList.get(v.getId()).getAppraiserPhone());
                    choosedCar.putExtra("appraiserMail",searchResultList.get(v.getId()).getAppraiserMail());
                    choosedCar.putExtra("insuranceCompany",searchResultList.get(v.getId()).getInsuranceCompany());
                    choosedCar.putExtra("insuranceName",searchResultList.get(v.getId()).getInsuranceName());
                    choosedCar.putExtra("insurancePhone",searchResultList.get(v.getId()).getInsurancePhone());
                    choosedCar.putExtra("levelDamage",searchResultList.get(v.getId()).getLevelDamage());
                    choosedCar.putExtra("list_of_points_saved",searchResultList.get(v.getId()).getList_of_points_saved());
                    choosedCar.putExtra("carDmgArray",searchResultList.get(v.getId()).getCarDmgArray());
                    startActivity(choosedCar);
                }
            });

            TR[trIndex].addView(B[i]);
        }

        boolean authorized = us_db.checkIfAuthorized();
        if (!authorized) Authorization_dialogView(thisActivity);
        else {
            helloUser.setText("Well come, " + us_db.getUser() + " !");
        }

        getSupportActionBar().hide();
    }

    public void Authorization_dialogView(Activity a) {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(a);

        LayoutInflater inflater = a.getLayoutInflater();
        View mV = inflater.inflate(R.layout.authorization_dialog, null);

        final TextView userName = (TextView) mV.findViewById(R.id.userName);
        final Button LogIn = (Button) mV.findViewById(R.id.LogIn);
        LogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userNameText = userName.getText().toString();
                if (!userNameText.equals("")) {
                    us_db.authorizateUser(userNameText);
                    helloUser.setText("Well come, " + us_db.getUser() + " !");
                    alert.dismiss();
                } else
                    Toast.makeText(getApplicationContext(), "Enter your User Name please", Toast.LENGTH_LONG).show();
            }
        });

//        builder1.setTitle("Enter User Name");

        builder1.setView(mV);
        alert = builder1.create();
//        alert.setCancelable(true);
        alert.show();

    }
}
