package cliente.servicos;

import compartilhado.ContextoAutenticado;
import compartilhado.RespostaServidor;
import compartilhado.Servidor;
import compartilhado.TipoRespostaServidor;
import compartilhado.usuario.TipoUsuario;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.subjects.ReplaySubject;

import java.util.Optional;
import java.util.UUID;

public class AuthenticationService {
    public static AuthenticationService instance = new AuthenticationService();
    private ReplaySubject<Optional<ContextoAutenticado>> flowContextoAutenticado = ReplaySubject.createWithSize(1);
    private AuthenticationService() {}

    public RespostaServidor<ContextoAutenticado> logar(String usuario) {
        this.deslogar();
        var resposta = ServidorService.instance.servidor.logar(usuario);
        if (resposta.tipo() == TipoRespostaServidor.Sucesso && resposta.valor().isPresent()) {
            flowContextoAutenticado.onNext(Optional.of(resposta.valor().get()));
        }
        return resposta;
    }

    public void deslogar() {
        flowContextoAutenticado.onNext(Optional.empty());
    }

    public RespostaServidor<UUID> registar(String usuario, TipoUsuario tipoUsuario) {
        return ServidorService.instance.servidor.registrar(usuario, tipoUsuario);
    }

    public Disposable onContextoAutenticadoChanges(Consumer<Optional<ContextoAutenticado>> consumer) {
        return flowContextoAutenticado.subscribe(consumer);
    }
}
