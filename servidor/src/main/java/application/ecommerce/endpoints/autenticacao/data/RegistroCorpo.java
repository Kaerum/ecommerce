package application.ecommerce.endpoints.autenticacao.data;

import lombok.Data;

import java.io.Serializable;

@Data
public
class RegistroCorpo implements Serializable {

    public enum AutoridadeUsuario {
        USER,
        ADMIN
    }
    public static final long serialVersionUID = -4617536529477623618L;

    private String nome;
    private String senha;
    private AutoridadeUsuario autoridadeUsuario;
    private boolean isCnpj;
}