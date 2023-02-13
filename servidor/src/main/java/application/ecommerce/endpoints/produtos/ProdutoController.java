package application.ecommerce.endpoints.produtos;

import application.ecommerce.db.busca.Busca;
import application.ecommerce.db.busca.BuscaSpecification;
import application.ecommerce.exceptions.NotFoundException;
import application.ecommerce.models.produto.Produto;
import application.ecommerce.models.produto.ProdutoRepository;
import application.ecommerce.security.Roles;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController()
@RequestMapping(value = "produtos")
public class ProdutoController {

    @Autowired
    private ProdutoRepository repository;

    @GetMapping()
    List<Produto> todos() {
        return repository.findAll();
    }

    @GetMapping(value = "/buscar", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public Page<Produto> buscar(@RequestBody Busca busca) {
        BuscaSpecification<Produto> specification = new BuscaSpecification<>(busca);
        Pageable pageable = BuscaSpecification.getPageable(busca.getPagina(), busca.getTamanho());
        return repository.findAll(specification, pageable);
    }

    @PostMapping()
    @RolesAllowed(Roles.ADMIN)
    Produto novo(@RequestBody Produto valor) {
        return repository.save(valor);
    }

    @GetMapping("/{id}")
    Produto um(@PathVariable Long id) throws NotFoundException {

        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException(id));
    }

    @PutMapping("/{id}")
    @RolesAllowed(Roles.ADMIN)
    Produto replace(@RequestBody Produto valor, @PathVariable Long id) {
        valor.setId(id);
        return repository.save(valor);
    }

    @DeleteMapping("/{id}")
    @RolesAllowed(Roles.ADMIN)
    void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
