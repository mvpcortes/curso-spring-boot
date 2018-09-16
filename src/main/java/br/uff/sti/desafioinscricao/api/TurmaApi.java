package br.uff.sti.desafioinscricao.api;

import br.uff.sti.desafioinscricao.dao.TurmaDAO;
import br.uff.sti.desafioinscricao.model.Turma;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/turma")
public class TurmaApi {

    private final TurmaDAO turmaDAO;

    public TurmaApi(TurmaDAO turmaDAO) {
        this.turmaDAO = turmaDAO;
    }

    /**
     *
     * Desafio tente usar Optional para detectar de 'atual é nulo ou não'
     */
    @GetMapping("")
    public List<Turma> findTurmasAtuais(@RequestParam(required = false) Boolean atual){

        return Optional.ofNullable(atual)
                .filter(x->x)//elimina se atual é falso
                .map(x->turmaDAO.findTurmasAtuais())
                .orElse(turmaDAO.findTurmas());
    }
}
