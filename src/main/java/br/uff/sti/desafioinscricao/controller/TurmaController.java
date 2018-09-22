package br.uff.sti.desafioinscricao.controller;

import br.uff.sti.desafioinscricao.dao.TurmaDAO;
import br.uff.sti.desafioinscricao.service.InscricaoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("turma")
public class TurmaController {

    private final TurmaDAO turmaDAO;

    private final InscricaoService inscricaoService;

    public TurmaController(TurmaDAO turmaDAO, InscricaoService inscricaoService) {
        this.turmaDAO = turmaDAO;
        this.inscricaoService = inscricaoService;
    }

    @GetMapping()
    public String turmas(Model model){
        model.addAttribute("anoSemestre", inscricaoService.getAnoSemestreAtual());
        model.addAttribute("turmas", turmaDAO.findTurmasAtuais());
        return "turma";
    }
}