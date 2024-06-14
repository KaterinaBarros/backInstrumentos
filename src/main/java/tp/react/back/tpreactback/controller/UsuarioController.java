package tp.react.back.tpreactback.controller;

import tp.react.back.tpreactback.modelo.Usuario;
import tp.react.back.tpreactback.services.UsuarioService ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;


    @GetMapping
    public List<Usuario> getAllUsuarios() {
        return usuarioService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getUsuarioById(@PathVariable Long id) {
        Optional<Usuario> usuario = usuarioService.findById(id);
        return usuario.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/buscar/{nombre}")
    public Usuario buscarPorNombre(@PathVariable String nombre) {
        return usuarioService.buscarPorNombre(nombre);
       // return usuarioOpt.orElse(null); // Maneja la lógica de retorno cuando no se encuentra el usuario
    }

    @GetMapping("/buscar/{id}/{clave}")
    public ResponseEntity<?> getDatosUsuarioyClave(@PathVariable String id, @PathVariable String clave) {
        Optional<Usuario> usuario = usuarioService.buscarPorNombreYClave(id, clave);
        if (usuario.isPresent()) {
            return ResponseEntity.ok(usuario.get());
        } else {
            return ResponseEntity.status(404).body("Usuario no encontrado");
        }
    }



    // Método para encriptar la clave usando SHA-256
//    private String encriptarClave(String clave) throws NoSuchAlgorithmException {
//        MessageDigest md = MessageDigest.getInstance("SHA-256");
//        byte[] hash = md.digest(clave.getBytes());
//        StringBuilder sb = new StringBuilder();
//        for (byte b : hash) {
//            sb.append(String.format("%02x", b));
//        }
//        return sb.toString();
//    }
//
//
//    @GetMapping("/buscar/{id}/{clave}")
//    public ResponseEntity<?> getDatosUsuarioyClave(@PathVariable String id, @PathVariable String clave) {
//        try {
//            // Encripta la clave proporcionada por el usuario
//            String claveEncriptada = encriptarClave(clave);
//
//            // Busca al usuario por id y clave encriptada
//            Optional<Usuario> usuario = usuarioService.buscarPorIdYClaveEncriptada(id, claveEncriptada);
//
//            if (usuario.isPresent()) {
//                return ResponseEntity.ok(usuario.get());
//            } else {
//                return ResponseEntity.status(404).body("Usuario no encontrado");
//            }
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//            return ResponseEntity.status(500).body("Error al encriptar la clave");
//        }
//    }


//
//    @PostMapping
//    public Usuario createUsuario(@RequestBody Usuario usuario) {
//        return usuarioService.save(usuario);
//    }

//

    @PostMapping
    public ResponseEntity<?> createUsuario(@RequestBody Usuario usuario) {
        try {
            Usuario savedUsuario = usuarioService.save(usuario);
            return ResponseEntity.ok(savedUsuario);
        } catch (NoSuchAlgorithmException e) {
            return ResponseEntity.status(500).body("Error en la encriptación de la clave");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> updateUsuario(@PathVariable Long id, @RequestBody Usuario usuarioDetails) throws NoSuchAlgorithmException {
        Optional<Usuario> usuarioOptional = usuarioService.findById(id);
        if (!usuarioOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Usuario usuario = usuarioOptional.get();
        usuario.setNombreUsuario(usuarioDetails.getNombreUsuario());
        usuario.setClave(usuarioDetails.getClave());
        usuario.setRol(usuarioDetails.getRol());

        Usuario updatedUsuario = usuarioService.save(usuario);
        return ResponseEntity.ok(updatedUsuario);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable Long id) {
        usuarioService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
