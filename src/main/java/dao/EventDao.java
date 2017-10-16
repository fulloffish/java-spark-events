package dao;

import model.Event;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class EventDao extends BaseDao {


    public List<Event> getAll() {
        List<Event> events =  new ArrayList<>();

        try {
            PreparedStatement statement = this.getConnection()
                                              .prepareStatement("SELECT * FROM events");
            ResultSet rs = statement.executeQuery();
            while(rs.next()) {
                Event event = new Event(
                        rs.getString("name"),
                        rs.getString("date"),
                        rs.getString("description"),
                        rs.getString("category"),
                        rs.getString("link")
                );
                event.setId(rs.getInt("id"));
                events.add(event);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return events;
    }

    public void addNewEvent(
            String name, String date, String description,
            String category, String link) {

        try {
            String instertTableSQL = "INSERT INTO events" +
                                     "(name, date, description, category, link) VALUES" +
                                    "(?, ?, ?, ?, ?)";
            PreparedStatement statement = this.getConnection()
                                          .prepareStatement(instertTableSQL);

            statement.setString(1, name);
            statement.setString(2, date);
            statement.setString(3, description);
            statement.setString(4, category);
            statement.setString(5, link);
            statement.executeUpdate();
            new Event(name, date, description, category, link);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void remove(Integer id) {
        try {
            PreparedStatement statement = this.getConnection()
                                              .prepareStatement("DELETE FROM events WHERE id=?");
            statement.setInt(1,id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(
            Integer id, String name, String date,
            String description, String category, String link) {

        try {
            PreparedStatement statement = this.getConnection()
                                              .prepareStatement(                                                                                              "UPDATE events SET " +
                                                      "name=?, date=?, description=?, category=?, link=? " +
                                                      "WHERE id=?");
            statement.setString(1, name);
            statement.setString(2, date);
            statement.setString(3, description);
            statement.setString(4, category);
            statement.setString(5, link);
            statement.setInt(6, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Event findEvent(Integer id) {
        Event event = null;

        try {
            PreparedStatement statement = this.getConnection()
                                              .prepareStatement("SELECT * FROM events WHERE id=?");
            statement.setInt(1,id);
            ResultSet rs = statement.executeQuery();
            event = new Event(
                    rs.getString("name"),
                    rs.getString("date"),
                    rs.getString("description"),
                    rs.getString("category"),
                    rs.getString("link")
            );
            event.setId(rs.getInt("id"));

            }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return event;
    }

    public List<String> getCategoryList() {
        List<String> categories = new ArrayList<>();
        try {
            PreparedStatement statement = this.getConnection()
                                              .prepareStatement("SELECT category FROM events");
            ResultSet rs = statement.executeQuery();

            while(rs.next()) {
                String category = rs.getString("category");

                if(!categories.contains(category)) {
                    categories.add(category);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categories;
    }

    public List<Event> filterBy(String category) {
        List<Event> byCategory = new ArrayList<>();

        try {
            PreparedStatement statement = this.getConnection()
                                              .prepareStatement("SELECT * FROM events WHERE category=?");
            statement.setString(1, category);
            ResultSet rs = statement.executeQuery();
            while(rs.next()) {
                Event event = new Event(
                        rs.getString("name"),
                        rs.getString("date"),
                        rs.getString("description"),
                        rs.getString("category"),
                        rs.getString("link")
                );
                event.setId(rs.getInt("id"));
                byCategory.add(event);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return byCategory;
        }
    }
