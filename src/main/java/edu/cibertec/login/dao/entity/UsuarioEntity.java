package edu.cibertec.login.dao.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Arrays;
import java.util.Base64;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "usuario")
public class UsuarioEntity {
    @Transient
    private int id;

    @Id
    @Size(min=3, max = 20, message = "El usuario debe tener entre 3 y 30 caracteres")
    private String usuario;

    @NotNull(message = "La clave no puede ser nula")
    @NotBlank(message = "La clave no puede estar en blanco")
    @Column(nullable = false)
    private String clave;
    @Column(name = "nombreCompleto")
    private String nombreCompleto;

    @Column
    private byte[] foto;

    public String getFotoBase64(){
        String rpta = null;
        if(foto != null && foto.length > 0)
            rpta = Base64.getEncoder().encodeToString(foto);
        return rpta;
    }

}
