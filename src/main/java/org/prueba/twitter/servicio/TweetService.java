package org.prueba.twitter.servicio;

import java.util.List;

import org.prueba.twitter.entidades.Tweet;

/**
 * <p>Interfaz del servicio para operar con la tabla Tweet</p>
 * 
 * @author Ignacio Rafael Viñas González
 */
public interface TweetService {
	
	/**
	 * <p>Listado con todos los Tweets persistidos en la base de datos</p>
	 * 
	 * @return {@link List} {@Link Tweet} listado de Tweets recuperados
	 */
	public List<Tweet> consultaTweets();
	
	/**
	 * <p>Validación de un Tweet</p>
	 * 
	 * @param id {@link String} identificador del Tweet que se quiere validar
	 * 
	 * @return {@link Boolean} retornará {@code true} en caso de que se haya validado correctamente
	 */
	public Boolean validacionTweet(String id);
	
	/**
	 * <p>Consulta de los tweets validados por un usuario</p>
	 * 
	 * @param usuario {@link String} identificador del usuario cuyo listado de tweets validados se quiere recuperar
	 * 
	 * @return {@link List} {@Link Tweet} listado de Tweets recuperados
	 */
	public List<Tweet> consultaTweetsValidadosUsuario(String usuario);
	
	/**
	 * <p>Listado de los Hashtags más usados</p>
	 * 
	 * @param usuario {@link Integer} número de Hashtags que se quieren recuperar, por defecto 10
	 * 
	 * @return {@link List} {@link String} listado con los Hashtags más usados
	 */
	public List<String> clasificacionHashtags(Integer numero);

}
