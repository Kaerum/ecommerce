package interfaces;

import servidor.banco.colecao.Colecao;
import servidor.banco.sessao.Permissao;

import java.util.Optional;

public record RespostaServidor<T> (TipoRespostaServidor tipo, Optional<T> valor, Optional<String> mensagemDeErro) {
    public static <T> RespostaServidor<T> erro(String mensagemDeErro, Object... args) {
        return new RespostaServidor<T>(
                TipoRespostaServidor.Erro,
                Optional.empty(),
                Optional.of(String.format(mensagemDeErro, args))
        );
    }

    public static <T> RespostaServidor<T> sucesso(Optional<T> valor) {
        return new RespostaServidor<T>(
                TipoRespostaServidor.Sucesso,
                valor,
                Optional.empty()
        );
    }

    public static <T> RespostaServidor<T> sucesso(T valor) {
        return new RespostaServidor<T>(
                TipoRespostaServidor.Sucesso,
                Optional.ofNullable(valor),
                Optional.empty()
        );
    }

    public static String faltaPermissaoColecao(Permissao permissao, String colecao) {
        return String.format(
                "Usuário não tem a permissão %s para a coleção %s",
                permissao.toString(),
                colecao
        );
    }
}
