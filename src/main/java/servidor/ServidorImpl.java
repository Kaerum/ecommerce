package servidor;

import interfaces.ContextoAutenticado;
import interfaces.Servidor;
import interfaces.RespostaServidor;
import interfaces.usuario.TipoUsuario;
import servidor.banco.Banco;
import servidor.banco.BancoException;

import java.util.UUID;

public class ServidorImpl implements Servidor {

    private Banco banco = new Banco();

    @Override
    public RespostaServidor<ContextoAutenticado> logar(String usuario) {
        try {
            var sessao = banco.logar(usuario);
            return RespostaServidor.sucesso(new ContextoAutenticadoImpl(sessao));
        } catch (BancoException exception) {
            return RespostaServidor.erro(exception.getMessage());
        }
    }

    @Override
    public RespostaServidor<UUID> registrar(String usuario, TipoUsuario tipo) {
        return null;
    }

//    public RespostaServidor<UUID> logar(String usuario) {
//        try {
//
//        } catch (BancoException exception) {
//            return RespostaServidor.erro()
//        }
//    }
//
//    public RespostaServidor<String> registrar(String usuario, TipoUsuario tipo) {
//        var usuarios = gerenciadorDeUsuarios.listar(entry -> entry.getValue().getNome() == usuario, null, Optional.of(1));
//        if (usuarios.size() > 0) {
//            return RespostaServidor.erro("Usuário %s já existe, por favor usar outro nome", usuario);
//        }
//        switch (tipo) {
//            case Cliente -> {}
//            case Administrador -> {}
//        }
//        return null;
//    }
}
