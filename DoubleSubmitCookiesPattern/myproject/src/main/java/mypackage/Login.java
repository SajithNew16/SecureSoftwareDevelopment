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

import org.apache.commons.codec.binary.Base64;


public class Login extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        processRequest(request, response);
    }


    private static final String SHA_1_PRNG = "SHA1PRNG";

    private static String generateCSRFToken() {
        String csrfToken = null;
        try {
            SecureRandom secureRandom = SecureRandom.getInstance(SHA_1_PRNG);
            byte[] bytes = new byte[16];
            // secureRandom is automatically seeded by calling nextBytes
            secureRandom.nextBytes(bytes);
            csrfToken = new String(Base64.encodeBase64(bytes));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA1PRNG algorithm could not be found.");
        }

        return csrfToken;
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            //request for auto generated cookies in the browser
            Cookie[] cookies = request.getCookies();
            cookies[0].setPath("/");
            if (username.equals("John") && password.equals("John123")) {
                HttpSession session = request.getSession();
                session.setAttribute("username", username);
                String csrfToken = generateCSRFToken();
                Cookie c1 = new Cookie(cookies[0].getValue(), csrfToken);
                c1.setPath("/");
                response.addCookie(c1);
                session.setAttribute("csrfToken", csrfToken);
                response.sendRedirect("welcome.jsp");
            } else {
                out.println("Invalid username and/or password");
            }
        } finally {
            out.close();
        }
    }
}

