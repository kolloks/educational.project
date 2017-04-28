package web.product.DAO;

import web.product.models.Product;

import java.util.List;

public interface ProductsDAO {
    Product productById(int id) throws NotFoundProductException;
    List<Product> selectAll();
}

