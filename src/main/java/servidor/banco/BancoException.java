package servidor.banco;

public class BancoException extends Exception {
    public BancoException(String message, Object... args) {
        super(String.format(message, args));
    }
}
