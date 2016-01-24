package com.example.evgeny.cardamage_project;

import java.util.List;

/**
 * Created by hackeru on 20/01/2016.
 */
public class list_of_points_saved_Class {
    List<damaged_point> list_of_points_save;

    public list_of_points_saved_Class() {
        this.list_of_points_save = car_view.list_of_points;
    }

    public List<damaged_point> getList_of_points_save() {
        return list_of_points_save;
    }
}
