package servidor;

import compartilhado.ContextoAutenticado;
import compartilhado.Servidor;
import compartilhado.RespostaServidor;
import compartilhado.usuario.TipoUsuario;
import servidor.banco.Banco;
import servidor.banco.BancoException;

import java.util.UUID;

public class ServidorImpl implements Servidor {

    private Banco banco = new Banco();

    @Override
    public RespostaServidor<ContextoAutenticado> logar(String usuario) {
        try {
            var sessao = banco.logar(usuario);
            return RespostaServidor.sucesso(new ContextoAutenticadoImpl(sessao,banco));
        } catch (BancoException exception) {
            return RespostaServidor.erro(exception.getMessage());
        }
    }

    @Override
    public RespostaServidor<UUID> registrar(String nome, TipoUsuario tipo) {
        try {
            return RespostaServidor.sucesso(banco.registrar(nome, tipo));
        } catch (BancoException exception) {
            return RespostaServidor.erro(exception.getMessage());
        }
    }
}
