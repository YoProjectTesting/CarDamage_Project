package com.example.evgeny.cardamage_project;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;

public class imagePresents extends AppCompatActivity {
    File file,dir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_presents);
        ImageView SI=(ImageView)findViewById(R.id.shownImage);
        Button saveImg=(Button)findViewById(R.id.SaveToFile);
        Button shareFromFile=(Button)findViewById(R.id.shareFromFile);
        getSupportActionBar().hide();

        SI.setImageBitmap(MainActivity.viewToPic_CAR_VIEW);

        saveImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveImgToFile();
            }
        });
        shareFromFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareMultiplyFiles();
            }
        });

    }

    public  void saveImgToFile(){
        dir = new File(this.getExternalFilesDir(
                Environment.DIRECTORY_PICTURES), "CarDamage_Project");
        if (!dir.mkdirs()) {
            Log.i("MyTag", "Directory not created");
        }
        file = new File(dir, "SavedActivity.png");
        Log.i("MyTag", " file: " + file.toString());
        Log.i("MyTag", " dir: " + dir.toString());
        int count=new File(dir.toString()).list().length;
        Log.i("MyTag", " count: " + count);
        FileOutputStream fOut;
        try {
            fOut = new FileOutputStream(file);

            MainActivity.viewToPic_CAR_VIEW.compress(Bitmap.CompressFormat.PNG, 85, fOut);
            fOut.flush();
            fOut.close();
            Toast.makeText(getApplicationContext(),"Saved",Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Log.i("MyTag", " e.getMessage():   " + e.getMessage());
        }
    }

    public  void shareMultiplyFiles(){
        String[] TO={"evgeny.strilchik@gmail.com"};
//    String[]CC={evgeny.strilchik@gmail.com};

        Intent emailIntent=new Intent(Intent.ACTION_SEND_MULTIPLE);
        emailIntent.setType("text/plain");

        ArrayList<Uri> uris = new ArrayList<Uri>();
        Uri uri1 = Uri.fromFile(file);
        Uri uri2 = Uri.fromFile(file);
        uris.add(uri1);
        uris.add(uri2);
        emailIntent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, uris);

        String msg,subject;
        msg="My default message";
        subject="Mail default subject";
        emailIntent.putExtra(Intent.EXTRA_EMAIL,TO);
//                    emailIntent.putExtra(Intent.EXTRA_CC,CC);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
        emailIntent.putExtra(Intent.EXTRA_TEXT, msg);
        try {
            startActivityForResult(Intent.createChooser(emailIntent, "Sending multiple attachment"), 12345);
            finish();
            Log.i("MyTag", "Finished sending email...");
        }
        catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(getApplicationContext(), "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }
    }

    public  void shareFromFile(){
        //Simple share
        Uri uri = Uri.fromFile(file);
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.setType("image/*");

        intent.putExtra(android.content.Intent.EXTRA_SUBJECT, "");
        intent.putExtra(android.content.Intent.EXTRA_TEXT, "");
        intent.putExtra(Intent.EXTRA_STREAM, uri);
        startActivity(Intent.createChooser(intent, "Share Cover Image"));
    }


}






