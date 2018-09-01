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
       /* //generate a session id and store it as a cookie in the browser
        String sessionid = generateSessionId();
        Cookie c1 = new Cookie("sessionid", sessionid);
        c1.setMaxAge(60 * 60); //1 hour
        c1.setSecure(true);
        response.addCookie(c1);*/
       /* Cookie[] cookies = request.getCookies();
        String value = cookies[0].getValue();
        System.out.println(value);*/
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
            if (username.equals("a") && password.equals("b")) {
                HttpSession session = request.getSession();
                session.setAttribute("username", username);
                // generate csrf token and store it in the hash map of Storage class server side
//                Storage storageObj = Storage.getInstance();
//                String csrfToken = generateCSRFToken();
                Storage.getInstance().addItem(cookies[0].getValue(), generateCSRFToken());
//                System.out.println("lging " + Storage.getInstance().getItem(cookies[0].getValue()));
//                session.setAttribute("csrfToken", csrfToken);
                response.sendRedirect("welcome.jsp");
            } else {
                out.println("Invalid username and/or password");
            }
        } finally {
            out.close();
        }
    }
}

