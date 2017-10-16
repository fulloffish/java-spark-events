package controller;

import dao.EventDao;
import model.Event;
import spark.ModelAndView;
import spark.Request;
import spark.Response;


import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class EventController {

    static EventDao eventDao = new EventDao();

    public static ModelAndView renderEvents(Request req, Response res) {
        List<Event> events = eventDao.getAll();
        List<String> categories = eventDao.getCategoryList();
        Map params = new HashMap();
        params.put("eventContainer", events);
        params.put("categoryList", categories);
        params.put("selectedCategory", "All events");
        return new ModelAndView(params, "event/index");
    }

    public static ModelAndView renderAddEvent(Request req, Response res) {
        Map params = new HashMap();
        return new ModelAndView(params, "event/add");
    }

    public static void addNewEvent(Request req, Response res) {
        String name = req.queryParams("name");
        String description = req.queryParams("description");
        String date = req.queryParams("date");
        String category = req.queryParams("category");
        String link = req.queryParams("link");
        eventDao.addNewEvent(name, date, description, category, link);
    }

    public static void removeEvent(Request req, Response res) {
        String id = req.params(":id");
        eventDao.remove(Integer.parseInt(id));
    }

    public static ModelAndView renderUpdateEvent(Request req, Response res) {
        Map params = new HashMap();
        Integer id = Integer.parseInt(req.params(":id"));
        Event event = eventDao.findEvent(id);
        params.put("event", event);
        return new ModelAndView(params, "event/edit");
    }

    public static void updateEvent(Request req, Response res) {
        String id = req.params(":id");
        String name = req.queryParams("name");
        String description = req.queryParams("description");
        String date = req.queryParams("date");
        String category = req.queryParams("category");
        String link = req.queryParams("link");
        eventDao.update(Integer.parseInt(id), name, date, description, category, link);
    }

    public static ModelAndView renderFilteredByCategory(Request req, Response res) {
        Map params = new HashMap();
        String category = req.queryParams("selectCategory");
        List<Event> events = eventDao.filterBy(category);
        List<String> categories = eventDao.getCategoryList();
        params.put("eventContainer", events);
        params.put("categoryList", categories);
        params.put("selectedCategory", category);
        return new ModelAndView(params, "event/index");
    }

}
