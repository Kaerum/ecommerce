package cliente.servicos;

import servidor.ServidorImpl;

public class ServidorService {
    public ServidorImpl servidor = new ServidorImpl();
    public static ServidorService instance = new ServidorService();
    private ServidorService() {}
}
