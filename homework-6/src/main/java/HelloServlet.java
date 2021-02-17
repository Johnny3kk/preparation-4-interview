
import javax.servlet.*;
import java.io.IOException;

public class HelloServlet implements Servlet {

    private transient ServletConfig config;

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        this.config = config;
    }

    @Override
    public ServletConfig getServletConfig() {
        return config;
    }

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        res.getWriter().println("<h1>Hello World!</h1>");
    }

    @Override
    public String getServletInfo() {
        return "HelloServlet";
    }

    @Override
    public void destroy() {

    }
}
