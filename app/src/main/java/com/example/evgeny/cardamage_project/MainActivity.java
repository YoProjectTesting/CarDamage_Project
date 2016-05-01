package com.example.evgeny.cardamage_project;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static LinearLayout mainLayout;
    File file,dir;

    Bundle savedInstanceState;
    int width, height;
    int flagSpinner=0;
    LinearLayout topLayout,main_screen,customer_data_form_ll;
    ScrollView customer_data_form;
    LinearLayout repair_car;
    Spinner appSpinner;
    List<String> spinnerList = new ArrayList<String>();

    public static int flag = 1;
    car_view car_check_damage;
    doh_mashlim complementary_report;
    static Bitmap viewToPic_CAR_VIEW;
    static Bitmap viewToPic_DOH_MASHLIM;
    int imgCounter;

    report_car_DB repCarDB;
    user_data_DB us_db;
    AppraiserList AppraisersList;

    String carNum,model_year,cusName,customerID,cusPhone,CustomerEmail,appraiserName,appraiserPhone,appraiserMail,insuranceCompany,insuranceName,insurancePhone,levelDamage;
    EditText carNumberField,CarType_YearField,CustomerNameField,CustomerIDField, CustomerPhoneField, CustomerEmailField, AppraiserNameField, AppraiserPhoneField, AppraiserEMailField, InsuranceCompanyField, InsuranceAgentField, InsuranceAgentPhoneField, DifficultyLevelField;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Bundle extra = data.getExtras();
        Bitmap newPhoto = (Bitmap)extra.get("data");
        byte []newPh = report_car_DB.convert_bitmap_to_byte(newPhoto);
        damaged_point mp1 = new damaged_point(car_view.cx, car_view.cy, car_view.damageType,newPh);
        car_view.list_of_points.add(mp1);



//        saveImgToFile(newPhoto, carNumberField.getText().toString(), imgCounter++);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent mainInt=new Intent(getApplicationContext(),MainScreen.class);
        startActivity(mainInt);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.savedInstanceState = savedInstanceState;
        Display ds1 = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        ds1.getSize(size);
        this.width = size.x;
        this.height = size.y;

        repCarDB=new report_car_DB(getApplicationContext(),"repCarDB",null,1);
        us_db = new user_data_DB(getApplicationContext(), "userDB", null, 1);
        AppraisersList =new AppraiserList();

        //        Get linearLayout from another xml
        LayoutInflater inflater;
        inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        customer_data_form = (ScrollView) inflater.inflate(R.layout.activity_customer_data_form, null);
        repair_car = (LinearLayout) inflater.inflate(R.layout.activity_repair_car, null);

        carNumberField = (EditText) customer_data_form.findViewById(R.id.carNumberField);
        CarType_YearField = (EditText) customer_data_form.findViewById(R.id.CarType_YearField);
        CustomerNameField = (EditText) customer_data_form.findViewById(R.id.CustomerNameField);
        CustomerIDField = (EditText) customer_data_form.findViewById(R.id.CustomerIDField);
        CustomerPhoneField = (EditText) customer_data_form.findViewById(R.id.CustomerPhoneField);
        CustomerEmailField = (EditText) customer_data_form.findViewById(R.id.CustomerEmailField);
        AppraiserNameField = (EditText) customer_data_form.findViewById(R.id.AppraiserNameField);
        Button AppraiserNameButton = (Button) customer_data_form.findViewById(R.id.AppraiserName);
        AppraiserPhoneField = (EditText) customer_data_form.findViewById(R.id.AppraiserPhoneField);
        AppraiserEMailField = (EditText) customer_data_form.findViewById(R.id.AppraiserEMailField);
        InsuranceCompanyField = (EditText) customer_data_form.findViewById(R.id.InsuranceCompanyField);
        InsuranceAgentField = (EditText) customer_data_form.findViewById(R.id.InsuranceAgentField);
        InsuranceAgentPhoneField = (EditText) customer_data_form.findViewById(R.id.InsuranceAgentPhoneField);
        DifficultyLevelField = (EditText) customer_data_form.findViewById(R.id.DifficultyLevelField);

        mainLayout = new LinearLayout(this);
        mainLayout.setOrientation(LinearLayout.VERTICAL);

        LinearLayout.LayoutParams lp1 = new LinearLayout.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT);
        mainLayout.setLayoutParams(lp1);

        topLayout = new LinearLayout(this);
        LinearLayout.LayoutParams lp2 = new LinearLayout.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT);

        topLayout.setLayoutParams(lp2);
        topLayout.setOrientation(LinearLayout.HORIZONTAL);


        appSpinner = new Spinner(this);
        appSpinner.setLayoutParams(new LinearLayout.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT));
        spinnerList.add(getBaseContext().getString(R.string.CustomerDataView));
        spinnerList.add(getBaseContext().getString(R.string.CarView));
        spinnerList.add(getBaseContext().getString(R.string.RepairView));

        ArrayAdapter<String> spinAdapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.spinner_item, spinnerList);
        appSpinner.setAdapter(spinAdapter);
        LinearLayout.LayoutParams lp3 = new LinearLayout.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT);
        appSpinner.setLayoutParams(lp3);

        final Button save=new Button(this);
        save.setText(R.string.Save);
        save.setTextSize(13);
        save.setTextColor(Color.WHITE);
        lp3.setMargins(30, 10, 0, 0);
        save.setBackgroundResource(R.drawable.savebuton);
        save.setLayoutParams(lp3);

        final Button next=new Button(this);
        next.setText(R.string.Next);
        next.setTextSize(13);
        next.setTextColor(Color.WHITE);
        next.setBackgroundResource(R.drawable.savebuton);
        next.setLayoutParams(lp3);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (appSpinner.getSelectedItemPosition() < 2)
                    appSpinner.setSelection(appSpinner.getSelectedItemPosition() + 1);
                else appSpinner.setSelection(0);
            }
        });



        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater inflater;
                inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                Bitmap cdfBitmap = getBitmapFromView(customer_data_form);
                Bitmap cchdBitmap = getBitmapFromView(car_check_damage);
                viewToPic_CAR_VIEW = combineImagesVertically(cdfBitmap, cchdBitmap);

                carNum = carNumberField.getText().toString();
                model_year = CarType_YearField.getText().toString();
                cusName = CustomerNameField.getText().toString();
                customerID = CustomerIDField.getText().toString();
                cusPhone = CustomerPhoneField.getText().toString();
                CustomerEmail = CustomerEmailField.getText().toString();
                appraiserName = AppraiserNameField.getText().toString();
                appraiserPhone = AppraiserPhoneField.getText().toString();
                appraiserMail = AppraiserEMailField.getText().toString();
                insuranceCompany = InsuranceCompanyField.getText().toString();
                insuranceName = InsuranceAgentField.getText().toString();
                insurancePhone = InsuranceAgentPhoneField.getText().toString();
                levelDamage = DifficultyLevelField.getText().toString();
                String savingUser=us_db.getUser();
