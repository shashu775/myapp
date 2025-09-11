package com.example;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/hello")
public class HelloServlet extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    resp.setContentType("text/plain");
    String build = System.getenv("BUILD_NUMBER");
    if (build == null) build = "local";
    resp.getWriter().println("Hello from Tomcat! Build: " + build);
  }
}
