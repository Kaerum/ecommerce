package compartilhado;

import compartilhado.usuario.ColecaoAutenticada;
import compartilhado.usuario.TipoUsuario;
import servidor.banco.sessao.Permissao;
import servidor.banco.usuarios.Usuario;

import java.util.Set;

public interface ContextoAutenticado {
    public <T> RespostaServidor<ColecaoAutenticada<T>> colecao(Class<T> classe);
    public RespostaServidor<Set<Permissao>> permissoesPara(Class<?> classe);

    public RespostaServidor<TipoUsuario> tipoUsuario();

    public String getNomeUsuario();

}
