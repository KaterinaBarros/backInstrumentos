package tp.react.back.tpreactback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tp.react.back.tpreactback.modelo.Categoria;
@Repository

public interface ICategoriaRepository extends JpaRepository<Categoria, Long> {
    Categoria findByCodigo(long codigo);
}
