package br.uff.sti.desafioinscricao.controller;

import br.uff.sti.desafioinscricao.dao.TurmaDAO;
import br.uff.sti.desafioinscricao.model.Inscricao;
import br.uff.sti.desafioinscricao.service.InscricaoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("inscricao")
public class InscricaoController {

    private final InscricaoService inscricaoService;

    private final TurmaDAO turmaDAO;

    public InscricaoController(InscricaoService inscricaoService, TurmaDAO turmaDAO) {
        this.inscricaoService = inscricaoService;
        this.turmaDAO = turmaDAO;
    }

    @GetMapping
    public String telaInscricao(Model model){
        model.addAttribute("turmas", turmaDAO.findTurmasAtuais());
        return "inscricao";
    }

    @PostMapping
    public String inscrever(@ModelAttribute Inscricao inscricao, Model model){

        try{
            inscricaoService.inscreveAluno(inscricao.getMatriculaAluno(), inscricao.getIdTurma());
        }catch(Exception e){
            model.addAttribute("erro", e.getMessage());
            return "inscricao";
        }

        model.addAttribute("matriculaAluno", inscricao.getMatriculaAluno());
        model.addAttribute("descTurma", turmaDAO.findTurma(inscricao.getIdTurma()).map(t->t.toString()).orElse("n/a"));

        return "inscricao_sucesso";
    }
}
