package com.escoladesurf.escola_surf_api;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "instrutores")
public class Instrutor extends Pessoa {

    private String especialidade;

    public Instrutor() {
        super();
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }
}