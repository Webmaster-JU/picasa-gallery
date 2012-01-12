package net.azib.photos;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import static javax.servlet.http.HttpServletResponse.SC_NOT_FOUND;

public class SiteMapServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        if (req.getServletPath().equals("/robots.txt")) {
            resp.setContentType("text/plain");
            out.write("Sitemap: http://" + req.getHeader("Host") + "/sitemap.xml\n" +
                    "User-Agent: *\n" +
                    "Allow: /\n");
            out.close();
        }
        else if (req.getServletPath().equals("/sitemap.xml")) {
            resp.setContentType("text/xml");
            Picasa picasa = new Picasa(null);
            req.setAttribute("host", req.getHeader("Host"));
            req.setAttribute("picasa", picasa);
            req.setAttribute("gallery", picasa.getGallery());
            req.getRequestDispatcher("/WEB-INF/jsp/sitemap.jsp").forward(req, resp);
        }
        else {
            resp.sendError(SC_NOT_FOUND);
        }
    }
}
