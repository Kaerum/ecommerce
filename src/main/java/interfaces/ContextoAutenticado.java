package interfaces;

import interfaces.usuario.ColecaoAutenticada;
import servidor.banco.colecao.Colecao;
import servidor.banco.sessao.Permissao;

import java.util.Set;

public interface ContextoAutenticado {
    public <T> RespostaServidor<ColecaoAutenticada<T>> colecao(Class<T> classe);
    public Set<Permissao> permissoesPara(Class<?> classe);
}
