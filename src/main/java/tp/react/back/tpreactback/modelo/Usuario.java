package tp.react.back.tpreactback.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Data;
import org.springframework.http.ResponseEntity;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Entity
@Data
public class Usuario extends EntityId{
    @Column(nullable = false, unique = true)
    private String nombreUsuario;

    @Column(nullable = false)
    private String clave;

    @Column(nullable = false)
    private String rol;

    public static final List<String> ROLES_VALIDOS = Arrays.asList("Admin", "Operador", "Visor");

    // Constructor por defecto
    public Usuario() {}

    // Constructor
    public Usuario(String nombreUsuario, String clave, String rol) throws NoSuchAlgorithmException {
        this.nombreUsuario = nombreUsuario;
        setClave(clave);
        setRol(rol);
    }

    // Getters y Setters
    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) throws NoSuchAlgorithmException {
        this.clave = clave;
//        this.clave = encriptarClave(clave);
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        if (ROLES_VALIDOS.contains(rol)) {
            this.rol = rol;
        } else {
            throw new IllegalArgumentException("Rol no válido. Los roles válidos son: " + ROLES_VALIDOS);
        }
    }

     //Método para encriptar la clave
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
//    @Override
//    public String toString() {
//        return "Usuario{" +
//                "id=" + getId() +
//                ", nombreUsuario='" + nombreUsuario + '\'' +
//                ", clave='" + clave + '\'' +
//                ", rol='" + rol + '\'' +
//                '}';
//    }

}
