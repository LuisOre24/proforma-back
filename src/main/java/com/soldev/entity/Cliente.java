package com.soldev.entity;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCliente;

    @Size(min = 3)
    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Size(min = 3)
    @Column(name = "apellido", nullable = true, length = 100)
    private String apellido;

    @Size(min = 8)
    @Column(name = "documento", nullable = false, unique = true)
    private String documento;

    @Column(name= "telefono", nullable = true)
    private String telefono;

    @Column(length = 200)
    private String correo;

    @Column(name = "direccion", length = 255)
    private String direccion;

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}
