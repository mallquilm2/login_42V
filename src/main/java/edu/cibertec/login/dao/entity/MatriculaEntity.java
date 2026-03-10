package edu.cibertec.login.dao.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "matricula")
public class MatriculaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idMatricula;
    @Column(name = ("fechaMat"))
    private Date fecMat;

    @OneToOne
    @JoinColumn(name = "usuario", updatable = false, nullable = false)
    private UsuarioEntity usuario;

    @OneToOne
    @JoinColumn(name = "idCurso", updatable = false, nullable = false)
    private CursoEntity curso;

    private int estado;

}
