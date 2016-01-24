package com.example.evgeny.cardamage_project;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by Evgeny on 16/12/2015.
 */
public class damaged_parts_car_draw {
    double x1,x2,x3,x4,x5,x6;
    double y1,y2,y3,y4,y5;
    Paint gameSheet2;
    int width, height;


    public damaged_parts_car_draw(){
        gameSheet2 = new Paint();
        gameSheet2.setColor(Color.YELLOW);
        x1= 0.05*width; x2 = 0.195*width; x3 = 0.3575*width; x4 = 0.6425*width; x5 = 0.805*width; x6 = 0.95*width; y1 = 0.02*height; y2 = 0.3*height; y3 = 0.46*height; y4 = 0.62*height; y5 = 0.83*height;
    }

    public void drowPart(int index,Canvas canvas){
        switch(index){
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
                canvas.drawRect((float) x3, (float) y2, (float) x4, (float) y3, gameSheet2);
                break;
            case 7:
                canvas.drawRect((float) x4, (float) y2, (float) x5, (float) y3, gameSheet2);
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

}
