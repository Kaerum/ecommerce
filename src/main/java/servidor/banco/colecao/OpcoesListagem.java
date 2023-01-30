package servidor.banco.colecao;

import lombok.Builder;
import lombok.Getter;

import java.util.OptionalInt;

@Builder
public class OpcoesListagem {
    @Builder.Default @Getter
    private final OptionalInt limite = OptionalInt.empty();
    @Builder.Default @Getter private final OptionalInt pular = OptionalInt.empty();
}
