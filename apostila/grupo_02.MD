# Grupo 02 - Contando a Carga Horária

Nas regras atuais do MEC, a carga horária cursada por um aluno é algo importante, pois ela é usada
para delimitar o escopo do curso de graduação e quanto aquele aluno concluiu o curso. 

Usaremos uma versão simplificada deste modelo, onde a Turma possui uma carga horária e a carga horária
de um aluno é calculada através das turmas onde o aluno está inscrito.

Mas antes disto vamos abrir um parênteses para explicar um tópico importante: [Optional](https://docs.oracle.com/javase/8/docs/api/java/util/Optional.html).

## O "bug" de 1 milhão de dólares

[Tony Hoare](https://en.wikipedia.org/wiki/Tony_Hoare) é um cientista da computação de estudou, criou e colabororou com várias coisas importantes, como 
o algoritmo quick-sort. E também a referência ao null. Em apresentação recente, ele se desculpa pela criação
da referência ao null, pois embora tenha sido uma solução simples "... gerou inúmeros erros, vulnerabilidades e
falhas nas aplicações. E possivelmente gerou um bilhão de dólares em dor e sofrimento nos últimos quarenta anos."
 
Para entender o "erro" na criação de um recurso que é usado até hoje, existe nas principais linguagens e se torna
peça fundamental no desenvolvimento de software. É preciso entender quais funções uma referência a null possui.

A primeira coisa a saber que _null_ representa algo _físico_, ele representa um valor zerado em determinado endereço de memória.
Isto é, é o nome que se dá a um estado de um endereço de memória com um valor especial, o zero.

Em organização de computadores, esta nomenclatura é fundamental, pois o valor zero equivale ao "terra" em física. É o valor
"neutro" e serve para dizer que aquele campo de memória possui um valor não definido, (literalmente nulo).

O problema está em modelar outros domínios com _null_. E entendo as desculpas de Hoare neste sentido.

Em java, até a versão 7, convencionou-se usar o valor _null_ para representar a inexistência de um objeto,
em especial após sua a procura utilizando uma função. Isto é ruim, pois usa-se um conceito de organização de computadores
(_null_) para representar um problema do mundo real (o objeto procurado não existe).

Em outras palavras, no _null_ de organização de computadores, é uma convenção que indica que o valor não é definido.
E é fundamental para solucionar o problema de representar informações na memória do computador. Já quando
você precisa reprentar que um objeto não existe ou não foi encotrado, ele não é tão ideal.

O principal motivo é que não deixa claro ao programador que aquele valor *pode* ser nulo, e não dá instrumentos
para lembrar ou forçar que o programador tratar o valor nulo. Ou pelo menos, definir um valor padrão.

Para lidar com isto, no Java 8 foi definido a classe _Optional_. Ela serve justamente para representar esta segunda situação:
O objeto não foi encontrado. O retorno da função é _opcional_ e pode não existir.

O _Optional_ não lhe dá o valor direto. Você pode pegá-lo diretamente com `get()`, correndo o risco de obter um `NullPointerException` 
como usando diretamente o `null`. Porém, o ideal é definir um valor padrão com `orElse(...)` ou transformá-lo usando o método 
`map(...)`. Isto 'obriga' o desenvolvedor a lidar com a possibilidade daquele valor não existir e tratar esta situação. Evitando
o `NullPointerException`.

Mas voltando aos nossos testes, os contidos na classe `quando_usando_Optional` mostram exemplos de uso de Optional.
Sinta-se livre para alterar alguns deles para entender como funciona a Optional. Ela será usada algumas vezes nos próximos
testes.

## Contando carga horária

Os testes contidos na classe `quando_aluno` são alinhados: existem classes dentro de classes, com métodos @BeforeEach 
e @AfterEach para construir elementos a serem usados pelos métodos da classes. evita a repetição do código que constrói
os dados necessários para os testes.

Além disto, os testes de agora servirão para exercitarmos basicamente um comando SQL. Eu sei, prometemos que o curso é de JAVA,
mas é praxe uma aplicação Web conectar-se a um banco de dados e usar SQL para realizar as consultas é muito importante para
estas aplicações. Será só um pouco de SQL, prometo >:)




 
 