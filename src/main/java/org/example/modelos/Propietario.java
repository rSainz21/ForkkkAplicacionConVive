package org.example.modelos;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.List;

@Entity
@Table(name = "propietarios")
public class Propietario extends Usuario{
    @OneToMany(mappedBy = "propietario", cascade = CascadeType.ALL)
    private List<Piso> pisos;

    public Propietario() {

    }

    public List<Piso> getPisos() {
        return pisos;
    }

    public void setPisos(List<Piso> pisos) {
        this.pisos = pisos;
    }
}
