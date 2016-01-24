package com.example.evgeny.cardamage_project;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hackeru on 02/12/2015.
 */
public class doh_mashlim extends View {
    Paint gameSheet2, gameSheet;
    Paint[] points = new Paint[3];
    int width, height, part;
    float cx, cy;
    double x1, x2, x3, x4, x5, x6;
    double y1, y2, y3, y4, y5;
    damaged_part_car dpc;
    public AlertDialog alert;
    String damageType;

    boolean isLongPress = false;
    long time;

    static byte[] carDmgArray = new byte[13];

    static List<damaged_point> list_of_points = new ArrayList<damaged_point>();

    public doh_mashlim(Context context, int width, int height) {
        super(context);
        this.width = width;
        this.height = height;
        dpc = new damaged_part_car();
        points[0] = new Paint();
        points[0].setColor(Color.BLACK);
        points[0].setTextSize(60);
        /*points[1] = new Paint();
        points[1].setColor(Color.BLUE);
        points[2] = new Paint();
        points[2].setColor(Color.GREEN);*/
        x1 = 0.05 * width;
        x2 = 0.195 * width;
        x3 = 0.3575 * width;
        x4 = 0.6425 * width;
        x5 = 0.805 * width;
        x6 = 0.95 * width;

        y1 = 0.02 * height;
        y2 = 0.3 * height;
        y3 = 0.46 * height;
        y4 = 0.62 * height;
        y5 = 0.83 * height;

        for (int i = 0; i < carDmgArray.length; i++) {
            carDmgArray[i] = 0;
        }

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //sheet of Game
        gameSheet = new Paint();
        gameSheet.setColor(Color.RED);
        gameSheet.setStrokeWidth(7);
        gameSheet2 = new Paint();
        gameSheet2.setColor(Color.CYAN);

        for (int i = 0; i < carDmgArray.length; i++) {
            if (carDmgArray[i] != 0) dpc.drowPart(i, canvas);

        }
        //external 1-4
        canvas.drawLine((float) x1, (float) y1, (float) x1, (float) y5, gameSheet);
        canvas.drawLine((float) x1, (float) y1, (float) x6, (float) y1, gameSheet);
        canvas.drawLine((float) x6, (float) y1, (float) x6, (float) y5, gameSheet);
        canvas.drawLine((float) x1, (float) y5, (float) x6, (float) y5, gameSheet);
        //internal 5-10
        canvas.drawLine((float) x2, (float) y1, (float) x2, (float) y5, gameSheet);
        canvas.drawLine((float) x5, (float) y1, (float) x5, (float) y5, gameSheet);
        canvas.drawLine((float) x1, (float) y2, (float) x6, (float) y2, gameSheet);
        canvas.drawLine((float) x1, (float) y4, (float) x6, (float) y4, gameSheet);

        canvas.drawLine((float) x1, (float) y3, (float) x2, (float) y3, gameSheet);
        canvas.drawLine((float) x5, (float) y3, (float) x6, (float) y3, gameSheet);

        canvas.drawLine((float) x3, (float) y2, (float) x3, (float) y4, gameSheet);
        canvas.drawLine((float) x4, (float) y2, (float) x4, (float) y4, gameSheet);

        //filled damage
        for (int i = 0; i < list_of_points.size(); i++) {
            float centerX = list_of_points.get(i).getX();
            float centerY = list_of_points.get(i).getY();
            //canvas.drawCircle(centerX, centerY, 35, list_of_points.get(i).getP());
            canvas.drawText(list_of_points.get(i).getDamageType(), centerX, centerY, points[0]);
        }
    }

    public void dialogView(Activity a) {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(a);

        LayoutInflater inflater = a.getLayoutInflater();
        View mV = inflater.inflate(R.layout.dialog_view, null);
        alert_listener aL = new alert_listener();
        final Button injuredColor = (Button) mV.findViewById(R.id.injuredColor);
        injuredColor.setOnClickListener(aL);
        final Button stretchedTin = (Button) mV.findViewById(R.id.stretchedTin);
        stretchedTin.setOnClickListener(aL);
        final Button toManyDents = (Button) mV.findViewById(R.id.toManyDents);
        toManyDents.setOnClickListener(aL);
        final Button lacBurned = (Button) mV.findViewById(R.id.lacBurned);
        lacBurned.setOnClickListener(aL);
        final Button noHailDamage = (Button) mV.findViewById(R.id.noHailDamage);
        noHailDamage.setOnClickListener(aL);


        final Button removeThis = (Button) mV.findViewById(R.id.removeThis);
        removeThis.setOnClickListener(aL);
        final Button removeAll = (Button) mV.findViewById(R.id.removeAll);
        removeAll.setOnClickListener(aL);

        builder1.setTitle("Select one");
        builder1.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                alert.dismiss();
            }
        });

        builder1.setView(mV);
        alert = builder1.create();
