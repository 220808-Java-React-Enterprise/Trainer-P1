package com.revature.yolp.daos;

import com.revature.yolp.models.Review;
import com.revature.yolp.utils.custom_exceptions.InvalidSQLException;
import com.revature.yolp.utils.database.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReviewDAO implements CrudDAO<Review> {
    @Override
    public void save(Review obj) {
        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("INSERT INTO reviews (id, comment, rating, user_id, restaurant_id) VALUES (?, ?, ?, ?, ?)");
            ps.setString(1, obj.getId());
            ps.setString(2, obj.getComment());
            ps.setInt(3, obj.getRating());
            ps.setString(4, obj.getUser_id());
            ps.setString(5, obj.getRestaurant_id());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new InvalidSQLException("An error occurred when tyring to save to the database.");
        }
    }

    @Override
    public void update(Review obj) {

    }

    @Override
    public void delete(String id) {

    }

    @Override
    public Review getById(String id) {
        return null;
    }

    @Override
    public List<Review> getAll() {
        return null;
    }

    public List<Review> getAllByRestaurantId(String id) {
        List<Review> reviews = new ArrayList<>();

        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM reviews WHERE restaurant_id = ?");
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Review review = new Review(rs.getString("id"), rs.getString("comment"), rs.getInt("rating"), rs.getString("user_id"), rs.getString("restaurant_id"));
                reviews.add(review);
            }
        } catch (SQLException e) {
            throw new InvalidSQLException("An error occurred when tyring to save to the database.");
        }

        return reviews;
    }
}
