package edu.cibertec.login.dao;

import edu.cibertec.login.dao.entity.MatriculaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatriculaDAO extends JpaRepository<MatriculaEntity, Integer> {
}
