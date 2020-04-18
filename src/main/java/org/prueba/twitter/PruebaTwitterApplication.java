package org.prueba.twitter;

import java.util.List;

import org.prueba.twitter.entidades.Idioma;
import org.prueba.twitter.repositorio.IdiomaRepository;
import org.prueba.twitter.repositorio.TweetRepository;
import org.prueba.twitter.utilidades.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

@SpringBootApplication
public class PruebaTwitterApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(PruebaTwitterApplication.class, args);
	}
	
	@Autowired
	private TweetRepository tweetRepositorio;
	
	@Autowired
	private IdiomaRepository idiomaRepositorio;

	/**
	 * <p>Método donde se va a implementar la carga inicial de la base de datos en memoria</p>
	 */
	@Override
	public void run(String... args) throws Exception {
		
		Idioma idiomaES = new Idioma();
		idiomaES.setCodigo("es");
		idiomaES.setDescripcion("Spanish");
		idiomaES.setActivo(Boolean.TRUE);
		
		idiomaRepositorio.save(idiomaES);
		
		Idioma idiomaFR = new Idioma();
		idiomaFR.setCodigo("fr");
		idiomaFR.setDescripcion("French");
		idiomaFR.setActivo(Boolean.FALSE);
		
		idiomaRepositorio.save(idiomaFR);
		
		Idioma idiomaIT = new Idioma();
		idiomaIT.setCodigo("it");
		idiomaIT.setDescripcion("Italian");
		idiomaIT.setActivo(Boolean.FALSE);
		
		idiomaRepositorio.save(idiomaIT);
		
		try {
			Twitter twitter = new TwitterFactory().getInstance();
			Query query = new Query("#nba");
			query.setCount(1000);
			QueryResult result;
			result = twitter.search(query);
			List<Status> tweets = result.getTweets();
			tweets.forEach(tweet -> {
				if (Utils.comprobarTweet(tweet, 1500, idiomaRepositorio.findByActivoTrue().get(0))) {
					tweetRepositorio.save(Utils.conversor(tweet));
				}
			});
		} catch (TwitterException twitterException) {
			twitterException.printStackTrace();
		}
		
		
	}

}
