package application.ecommerce.models.produto;

import application.ecommerce.models.generic.ApplicationEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Produto implements ApplicationEntity<Long> {
    private @Id @GeneratedValue Long id;
    private String nome;
    private String descricao;
    private String categoria;
    private String marca;
    private double preco;
    private double peso;
}
