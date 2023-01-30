package cliente.servicos;

import compartilhado.ContextoAutenticado;
import io.reactivex.rxjava3.subjects.ReplaySubject;

import java.util.Optional;

public class ProdutosService {
    public static ProdutosService instance = new ProdutosService();
    private ProdutosService() {}
}
