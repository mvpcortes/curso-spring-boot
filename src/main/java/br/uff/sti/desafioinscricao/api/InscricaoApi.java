package br.uff.sti.desafioinscricao.api;

import br.uff.sti.desafioinscricao.model.Inscricao;
import br.uff.sti.desafioinscricao.service.InscricaoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/inscricao")
public class InscricaoApi {

    private final InscricaoService inscricaoService;


    public InscricaoApi(InscricaoService inscricaoService) {
        this.inscricaoService = inscricaoService;
    }

    @PostMapping()
    public ResponseEntity<Void> criaInscricao(@RequestBody Inscricao inscricao){
        inscricaoService.inscreveAluno(inscricao.getMatriculaAluno(), inscricao.getIdTurma());
        return ResponseEntity.ok().build();
    }
}
