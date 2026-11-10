
package com.escoladesurf.escola_surf_api;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "alunos")
public class Aluno extends Pessoa {

    private String nivel;

    public Aluno() {
        super();
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }
}
