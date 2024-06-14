package tp.react.back.tpreactback.modelo;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
public class PedidoDetalle extends EntityId{

    private int cantidad;
    private double subtotal;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "instrumento_id")
    private Instrumento instrumento;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "pedido_id")
    @JsonBackReference
    private Pedido pedido;

    public PedidoDetalle() {

    }


    @PrePersist
    @PreUpdate
    public void calcularSubtotal() {
        if (instrumento != null  && cantidad > 0) {
            subtotal = instrumento.getPrecio() * cantidad;
        }
    }

    public PedidoDetalle(Instrumento i, int cantidad){
        this.cantidad= cantidad;
        this.instrumento=i;
    }

}