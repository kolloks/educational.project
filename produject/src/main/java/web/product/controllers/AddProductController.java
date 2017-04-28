package web.product.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import web.product.DAO.NotFoundProductException;
import web.product.DAO.Products;
import web.product.DAO.ProductsDAO;
import web.product.models.Product;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import static java.util.Collections.singletonMap;
import static java.util.Collections.unmodifiableMap;
import static web.product.Attributes.*;

public class AddProductController extends HttpServlet {

    private static Logger logger = LogManager.getLogger();
    private ProductsDAO products = new Products();

    public void doGet (HttpServletRequest request, HttpServletResponse response) throws IOException {
        String param = request.getParameter(PARAM_ID);
        if (param!=null){
            try{
                Integer id = Integer.parseInt(param);
                Product product = products.productById(id);
                HttpSession session = request.getSession(true);
                Map<Product, Integer> oldBasket = (Map<Product, Integer>) session.getAttribute(PRODUCTS_IN_BASKET);
                if (oldBasket==null){
                    session.setAttribute(PRODUCTS_IN_BASKET, singletonMap(product, 1));
                    logger.trace("New PRODUCTS_IN_BASKET.");
                } else {
                    Map<Product, Integer> newBasket = new LinkedHashMap<>(oldBasket);
                    if (!newBasket.containsKey(product)) newBasket.put(product, 1);
                    else newBasket.put(product, newBasket.get(product)+1);
                    session.setAttribute(PRODUCTS_IN_BASKET, unmodifiableMap(newBasket));
                    logger.trace("PRODUCTS_IN_BASKET++.");
                }
                logger.debug("sendRedirect product?id="+id);
                response.sendRedirect("product?id="+id);
                return; //OK
            } catch (NumberFormatException | NullPointerException | NotFoundProductException e){
                logger.error(e);
            }
        }
        logger.warn("BadPage! param.id="+param+". SendRedirect"+PAGE_ERROR);
        response.sendRedirect(PAGE_ERROR);
    }
}
