# Grupo 01 - Que comece a inscrição!

Vamos usar como exemplo uma versão simplificada da inscrição online. Achamos ideal este exemplo,
pois todos os alunos, da UFF ou não, realizam inscrição algum momento da vida acadêmica 
estarão famializados com os conceitos.
  
Nesta primeira parte três conceitos particulares nos interessam: o aluno, a turma e o ano-semestre.

> Um aluno se inscreve em disciplinas em um ano-semestre. Em outras palavras: o aluno joão-silva, de matrícula '1111' se inscreve
em uma turma 'A1' da disciplina 'COM00001' no ano-semestre 20182.

Então criamos três classes: `Aluno`, `Turma` e `AnoSemestre`. Note que elas armazenam informações "do negócio" da universidade,
e armazenam dados que devem ser manipulados pelo algoritmo. Entretanto, como usamos orientação a objetos,
alguns algoritmos ficarão "juntos" aos dados (gerando a classe). 

* O aluno possui os seguintes valores: matricula, nome e código do curso;
* A turma possui: id, codigoTurma, codigoDisciplina, anoSemestre cargaHoraria;
* A inscricao possui: matriculaAluno e idTurma;
* E o ano semestre o valor do ano-semestre em int. Onde o último algoritmo contém o semestre (1 ou 2) 
e os primeiros algoritmos o ano.

Se seu instrutor foi _bomzinho_ ele já deixou estes campos na classe. Se ele estiver afim de se divertir você terá de 
construir a classe do zero (o que torna tudo mais interessante >:))

## AlunoTest.quando_chama_toString_entao_retorna_matricula_e_nome

O primeiro teste verifica se o método toString de Aluno retornará um formato específico: matricula mais o nome, com um hífen entre eles.

Então vamos lá, implemente o toString na classe aluno e verifique se o teste passará depois disto. Você pode usar o operador `+`, usar string format
ou ainda o StringBuilder. 

## AnoSemestreTest

Vamos exercitar nossa classe de ano-semestre. Este exemplo é real: antes o ano-semestre era meramente um int e 
realizar operações nele para obter o ano e semestre era disperso no código todo das aplicações. Após a introdução da classe
o algoritmo foi agrupado junto ao dado e realizar operações sobre o ano-semestre se tornou mais seguro.

Nosso ano-semestre segue o padrão [Immutable](https://docs.oracle.com/javase/tutorial/essential/concurrency/imstrat.html), 
que é adotado pela própria classe `String` do java e nos permite uma coisa importante: um objeto se comportará como um valor isolado 
e será impossível contaminar um valor previamente armazenado por uma operação. Pois todas as operações são definidas como metodos
da classe que geram sempre novas instâncias daquela classe.

Outro detalhe do ano-semestre é que ele é um Number. Fizemos isto para que possamos ter certa capacidade de operação com outros
numbers, como o Integer ou Long.


## Obtendo o ano e o semestre
Mas afinal, podemos pegar o ano e o semestre do nosso AnoSemestre?

Os próximos 3 testes:
* quando_constroi_um_anosemestre_com_ano_semestre_valido_entao_obtem_ano_valido
* quando_constroi_um_anosemestre_com_ano_semestre_valido_entao_obtem_primeiro_semestre_valido
* quando_constroi_um_anosemestre_com_ano_semestre_valido_entao_obtem_segundo_semestre_valido

Tratam disto: verifica se os métodos `getAno()` e `getSemestre()` retornam valores esperados. Como
só um valor inteiro, precisaremos operá-lo usando divisões (`/`)e módulos (`%`) para obter estes valores. É simples.

## Ele é um número?
Os próximo testes servem para verificar se os métodos herdados de `Number` estão funcionando adequadamente:

* quando_obtem_o_valor_int_do_anosemestre_entao_ok
* quando_obtem_o_valor_long_do_anosemestre_entao_ok
* quando_obtem_o_valor_double_do_anosemestre_entao_ok
* quando_obtem_o_valor_float_do_anosemestre_entao_ok

Estes são resolvidos por cast do valor original. Mais simples que o anterior. hehehe.

## Imagina 5 semestres em um ano?

O próximos testes da classe `SemestresInvalidos` serve para garantirmos o formato do nosso ano-semestre. Ele só pode ter um primeiro (1) e um segundo (2) 
semestre. E não um quinto (5). Para isto é preciso criar uma validação no construtor de AnoSemestre e gerar a exceção 
`IllegalArgumentException`.

Este teste usa o método `assertThrows` para verificar se a exceção ocorreu. É um caso onde a verificação do resultado é 
mais complexa. Mas relaxe que tudo dará certo.
 
 
## Comparando Ano-Semestres

Também precisamos saber se o segundo ano=semestre de 2009 veio antes ou depois do primeiro ano-semestre de 2010. Para isto
o Java nos oferece a interface `Comparable` que possui um método `compareTo()` que retorna um valor inteiro de acordo com a ordem
entre dois elementos.

Podemos preencher o método `compareTo` usando ifs e os métodos getSemestre()/getAno().

<pre>
Existe uma forma mais eficiente:

Usar `int` para armazenar o valor do ano-semestre e usar o padrão ano-semestre (ano=2010, semestre = 2, ano-semestre=20102)
nos trás uma vantagem que a ordem deste valor em inteiro é similar a ordem do próprio ano-semestre. Então basta compararmos
os inteiros que será equivalente a comparar o ano e depois o semestre separadamente.

Então basta retornar a diferença entre os dois `int` dos objetos.
</pre>
   
   
## Incrementando

Incrementar o semestre de um ano-semestre é a operação mais útil, pois sempre precisamos verificar e consultar informações relativas ao
ano-semestre atual. 
Os testes das classes
 
 * `quando_anosemestre_20101_e_incrementa_semestre` 
 * `quando_anosemestre_20122_e_incrementa_semestre`

Servem para verificar se a operação de incrementar proposta pelo método `incSemestre()`. A forma mais simples de implementar este método é verificar o valor
do semestre e de acordo dele verificar se devemos incrementar o ano ou o semestre. 

## Decrementando

O método `decSemestre()` o mesmo do método de incrementar, mas fazendo o inverso. As classes:

* `quando_anosemestre_20182_e_decrementa_semestre`
* `quando_anosemestre_20201_e_decrementa_semestre`

validam esta operação. A implementação também é similar.

## Teste de Turma

Existe um último teste que verifica se uma turma quando gerada dado ano-semestre (em `int`), o método
getAnoSemestre irá adequadamente gerar um ano-semestre. O que testamos aqui é o método getAnoSemestre.

Neste caso caímos na discussão se não deveríamos mockar o AnoSemestre em algum momento para testar 
unicamente o getAnoSemestre. Entretanto isto seria tão custoso que não vale a pena. Como falei, é uma
heurística, as vezes quebrar a regra é mais vantajoso do que *forçar uma barra*  para concretizá-la.

 
## Conclusão

Pronto! Fizemos nossos primeiros algoritmos para passarem em nossos testes. Foram simples e pequenos, mas perceba
tantos conceitos precisamos trazer para realizá-lo de forma mais segura e formal. Isto nos fará feliz no futuro, com 
um algoritmo se erro e com maior qualidade para nosso cliente :D.