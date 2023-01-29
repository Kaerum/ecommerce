package servidor;

import interfaces.ContextoAutenticado;
import interfaces.RespostaServidor;
import interfaces.usuario.ColecaoAutenticada;
import interfaces.usuario.TipoUsuario;
import servidor.banco.colecao.Colecao;
import servidor.banco.sessao.Permissao;
import servidor.banco.sessao.Sessao;

import java.util.Set;
import java.util.UUID;

public class ContextoAutenticadoImpl implements ContextoAutenticado {
    private Sessao sessao;
    ContextoAutenticadoImpl(Sessao sessao) {
        this.sessao = sessao;
    }
    @Override
    public <T> RespostaServidor<ColecaoAutenticada<T>> colecao(Class<T> classe) {
        return null;
    }

    @Override
    public Set<Permissao> permissoesPara(Class<?> classe) {
        return null;
    }

    @Override
    public TipoUsuario tipoUsuario() {
        return null;
    }
}
