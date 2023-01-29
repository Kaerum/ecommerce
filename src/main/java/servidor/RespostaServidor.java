package servidor;

import java.util.Optional;

public record RespostaServidor<T> (TipoRespostaServidor tipo, Optional<T> valor, Optional<String> mensagemDeErro) {
    static <T> RespostaServidor<T> erro(String mensagemDeErro, Object... args) {
        return new RespostaServidor<T>(
                TipoRespostaServidor.Erro,
                Optional.empty(),
                Optional.of(String.format(mensagemDeErro, args))
        );
    }

    static <T> RespostaServidor<T> sucesso(Optional<T> valor) {
        return new RespostaServidor<T>(
                TipoRespostaServidor.Sucesso,
                valor,
                Optional.empty()
        );
    }

    static <T> RespostaServidor<T> sucesso(T valor) {
        return new RespostaServidor<T>(
                TipoRespostaServidor.Sucesso,
                Optional.ofNullable(valor),
                Optional.empty()
        );
    }
}
