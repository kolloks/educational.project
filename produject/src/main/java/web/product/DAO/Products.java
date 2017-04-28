package web.product.DAO;

import web.product.models.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public class Products implements ProductsDAO{

    private final Map<Integer, Product> mapProducts = new ConcurrentHashMap<>();
    private Integer productId;
    private Product product;

    public Products(){
        this.mapProducts.put(1, new Product(1,"Bread"));
        this.mapProducts.put(2, new Product(2,"Lard"));
        this.mapProducts.put(3, new Product(3,"Potatoes"));
    }

    @Override
    public Product productById(int id) throws NotFoundProductException {
        if (mapProducts.containsKey(id)) {
            return mapProducts.get(id);
        }
        throw new NotFoundProductException(id);
    }

    @Override
    public List<Product> selectAll() {
        List<Product> productList = new ArrayList<>();
        for (Map.Entry<Integer, Product> mm : getMapProducts().entrySet()){
            productList.add(mm.getValue());
        }
        return productList;
    }

    public Map<Integer, Product> getMapProducts() {
        return mapProducts;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Products products = (Products) o;
        return Objects.equals(mapProducts, products.mapProducts) &&
                Objects.equals(productId, products.productId) &&
                Objects.equals(product, products.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mapProducts, productId, product);
    }
}
