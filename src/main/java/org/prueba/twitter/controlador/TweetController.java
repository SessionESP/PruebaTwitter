package org.prueba.twitter.controlador;

import java.util.List;

import org.prueba.twitter.entidades.Tweet;
import org.prueba.twitter.servicio.TweetService;
import org.prueba.twitter.utilidades.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>Clase de controlador con los métodos expuestos en el microservicio</p>
 * 
 * @author Ignacio Rafael Viñas González
 */
@RestController
@RequestMapping("pruebaTweets")
public class TweetController {
	
    @Autowired
    private TweetService servicio;
    
	/**
	 * <p>Listado con todos los Tweets persistidos en la base de datos</p>
	 * 
	 * @return {@link List} {@Link Tweet} listado de Tweets recuperados
	 */
    @SuppressWarnings("rawtypes")
	@RequestMapping(method = RequestMethod.GET, value = "/consultaTweets")
    @ResponseBody
    public ResponseEntity<List> consultaTweets() {
    	
    	// Realizar la consulta a la capa de servicio
    	List<Tweet> listadoTweets = servicio.consultaTweets();
    	
    	// Retornar los resultados de la consulta
    	return new ResponseEntity<List>(listadoTweets, Utils.validarColeccion(listadoTweets));
    	
    }
    
	/**
	 * <p>Validación de un Tweet</p>
	 * 
	 * @param id {@link String} identificador del Tweet que se quiere validar
	 * 
	 * @return {@link Boolean} retornará {@code true} en caso de que se haya validado correctamente
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/validacionTweet")
    @ResponseBody
    public ResponseEntity<Boolean> validacionTweet(@RequestParam(required = true) String id) {
		
		// Retornar el resultado de la actualización
		return new ResponseEntity<Boolean>(servicio.validacionTweet(id), HttpStatus.OK);
        
    }
	
	/**
	 * <p>Consulta de los tweets validados por un usuario</p>
	 * 
	 * @param usuario {@link String} identificador del usuario cuyo listado de tweets validados se quiere recuperar
	 * 
	 * @return {@link List} {@Link Tweet} listado de Tweets recuperados
	 */
    @SuppressWarnings("rawtypes")
	@RequestMapping(method = RequestMethod.GET, value = "/consultaTweetsValidadosUsuario")
    @ResponseBody
    public ResponseEntity<List> consultaTweetsValidadosUsuario(@RequestParam(required = true) String usuario) {
    	
    	// Realizar la consulta a la capa de servicio
    	List<Tweet> listadoTweets = servicio.consultaTweetsValidadosUsuario(usuario);
    	
    	// Retornar los resultados de la consulta
    	return new ResponseEntity<List>(listadoTweets, Utils.validarColeccion(listadoTweets));
    	
    }
    
	/**
	 * <p>Listado de los Hashtags más usados</p>
	 * 
	 * @param usuario {@link Integer} número de Hashtags que se quieren recuperar, por defecto 10
	 * 
	 * @return {@link List} {@link String} listado con los Hashtags más usados
	 */
    @SuppressWarnings("rawtypes")
	@RequestMapping(method = RequestMethod.GET, value = "/clasificacionHashtags")
    @ResponseBody
    public ResponseEntity<List> clasificacionHashtags(@RequestParam(required = false) Integer numero) {
    	
    	// Realizar la consulta a la capa de servicio
    	List<String> listadoTweets = servicio.clasificacionHashtags(numero);
    	
    	// Retornar los resultados de la consulta
    	return new ResponseEntity<List>(listadoTweets, Utils.validarColeccion(listadoTweets));

    }

}
