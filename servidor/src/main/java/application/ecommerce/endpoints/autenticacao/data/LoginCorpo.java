package application.ecommerce.endpoints.autenticacao.data;

import lombok.Data;

import java.io.Serializable;

@Data
public class LoginCorpo implements Serializable {
    private static final long serialVersionUID = -1981285753318839826L;
    private String nome;
    private String senha;
}