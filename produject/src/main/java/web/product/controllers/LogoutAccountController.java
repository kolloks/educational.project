package web.product.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static web.product.Attributes.PAGE_INDEX;

public class LogoutAccountController extends HttpServlet{

    private static Logger logger = LogManager.getLogger();

    @Override
    public void service(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.getSession().invalidate();
        logger.debug("Logout successful! SendRedirect "+PAGE_INDEX);
        resp.sendRedirect(PAGE_INDEX);
    }
}
