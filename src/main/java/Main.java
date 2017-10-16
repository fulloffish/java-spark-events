import controller.EventController;
import dao.DbConnection;
import spark.template.thymeleaf.ThymeleafTemplateEngine;


import  static spark.Spark.*;


public class Main {

    public static void main(String[] args) {

        DbConnection.createTables();

        exception(Exception.class, (e, req, res) -> e.printStackTrace());
        staticFileLocation("/public");
        port(8888);

        get("/", EventController::renderEvents, new ThymeleafTemplateEngine());

        post("/", EventController::renderFilteredByCategory, new ThymeleafTemplateEngine());

        get("/add", EventController::renderAddEvent, new ThymeleafTemplateEngine());

        post("/add", (req, res) -> {
            EventController.addNewEvent(req, res);
            res.redirect("/");
            return "";
        });

        get("/edit/:id", EventController::renderUpdateEvent, new ThymeleafTemplateEngine());

        post("/edit/:id", (req, res) -> {
            EventController.updateEvent(req, res);
            res.redirect("/");
            return "";
        });

        get("/remove/:id", (req, res) -> {

            EventController.removeEvent(req, res);
            res.redirect("/");
            return "";
        });
        }
    }


