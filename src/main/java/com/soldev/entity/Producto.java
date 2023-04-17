package com.soldev.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Table(name="producto")
@SQLDelete(sql = "UPDATE producto  SET estado=0 WHERE id_producto=?")
@Where(clause = "estado=1")
public class Producto {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer idProducto;
	
	@Size(min=3)
	@Column(name="producto", nullable=false, length=300)
	private String producto;
	
	@ManyToOne
	@JoinColumn(name="id_categoria", nullable=false, foreignKey=@ForeignKey(name="fk_producto_categoria"))
	private Categoria categoria;
	
	@ManyToOne
	@JoinColumn(name="id_marca", nullable=false, foreignKey=@ForeignKey(name="fk_producto_marca"))
	private Marca marca;
	
	@Column(name="precio_base", scale=2)
	private Double precioBase;
	
	@Column(name="stock")
	private Integer stock;
	
	@Column(name="estado")
	private Integer estado=1;
	

	public Integer getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(Integer idProducto) {
		this.idProducto = idProducto;
	}

	public String getProducto() {
		return producto;
	}

	public void setProducto(String producto) {
		this.producto = producto;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Marca getMarca() {
		return marca;
	}

	public void setMarca(Marca marca) {
		this.marca = marca;
	}

	public Double getPrecioBase() {
		return precioBase;
	}

	public void setPrecioBase(Double precioBase) {
		this.precioBase = precioBase;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public Integer getEstado() {
		return estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}
	
	
	
}
