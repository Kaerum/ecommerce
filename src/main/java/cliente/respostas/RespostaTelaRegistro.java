package cliente.respostas;

import servidor.usuarios.TipoUsuario;

public record RespostaTelaRegistro(TipoUsuario tipoUsuario, String usuario) {}
