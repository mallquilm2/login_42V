package edu.cibertec.login.dao.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Entity
@Table(name = "curso")
@Data
@NoArgsConstructor
@NamedQuery(name = "CursoEntity.abiertoIncompleto", query = "SELECT c FROM CursoEntity c WHERE c.alumnosMin > c.alumnosAct and c.estado = 1")
public class CursoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCurso;
    @Column(name = "nomcurso")
    private String nomCurso;
    private Date   fechaInicio;
    private Integer alumnosMin;
    private Integer alumnosAct;
    private Integer estado;

}
