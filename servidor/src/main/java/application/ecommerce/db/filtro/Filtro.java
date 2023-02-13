package application.ecommerce.db.filtro;

import application.ecommerce.db.Operador;
import application.ecommerce.db.TipoCampo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Filtro implements Serializable {
    private static final long serialVersionUID = -6958733844781154978L;
    private String chave;
    private Operador operador;
    private TipoCampo tipoCampo;
    private transient Object valor;
    private transient Object valorAte;
    private transient List<Object> valores;
}
