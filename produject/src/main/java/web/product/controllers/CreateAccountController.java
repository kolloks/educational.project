package web.product.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import web.product.DAO.AddUserException;
import web.product.DAO.NotFoundUserException;
import web.product.actions.DBWorker;
import web.product.models.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

import static web.product.Attributes.*;

public class CreateAccountController extends HttpServlet{

    private static Logger logger = LogManager.getLogger();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            logger.debug("Forward on "+PAGE_REGISTRATION);
            req.getRequestDispatcher(PAGE_REGISTRATION).forward(req,resp);
        } catch (ServletException e) {
            logger.error(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        User user = new User();
        user.setLogin(req.getParameter(PARAM_LOGIN));
        user.setPassword(req.getParameter(PARAM_PASSWORD));
        user.setAge(Date.valueOf(req.getParameter(PARAM_AGE)));
        user.setEmail(req.getParameter(PARAM_EMAIL));
        DBWorker dbWorker = new DBWorker();
        try {
            dbWorker.addUser(user);
            user = dbWorker.selectUserByLoginAndPassword(user.getLogin(), user.getPassword());
            req.getSession().setAttribute(USER, user);
            logger.debug("Successful registration. User id = " + user.getId());
        } catch (NotFoundUserException | AddUserException e) {
            logger.warn("CreateAccountException!" + e);
        } finally {
            try {
                dbWorker.closeConnection();
            } catch (SQLException e) {
                logger.error(e);
            }
        }
        //Cookie login = new Cookie(PARAM_LOGIN, user.getLogin());
        //Cookie password = new Cookie(PARAM_PASSWORD, user.getPassword());
        //resp.addCookie(login);
        //resp.addCookie(password);
        logger.trace("SendRedirect "+PAGE_INDEX);
        resp.sendRedirect(PAGE_INDEX);
    }
}
