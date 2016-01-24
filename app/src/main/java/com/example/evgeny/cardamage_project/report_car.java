package com.example.evgeny.cardamage_project;

import android.graphics.Bitmap;

import java.util.Date;

/**
 * Created by Evgeny on 27/12/2015.
 */
public class report_car {
    private String carNum,model,yearOfProduction,cusName,customerID,cusPhone,customerEmail,appraiserName,appraiserPhone,appraiserMail,insuranceCompany,insuranceName,insurancePhone,levelDamage,savingUser;
    String list_of_points_saved;
    Date savingDate;

    byte [] carDmgArray;
    Bitmap car_damage_Report;
    Bitmap complementary_Report;

    public report_car(String carNum, String model, String yearOfProduction,
                      String cusName, String customerID, String cusPhone,
                      String customerEmail, String appraiserName, String appraiserPhone,
                      String appraiserMail, String insuranceCompany, String insuranceName,
                      String insurancePhone, String levelDamage, String savingUser,String list_of_points_saved,
                      Date savingDate,byte [] carDmgArray, Bitmap car_damage_Report, Bitmap complementary_Report) {
        this.carNum = carNum;
        this.model = model;
        this.yearOfProduction = yearOfProduction;
        this.cusName = cusName;
        this.customerID = customerID;
        this.cusPhone = cusPhone;
        this.customerEmail = customerEmail;
        this.appraiserName = appraiserName;
        this.appraiserPhone = appraiserPhone;
        this.appraiserMail = appraiserMail;
        this.insuranceCompany = insuranceCompany;
        this.insuranceName = insuranceName;
        this.insurancePhone = insurancePhone;
        this.levelDamage = levelDamage;
        this.savingUser = savingUser;
        this.list_of_points_saved = list_of_points_saved;
        this.savingDate = savingDate;
        this.carDmgArray = carDmgArray;
        this.car_damage_Report = car_damage_Report;
        this.complementary_Report = complementary_Report;
    }

    public String getCarNum() {
        return carNum;
    }

    public void setCarNum(String carNum) {
        this.carNum = carNum;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getYearOfProduction() {
        return yearOfProduction;
    }

    public void setYearOfProduction(String yearOfProduction) {
        this.yearOfProduction = yearOfProduction;
    }

    public String getCusName() {
        return cusName;
    }

    public void setCusName(String cusName) {
        this.cusName = cusName;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getCusPhone() {
        return cusPhone;
    }

    public void setCusPhone(String cusPhone) {
        this.cusPhone = cusPhone;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getAppraiserName() {
        return appraiserName;
    }

    public void setAppraiserName(String appraiserName) {
        this.appraiserName = appraiserName;
    }

    public String getAppraiserPhone() {
        return appraiserPhone;
    }

    public void setAppraiserPhone(String appraiserPhone) {
        this.appraiserPhone = appraiserPhone;
    }

    public String getAppraiserMail() {
        return appraiserMail;
    }

    public void setAppraiserMail(String appraiserMail) {
        this.appraiserMail = appraiserMail;
    }

    public String getInsuranceCompany() {
        return insuranceCompany;
    }

    public void setInsuranceCompany(String insuranceCompany) {
        this.insuranceCompany = insuranceCompany;
    }

    public String getInsuranceName() {
        return insuranceName;
    }

    public void setInsuranceName(String insuranceName) {
        this.insuranceName = insuranceName;
    }

    public String getInsurancePhone() {
        return insurancePhone;
    }

    public void setInsurancePhone(String insurancePhone) {
        this.insurancePhone = insurancePhone;
    }

    public String getLevelDamage() {
        return levelDamage;
    }

    public void setLevelDamage(String levelDamage) {
        this.levelDamage = levelDamage;
    }

    public String getSavingUser() {
        return savingUser;
    }

    public String getList_of_points_saved() {
        return list_of_points_saved;
    }

    public Date getSavingDate() {
        return savingDate;
    }

    public byte[] getCarDmgArray() {
        return carDmgArray;
    }

    public Bitmap getCar_damage_Report() {
        return car_damage_Report;
    }

    public Bitmap getComplementary_Report() {
        return complementary_Report;
    }

    @Override
    public String toString() {
        return "report_car{" +
                "carNum='" + carNum + '\'' +
                '}';
    }
}
