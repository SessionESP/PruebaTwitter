package org.prueba.twitter.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * <p>Entidad para operar con la tabla que almacena el listado de Tweets</p>
 * 
 * @author Ignacio Rafael Viñas González
 */
@Entity
public class Tweet {
	
    @Id
    @Column(name = "id", nullable = false)
	String id;

    @Column(name = "usuario", nullable = false)
	String usuario;
	
    @Column(name = "texto", nullable = false, length = 500)
	String texto;
	
    @Column(name = "localizacion", nullable = false)
	String localizacion;
	
    @Column(name = "validacion", nullable = false)
	Boolean validacion;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public String getLocalizacion() {
		return localizacion;
	}

	public void setLocalizacion(String localizacion) {
		this.localizacion = localizacion;
	}

	public Boolean getValidacion() {
		return validacion;
	}

	public void setValidacion(Boolean validacion) {
		this.validacion = validacion;
	}
    
    

}