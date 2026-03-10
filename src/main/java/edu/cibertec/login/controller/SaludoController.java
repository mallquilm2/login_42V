package edu.cibertec.login.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SaludoController {

    @RequestMapping("saludo")
    public ModelAndView saludar(){
        /*
        ModelAndView mv = new ModelAndView();
        mv.addObject("mensaje","Bienvenido desde el controlador");
        mv.setViewName("saludo");
        return mv;
         */
        return new ModelAndView("saludo", "mensaje", "Bienveniod desde el controlador");
    }

}
