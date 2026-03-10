package edu.cibertec.login.controller;

import edu.cibertec.login.service.CursoService;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class CursoController {

    Logger logger = LoggerFactory.getLogger(CursoController.class);

    @Autowired
    CursoService cursoService;

    @RequestMapping("cursoMostrar")
    public String cursoMostrar(){
        logger.debug("Listando cursos");
        return "cursoBusqueda";
    }

    @RequestMapping("cursoBusqueda")
    public ModelAndView cursoBusqueda(HttpServletRequest request){
        logger.info("Ingresando a cursoBusqueda");
        ModelAndView mv = new ModelAndView("cursoBusqueda");
        String tipoConsulta = request.getParameter("tipo");
        switch (tipoConsulta){
            case "estado":
                int estado = Integer.parseInt(request.getParameter("estado"));
                mv.addObject("lista", cursoService.consultarPorEstado(estado));
                break;
            case "incompleto":
                mv.addObject("lista", cursoService.consultaAbiertoIncompleto());
                break;
            case "porfecha":
                java.sql.Date fecha = java.sql.Date.valueOf(request.getParameter("fecha"));
                mv.addObject("lista", cursoService.consultarPorFecha(fecha));
                break;
            case "faltante":
                int faltante = Integer.parseInt(request.getParameter("numero"));
                mv.addObject("lista", cursoService.consultarPorFaltantes(faltante));
                break;
            case "nombre":
                String cadena = request.getParameter("cadena");
                mv.addObject("lista", cursoService.consultarPorNombre(cadena));
                break;
            default:
                mv.addObject("lista", null);
                break;
        }
        logger.info("Saliendo de cursoBusqueda");
        return mv;
    }

}
