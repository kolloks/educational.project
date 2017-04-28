package web.product.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import web.product.DAO.NotFoundProductException;
import web.product.DAO.Products;
import web.product.DAO.ProductsDAO;
import web.product.models.Product;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static web.product.Attributes.*;

public class ProductController extends HttpServlet{

    private static final String MODEL_TO_VIEW = "product";
    private static Logger logger = LogManager.getLogger();
    private ProductsDAO products = new Products();

    public void doGet (HttpServletRequest request, HttpServletResponse response) throws IOException {
        String param = request.getParameter(PARAM_ID);
        if (param!=null){
            try{
                Integer id = Integer.parseInt(param);
                Product product = products.productById(id);
                request.setAttribute(MODEL_TO_VIEW, product);
                logger.trace("Forward "+PAGE_INDEX);
                request.getRequestDispatcher(PAGE_OK).forward(request, response);
                return; //OK
            } catch (NumberFormatException | ServletException | NotFoundProductException e){
                logger.error(e);
            }
        }
        logger.warn("BadPage! param.id="+param+". SendRedirect"+PAGE_ERROR);
        response.sendRedirect(PAGE_ERROR);
    }
}
