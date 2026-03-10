package edu.cibertec.login.dao;

import edu.cibertec.login.dao.entity.CursoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Date;
import java.util.List;

public interface CursoDAO extends JpaRepository<CursoEntity, Integer> {

    //Consulta de cursos por estado.
    @Query("SELECT c FROM CursoEntity c WHERE c.estado=?1")
    public List<CursoEntity> consultaPorEstado(int estado);

    //Consulta de cursos abiertos pero que aun no estan completos
    public List<CursoEntity> abiertoIncompleto();

    //Consulta para cursos despues de la fecha X
    @Query("SELECT c FROM CursoEntity c WHERE c.fechaInicio >= :fecha")
    public List<CursoEntity> consultaPorFecha(@Param("fecha")Date fecha);

    //Consulta de cursos para los que les falta X numeros de alumnos
    @Query(value = "SELECT * FROM Curso WHERE alumnosMin - alumnosAct - :cantidad", nativeQuery = true)
    public List<CursoEntity> consultarFaltantes(@Param("cantidad") Integer cantidad);


    //Consulta curso por nombre invocando a SP
    @Query(nativeQuery = true, value = "call Curso_Por_Nombre(:cadena)")
    public List<CursoEntity> consultarPorNombre(@Param("cadena") String cadena);

}
