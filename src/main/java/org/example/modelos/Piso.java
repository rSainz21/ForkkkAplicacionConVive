package org.example.modelos;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "pisos")
public class Piso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Embedded
    @Column(nullable = false)
    private Direccion direccion;
    @Column(nullable = false)
    private String descripcion;
    private String urlImagen;
    @OneToMany(mappedBy = "piso", cascade = CascadeType.ALL)
    List<Inquilino> inquilinos;
    @ManyToOne
    Propietario propietario;

    public Piso() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUrlImagen() {
        return urlImagen;
    }

    public void setUrlImagen(String urlImagen) {
        this.urlImagen = urlImagen;
    }

    public List<Inquilino> getInquilinos() {
        return inquilinos;
    }

    public void setInquilinos(List<Inquilino> inquilinos) {
        this.inquilinos = inquilinos;
    }

    public Propietario getPropietario() {
        return propietario;
    }

    public void setPropietario(Propietario propietario) {
        this.propietario = propietario;
    }
}
