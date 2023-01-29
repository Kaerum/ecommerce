package interfaces;

import interfaces.usuario.ColecaoAutenticada;
import interfaces.usuario.TipoUsuario;
import servidor.banco.sessao.Permissao;

import java.util.Set;

public interface ContextoAutenticado {
    public <T> RespostaServidor<ColecaoAutenticada<T>> colecao(Class<T> classe);
    public RespostaServidor<Set<Permissao>> permissoesPara(Class<?> classe);

    public RespostaServidor<TipoUsuario> tipoUsuario();
}
