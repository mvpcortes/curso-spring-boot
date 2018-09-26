# Grupo 0 - Primeiros passos

Vamos apresentar os conceitos e ferramentas necessárias para a realização do curso:

## Testes Automatizados

Todo software deve ser testado. Simplesmente pelo fato que é _deselegante_ você fazer um software, não verificar que ele
 está funcionando, entregá-lo ao cliente e **ele** descobrir o bug (afinal, é ele que paga seu salário Hehe).

Então, o ideal é nós implementarmos o software e depois verificarmos se ele está funcionando. E para **realizamos testes**.
Assim vemos antes de entregar ao cliente se tudo está funcionando como esperado.

Pode parecer óbvio, mas testes automatizados são testes. Porém o que faríamos manualmente, será executado por um código 
especial que programamos executando o código original. Isto é esperto, não é? Não precisaremos repetir os testes 
manualmente a cada vez que modificarmos o software e será realizado de forma muito mais rápida.

Podemos dizer que um teste automatizado possui 4 etapas:

|Nome     |Descrição|
|---------|---------|
|Configurar|cria as entradas do algoritmo e as dependências do algoritmo|
|Executar| executa o algoritmo sobre teste|
|Verificar estado| verifica o resultado da execução, se é o esperado|
|Limpar| destrói as entradas e depeendências do algoritmo|

Com isto em mente podemos agora falar dos nossos primeiros testes.

## Testes unitários
Vamos realizar testes simples que verificam um algoritmos isolado. Estes testes são chamados de testes
unitaŕios.

