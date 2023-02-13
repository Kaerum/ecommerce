package application.ecommerce.db.sort;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Order;
import jakarta.persistence.criteria.Root;

public enum OrdemSort {
    ASC {
        public <T> Order build(Root<T> raiz, CriteriaBuilder cb, Sort pedido) {
            return cb.asc(raiz.get(pedido.getChave()));
        }
    },
    DESC {
        public <T> Order build(Root<T> raiz, CriteriaBuilder cb, Sort pedido) {
            return cb.desc(raiz.get(pedido.getChave()));
        }
    };

    public abstract <T> Order build(Root<T> raiz, CriteriaBuilder cb, Sort pedido);

}
