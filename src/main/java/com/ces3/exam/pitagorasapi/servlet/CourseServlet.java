package com.ces3.exam.pitagorasapi.servlet;

import com.ces3.exam.pitagorasapi.model.Course;
import com.ces3.exam.pitagorasapi.service.CourseService;
import com.ces3.exam.pitagorasapi.utils.UtilMethods;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.NoSuchElementException;

@WebServlet(name = "course-servlet", urlPatterns = {"/courses", "/courses/faculty", "/courses/route"})
public class CourseServlet extends HttpServlet {

    @Inject
    private CourseService courseService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        Gson gson = new Gson();
        PrintWriter out = resp.getWriter();

        try {
            String path = req.getRequestURI();
            String facultyParam = req.getParameter("name");
            String codeParam = req.getParameter("code");
            String idParam = req.getParameter("id");

            if (path.endsWith("/courses/faculty") && facultyParam != null) {
                out.println(gson.toJson(courseService.getCourseByFaculty(facultyParam)));
            } else if (path.endsWith("/courses/route") && codeParam != null) {
                out.println(gson.toJson(courseService.getPrerequisiteCoursesByCode(codeParam)));
            } else if (path.endsWith("/courses") && idParam != null) {
                int id = Integer.parseInt(idParam);
                out.println(gson.toJson(courseService.getCourseById(id)));
            } else if (path.endsWith("/courses")) {
                out.println(gson.toJson(courseService.getAllCourses()));
            } else {
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                out.print("{\"message\": \"Invalid endpoint or missing parameters\"}");
                return;
            }
            resp.setStatus(HttpServletResponse.SC_OK);
        } catch (NoSuchElementException e) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            out.print("{\"message\": \"" + e.getMessage() + "\"}");
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            out.print("{\"error\": \"" + e.getMessage() + "\"}");
        }

        out.flush();
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();

        try{
            JsonObject reqBody = UtilMethods.getParamsFromBody(req);

            JsonArray coursePrerequisitesArray = reqBody.getAsJsonArray("prerequisites");
            ArrayList<String> coursePrerequisites = new ArrayList<>();

            for (JsonElement element : coursePrerequisitesArray) {
                coursePrerequisites.add(element.getAsString());
            }
            courseService.createCourse(new Course(
                    reqBody.get("id").getAsInt(),
                    reqBody.get("name").getAsString(),
                    reqBody.get("code").getAsString(),
                    reqBody.get("teacher").getAsString(),
                    reqBody.get("maxCapacity").getAsInt(),
                    reqBody.get("enrolledStudents").getAsInt(),
                    reqBody.get("faculty").getAsString(),
                    coursePrerequisites,
                    reqBody.get("level").getAsInt(),
                    reqBody.get("startDate").getAsString()
            ));
            resp.setStatus(HttpServletResponse.SC_CREATED);
            out.print("{\"message\": \"Course created successfully\"}");
        } catch (IllegalArgumentException e){
            resp.setStatus(HttpServletResponse.SC_CONFLICT);
            out.print("{\"message\": \"" + e.getMessage() + "\"}");
        } catch (Exception e){
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            out.print("{\"message\": \"" + e.getMessage() + "\"}");
        }
        out.flush();
    }
}
