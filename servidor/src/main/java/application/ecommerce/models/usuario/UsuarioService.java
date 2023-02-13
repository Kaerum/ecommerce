package application.ecommerce.models.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired private UsuarioRepository usuarioRepository;

    public Optional<Usuario> getByName(String name) {
        return this.usuarioRepository.findOne(Example.of(Usuario.builder().nome(name).build()));
    }

    public Optional<Usuario> findOne(Example<Usuario> example) {
        return this.usuarioRepository.findOne(example);
    }
}
