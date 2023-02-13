package application.ecommerce.exceptions;

public class NotFoundException extends Exception {
    public <K> NotFoundException(K id) {
        super("Entidade não existe " + id);
    }
}
