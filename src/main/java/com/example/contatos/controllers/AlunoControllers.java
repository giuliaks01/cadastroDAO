package com.example.contatos.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.contatos.dao.AlunoDao;
import com.example.contatos.domain.Aluno;

@Controller
public class AlunoControllers {

    @Autowired
    AlunoDao dao;

    @GetMapping(value = "/inicio")
    public ModelAndView inicio() {
        ModelAndView mv = new ModelAndView("inicio");
        mv.addObject("aluno", new Aluno());
        mv.addObject("alunos", dao.getAlunos());
        return mv;
    }

    @PostMapping(value = "/cadastrarAluno")
    public ModelAndView cadastrar(@ModelAttribute("aluno") Aluno aluno) {
        if (dao.getAlunos().isEmpty()) {
            dao.salvar(aluno);
        } else if (dao.getAlunos().contains(dao.getAluno(aluno.getEmail()))) {
            dao.atualizar(aluno);
        } else {
            dao.salvar(aluno);

        }
        return inicio();
    }

    @GetMapping(value = "/editarAluno")
    public ModelAndView editar(@RequestParam("email") String email) {
        ModelAndView mv = new ModelAndView("inicio");
        Aluno aluno = dao.getAluno(email);
        if (aluno != null) {
            mv.addObject("aluno", aluno);
        } else {
            mv.addObject("aluno", new Aluno());
        }
        mv.addObject("cadastros", dao.getAlunos());
        return mv;
    }

    @GetMapping(value = "/excluirAluno")
    public ModelAndView excluir(@RequestParam("email") String email) {
        dao.excluir(email);
        return inicio();
    }

    @GetMapping(value = "/procurarAluno")
    public ModelAndView procurar(@RequestParam("nome") String nome) {
        ModelAndView mv = new ModelAndView("inicio");
        List<Aluno> alunos = dao.procurar(nome);
        mv.addObject("alunos", alunos);
        mv.addObject("aluno", new Aluno());
        return mv;
    }

}
