package application.ecommerce.db.busca;

import application.ecommerce.db.filtro.Filtro;
import application.ecommerce.db.sort.Sort;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Busca implements Serializable {
    private static final long serialVersionUID = -3061292221120607093L;

    private List<Filtro> filtros;

    private List<Sort> sorts;

    private Integer pagina;

    private Integer tamanho;

    public List<Filtro> getFiltros() {
        if (Objects.isNull(this.filtros)) return new ArrayList<>();
        return this.filtros;
    }

    public List<Sort> getSorts() {
        if (Objects.isNull(this.sorts)) return new ArrayList<>();
        return this.sorts;
    }
}
