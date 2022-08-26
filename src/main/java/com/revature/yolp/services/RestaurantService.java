package com.revature.yolp.services;

import com.revature.yolp.daos.RestaurantDAO;
import com.revature.yolp.models.Restaurant;

import java.util.List;

public class RestaurantService {
    private final RestaurantDAO restoDAO;

    public RestaurantService(RestaurantDAO restoDAO) {
        this.restoDAO = restoDAO;
    }

    public List<Restaurant> getAllRestaurants() {
        return restoDAO.getAll();
    }
}
