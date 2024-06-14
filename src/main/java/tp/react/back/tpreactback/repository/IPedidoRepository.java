package tp.react.back.tpreactback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tp.react.back.tpreactback.modelo.Instrumento;
import tp.react.back.tpreactback.modelo.Pedido;

import java.util.List;

@Repository
public interface IPedidoRepository extends JpaRepository<Pedido, Long> {
    @Query("SELECT new Instrumento (d.instrumento, d.precio) " +
            "FROM Instrumento d " +
            "GROUP BY d.instrumento, d.precio " +
            "ORDER BY d.instrumento")
    List<Instrumento> findDatosChart();


}
