package tp.react.back.tpreactback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tp.react.back.tpreactback.modelo.PedidoDetalle;

import java.util.List;

@Repository
public interface IPedidoDetalleRepository extends JpaRepository<PedidoDetalle, Long> {

//    @Query("SELECT new PedidoDetalle (d.instrumento, d.cantidad) " +
//            "FROM PedidoDetalle d " +
//            "GROUP BY d.instrumento,")
//    List<PedidoDetalle> findDatosChartPedidoPie();


    @Query("SELECT d.instrumento, SUM(d.cantidad) " +
            "FROM PedidoDetalle d " +
            "GROUP BY d.instrumento")
    List<Object[]> findDatosChartPedidoPie();

//    @Query("SELECT d.f, SUM(d.valor) " +
//            "FROM PedidoDetalle d " +
//            "GROUP BY d.fecha")
//    List<Object[]> findDatosChartPedidoLine();

}
