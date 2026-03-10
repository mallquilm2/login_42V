package edu.cibertec.login.service;

import edu.cibertec.login.dao.MatriculaDAO;
import edu.cibertec.login.dao.entity.MatriculaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatriculaService {

    @Autowired
    private MatriculaDAO matriculaDAO;

    public List<MatriculaEntity> listarTodas(){
        return matriculaDAO.findAll();
    }

    public void grabarMatricula(MatriculaEntity me){
        matriculaDAO.save(me);
    }

}
