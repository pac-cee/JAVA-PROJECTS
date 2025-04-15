# ServletsJSPDemo Project

This project demonstrates the basics of Java Servlets, which are the foundation of Java web applications.

## Business Logic / Explanation

- **Servlet:**
  - `HelloServlet` extends `HttpServlet` and overrides the `doGet` method to handle HTTP GET requests.
  - Sets the response content type to HTML and writes a simple greeting message.
- **Deployment:**
  - Servlets must be deployed in a servlet container (like Apache Tomcat) and mapped in a `web.xml` file or via annotations.

## How to Run
1. Package the servlet into a `.war` file.
2. Deploy to a servlet container (e.g., Tomcat).
3. Access via your browser at the mapped URL (e.g., `http://localhost:8080/ServletsJSPDemo/HelloServlet`).

Expected output:
```
<html><body>
<h1>Hello from Servlet!</h1>
</body></html>
```

---

This example is a conceptual introduction. For a full runnable demo, set up a Java web project with a proper directory structure and deployment descriptor (`web.xml`).
