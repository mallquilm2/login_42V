package edu.cibertec.login.dao.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Arrays;
import java.util.Base64;

@Entity
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

    public UsuarioEntity() {
    }

    public UsuarioEntity(int id, String usuario, String clave, String nombreCompleto, byte[] foto) {
        this.id = id;
        this.usuario = usuario;
        this.clave = clave;
        this.nombreCompleto = nombreCompleto;
        this.foto = foto;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public String getFotoBase64(){
        String rpta = null;
        if(foto != null && foto.length > 0)
            rpta = Base64.getEncoder().encodeToString(foto);
        return rpta;
    }

    @Override
    public String toString() {
        return "UsuarioEntity{" +
                "id=" + id +
                ", usuario='" + usuario + '\'' +
                ", clave='" + clave + '\'' +
                ", nombreCompleto='" + nombreCompleto + '\'' +
                ", foto=" + Arrays.toString(foto) +
                '}';
    }
}
