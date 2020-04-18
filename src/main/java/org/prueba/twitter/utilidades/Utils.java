package org.prueba.twitter.utilidades;

import java.util.Collection;

import org.prueba.twitter.entidades.Idioma;
import org.prueba.twitter.entidades.Tweet;
import org.springframework.http.HttpStatus;

import twitter4j.Status;

/**
 * <p>Clase que contiene los métodos estáticos usados en el microservicio</p>
 * 
 * @author Ignacio Rafael Viñas González
 */
public class Utils {
	
	/**
	 * <p>Método que convierte un objeto recuperado de Twitter en un objeto compatible con la tabla de la base de datos</p>
	 * 
	 * @param mensajeTwitter {@link Status} mensaje de Twitter
	 * 
	 * @return {@link Tweet} entidad de la base de datos
	 */
	public static Tweet conversor (Status mensajeTwitter) {
		
		// Inicializar la entidad que se va a retornar
		Tweet mensaje = new Tweet();
		
		// Comprobar que el parámetro NO es nulo
		if(mensajeTwitter != null) {
			
			// Establecer los valores de los atributos de la entidad
			mensaje.setId(String.valueOf(mensajeTwitter.getId()));
			mensaje.setUsuario(mensajeTwitter.getUser().getName());
			mensaje.setTexto(mensajeTwitter.getText());
			mensaje.setLocalizacion(mensajeTwitter.getUser().getLocation());
			mensaje.setValidacion(Boolean.FALSE);
			
		}
		
		// Retornar la entidad
		return mensaje;
		
	}
	

	/**
	 * <p>Método que comprueba si un Tweet debe ser persistido en la base de datos</p>
	 * 
	 * @param mensajeTwitter {@link Status} Tweet que se va a comprobar
	 * @param numeroSeguidores {@link Integer} número de seguidores que debe tener, por defecto 1500
	 * @param idiomaActivo {@link Idioma} idioma del Tweet
	 * 
	 * @return {@link Boolean} retorna {@true} en caso de que el Tweet deba ser persistido en la base de datos
	 */
	public static Boolean comprobarTweet(Status mensajeTwitter, Integer numeroSeguidores, Idioma idiomaActivo) {
		
		// Comprobar que los parámetros obligatorios NO son nulos
		if(mensajeTwitter != null && idiomaActivo != null) {
			
			// Establecer un número de seguidores por defecto en caso de que el parámetro de entrada correspondiente sea nulo
			numeroSeguidores = numeroSeguidores != null ? numeroSeguidores : 1500;
			
			if (numeroSeguidores.compareTo(mensajeTwitter.getUser().getFollowersCount()) > 0) {
				
				return Boolean.TRUE;
				
			}

// TODO: se desconoce el atributo o no se han encontrado tweets con idioma español
//			if (numeroSeguidores.compareTo(mensajeTwitter.getUser().getFollowersCount()) > 0 && 
//					idiomaActivo.getCodigo().equals(mensajeTwitter.getUser().getLang())) {
//				
//				return Boolean.TRUE;
//				
//			}
			
		}
		
		return Boolean.FALSE;
		
	}
	
	/**
	 * <p>Método que comprueba si una colección es nula o está vacía y retorna el código de estado correspondiente</p>
	 * 
	 * @param coleccion {@link Collection} colección de Java
	 * 
	 * @return {@link HttpStatus} retorna el código de estado {@code 200} en caso de que la colección NO sea nula NI esté vacía 
	 */
	public static HttpStatus validarColeccion(Collection<?> coleccion) {
		
		return (coleccion != null && coleccion.isEmpty()) ? HttpStatus.NO_CONTENT : HttpStatus.OK;
		
	}

}
