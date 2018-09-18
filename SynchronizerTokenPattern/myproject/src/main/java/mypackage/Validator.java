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
import javax.swing.JOptionPane;
import net.sf.json.JSONObject;
import org.apache.commons.codec.binary.Base64;


public class Validator extends HttpServlet {


    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        Cookie[] cookies = request.getCookies();
        String csrfToken = Storage.getInstance().getItem(cookies[0].getValue());
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
        String key = request.getParameter("key");
        String recvCsrfToken = request.getParameter("tokentxt");
        Cookie[] cookies = request.getCookies();
        String csrfToken = Storage.getInstance().getItem(cookies[0].getValue());
        if (recvCsrfToken.equals(csrfToken)) {
            JOptionPane.showMessageDialog(null, "Verified Successfully", "InfoBox: " + "Verification", JOptionPane.INFORMATION_MESSAGE);
            response.sendRedirect("https://github.com/SajithNew16/SecureSoftwareDevelopment");
        } else {
            JOptionPane.showMessageDialog(null, "Verification is Unsuccessful", "InfoBox: " + "Verification", JOptionPane.INFORMATION_MESSAGE);
            response.sendRedirect("welcome.jsp");
        }

    }
}

