package com.revature.yolp.models;

public class Review {
    private String id;
    private String comment;
    private int rating;
    private String user_id;
    private String restaurant_id;

    public Review() {

    }

    public Review(String id, String comment, int rating, String user_id, String restaurant_id) {
        this.id = id;
        this.comment = comment;
        this.rating = rating;
        this.user_id = user_id;
        this.restaurant_id = restaurant_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getRestaurant_id() {
        return restaurant_id;
    }

    public void setRestaurant_id(String restaurant_id) {
        this.restaurant_id = restaurant_id;
    }

    @Override
    public String toString() {
        return "Review{" +
                "id='" + id + '\'' +
                ", comment='" + comment + '\'' +
                ", rating=" + rating +
                ", user_id='" + user_id + '\'' +
                ", restaurant_id='" + restaurant_id + '\'' +
                '}';
    }
}