//        alert.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        alert.setCancelable(true);
        alert.show();

    }

    public class alert_listener implements OnClickListener {

        @Override
        public void onClick(View v) {
            damaged_point mp1;
            switch (v.getId()) {
                case R.id.injuredColor:
                    damageType = "A";
                    mp1 = new damaged_point(cx, cy, damageType);
                    list_of_points.add(mp1);

                    break;
                case R.id.stretchedTin:
                    damageType = "B";
                    mp1 = new damaged_point(cx, cy, damageType);
                    list_of_points.add(mp1);

                    break;
                case R.id.toManyDents:
                    damageType = "C";
                    mp1 = new damaged_point(cx, cy, damageType);
                    list_of_points.add(mp1);

                    break;
                case R.id.lacBurned:
                    damageType = "D";
                    mp1 = new damaged_point(cx, cy, damageType);
                    list_of_points.add(mp1);

                    break;
                case R.id.noHailDamage:
                    damageType = "E";
                    mp1 = new damaged_point(cx, cy, damageType);
                    list_of_points.add(mp1);

                    break;
                case R.id.removeThis:
                    dpc.removeClosest(cx, cy, list_of_points);

                    break;
                case R.id.removeAll:
                    dpc.removeAllOnPartCar(part, list_of_points);

                    break;

            }
            alert.dismiss();
            invalidate();
        }
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getActionMasked();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                time = System.currentTimeMillis();

                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:

                cx = event.getX();
                cy = event.getY();
                part = dpc.checkPart(cx, cy);
                if (System.currentTimeMillis() - time > 500) {
                    dialogView((Activity) getContext());
                } else {
                    if (part != 99)
                        carDmgArray[part] = (byte) (carDmgArray[part] == 1 ? 0 : 1);
                }
                break;
            case MotionEvent.ACTION_CANCEL:
                break;
            case MotionEvent.ACTION_OUTSIDE:
                break;
        }
        invalidate();
        return true;
    }


    public class damaged_part_car {

        public damaged_part_car() {

        }

        public int checkPart(float cx, float cy) {
            if (cx > x1 && cx < x2) {
                if (cy > y1 && cy < y2)
                    return 0;
                if (cy > y2 && cy < y3)
                    return 3;
                if (cy > y3 && cy < y4)
                    return 4;
                if (cy > y4 && cy < y5)
                    return 10;
            }
            if (cx > x2 && cx < x3) {
                if (cy > y1 && cy < y2)
                    return 1;
                if (cy > y2 && cy < y4)
                    return 5;
                if (cy > y4 && cy < y5)
                    return 11;

            }
            if (cx > x3 && cx < x4) {
                if (cy > y1 && cy < y2)
                    return 1;
                if (cy > y2 && cy < y4)
                    return 6;
                if (cy > y4 && cy < y5)
                    return 11;

            }
            if (cx > x4 && cx < x5) {
                if (cy > y1 && cy < y2)
                    return 1;
                if (cy > y2 && cy < y4)
                    return 7;
                if (cy > y4 && cy < y5)
                    return 11;
            }
            if (cx > x5 && cx < x6) {
                if (cy > y1 && cy < y2)
                    return 2;
                if (cy > y2 && cy < y3)
                    return 8;
                if (cy > y3 && cy < y4)
                    return 9;
                if (cy > y4 && cy < y5)
                    return 12;

            }
            return 99;
        }

        public void drowPart(int index, Canvas canvas) {
            switch (index) {
                case 0:
                    canvas.drawRect((float) x1, (float) y1, (float) x2, (float) y2, gameSheet2);
                    break;
                case 1:
                    canvas.drawRect((float) x2, (float) y1, (float) x5, (float) y2, gameSheet2);
                    break;
                case 2:
                    canvas.drawRect((float) x5, (float) y1, (float) x6, (float) y2, gameSheet2);
                    break;
                case 3:
                    canvas.drawRect((float) x1, (float) y2, (float) x2, (float) y3, gameSheet2);
                    break;
                case 4:
                    canvas.drawRect((float) x1, (float) y3, (float) x2, (float) y4, gameSheet2);
                    break;
                case 5:
                    canvas.drawRect((float) x2, (float) y2, (float) x3, (float) y4, gameSheet2);
                    break;
                case 6:
                    canvas.drawRect((float) x3, (float) y2, (float) x4, (float) y4, gameSheet2);
                    break;
                case 7:
                    canvas.drawRect((float) x4, (float) y2, (float) x5, (float) y4, gameSheet2);
                    break;
                case 8:
                    canvas.drawRect((float) x5, (float) y2, (float) x6, (float) y3, gameSheet2);
                    break;
                case 9:
                    canvas.drawRect((float) x5, (float) y3, (float) x6, (float) y4, gameSheet2);
                    break;
                case 10:
                    canvas.drawRect((float) x1, (float) y4, (float) x2, (float) y5, gameSheet2);
                    break;
                case 11:
                    canvas.drawRect((float) x2, (float) y4, (float) x5, (float) y5, gameSheet2);
                    break;
                case 12:
                    canvas.drawRect((float) x5, (float) y4, (float) x6, (float) y5, gameSheet2);
                    break;


            }
        }

        public void removeClosest(float cx, float cy, List<damaged_point> list_of_points) {
            double distMin;
            double tmpX, tmpY;
            int tmpI = 0;
            if (list_of_points.size() > 0) {
                tmpX = Math.pow((list_of_points.get(0).getX() - cx), 2);
                tmpY = Math.pow((list_of_points.get(0).getY() - cy), 2);
                distMin = Math.sqrt(tmpX + tmpY);
                for (int i = 1; i < list_of_points.size(); i++) {
                    tmpX = Math.pow((list_of_points.get(i).getX() - cx), 2);
                    tmpY = Math.pow((list_of_points.get(i).getY() - cy), 2);
                    if (Math.sqrt(tmpX + tmpY) < distMin) {
                        distMin = Math.sqrt(tmpX + tmpY);
                        tmpI = i;
                    }
                }
                list_of_points.remove(tmpI);
            } else {
                Toast.makeText(getContext(), "Nothing to remove", Toast.LENGTH_LONG).show();
            }
        }

        public void removeAllOnPartCar(int part, List<damaged_point> list_of_points) {
            double[] limit = new double[4];
            int j = 0;
            switch (part) {
                case 0:
                    limit[0] = x1;
                    limit[1] = x2;
                    limit[2] = y1;
                    limit[3] = y2;
                    break;
                case 1:
                    limit[0] = x2;
                    limit[1] = x5;
                    limit[2] = y1;
                    limit[3] = y2;
                    break;
                case 2:
                    limit[0] = x5;
                    limit[1] = x6;
                    limit[2] = y1;
                    limit[3] = y2;
                    break;
                case 3:
                    limit[0] = x1;
                    limit[1] = x2;
                    limit[2] = y2;
                    limit[3] = y3;
                    break;
                case 4:
                    limit[0] = x1;
                    limit[1] = x2;
                    limit[2] = y3;
                    limit[3] = y4;
                    break;
                case 5:
                    limit[0] = x2;
                    limit[1] = x3;
                    limit[2] = y2;
                    limit[3] = y4;
                    break;
                case 6:
                    limit[0] = x3;
                    limit[1] = x4;
                    limit[2] = y2;
                    limit[3] = y4;
                    break;
                case 7:
                    limit[0] = x4;
                    limit[1] = x5;
                    limit[2] = y2;
                    limit[3] = y4;
                    break;
                case 8:
                    limit[0] = x5;
                    limit[1] = x6;
                    limit[2] = y2;
                    limit[3] = y3;
                    break;
                case 9:
                    limit[0] = x5;
                    limit[1] = x6;
                    limit[2] = y3;
                    limit[3] = y4;
                    break;
                case 10:
                    limit[0] = x1;
                    limit[1] = x2;
                    limit[2] = y4;
                    limit[3] = y5;
                    break;
                case 11:
                    limit[0] = x2;
                    limit[1] = x5;
                    limit[2] = y4;
                    limit[3] = y5;
                    break;
                case 12:
                    limit[0] = x5;
                    limit[1] = x6;
                    limit[2] = y4;
                    limit[3] = y5;
                    break;

            }
            int[] removeArr = new int[list_of_points.size()];
            for (int i = 0; i < list_of_points.size(); i++) {
                double tmpX = list_of_points.get(i).getX();
                double tmpY = list_of_points.get(i).getY();
                if (tmpX > limit[0] && tmpX < limit[1] && tmpY > limit[2] && tmpY < limit[3]) {
                    removeArr[j++] = i;
                }

            }
            if (j != 0) {
                for (j--; j >= 0; j--) {
                    list_of_points.remove(removeArr[j]);

                }
            } else Toast.makeText(getContext(), "Nothing to remove", Toast.LENGTH_LONG).show();
        }
    }

    public static String convert_list_of_points_to_JsonString() {
        String JsonString = "";
//        JSONObject pointsListObject = new JSONObject();
        JSONArray pointsListArr = new JSONArray();

        try {
            for (int i = 0; i < doh_mashlim.list_of_points.size(); i++) {
                JSONObject point = new JSONObject();
                point.put("x", doh_mashlim.list_of_points.get(i).getX());
                point.put("y", doh_mashlim.list_of_points.get(i).getY());
                point.put("damageType", doh_mashlim.list_of_points.get(i).getDamageType());
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
        JSONObject point;
//        JSONObject pointsListObject = new JSONObject();
        try {
            JSONArray pointsListArr = new JSONArray(JsonString);
            for (int i = 0; i < pointsListArr.length(); i++) {
                point = (JSONObject) pointsListArr.get(i);
                x = (float) point.getDouble("x");
                y = (float) point.getDouble("y");
                damageType = point.getString("damageType");

                damaged_point dp = new damaged_point(x, y, damageType);
                list_of_points.add(dp);
            }
        } catch (JSONException e) {
            Log.i("MyTag", e.getMessage());
        }
        return list_of_points;
    }
}
