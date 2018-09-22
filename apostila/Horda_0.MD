# Horda 0 - Nosso armamento

Vamos apresentar os conceitos e ferramentas necessárias para prosseguirmos:

## Testes Automatizados

Todo software deve ser testado. Simplesmente pelo fato que é _deselegante_ você fazer um software, não testá-lo para 
garantir que está funcionando e entregá-lo ao cliente e **ele (quem avalia o que você faz e paga seu salário)** 
descobrir o bug. 

Então, nós implementamos o software e depois executamos ele para testar. Usamos alguns dados simulados e vemos se ele 
responde de forma adequada. Isto em um ambiente seguro e separado do ambiente real (de produção). Assim **verificamos** 
que o programado está fazendo o que queríamos que ele fizesse, e não o usuário.

Testes automatizados são tipo isto, porém mais espertos, pois não precisamos realizá-los manualmente,
mas programamos um código que realiza o teste, de forma mais simples, rápida e formal.

Um teste automatizado possui 4 etapas:

|Nome     |Descrição|
|---------|---------|
|Configurar|cria as entradas do algoritmo e as dependências do algoritmo|
|Executar| executa o algoritmo sobre teste|
|Verificar estado| verifica o resultado da execução, se é o esperado|
|Limpar| destrói as entradas e depeendências do algoritmo|

## 