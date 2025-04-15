// ServletsJSPDemo.java
// Demonstrates a simple Java Servlet (conceptual example, not runnable without a servlet container like Tomcat).
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class HelloServlet extends HttpServlet {
    // Handles GET requests
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>Hello from Servlet!</h1>");
        out.println("</body></html>");
    }
}
