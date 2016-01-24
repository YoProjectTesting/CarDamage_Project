package com.example.evgeny.cardamage_project;

import android.graphics.Canvas;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class customerDataForm extends AppCompatActivity {

    public static String carNum,model,yearOfProduction,cusName,customerID,cusPhone,appraiserName,appraiserPhone,appraiserMail,insuranceCompany,insuranceName,insurancePhone,levelDamage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_data_form);


        EditText carNumberField=(EditText)findViewById(R.id.carNumberField);
        EditText CarType_YearField=(EditText)findViewById(R.id.CarType_YearField);
        EditText CustomerNameField=(EditText)findViewById(R.id.CustomerNameField);
        EditText CustomerIDField=(EditText)findViewById(R.id.CustomerIDField);
        EditText CustomerPhoneField=(EditText)findViewById(R.id.CustomerPhoneField);
        EditText CustomerEmailField=(EditText)findViewById(R.id.CustomerEmailField);
        EditText AppraiserNameField=(EditText)findViewById(R.id.AppraiserNameField);
        EditText AppraiserPhoneField=(EditText)findViewById(R.id.AppraiserPhoneField);
        EditText AppraiserEMailField=(EditText)findViewById(R.id.AppraiserEMailField);
        EditText InsuranceCompanyField=(EditText)findViewById(R.id.InsuranceCompanyField);
        EditText InsuranceAgentField=(EditText)findViewById(R.id.InsuranceAgentField);
        EditText InsuranceAgentPhoneField=(EditText)findViewById(R.id.InsuranceAgentPhoneField);
        EditText DifficultyLevelField=(EditText)findViewById(R.id.DifficultyLevelField);

        carNum = carNumberField.toString();
        model = CarType_YearField.toString();
        yearOfProduction =  CustomerNameField.toString();
        cusName = CustomerIDField.toString();
        customerID =CustomerPhoneField.toString();
        cusPhone = CustomerEmailField.toString();
        appraiserName = AppraiserNameField.toString();
        appraiserPhone = AppraiserPhoneField.toString();
        appraiserMail = AppraiserEMailField.toString();
        insuranceCompany = InsuranceCompanyField.toString();
        insuranceName = InsuranceAgentField.toString();
        insurancePhone = InsuranceAgentPhoneField.toString() ;
        levelDamage = DifficultyLevelField.toString();


        getSupportActionBar().hide();

    }
}
