package web.product.DAO;

public class NotFoundUserException extends UserException{
    public NotFoundUserException() {
        System.out.println("NotFoundUserException!");
    }
    public NotFoundUserException(int id){
        System.out.println("User by "+id+" not found!");
    }
}
