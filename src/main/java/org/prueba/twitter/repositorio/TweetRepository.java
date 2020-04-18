package org.prueba.twitter.repositorio;

import java.util.List;

import org.prueba.twitter.entidades.Tweet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

/**
 * <p>Repositorio con el CRUD de la tabla Tweet</p>
 * 
 * @author Ignacio Rafael Viñas González
 */
public interface TweetRepository extends Repository<Tweet, String>, JpaRepository<Tweet, String> {
	
	/**
	 * <p>Método de búsqueda en la tabla por el atributo {@link Tweet#getUsuario()} de la entidad y el atributo {@link Tweet#getValidacion()} con valor {@code true}</p>
	 * 
	 * @param usuario {@link String} usuario por el que se quiere filtrar
	 * 
	 * @return {@link List} {@link Tweet} listado de tweets 
	 */
	public List<Tweet> findByUsuarioAndValidacionTrue(String usuario);

}
