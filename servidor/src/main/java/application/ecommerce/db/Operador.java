package application.ecommerce.db;

import application.ecommerce.db.filtro.Filtro;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
public enum Operador {
    EQUAL {
        public <T> Predicate construir(Root<T> raiz, CriteriaBuilder cb, Filtro pedido, Predicate predicado) {
            Object valor = pedido.getTipoCampo().converter(pedido.getValor().toString());
            Expression<?> chave = raiz.get(pedido.getChave());
            return cb.and(cb.equal(chave, valor), predicado);
        }
    },

    NOT_EQUAL {
        public <T> Predicate construir(Root<T> raiz, CriteriaBuilder cb, Filtro pedido, Predicate predicado) {
            Object valor = pedido.getTipoCampo().converter(pedido.getValor().toString());
            Expression<?> chave = raiz.get(pedido.getChave());
            return cb.and(cb.notEqual(chave, valor), predicado);
        }
    },

    LIKE {
        public <T> Predicate construir(Root<T> raiz, CriteriaBuilder cb, Filtro pedido, Predicate predicado) {
            Expression<String> chave = raiz.get(pedido.getChave());
            return cb.and(cb.like(cb.upper(chave), "%" + pedido.getValor().toString().toUpperCase() + "%"), predicado);
        }
    },

    IN {
        public <T> Predicate construir(Root<T> raiz, CriteriaBuilder cb, Filtro pedido, Predicate predicado) {
            List<Object> valores = pedido.getValores();
            CriteriaBuilder.In<Object> in = cb.in(raiz.get(pedido.getChave()));
            for (Object valor : valores) {
                in.value(pedido.getTipoCampo().converter(valor.toString()));
            }
            return cb.and(in, predicado);
        }
    },

    BETWEEN {
        public <T> Predicate construir(Root<T> raiz, CriteriaBuilder cb, Filtro pedido, Predicate predicado) {
            Object valor = pedido.getTipoCampo().converter(pedido.getValor().toString());
            Object valorAte = pedido.getTipoCampo().converter(pedido.getValorAte().toString());
            if (pedido.getTipoCampo() == TipoCampo.DATE) {
                LocalDateTime dataInicial = (LocalDateTime) valor;
                LocalDateTime dataFinal = (LocalDateTime) valorAte;
                Expression<LocalDateTime> chave = raiz.get(pedido.getChave());
                return cb.and(cb.and(cb.greaterThanOrEqualTo(chave, dataInicial), cb.lessThanOrEqualTo(chave, dataFinal)), predicado);
            }

            if (pedido.getTipoCampo() != TipoCampo.CHAR && pedido.getTipoCampo() != TipoCampo.BOOLEAN) {
                Number comeco = (Number) valor;
                Number fim = (Number) valorAte;
                Expression<Number> key = raiz.get(pedido.getChave());
                return cb.and(cb.and(cb.ge(key, comeco), cb.le(key, fim)), predicado);
            }

            log.info("Can not use between for {} field type.", pedido.getTipoCampo());
            return predicado;
        }
    };

    public abstract <T> Predicate construir(Root<T> raiz, CriteriaBuilder cb, Filtro pedido, Predicate predicado);
}
