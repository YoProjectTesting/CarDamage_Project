package com.example.evgeny.cardamage_project;

import android.util.Base64;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Evgeny on 23/12/2015.
 */
public class Junk {




/*

    public static String convert_list_of_points_to_JsonString() {
        String JsonString = "";
//        JSONObject pointsListObject = new JSONObject();
        JSONArray pointsListArr = new JSONArray();

        try {
            for (int i = 0; i < car_view.list_of_points.size(); i++) {
                JSONObject point = new JSONObject();
                point.put("x", car_view.list_of_points.get(i).getX());
                point.put("y", car_view.list_of_points.get(i).getY());
                point.put("damageType", car_view.list_of_points.get(i).getDamageType());
                if( car_view.list_of_points.get(i).getImage()!=null){
                    byte[] originalBytes= car_view.list_of_points.get(i).getImage();
                    String base64String = Base64.encodeToString(originalBytes, 0);
                    point.put("image",);
                } else point.put("image",0);
                pointsListArr.put(point);
            }
//            pointsListObject.put("pointsListArr",pointsListArr);
            JsonString = pointsListArr.toString();
            Log.i("MyTag", "JsoString  " + JsonString);

        } catch (JSONException e) {
            Log.i("MyTag", e.getMessage());
        }
        return JsonString;
    }

    public static List<damaged_point> convert_from_JsonString_2_list_of_points(String JsonString) {
        List<damaged_point> list_of_points = new ArrayList<damaged_point>();
        float x, y;
        String damageType;
        byte[] image;
        JSONObject point;
//        JSONObject pointsListObject = new JSONObject();
        try {
            JSONArray pointsListArr = new JSONArray(JsonString);
            for (int i = 0; i < pointsListArr.length(); i++) {
                point = (JSONObject) pointsListArr.get(i);
                x = (float) point.getDouble("x");
                y = (float) point.getDouble("y");
                damageType = point.getString("damageType");
                image = (byte[])point.get("image");

                damaged_point dp = new damaged_point(x, y, damageType,image);
                list_of_points.add(dp);
            }
        } catch (JSONException e) {
            Log.i("MyTag", e.getMessage());
        }
        return list_of_points;
    }*/



















      /* this.setLongClickable(true);
        this.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                *//*damaged_point mp1 = new damaged_point(cx, cy, points[0]);
                list_of_points.add(mp1);*//*
                isLongPress=true;
                dialogView((Activity) getContext());
                Log.i("MyTag","LongPress");
                return false;
            }
        });

         isLongPress= (System.currentTimeMillis()-time)>1000?true:false;

        if(!isLongPress){
            cx = event.getX();
            cy = event.getY();
            int part=dpc.checkPart(cx,cy);
            if (part!=99)
                carDmgArray[part]=1-carDmgArray[part];
        }else {
            isLongPress=true;
            dialogView((Activity) getContext());
            Log.i("MyTag","LongPress");
        }*/

/*
    <string name="carNumber">Car Number</string>
    <string name="CarType_Year">CarType_Year</string>
    <string name="CustomerName">Customer Name</string>
    <string name="CustomerPhone">Customer Phone</string>
    <string name="CustomerEmail">Customer Email</string>
    <string name="AppraiserName">Appraiser Name</string>
    <string name="AppraiserPhone">Appraiser Phone</string>
    <string name="AppraiserEMail">Appraise EMail</string>
    <string name="InsuranceCompany">Insurance Company</string>
    <string name="InsuranceAgent">Insurance Agent</string>
    <string name="InsuranceAgentPhone">Insurance Agent Phone</string>
    <string name="DifficultyLevel">Difficulty Level</string>
*/




    /*spinnerList.add(R.string.CheckView);
    spinnerList.add(R.string.CarView);
    spinnerList.add(R.string.CustomerDataView);


    spinnerList.add(getBaseContext().getString(R.string.CheckView));
    spinnerList.add(getBaseContext().getString(R.string.CarView));
    spinnerList.add(getBaseContext().getString(R.string.CustomerDataView));

    spinnerList.add("Check view:");
    spinnerList.add("Car View");
    spinnerList.add("Customer Data View");

    */




}
