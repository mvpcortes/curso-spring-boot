DROP TABLE  IF EXISTS valor_banco;
DROP TABLE  IF EXISTS aluno;
DROP TABLE  IF EXISTS turma;
DROP TABLE  IF EXISTS inscricao;

CREATE TABLE valor_banco(
    nome VARCHAR(255)   NOT NULL,
    valor INT           NOT NULL
);


CREATE TABLE aluno(

    matricula           VARCHAR(255) NOT NULL,
    nome                VARCHAR(255) NOT NULL,
    codigoCurso         VARCHAR(255) NOT NULL
);

CREATE TABLE turma(
    id                  BIGINT          NOT NULL,
    codigo_turma        VARCHAR(10)     NOT NULL,
    codigo_disciplina   VARCHAR(10)     NOT NULL,
    ano_semestre        INT             NOT NULL,
    carga_horaria       INT             NOT NULL
);

CREATE TABLE inscricao(
    matricula_aluno     VARCHAR(255)    NOT NULL,
    id_turma            BIGINT          NOT NULL,
    CONSTRAINT fk_inscricao_aluno       FOREIGN KEY (matricula_aluno) REFERENCES aluno(matricula),
    CONSTRAINT fk_inscricao_turma       FOREIGN KEY (id_turma)        REFERENCES turma(id)
);