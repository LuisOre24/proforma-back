package com.soldev.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.annotations.SQLDelete;

@Entity
@Table(name="marca")
@SQLDelete(sql = "UPDATE marca  SET estado=0 WHERE id_marca=?")
//@Where(clause = "estado=1")
public class Marca {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer idMarca;
	
	@Size(min=2)
	@Column(name="marca", nullable=false, length=150)
	private String marca;
	
	@Column(name="estado")
	private Integer estado=1;

	public Integer getIdMarca() {
		return idMarca;
	}

	public void setIdMarca(Integer idMarca) {
		this.idMarca = idMarca;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public Integer getEstado() {
		return estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}
	
	
	
}
