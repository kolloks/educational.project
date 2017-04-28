package web.product.filters;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import web.product.models.User;
import web.product.models.UserRole;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static web.product.Attributes.USER;

public class UserFilter implements Filter{

    private UserRole isUserInRole = new UserRole();
    private static Logger logger = LogManager.getLogger();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        if (req.getSession(false)!=null && req.getSession().getAttribute(USER)!=null){
            isUserInRole.setUserInRole(true);
            logger.debug("User.id="+((User)req.getSession().getAttribute(USER)).getId());
            req.setAttribute("UserRole", isUserInRole);
        } else {
            isUserInRole.setUserInRole(false);
            logger.trace("Guest.ip="+req.getLocalName()+". From "+req.getLocale().getCountry());
            req.setAttribute("UserRole", isUserInRole);
        }
        /*if (req.getSession().getAttribute(USER)==null){
            User user = new User(0, GUEST);
            req.getSession().setAttribute(USER, user);
        }*/

        filterChain.doFilter(req, resp);
    }

    @Override
    public void destroy() {

    }
}
