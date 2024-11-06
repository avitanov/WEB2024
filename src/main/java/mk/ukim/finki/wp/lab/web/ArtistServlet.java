package mk.ukim.finki.wp.lab.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.wp.lab.model.Artist;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;
import mk.ukim.finki.wp.lab.service.ArtistService;

import java.io.IOException;

@WebServlet(urlPatterns = "/artist")
public class ArtistServlet extends HttpServlet {
    private final ArtistService artistService;

    private final SpringTemplateEngine springTemplateEngine;

    public ArtistServlet(ArtistService artistService, SpringTemplateEngine springTemplateEngine) {
        this.artistService = artistService;
        this.springTemplateEngine = springTemplateEngine;
    }
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req, resp);

        WebContext context = new WebContext(webExchange);
        context.setVariable("artists",artistService.listArtists());
        String trackId=(String) req.getSession().getAttribute("trackId");
        context.setVariable("trackId",trackId);
        springTemplateEngine.process("artist.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idArtist = req.getParameter("id");
        Artist selectedArtist = artistService.ArtistfindById(Long.valueOf(idArtist));
        req.getSession().setAttribute("selectedArtist",selectedArtist);
        resp.sendRedirect("/songDetails");
    }
}
