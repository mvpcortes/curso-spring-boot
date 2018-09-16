package br.uff.sti.desafioinscricao.service;

import br.uff.sti.desafioinscricao.dao.AlunoDAO;
import br.uff.sti.desafioinscricao.dao.InscricaoDAO;
import br.uff.sti.desafioinscricao.dao.TurmaDAO;
import br.uff.sti.desafioinscricao.model.AnoSemestre;
import br.uff.sti.desafioinscricao.model.Turma;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Transactional;

@Service
public class InscricaoService {


    private final InscricaoDAO inscricaoDAO;

    private final AlunoDAO alunoDAO;

    private final TurmaDAO turmaDAO;

    private final PlatformTransactionManager platformTransactionManager;

    private final AnoSemestre ANO_SEMESTRE_ATUAL = new AnoSemestre(20182);

    private final long MAX_CARGA_HORARIA = 200L;

    public InscricaoService(InscricaoDAO inscricaoDAO, AlunoDAO alunoDAO, TurmaDAO turmaDAO, PlatformTransactionManager platformTransactionManager) {
        this.inscricaoDAO = inscricaoDAO;
        this.alunoDAO = alunoDAO;
        this.turmaDAO = turmaDAO;
        this.platformTransactionManager = platformTransactionManager;
    }

    /**
     * Veja que existe duas formas de realizar o tratamento de exceções neste método. Deixar a foreign key do banco lidar
     * com a inexistencia de uma turma ou aluno. Ou checar programacionalmente. Para efeitos didáticos faremos programacionalmente.
     * Mas na prática só fazemos isto depois de dectarmos que será mais simples trabalhar e detectar erros do que usando a FK do banco.
     * Isto otimiza o desenvolvimento.
     * @param matricula
     * @param idTurma
     */
    @Transactional
    public void inscreveAluno(String matricula, long idTurma){
        final long qtdAtual = alunoDAO.getCargaHorariaTotal(matricula, ANO_SEMESTRE_ATUAL);

        final Turma turma = turmaDAO
                .findTurma(idTurma)
                .orElseThrow(()->new IllegalArgumentException(String.format("A turma %d não existe", idTurma)));

        if(!turma.getAnoSemestre().equals(this.ANO_SEMESTRE_ATUAL)){
            throw new IllegalArgumentException(String.format("A turma %d não pertence ao AnoSemestre atual[%s, %s]",
                    idTurma,
                    turma.getAnoSemestre(),
                    ANO_SEMESTRE_ATUAL));
        }

        if(!alunoDAO.existeAluno(matricula)){
            throw new IllegalArgumentException(String.format("O aluno '%s' não existe", matricula));
        }

        final long qtdPrevista = qtdAtual + turma.getCargaHoraria();

        if(qtdPrevista > MAX_CARGA_HORARIA){
            throw new IllegalArgumentException(String
                    .format("Não é possível inscrever %s na turma %d porque a carga horária será excedida [%d, %d]",
                    matricula,
                    idTurma,
                    qtdAtual,
                    qtdPrevista)
            );
        }

        inscricaoDAO.inscrever(matricula, idTurma);
    }
}
