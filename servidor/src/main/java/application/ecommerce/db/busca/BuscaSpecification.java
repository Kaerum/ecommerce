package application.ecommerce.db.busca;

import application.ecommerce.db.filtro.Filtro;
import application.ecommerce.db.sort.Sort;
import jakarta.persistence.criteria.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@AllArgsConstructor
public class BuscaSpecification<T> implements Specification<T> {
    private static final long serialVersionUID = 8866595156136922783L;

    private final transient Busca pedido;

    @Override
    public Predicate toPredicate(Root<T> raiz, CriteriaQuery<?> query, CriteriaBuilder cb) {
        Predicate predicado = cb.equal(cb.literal(Boolean.TRUE), Boolean.TRUE);

        for (Filtro filter : this.pedido.getFiltros()) {
            log.info("Filter: {} {} {}", filter.getChave(), filter.getOperador().toString(), filter.getValor());
            predicado = filter.getOperador().construir(raiz, cb, filter, predicado);
        }

        List<Order> orders = new ArrayList<>();
        for (Sort sort : this.pedido.getSorts()) {
            orders.add(sort.getDirecao().build(raiz, cb, sort));
        }

        query.orderBy(orders);
        return predicado;
    }

    public static Pageable getPageable(Integer pagina, Integer tamanho) {
        return PageRequest.of(Objects.requireNonNullElse(pagina, 0), Objects.requireNonNullElse(tamanho, 100));
    }
}
