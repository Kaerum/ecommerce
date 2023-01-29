package servidor.banco.usuarios;

import interfaces.usuario.TipoUsuario;

public record Usuario(String nome, TipoUsuario tipo) {}
