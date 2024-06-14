package tp.react.back.tpreactback.repository;

import tp.react.back.tpreactback.modelo.Usuario ;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUsuarioRepository extends JpaRepository<Usuario, Long> {
    Usuario findByNombreUsuario(String nombreUsuario);

    Optional<Usuario> findByNombreUsuarioAndClave(String nombreUsuario, String clave);

}
