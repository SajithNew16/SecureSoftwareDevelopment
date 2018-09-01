package mypackage;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import javax.servlet.ServletException;
import java.util.HashMap;
import java.util.Map;
import java.net.URLEncoder;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import net.sf.json.JSONObject;


import org.apache.commons.codec.binary.Base64;


public class Validator extends HttpServlet {


    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        Cookie[] cookies = request.getCookies();
        String csrfToken = Storage.getInstance().getItem(cookies[0].getValue());
//        System.out.println("valodatpr " + csrf);
        //writing data to json
        response.setContentType("application/json;charset=utf-8");
        JSONObject member = new JSONObject();

        member.put("csrfToken", csrfToken);
        PrintWriter pw = response.getWriter();
        pw.print(member.toString());
        pw.close();

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        processRequest(request, response);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            String id = request.getParameter("id");
            String key = request.getParameter("key");
            String recvCsrfToken = request.getParameter("tokentxt");
            out.println("id " + id);
            out.println("key " + key);
            out.println("token " + recvCsrfToken);
            Cookie[] cookies = request.getCookies();
            String csrfToken = Storage.getInstance().getItem(cookies[0].getValue());
            if (recvCsrfToken.equals(csrfToken)) {
                out.println("Verified Successfully");
//                response.sendRedirect("confirm.jsp");
            } else {
                out.println("Verification is unsuccessfull");
            }
        } finally {
            out.close();
        }

    }
}

