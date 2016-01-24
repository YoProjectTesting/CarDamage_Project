package com.example.evgeny.cardamage_project;

import android.graphics.Paint;

/**
 * Created by hackeru on 02/12/2015.
 */
public class damaged_point {
    private float x;
    private float y;
    String damageType;
    byte [] image;

//uses for save letters
    public damaged_point(float x, float y, String damageType) {
        this.y = y;
        this.x = x;
        this.damageType = damageType;
        this.image=null;
    }
//uses for save letters
    public damaged_point(float x, float y, String damageType, byte [] image) {
        this.y = y;
        this.x = x;
        this.damageType = damageType;
        this.image = image;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getY() {

        return y;
    }

    public float getX() {
        return x;
    }

    public String getDamageType() {
        return damageType;
    }

    public byte[] getImage() {
        return image;
    }

    @Override
    public String toString() {
        return "damaged_point{" +
                "damageType='" + damageType + '\'' +
                ", y=" + y +
                ", x=" + x +
                '}';
    }
}
