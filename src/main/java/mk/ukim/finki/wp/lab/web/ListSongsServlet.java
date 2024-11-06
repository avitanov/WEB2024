package mk.ukim.finki.wp.lab.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;
import mk.ukim.finki.wp.lab.service.SongService;

import java.io.IOException;

@WebServlet(name="songList", urlPatterns = "/listSongs")
public class ListSongsServlet extends HttpServlet {
    private final SongService songService;
    private final SpringTemplateEngine springTemplateEngine;

    public ListSongsServlet(SongService songService, SpringTemplateEngine springTemplateEngine) {
        this.songService = songService;
        this.springTemplateEngine = springTemplateEngine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req, resp);

        WebContext context = new WebContext(webExchange);


        context.setVariable("songs", this.songService.listSongs());
        springTemplateEngine.process("listSongs.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String trackId=req.getParameter("trackId");
        req.getSession().setAttribute("trackId",trackId);
        resp.sendRedirect("/artist");
    }
}
