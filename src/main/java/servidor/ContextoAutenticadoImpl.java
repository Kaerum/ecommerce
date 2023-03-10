package servidor;

import compartilhado.ContextoAutenticado;
import compartilhado.RespostaServidor;
import compartilhado.usuario.ColecaoAutenticada;
import compartilhado.usuario.TipoUsuario;
import servidor.banco.Banco;
import servidor.banco.sessao.Permissao;
import servidor.banco.sessao.Sessao;
import servidor.banco.usuarios.Usuario;

import java.util.Set;

public class ContextoAutenticadoImpl implements ContextoAutenticado {
    private Sessao sessao;
    private Banco banco;
    ContextoAutenticadoImpl(Sessao sessao, Banco banco) {
        this.sessao = sessao;
        this.banco = banco;
    }
    @Override
    public <T> RespostaServidor<ColecaoAutenticada<T>> colecao(Class<T> classe) {
        return RespostaServidor.sucesso(new ColecaoAutenticadaImpl<>(banco.colecao(classe), sessao.getPermissoesPara(classe)));
    }

    @Override
    public RespostaServidor<Set<Permissao>> permissoesPara(Class<?> classe) {
        return RespostaServidor.sucesso(sessao.getPermissoesPara(classe));
    }

    @Override
    public RespostaServidor<TipoUsuario> tipoUsuario() {
        return RespostaServidor.sucesso(sessao.getTipoUsuario());
    }

    @Override
    public String getNomeUsuario() {
        return sessao.getNome();
    }
}
