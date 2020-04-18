package org.prueba.twitter.repositorio;

import java.util.List;

import org.prueba.twitter.entidades.Idioma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

/**
 * <p>Repositorio con el CRUD de la tabla Idioma</p>
 * 
 * @author Ignacio Rafael Viñas González
 */
public interface IdiomaRepository extends Repository<Idioma, String>, JpaRepository<Idioma, String> {
	
	/**
	 * <p>Método que recupera el idioma que está activo. Sólo debería de haber uno activo</p>
	 * 
	 * @return {@link List} {Idioma} listado de idiomas activos
	 */
	public List<Idioma> findByActivoTrue();

}