//                String list_of_points_saved=car_view.convert_list_of_points_to_JsonString();

                Gson gson = new Gson();
                list_of_points_saved_Class lps=new list_of_points_saved_Class();
                String list_of_points_saved= gson.toJson(lps);
                Log.i("MyTag",list_of_points_saved);
                //Date added at add_report function
                if(!carNum.equals("")) {
                    report_car rp = new report_car(carNum, model_year, model_year, cusName,
                            customerID, CustomerEmail, cusPhone, appraiserName,
                            appraiserPhone, appraiserMail, insuranceCompany, insuranceName,
                            insurancePhone, levelDamage, savingUser,
                            list_of_points_saved, null, car_view.carDmgArray, viewToPic_CAR_VIEW, null);

                    if (repCarDB.searchByQuery(carNum, "", "", "", "").size() == 0) {

                        repCarDB.addReport(rp);
                    } else {
                        repCarDB.updateReport(rp);

                    }

                    Toast.makeText(getApplicationContext(), "Short click - report added to DB", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getApplicationContext(), "Please fill car number", Toast.LENGTH_LONG).show();

                }

            }
        });
        save.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                LayoutInflater inflater;
                inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                Bitmap cdfBitmap = getBitmapFromView(customer_data_form);
                Bitmap cchdBitmap = getBitmapFromView(car_check_damage);
                viewToPic_CAR_VIEW = combineImagesVertically(cdfBitmap, cchdBitmap);

                carNum = carNumberField.getText().toString();
                model_year = CarType_YearField.getText().toString();
                cusName = CustomerNameField.getText().toString();
                customerID = CustomerIDField.getText().toString();
                cusPhone = CustomerPhoneField.getText().toString();
                CustomerEmail = CustomerEmailField.getText().toString();
                appraiserName = AppraiserNameField.getText().toString();
                appraiserPhone = AppraiserPhoneField.getText().toString();
                appraiserMail = AppraiserEMailField.getText().toString();
                insuranceCompany = InsuranceCompanyField.getText().toString();
                insuranceName = InsuranceAgentField.getText().toString();
                insurancePhone = InsuranceAgentPhoneField.getText().toString();
                levelDamage = DifficultyLevelField.getText().toString();
                String savingUser=us_db.getUser();

                Gson gson = new Gson();
                list_of_points_saved_Class lps=new list_of_points_saved_Class();
                String list_of_points_saved= gson.toJson(lps);
                Log.i("MyTag",list_of_points_saved);

                if(!carNum.equals("")) {
                    //Date added at add_report function
                    report_car rp = new report_car(carNum, model_year, model_year, cusName,
                            customerID, CustomerEmail, cusPhone, appraiserName,
                            appraiserPhone, appraiserMail, insuranceCompany, insuranceName,
                            insurancePhone, levelDamage, savingUser,
                            list_of_points_saved, null, car_view.carDmgArray, viewToPic_CAR_VIEW, null);
                    if (repCarDB.searchByQuery(carNum, "", "", "", "").size() == 0) {

                        repCarDB.addReport(rp);
                    } else {
                        repCarDB.updateReport(rp);

                    }

                    Toast.makeText(getApplicationContext(), "Long click", Toast.LENGTH_LONG).show();
                    saveImgToFile(viewToPic_CAR_VIEW, carNumberField.getText().toString(), 0);
                    Bitmap tempPhoto;
                    imgCounter=1;
//                    if (car_view.list_of_points.size()>0)
                    for (int i = 0; i < car_view.list_of_points.size(); i++) {
                        if(car_view.list_of_points.get(i).getImage()!=null){
                            tempPhoto=report_car_DB.convert_byte_array_to_bitmap(car_view.list_of_points.get(i).getImage());
                            saveImgToFile(tempPhoto, carNumberField.getText().toString(), imgCounter++);
                        }
                    }


                    int filesCount = new File(dir.toString()).list().length;
                    Log.i("MyTag", " filesCount: " + filesCount);
                   shareMultiplyFiles(filesCount, carNumberField.getText().toString());
              /* Intent intPic = new Intent(getApplicationContext(), imagePresents.class);
                startActivity(intPic);*/
                }else{
                    Toast.makeText(getApplicationContext(), "Please fill car number", Toast.LENGTH_LONG).show();
                }
                return false;
            }
        });

        car_check_damage = new car_view(this, width, height);
        complementary_report = new doh_mashlim(this, width, height);

        topLayout.addView(appSpinner);
        topLayout.addView(next);

        mainLayout.addView(topLayout);

        appSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                TextView item = ((TextView) parent.getChildAt(0));
                item.setTextColor(Color.BLACK);
                item.setTextSize(15);
                switch (item.getText().toString()) {
                    case "Вид машины":
                    case "סימון נזק ברכב":
                    case "Car View":
                        if (flagSpinner == 2) {
                            mainLayout.removeView(customer_data_form);
                            topLayout.addView(save);
                        }
                        if (flagSpinner == 3) mainLayout.removeView(complementary_report);

                        mainLayout.addView(car_check_damage);
                        flagSpinner = 1;
                        break;
                    case "Данные клиента":
                    case "פרטי לקוח":
                    case "Customer Data View":
                        if (flagSpinner == 1) mainLayout.removeView(car_check_damage);
                        if (flagSpinner == 3) mainLayout.removeView(complementary_report);
                        if (flagSpinner == 1||flagSpinner == 3) topLayout.removeView(save);
                        mainLayout.addView(customer_data_form);
                        flagSpinner = 2;
                        break;
                    case "Доп.доклад":
                    case "דו-ח משלים":
                    case "Compl.Report":
                        if (flagSpinner == 1) mainLayout.removeView(car_check_damage);
                        if (flagSpinner == 2) {
                            mainLayout.removeView(customer_data_form);
                            topLayout.addView(save);
                        }
                        mainLayout.addView(complementary_report);
                        flagSpinner = 3;
                        break;
                }
                setContentView(mainLayout);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        setContentView(mainLayout);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            carNumberField.setText(extras.getString("carNum"));
            CarType_YearField.setText(extras.getString("model"));
            CustomerNameField.setText(extras.getString("cusName"));
            CustomerIDField.setText(extras.getString("customerID"));
            CustomerPhoneField.setText(extras.getString("cusPhone"));
            CustomerEmailField.setText(extras.getString("customerEmail"));
            AppraiserNameField.setText(extras.getString("appraiserName"));
            AppraiserPhoneField.setText(extras.getString("appraiserPhone"));
            AppraiserEMailField.setText(extras.getString("appraiserMail"));
            InsuranceCompanyField.setText(extras.getString("insuranceCompany"));
            InsuranceAgentField.setText(extras.getString("insuranceName"));
            InsuranceAgentPhoneField.setText(extras.getString("insurancePhone"));
            DifficultyLevelField.setText(extras.getString("levelDamage"));
            Gson gson = new Gson();

            car_view.list_of_points=(gson.fromJson(extras.getString("list_of_points_saved"),list_of_points_saved_Class.class)).list_of_points_save;

