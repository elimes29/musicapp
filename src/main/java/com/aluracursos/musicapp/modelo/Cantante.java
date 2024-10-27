package com.aluracursos.musicapp.modelo;

import jakarta.persistence.*;

import java.util.List;
@Entity
@Table(name = "cantantes")
public class Cantante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    @Enumerated(EnumType.STRING)
    private Genero genero;
    @OneToMany(mappedBy = "cantante",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Cancion> canciones;

    public Cantante() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNobre(String nobre) {
        this.nombre = nobre;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public List<Cancion> getCanciones() {
        return canciones;
    }

    public void setCanciones(List<Cancion> canciones) {
        this.canciones = canciones;
    }

    @Override
    public String toString() {
        return
                "id=" + id +
                ", nobre='" + nombre + '\'' +
                ", genero=" + genero +
                        ", canciones=" + (canciones != null ? canciones.size() : "0") ;
    }
}
