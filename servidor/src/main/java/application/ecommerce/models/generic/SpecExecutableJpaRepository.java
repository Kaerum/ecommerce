package application.ecommerce.models.generic;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface SpecExecutableJpaRepository<T, K> extends JpaRepository<T, K>, JpaSpecificationExecutor<T> {
}
