package tp.react.back.tpreactback.modelo;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Categoria extends EntityId{

    private String denominacion;

    @Column(unique = true)
    private long codigo;



}