Na classe [AprendendoTestesUnitariosTest](/src/test/br/uff/sti/desafioinscricao/Grupo00Test.java#AprendendoTestesUnitariosTest) 
existem dois métodos de teste (que possuem a anotação `@Test`) com o básico de teste unitário.
 
### quando_soma_1_mais_1_entao_igual_a_2

Este teste é simples. O `assertEquals` verifica se a variável `soma` será igual a 2. Esta é a etapa de
verificação. Então precisamos realizar a operação e atribuir ao valor de soma:

```java    
    int soma = valorA + valorB;
```

Simples assim, seu teste deve passar após isto.

### quando_usa_somador_com_valores_10_e_20_e_soma_entao_entao_retorna_a_soma

Agora nosso teste está usando uma classe `SomaSimples`, que recebe dois argumentos no construtor,
e depois chamamos o método `soma()` para obter o valor somado e depois validar com o `assertEquals( 30, soma)`.

Então para resolver este teste é preciso implementar esta classe, armazenando os valores como campos
e depois realizar a soma destes campos no método `soma()`

```java
public class SomadorSimples {
    
    //Campos declarados para armazenar os valores
    final int valorA;
    final int valorB;

    //Construtor
    public SomadorSimples(int valorA, int valorB) {
        this.valorA = valorA;
        this.valorB = valorB;
    }

    //realiza a soma.
    public int soma(){
        return valorA + valorB;
    }
}
```

E pronto! O teste irá passar

### Saiba o que é testado
Vamos combinar uma 'regra': um teste unitário, **sempre testa algum algoritmo**, então este algoritmo é o 
**código sobre teste** (em inglês o termo é _system under test_). Parece simples, mas enquanto
não achamos o que estamos testando não faremos bons testes. Observe nossos testes anteriores: testamos
a operação de soma, e depois um método de soma. Sabemos exatamente o que testamos e temos de garantir que
o que nos propomos a testar:

* Está sendo chamado (exercitado) durante o teste;
* Dado valores de contexto (ex. entrada do método), isto gerará exatamente um valor de saída (o que verificaremos no assert)

## Dublando e Mockando
Ok, já sabemos o que temos de testar, mas um código a ser testado não depende só de argumentos como inteiros;
Na salva dos algoritmos isto é um pouco mais complicado. *Algoritmos dependem de outros*.
Podemos simplesmente ignorar este fato e sair chamando métodos por aí. Porém isto torna nossa tarefa de testar
complicada: imagina que você quer guardar no banco de dados o resultado da soma. Não é desejável alterar o banco
de dados a cada teste executado. Além de demorar, talvez esta base seja usada por nossos colegas e outros testes, e 
as coisas se tornam complicadas.
Então por que não substituir as dependências por dublês (doubles)? Na verdade, este é o caminho principal em testes
unitários. Substituímos tudo que não é código sobre teste por versões triviais ou simplificadas.

Para isto existe o framework [Mockito](https://site.mockito.org/). Ele é um framework para criar dublês durante os testes.

Então estamos prontos para o próximo teste:


### quando_usa_ValoresBanco_com_valores_300_e_301_em_teste_unitario_entao_retorna_601

`ValorBancoService` é nosso código sobre teste. Ele deveria possuir um método `somaValores()` que vai ao banco e soma
os valores A e B do banco, usando um objeto da interface `ValorBancoDAO`.

Como não queremos ir a base de dados, então vamos `mockar` a classe `ValorBancoDAO`.

Observe o techo de código:

```java

ValorBancoDAO valorBancoDAO = mock(ValorBancoDAO.class);
doReturn(valorBancoA).when(valorBancoDAO).getValorA();
doReturn(valorBancoB).when(valorBancoDAO).getValorB();

```

O mockito tenta oferecer métodos que facilitam a leitura do código. Então o que a primeira linha
de código faz é _criar um mock_ de ValorBancoDAO. A segunda linha faz o mock criado _retornar_ 
o objeto valorBancoA quando alguém chamar o método `getValorA()`. O mesmo vale para a terceira linha, 
mas para o método `getValorB()`; 

Nas próximas linhas o código sobre teste é referenciado (a classe é criada), o método é chamado
e o valor retornado é verificado.

O Mockito nos permite criar vários outros casos de dependências substituídas por dublês. Infelizmente não 
entraremos em detalhes nelas aqui. Mas nas referências existe material para aprofundamento.

### UsandoBeforeAfter

Agora que já sabemos testar e depois de construírmos alguns, será perceptível que, por ser código, 
algumas coisas podem se repetir e podemos realizar modularização em funções, classes, etc. Não se 
sinta intimidado em modularizar e refatorar o código de testes, é preciso fazer isto para melhorar a 
manutenção deles.

O Junit nos trás duas ferramentas que facilitam esta modularização, que são as anotações `@BeforeEach`
e `@AfterEach`. A primeira executa um código antes de cada um dos testes da classe e o segundo depois 
de cada um dos testes. O exemplo da classe `UsandoBeforeAfter` é idêntico ao teste anterior, porém usando estas
ferramentas.

Até agora não realizamos nenhum código da etapa _Limpar_. Isto se dá a dois motivos: Os recursos que usamos 
até agora foram da linguagem java, e ela limpa automaticamente variáveis de tipo simples no escopo das funções
quando a função é terminada e limpa automaticamente variáveis de objetos quando o Garbage Collector é executado.
O mesmo não ocorreria com a linguagem C++ por exemplo, que exigiria que deletemos os objetos da memória 
(velhos temos... rs). Porém isto não permanecerá assim quando chegarmos na parte de banco de dados, pois teremos
de deletar a _sujeira_ do banco após o teste.


## Conclusão
Agora você adquiriu um pouco de conhecimento sobre testes. Pode parecer pouco, mas são os tópicos
fundamentais para realização de testes. E, relembrando:

Vou enumerar algumas "regras". São heurísticas, na maioria dos casos funciona e te ajudarão a pensar nos próximos
testes.

### Etapas de teste

* Configurar
* Executar
* Verificar
* Limpar

Se você não sabe exatamente estas etapas em um teste, volte atrás e as defina. Elas podem ser implícitas ou 
não terem código. Mas elas estão lá e você precisa detectá-las e se assegurar que estão sendo
passadas. Caso contrário há grande chance do seu teste não... testar. 

### "Regra" de ouro (saiba o que está testando)
Acho que esta é a regra de ouro. Sem isto você não testa:

> Saiba o que você quer testar, chame exatamente o código sobre teste e verifique o contexto gerado.

### "Regra" da dependência (saiba suas entradas e dependências)
Normalmente as pessoas confudem valores de entrada do algoritmo com dependências. Esta confusão normalmente
se dá, pois ambos (pelo menos em java) são passados ao algoritmo/classe/função sobre teste como objetos construídos 
previamente. Porém existe uma diferença fundamental:

* As entradas são *valores*. Os dados que seu algoritmo computa e que refletem a dados reais. Como o CPF,
a idade, o nome, o objeto aluno. O que vai no banco de dados. Enfim, se o _código_ que está em dúvida armazena
dados do negócio modelado pelo algoritmo, provavelmente isto é um dado de entrada. Você deve usar 
os objetos reais com valores _verossímeis_.
* Dependências são *algoritmos*. Neste caso são algoritmos usados pelo algoritmo que está sobre teste. Ele normalmente 
é um outro objeto _que faz algo_ ou _vai em algum lugar_ com as entradas ou valores intermediários.
São exemplos as classes que vão no banco de dados. As classes que se comunicam com periféricos. Algoritmos
em bibliotecas utilizárias, etc. Estes itens devem ser mockados.  
