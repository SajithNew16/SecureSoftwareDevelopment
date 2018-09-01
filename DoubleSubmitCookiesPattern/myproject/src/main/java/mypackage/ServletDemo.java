package mypackage;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;


public class ServletDemo extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        Cookie c1 = new Cookie("c1", "anything");
        Cookie c2 = new Cookie("c2", "anything");

        c1.setMaxAge(60 * 60); //1 hour
        c2.setMaxAge(60 * 60); //1 hour

        c2.setSecure(true);

        response.addCookie(c1);
        response.addCookie(c2);

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        processRequest(request, response);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (username.equals("a") && password.equals("b")) {
//            HttpSession session = request.getSession();
//            session.setAttribute("username", username);
            response.sendRedirect("welcome.jsp");
        } else {
            out.println("Invalid username and/or password");
        }
    }

}
