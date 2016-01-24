package com.example.evgeny.cardamage_project;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Evgeny on 27/12/2015.
 */
public class report_car_DB extends SQLiteOpenHelper {
    String carNum, model, yearOfProduction, cusName, customerID, cusPhone,
            customerEmail, appraiserName, appraiserPhone, appraiserMail,
            insuranceCompany, insuranceName, insurancePhone, levelDamage,savingUser;
    String list_of_points_saved;
    Date savingDate;
    byte [] carDmgArray;
    user_data_DB us_db;


    public report_car_DB(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        us_db = new user_data_DB(context, "userDB", null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String queri = "create table if not exists car_reports(carNum text NOT NULL UNIQUE,model text,yearOfProduction text,cusName text,customerID text,cusPhone text,customerEmail text,appraiserName text,appraiserPhone text,appraiserMail text,insuranceCompany text,insuranceName text,insurancePhone text,levelDamage text, savingUser text, list_of_points_saved, savingDate date, carDmgArray BLOB, car_damage_Report BLOB,complementary_Report BLOB )";
        db.execSQL(queri);
}

    public void addReport(report_car cr) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("carNum", cr.getCarNum());
        values.put("model", cr.getModel());
        values.put("yearOfProduction", cr.getModel());
        values.put("cusName", cr.getCusName());
        values.put("customerID", cr.getCustomerID());
        values.put("cusPhone", cr.getCusPhone());
        values.put("customerEmail", cr.getCustomerEmail());
        values.put("appraiserName", cr.getAppraiserName());
        values.put("appraiserPhone", cr.getAppraiserPhone());
        values.put("appraiserMail", cr.getAppraiserMail());
        values.put("insuranceCompany", cr.getInsuranceCompany());
        values.put("insuranceName", cr.getInsuranceName());
        values.put("insurancePhone", cr.getInsurancePhone());
        values.put("levelDamage", cr.getLevelDamage());
        values.put("savingUser", cr.getSavingUser());
        values.put("list_of_points_saved", cr.getList_of_points_saved());

        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        values.put("savingDate", dateFormat.format(date));
        values.put("carDmgArray", cr.getCarDmgArray());

        if(cr.getCar_damage_Report()!=null)values.put("car_damage_Report", convert_bitmap_to_byte(cr.getCar_damage_Report()));
        if(cr.getComplementary_Report()!=null)values.put("complementary_Report", convert_bitmap_to_byte(cr.getComplementary_Report()));

        long result=db.insert("car_reports", null, values);
        db.close();
    }

    public void updateReport(report_car cr) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("carNum", cr.getCarNum());
        values.put("model", cr.getModel());
        values.put("yearOfProduction", cr.getModel());
        values.put("cusName", cr.getCusName());
        values.put("customerID", cr.getCustomerID());
        values.put("cusPhone", cr.getCusPhone());
        values.put("customerEmail", cr.getCustomerEmail());
        values.put("appraiserName", cr.getAppraiserName());
        values.put("appraiserPhone", cr.getAppraiserPhone());
        values.put("appraiserMail", cr.getAppraiserMail());
        values.put("insuranceCompany", cr.getInsuranceCompany());
        values.put("insuranceName", cr.getInsuranceName());
        values.put("insurancePhone", cr.getInsurancePhone());
        values.put("levelDamage", cr.getLevelDamage());
        values.put("savingUser", cr.getSavingUser());
        values.put("list_of_points_saved", cr.getList_of_points_saved());

        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        values.put("savingDate", dateFormat.format(date));
        values.put("carDmgArray", cr.getCarDmgArray());

        if(cr.getCar_damage_Report()!=null)values.put("car_damage_Report", convert_bitmap_to_byte(cr.getCar_damage_Report()));
        if(cr.getComplementary_Report()!=null)values.put("complementary_Report", convert_bitmap_to_byte(cr.getComplementary_Report()));

