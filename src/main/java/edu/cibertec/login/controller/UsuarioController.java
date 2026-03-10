package edu.cibertec.login.controller;

import edu.cibertec.login.dao.entity.UsuarioEntity;
import edu.cibertec.login.service.UsuarioService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.List;

@Controller
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @RequestMapping("loginMostrar")
    public String loginMostrar(){
        return "login";
    }

    @RequestMapping(value = "loginAccion")
    public ModelAndView loginAccion(HttpServletRequest request){
        ModelAndView mv = null;

        UsuarioEntity usuarioValida = new UsuarioEntity();
        usuarioValida.setUsuario(request.getParameter("txtUsuario"));
        usuarioValida.setClave(request.getParameter("txtClave"));

        UsuarioEntity ue = usuarioService.validarLogin(usuarioValida);
        if(ue == null){
            mv = new ModelAndView("login","msgError", "Usuario y clave no existen. Vuelva a intentar");
        }else{
            mv = new ModelAndView("menu", "usuario", ue);
        }
        return mv;
    }

    @RequestMapping("usuarioListar")
    public ModelAndView usuarioListar(){
        ModelAndView mv = new ModelAndView("usuarioLista", "lista", usuarioService.getListaUsuario());
        return mv;
    }

    @RequestMapping("usuarioCrear")
    public ModelAndView crearUsuario(){
        return new ModelAndView("usuarioDatos", "usuarioBean", new UsuarioEntity());
    }

    @RequestMapping("usuarioGrabar")
    public ModelAndView grabarUsuario(@Valid @ModelAttribute("usuarioBean") UsuarioEntity usuario, BindingResult result){
        ModelAndView mv = null;
        if(result.hasErrors()){
            mv = new ModelAndView("usuarioDatos","usuarioBean", usuario);
        }else{
            usuarioService.insertarUsuario(usuario);
            mv = new ModelAndView("usuarioLista", "lista", usuarioService.getListaUsuario());
        }
        return mv;
    }

    @RequestMapping("usuarioEliminar")
    public ModelAndView usuarioEliminar(HttpServletRequest request){
        String id = request.getParameter("codigoUsuario").trim();
        usuarioService.usuarioEliminar(id);
        return new ModelAndView("usuarioLista", "lista",actualizarListadoUsuarios());
    }

    public List<UsuarioEntity> actualizarListadoUsuarios(){
        return usuarioService.getListaUsuario();
    }

    @RequestMapping("fotoMostrar")
    public String fotoMostrar(HttpServletRequest request, Model modelo){
        modelo.addAttribute("usuario", usuarioService.getUsuario(request.getParameter("codigoUsuario")));
        return "fotoUsuario";
    }

    @RequestMapping("fotoGrabar")
    public ModelAndView fotoGrabar(@RequestParam("archivo") MultipartFile archivo,
                                   @RequestParam("codigoUsuario") String codigoUsuario){
        UsuarioEntity usuario = usuarioService.getUsuario(codigoUsuario);
        try {
            usuario.setFoto(archivo.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return new ModelAndView("usuarioLista","lista",usuarioService.getListaUsuario());
    }

    @RequestMapping("usuarioActualizar")
    public ModelAndView usuarioActualizar(@RequestParam("codigoUsuario") String codigo){
        ModelAndView mv = new ModelAndView("usuarioDatos", "usuarioBean", usuarioService.getUsuario(codigo));
        mv.addObject("accion","Modificar");
        return mv;
    }

}
