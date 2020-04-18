package org.prueba.twitter.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * <p>Entidad para operar con la tabla de idiomas</p>
 * 
 * @author Ignacio Rafael Viñas González
 */
@Entity
public class Idioma {
	
    @Id
    @Column(name = "codigo", nullable = false)
    private String codigo;
    
	@Column(name = "descripcion", nullable = true)
    private String descripcion;
	
	@Column(name = "activo", nullable = true)
    private Boolean activo;

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

}
