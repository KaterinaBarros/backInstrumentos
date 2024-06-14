package tp.react.back.tpreactback.repository;


import org.springframework.data.jpa.repository.Query;
import tp.react.back.tpreactback.modelo.Instrumento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IInstrumentoRepository extends JpaRepository <Instrumento,Long> {

    Instrumento findById(long id);

    @Query("SELECT new Instrumento (d.instrumento, d.precio) " +
            "FROM Instrumento d " +
            "GROUP BY d.instrumento, d.precio " +
            "ORDER BY d.instrumento")
    List<Instrumento> findDatosChart();

}
