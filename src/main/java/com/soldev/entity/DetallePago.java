package com.soldev.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Entity
@Table(name = "detalle_pago")
public class DetallePago {

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_atencion", nullable = false, foreignKey = @ForeignKey(name = "fk_detalle_atencion"))
    private Atencion atencion;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idDetPago;

    private Double montoPago;

    private LocalDate fechaDetPago;

    @Size(min = 3)
    @Column(name = "descripcion", nullable = true)
    private String descripcion;


    public Atencion getAtencion() {
        return atencion;
    }

    public void setAtencion(Atencion atencion) {
        this.atencion = atencion;
    }

    public Integer getIdDetPago() {
        return idDetPago;
    }

    public void setIdDetPago(Integer idDetPago) {
        this.idDetPago = idDetPago;
    }

    public Double getMontoPago() {
        return montoPago;
    }

    public void setMontoPago(Double montoPago) {
        this.montoPago = montoPago;
    }

    public LocalDate getFechaDetPago() {
        return fechaDetPago;
    }

    public void setFechaDetPago(LocalDate fechaDetPago) {
        this.fechaDetPago = fechaDetPago;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
