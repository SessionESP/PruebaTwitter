package org.prueba.twitter.servicio.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.prueba.twitter.entidades.Tweet;
import org.prueba.twitter.repositorio.TweetRepository;
import org.prueba.twitter.servicio.TweetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import twitter4j.Trend;
import twitter4j.Trends;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

/**
 * <p>Implementación de la interfaz del servicio para operar con la tabla Tweet</p>
 * 
 * @author Ignacio Rafael Viñas González
 */
@Service
public class TweetServiceImpl implements TweetService {
	
	@Autowired
	private TweetRepository tweetRepositorio;

	/**
	 * <p>Listado con todos los Tweets persistidos en la base de datos</p>
	 */
	@Override
	public List<Tweet> consultaTweets() {
		return tweetRepositorio.findAll();
	}

	/**
	 * <p>Validación de un Tweet</p>
	 */
	@Override
	public Boolean validacionTweet(String id) {
		
		// Comprobar que el parámetro de entrada NO es nulo
		if(!StringUtils.isEmpty(id)) {
			Optional<Tweet> mensaje = tweetRepositorio.findById(id);
			if (mensaje != null) {
				Tweet tweetActualizado = mensaje.get();
				tweetActualizado.setValidacion(Boolean.TRUE);
				return tweetRepositorio.save(tweetActualizado).getValidacion();
			}
		}
		
		return Boolean.FALSE;
	}

	/**
	 * <p>Consulta de los tweets validados por un usuario</p>
	 */
	@Override
	public List<Tweet> consultaTweetsValidadosUsuario(String usuario) {
		
		// Comprobar que el parámetro de entrada NO es nulo
		if(!StringUtils.isEmpty(usuario)) {
			
			// Retornar la consulta a la tabla de la base de datos
			return tweetRepositorio.findByUsuarioAndValidacionTrue(usuario);
			
		}
		
		return null;
	}

	/**
	 * <p>Listado de los Hashtags más usados</p>
	 */
	@Override
	public List<String> clasificacionHashtags(Integer numero) {
		
		// Inicializar el listado que se va a retornar
		List<String> listadoHashtags = new ArrayList<String>();
		
		// Establecer un número por defecto en caso de que el parámetro de entrada sea nulo
		numero = numero != null ? numero : 10;
		
		try {
			
			// Obtener listado de tendencias mundiales (WOEID = 1)
			Twitter twitter = new TwitterFactory().getInstance();
			Trends trends = twitter.getPlaceTrends(1);
			
			// Comprobar que el objeto que contiene el listado NO es nulo NI el propio listado tampoco
			if (trends != null && trends.getTrends() != null && trends.getTrends().length > 0) {
				List<Trend> listadoTendencias = new ArrayList<>(Arrays.asList(Arrays.copyOfRange(trends.getTrends(), 0, numero)));
				listadoTendencias.forEach(tendencia -> listadoHashtags.add(tendencia.getName()));
			}
		} catch (TwitterException twitterException) {
			twitterException.printStackTrace();
		}
		
		// Retornar el listado
		return listadoHashtags;
		
	}

}
