package tp.react.back.tpreactback.services;

import tp.react.back.tpreactback.modelo.Usuario;
import tp.react.back.tpreactback.repository.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private IUsuarioRepository usuarioRepository;

    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> findById(Long id) {
        return usuarioRepository.findById(id);
    }

    public Usuario buscarPorNombre(String nombre) {
        return usuarioRepository.findByNombreUsuario(nombre);
    }

    public Optional<Usuario> buscarPorNombreYClave(String nombreUsuario, String clave) {
        return usuarioRepository.findByNombreUsuarioAndClave(nombreUsuario, clave);
    }

    public Usuario save(Usuario usuario) throws NoSuchAlgorithmException {
        usuario.setClave(usuario.getClave()); // Encripta la clave
        return usuarioRepository.save(usuario);
    }

    public void deleteById(Long id) {
        usuarioRepository.deleteById(id);
    }

    public Optional<Usuario> buscarPorIdYClaveEncriptada(String id, String claveEncriptada) {
        return usuarioRepository.findByNombreUsuarioAndClave(id, claveEncriptada);
    }

}
