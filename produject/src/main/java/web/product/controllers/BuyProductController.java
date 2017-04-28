package web.product.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static web.product.Attributes.*;

public class BuyProductController extends HttpServlet{

    private static Logger logger = LogManager.getLogger();
    private static final String PURCHASE_HISTORY = "purchaseHistory";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();
        if ((session.getAttribute(USER))!=null){
            session.setAttribute(PRODUCTS_IN_BASKET, null);
            logger.trace("Purchase successfully. SendRedirect"+PAGE_INDEX);
            resp.sendRedirect(PAGE_INDEX);
            return; //OK
        }
        logger.debug("Need login or registration!");
        try {
            req.getRequestDispatcher("WEB-INF/jsp/authorization.jsp").forward(req, resp);
        } catch (ServletException e) {
            logger.error(e + "\n SendRedirect"+PAGE_ERROR);
            resp.sendRedirect(PAGE_ERROR);
        }
    }
}
