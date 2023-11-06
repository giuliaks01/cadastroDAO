package com.example.contatos.domain;

import lombok.Data;

@Data
public class Aluno {

    private String nome;
    private String materia;
    private String curso;
    private String email;

    public Aluno() {
    }

    public Aluno(String nome, String materia, String curso, String email) {
        this.nome = nome;
        this.materia = materia;
        this.curso = curso;
        this.email = email;
    }
}
