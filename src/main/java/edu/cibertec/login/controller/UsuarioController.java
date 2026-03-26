package edu.cibertec.login.controller;

import edu.cibertec.login.dao.entity.UsuarioEntity;
import edu.cibertec.login.service.UsuarioService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
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
import java.util.Map;

@Controller
public class UsuarioController {

    Logger logger = LoggerFactory.getLogger(UsuarioController.class);

    @Autowired
    private UsuarioService usuarioService;

    @RequestMapping("loginMostrar")
    public String loginMostrar(){
        logger.info("ingresando a loginMostrar");
        return "login";
    }

    @RequestMapping("/menu")
    public ModelAndView menuMostrar(@AuthenticationPrincipal OAuth2User oAuth2User){
        Map<String,Object> attributes = oAuth2User.getAttributes();
        return new ModelAndView("menu","usuario",attributes.get("name"));
    }

    @RequestMapping(value = "loginAccion")
    public ModelAndView loginAccion(HttpServletRequest request){
        logger.info("Ingresando a loginAccion");
        ModelAndView mv = null;

        UsuarioEntity usuarioValida = new UsuarioEntity();
        usuarioValida.setUsuario(request.getParameter("txtUsuario"));
        usuarioValida.setClave(request.getParameter("txtClave"));

        UsuarioEntity ue = usuarioService.validarLogin(usuarioValida);
        if(ue == null){
            logger.warn("Usuario y clave no existen. Vuelva a intentar");
            mv = new ModelAndView("login","msgError", "Usuario y clave no existen. Vuelva a intentar");
        }else{
            mv = new ModelAndView("menu", "usuario", ue);
        }
        logger.info("Saliendo de loginAccion");
        return mv;
    }

    @RequestMapping("usuarioListar")
    public ModelAndView usuarioListar(){
        logger.info("Ingresando a usuarioListar");
        ModelAndView mv = new ModelAndView("usuarioLista", "lista", usuarioService.getListaUsuario());
        logger.info("Saliendo de usuarioListar");
        return mv;
    }

    @RequestMapping("usuarioCrear")
    public ModelAndView crearUsuario(){
        logger.info("ingresando a usuarioCrear");
        return new ModelAndView("usuarioDatos", "usuarioBean", new UsuarioEntity());
    }

    @RequestMapping("usuarioGrabar")
    public ModelAndView grabarUsuario(@Valid @ModelAttribute("usuarioBean") UsuarioEntity usuario, BindingResult result){
        logger.info("Ingresando a grabarUsuario");
        ModelAndView mv = null;
        if(result.hasErrors()){
            logger.error("Hubieron problemas para la creaciòn del usuario");
            mv = new ModelAndView("usuarioDatos","usuarioBean", usuario);
        }else{
            usuarioService.insertarUsuario(usuario);
            mv = new ModelAndView("usuarioLista", "lista", usuarioService.getListaUsuario());
        }
        logger.info("Saliendo de grabarUsuario");
        return mv;
    }

    @RequestMapping("usuarioEliminar")
    public ModelAndView usuarioEliminar(HttpServletRequest request){
        logger.info("Ingresando al usuarioEliminar");
        String id = request.getParameter("codigoUsuario").trim();
        usuarioService.usuarioEliminar(id);
        return new ModelAndView("usuarioLista", "lista",actualizarListadoUsuarios());
    }

    public List<UsuarioEntity> actualizarListadoUsuarios(){
        logger.info("Ingresando a actualziarListadoUsuarios");
        return usuarioService.getListaUsuario();
    }

    @RequestMapping("fotoMostrar")
    public String fotoMostrar(HttpServletRequest request, Model modelo){
        logger.info("Ingresando a fotoMostrar");
        modelo.addAttribute("usuario", usuarioService.getUsuario(request.getParameter("codigoUsuario")));
        logger.info("Saliendo de fotoMostrar");
        return "fotoUsuario";
    }

    @RequestMapping("fotoGrabar")
    public ModelAndView fotoGrabar(@RequestParam("archivo") MultipartFile archivo,
                                   @RequestParam("codigoUsuario") String codigoUsuario){
        logger.info("Ingresando a fotoGrabar");
        UsuarioEntity usuario = usuarioService.getUsuario(codigoUsuario);
        try {
            usuario.setFoto(archivo.getBytes());
        } catch (IOException e) {
            logger.error("Hubo un error al grabar la foto!");
            throw new RuntimeException(e);
        }
        logger.info("Saliendo de fotoGrabar");
        return new ModelAndView("usuarioLista","lista",usuarioService.getListaUsuario());
    }

    @RequestMapping("usuarioActualizar")
    public ModelAndView usuarioActualizar(@RequestParam("codigoUsuario") String codigo){
        logger.info("Ingresando a usuarioActualizar");
        ModelAndView mv = new ModelAndView("usuarioDatos", "usuarioBean", usuarioService.getUsuario(codigo));
        mv.addObject("accion","Modificar");
        logger.info("Saliendo de usuarioActualizar");
        return mv;
    }

}
