package com.example.contatos.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.example.contatos.domain.Aluno;

@Component
public class AlunoDao {

    @Autowired
    JdbcTemplate db;

    public List<Aluno> getAlunos() {
        String sql = "select * from alunos";
        return db.query(sql, (res, rowNum) -> new Aluno(
                res.getString("nome"),
                res.getString("materia"),
                res.getString("curso"),
                res.getString("email")));
    }

    public Aluno getAluno(String email) {
        String sql = "select * from alunos where email = ?";
        List<Aluno> alunos = db.query(sql, (res, rowNum) -> new Aluno(
                res.getString("nome"),
                res.getString("materia"),
                res.getString("curso"),
                res.getString("email")), email);
        if (alunos != null && alunos.size() > 0) {
            return alunos.get(0);
        } else {
            return null;
        }
    }

    public void salvar(Aluno aluno) {
        String sql = "insert into alunos(nome, materia, curso, email) values(?,?,?,?)";
        db.update(sql, aluno.getNome(), aluno.getMateria(), aluno.getCurso(), aluno.getEmail());
    }

    public void excluir(String email) {
        String sql = "delete from alunos where email = ?";
        db.update(sql, email);
    }

    public void atualizar(Aluno aluno) {
        String sql = "update alunos set nome = ?, materia = ?, curso = ? where email = ?";
        db.update(sql, aluno.getNome(), aluno.getMateria(), aluno.getCurso(), aluno.getEmail());
    }

    public List<Aluno> procurar(String nome) {
        String sql = "select * from alunos where lower(nome) like ?";
        return db.query(sql, (res, rowNum) -> new Aluno(
                res.getString("nome"),
                res.getString("materia"),
                res.getString("curso"),
                res.getString("email")), "%"+nome+"%");
    }

}
