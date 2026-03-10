package edu.cibertec.login.service;

import edu.cibertec.login.dao.CursoDAO;
import edu.cibertec.login.dao.entity.CursoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class CursoService {

    @Autowired
    private CursoDAO cursoDAO;

    public List<CursoEntity> consultarPorEstado(int estado){
        return cursoDAO.consultaPorEstado(estado);
    }

    public List<CursoEntity> consultaAbiertoIncompleto(){
        return cursoDAO.abiertoIncompleto();
    }

    public List<CursoEntity> consultarPorFecha(Date fecha){
        return cursoDAO.consultaPorFecha(fecha);
    }

    public List<CursoEntity> consultarPorFaltantes(Integer cantidad){
        return cursoDAO.consultarFaltantes(cantidad);
    }

    public List<CursoEntity> consultarPorNombre(String cadena){
        return cursoDAO.consultarPorNombre(cadena);
    }

}
