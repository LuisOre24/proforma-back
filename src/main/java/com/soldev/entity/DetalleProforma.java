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
import javax.validation.constraints.Min;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "detalle_proforma")
public class DetalleProforma {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idDetalleProforma;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="id_proforma", nullable=false)
	private Proforma proforma;
	
	@ManyToOne
	@JoinColumn(name = "id_producto", nullable = false, foreignKey = @ForeignKey(name = "fk_detProforma_producto"))
	private Producto producto;
	
	@Min(value = 1)
	@Column(name = "cantidad")
	private Integer cantidad;
	
	@Column(name = "precio_base")
	private Double precioBase;
	
	@Column(name = "precio_venta")
	private Double precioVenta;
	
	@Column(name = "total")
	private Double total;

	public Integer getIdDetalleProforma() {
		return idDetalleProforma;
	}

	public void setIdDetalleProforma(Integer idDetalleProforma) {
		this.idDetalleProforma = idDetalleProforma;
	}

	public Proforma getProforma() {
		return proforma;
	}

	public void setProforma(Proforma proforma) {
		this.proforma = proforma;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public Double getPrecioBase() {
		return precioBase;
	}

	public void setPrecioBase(Double precioBase) {
		this.precioBase = precioBase;
	}

	public Double getPrecioVenta() {
		return precioVenta;
	}

	public void setPrecioVenta(Double precioVenta) {
		this.precioVenta = precioVenta;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}
	
	public String getProductoDesc() {
		return this.producto!= null ? this.producto.getProducto() : "--"; 
	}
	
}