        long result=db.replace("car_reports", null, values);
//        long result=db.insert("car_reports", null, values);
        db.close();
    }



    public void delete_report_car_by_carNum(String carNum) {
       /* SQLiteDatabase db = getWritableDatabase();
        String query;
        query = "delete from employees where id = '"+id+"'";
        db.execSQL(query);*/
    }

    public List<report_car> searchAllCarReports() {
        List<report_car> resultList = new ArrayList<report_car>();

        String queri = "select * from car_reports";
        Cursor c1;
        SQLiteDatabase db = getWritableDatabase();

        c1 = db.rawQuery(queri, null);
        report_car carRep;
        if (c1.moveToFirst()) {
            do {
                carNum = c1.getString(0);
                model = c1.getString(1);
                yearOfProduction = c1.getString(2);
                cusName = c1.getString(3);
                customerID = c1.getString(4);
                cusPhone = c1.getString(5);
                customerEmail = c1.getString(6);
                appraiserName = c1.getString(7);
                appraiserPhone = c1.getString(8);
                appraiserMail = c1.getString(9);
                insuranceCompany = c1.getString(10);
                insuranceName = c1.getString(11);
                insurancePhone = c1.getString(12);
                levelDamage = c1.getString(13);
                savingUser = c1.getString(14);
                list_of_points_saved = c1.getString(15);


                savingDate = new Date(c1.getLong(16)*1000);
                carDmgArray=c1.getBlob(17);
                byte[] car_damage_Report = c1.getBlob(18);
                byte[] complementary_Report = c1.getBlob(19);
                Bitmap car_damage_Report_bitmap = (car_damage_Report != null) ? convert_byte_array_to_bitmap(car_damage_Report) : null;
                Bitmap complementary_Report_bitmap = (complementary_Report != null) ? convert_byte_array_to_bitmap(complementary_Report) : null;

                carRep = new report_car(carNum, model, yearOfProduction, cusName, customerID,
                        cusPhone, customerEmail, appraiserName, appraiserPhone, appraiserMail,
                        insuranceCompany, insuranceName, insurancePhone, levelDamage, savingUser,
                        list_of_points_saved,savingDate,carDmgArray,car_damage_Report_bitmap, complementary_Report_bitmap);
                resultList.add(carRep);
            } while (c1.moveToNext());
        }
        return resultList;
    }

    public List<report_car> searchByQuery(String carNumStr,String cusNameStr,String carTypeStr,String dateStr,String userStr){
        List<report_car> resultList = new ArrayList<report_car>();

        String[] params = {carNumStr, carTypeStr, cusNameStr, dateStr, userStr};
        boolean firstFlag = true;
        String queriPart = "";

        for (int i = 0; i < params.length; i++) {
            if (!params[i].equals("")) {
                if (firstFlag) {
                    queriPart += " where";
                    firstFlag = !firstFlag;
                } else {
                    queriPart += " and";
                }
                switch (i) {
                    case 0:
                        queriPart += " carNum ='" + params[i] + "'";
                        break;
                    case 1:
                        queriPart += " model ='" + params[i] + "'";
                        break;
                    case 2:
                        queriPart += " cusName ='" + params[i] + "'";
                        break;
                    case 3:
                        queriPart += " savingDate ='" + params[i] + "'";
                        break;
                    case 4:
                        queriPart += " savingUser ='" + params[i] + "'";
                        break;

                }
            }
        }
        String queri ="select * from car_reports "+queriPart;
        Log.i("MyTag",queri);
        Cursor c1;
        SQLiteDatabase db = getWritableDatabase();

        c1 = db.rawQuery(queri, null);
        report_car carRep;
        if (c1.moveToFirst()) {
            do {
                carNum = c1.getString(0);
                model = c1.getString(1);
                yearOfProduction = c1.getString(2);
                cusName = c1.getString(3);
                customerID = c1.getString(4);
                cusPhone = c1.getString(5);
                customerEmail = c1.getString(6);
                appraiserName = c1.getString(7);
                appraiserPhone = c1.getString(8);
                appraiserMail = c1.getString(9);
                insuranceCompany = c1.getString(10);
                insuranceName = c1.getString(11);
                insurancePhone = c1.getString(12);
                levelDamage = c1.getString(13);
                savingUser = c1.getString(14);
                list_of_points_saved = c1.getString(15);


                savingDate = new Date(c1.getLong(16)*1000);
                carDmgArray=c1.getBlob(17);
                byte[] car_damage_Report = c1.getBlob(18);
                byte[] complementary_Report = c1.getBlob(19);
                Bitmap car_damage_Report_bitmap = (car_damage_Report != null) ? convert_byte_array_to_bitmap(car_damage_Report) : null;
                Bitmap complementary_Report_bitmap = (complementary_Report != null) ? convert_byte_array_to_bitmap(complementary_Report) : null;

                carRep = new report_car(carNum, model, yearOfProduction, cusName, customerID,
                        cusPhone, customerEmail, appraiserName, appraiserPhone, appraiserMail,
                        insuranceCompany, insuranceName, insurancePhone, levelDamage, savingUser,
                        list_of_points_saved,savingDate,carDmgArray,car_damage_Report_bitmap, complementary_Report_bitmap);
                resultList.add(carRep);
            } while (c1.moveToNext());
        }

        return resultList;
    }

    public static byte[] convert_bitmap_to_byte(Bitmap btmp1) {
        byte[] array = null;
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        btmp1.compress(Bitmap.CompressFormat.PNG, 0, stream);
        array = stream.toByteArray();

        return array;
    }

    public static Bitmap convert_byte_array_to_bitmap(byte[] array) {
        Bitmap res;
        res = BitmapFactory.decodeByteArray(array, 0, array.length);
        return res;
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
