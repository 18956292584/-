package com.springboot.Repository;

import com.springboot.entity.Business;
import com.springboot.entity.Food;
import com.springboot.entity.IdPasswordModel;

import java.util.ArrayList;

public interface BusService {
    String dealLogin(IdPasswordModel idPasswordModel);

    ArrayList<Food> getAllFood(String bus, int state);

    void addFood(Food food);

    void foodState(int foodid, int state);

    void changeFood(Food food);

    Object searchFood(String food, String bus);

    Business getBusById(String bus);
}