//            car_view.list_of_points=car_view.convert_from_JsonString_2_list_of_points(extras.getString("list_of_points_saved"));
            car_view.carDmgArray=extras.getByteArray("carDmgArray");

        }else car_view.list_of_points= new ArrayList<damaged_point>();

        getSupportActionBar().hide();

    }

    public static Bitmap getBitmapFromView(View view) {
//        int totalHeight = customer_data_form.getChildAt(0).getHeight();

        Bitmap returnedBitmap = null;
        returnedBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(returnedBitmap);
        Drawable bgDrawable =view.getBackground();
        if (bgDrawable!=null) {
            bgDrawable.draw(canvas);
        } else {
            canvas.drawColor(Color.WHITE);
        }
        view.draw(canvas);
        return returnedBitmap;
    }

    public Bitmap combineImagesVertically(Bitmap c, Bitmap s) {
        Bitmap cs = null;
        int width, height = 0;
        if(c.getWidth() > s.getWidth()) {
            width = c.getWidth();
            height = c.getHeight() + s.getHeight();
        } else {
            width = s.getWidth();
            height = c.getHeight() + s.getHeight();
        }
        cs = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas comboImage = new Canvas(cs);
        comboImage.drawBitmap(c, 0f, 0f, null);
        comboImage.drawBitmap(s, 0f, c.getHeight(), null);
        return cs;
    }

    public Bitmap combineImagesHorizontally (Bitmap c, Bitmap s) {
        Bitmap cs = null;

        int width, height = 0;

        if(c.getWidth() > s.getWidth()) {
            width = c.getWidth() + s.getWidth();
            height = c.getHeight();
        } else {
            width = s.getWidth() + s.getWidth();
            height = c.getHeight();
        }

        cs = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);

        Canvas comboImage = new Canvas(cs);

        comboImage.drawBitmap(c, 0f, 0f, null);
        comboImage.drawBitmap(s, c.getWidth(), 0f, null);

        return cs;
    }

    public  void saveImgToFile(Bitmap img, String carNum, int imgCounter){
        dir = new File(this.getExternalFilesDir(
                Environment.DIRECTORY_PICTURES), "CarDamage_Project/"+carNum);
        if (!dir.mkdirs()) {
            Log.i("MyTag", "Directory not created");
        }
        file = new File(dir, carNum+"_SavedReportImg_"+imgCounter+".png");
        FileOutputStream fOut;
        try {
            fOut = new FileOutputStream(file);

            img.compress(Bitmap.CompressFormat.PNG, 85, fOut);
            fOut.flush();
            fOut.close();
            Toast.makeText(getApplicationContext(),"Saved",Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Log.i("MyTag", " e.getMessage():   " + e.getMessage());
        }
    }

    public  void shareMultiplyFiles(int filesCount,String carNum){
        String[] TO={"allumaDemo@gmail.com"};
//        String[] TO={"evgeny.strilchik@gmail.com"};
//    String[]CC={evgeny.strilchik@gmail.com};

        Intent emailIntent=new Intent(Intent.ACTION_SEND_MULTIPLE);
        emailIntent.setType("text/plain");

        ArrayList<Uri> uris = new ArrayList<Uri>();
        File [] files=new File[filesCount+1];
        for (int i = 0; i <filesCount ; i++) {
            Log.i("MyTag", "Directory created:  "+dir);
            files[i] = new File(dir, carNum+"_SavedReportImg_"+i+".png");
            Log.i("MyTag", "files[i]:  "+files[i]);
            uris.add(Uri.fromFile(files[i]));
        }

        emailIntent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, uris);

        String msg,subject;
        msg="Message to Alluma car#: _"+carNumberField.getText().toString();;
        subject="Message to Alluma car#: _"+carNumberField.getText().toString();
        emailIntent.putExtra(Intent.EXTRA_EMAIL,TO);
//                    emailIntent.putExtra(Intent.EXTRA_CC,CC);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
//        emailIntent.putExtra(Intent.EXTRA_TEXT, msg);
        try {
            startActivityForResult(Intent.createChooser(emailIntent, "Sending multiple attachment"), 12345);
            finish();
        }
        catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(getApplicationContext(), "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }
    }

    public void fillFields(View v1){
        String AppraiserNameStr=AppraiserNameField.getText().toString();
        AppraiserList.Appraiser foundAppr=AppraisersList.searchAppraiser(AppraiserNameStr);
        if(foundAppr!=null) {
            AppraiserPhoneField.setText(foundAppr.getPhone());
            AppraiserEMailField.setText(foundAppr.getEmail());
        }
    }
}
