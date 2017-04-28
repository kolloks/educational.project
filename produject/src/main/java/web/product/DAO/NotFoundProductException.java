package web.product.DAO;


public class NotFoundProductException extends ProductException {
    public NotFoundProductException() {
        System.out.println("NotFoundProductException!");
    }

    NotFoundProductException(int id){
        System.out.println("Product by "+id+" not found!");
    }
}
