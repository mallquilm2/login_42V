package edu.cibertec.login.controller;

import edu.cibertec.login.dao.entity.CursoEntity;
import edu.cibertec.login.dao.entity.MatriculaEntity;
import edu.cibertec.login.dao.entity.UsuarioEntity;
import edu.cibertec.login.service.CursoService;
import edu.cibertec.login.service.MatriculaService;
import edu.cibertec.login.service.UsuarioService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Date;

@Controller
public class MatriculaController {

    @Autowired
    private MatriculaService matriculaService;
    @Autowired
    private CursoService cursoService;
    @Autowired
    private UsuarioService usuarioService;

    @RequestMapping("matriculaMostrar")
    public ModelAndView matriculaMostrar(){
        ModelAndView mv = new ModelAndView("matricula","listaMatricula", matriculaService.listarTodas());
        mv.addObject("listaUsuarios", usuarioService.getListaUsuario());
        mv.addObject("listaCursos", cursoService.consultarPorEstado(0));
        return mv;
    }

    @RequestMapping("matriculaGrabar")
    public ModelAndView matriculaGrabar(MatriculaEntity entity, HttpServletRequest request){
        CursoEntity ce = new CursoEntity();
        ce.setIdCurso(Integer.parseInt(request.getParameter("cmbCurso")));
        UsuarioEntity ue = new UsuarioEntity();
        ue.setUsuario(request.getParameter("cmbUsuario"));
        entity.setCurso(ce);
        entity.setUsuario(ue);
        entity.setFecMat(Date.valueOf(request.getParameter("fechaMat")));
        entity.setEstado(Integer.parseInt(request.getParameter("estado")));
        matriculaService.grabarMatricula(entity);
        return new ModelAndView("redirect:matriculaMostrar");
    }

}
