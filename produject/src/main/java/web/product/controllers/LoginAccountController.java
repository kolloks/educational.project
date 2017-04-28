package web.product.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import web.product.DAO.NotFoundUserException;
import web.product.actions.DBWorker;
import web.product.models.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import static web.product.Attributes.*;

public class LoginAccountController extends HttpServlet{

    private static Logger logger = LogManager.getLogger();

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            req.getRequestDispatcher("WEB-INF/jsp/authorization.jsp").forward(req, resp);
        } catch (ServletException e) {
           logger.error(e);
        }
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String login = req.getParameter(PARAM_LOGIN);
        String password = req.getParameter(PARAM_PASSWORD);
        DBWorker dbWorker = new DBWorker();
        boolean loginSuccessful = false;
        try {
            User user = dbWorker.selectUserByLoginAndPassword(login, password);
            req.getSession().setAttribute(USER, user);
            loginSuccessful = true;
            logger.trace("Login successful.");
        } catch (NotFoundUserException e) {
            logger.debug(e);
        } finally {
            try {
                dbWorker.closeConnection();
            } catch (SQLException e) {
                logger.error(e);
            }
        }

        if (!loginSuccessful) {
            try {
                logger.debug("User not found or need registration!");
                req.getRequestDispatcher("WEB-INF/jsp/authorization.jsp").forward(req, resp);
            } catch (ServletException e) {
                logger.error(e);
            }
        } else {
            logger.trace("SendRedirect "+PAGE_INDEX);
            resp.sendRedirect(PAGE_INDEX); //OK
        }
    }
}
