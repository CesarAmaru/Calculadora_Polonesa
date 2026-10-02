# Calculadora_Polonesa
**Versão em Java 25 do projeto TP03_Calculadora.**

Este projeto tem como foco resolver expressões matemáticas em notação polonesa reversa (notação pós-fixa) e converter as expressões de pós-fixa para infixa.

* Operações básicas suportadas:* adição(+), subtração(-), multiplicação(*), divisão(/) e potenciação(^);
* Além de:* raiz-quadrada(raiz), log(log), cosseno(cos), seno(sen) e tangente(tg);

## Compilar o projeto:
### Pré-requisitos:
* **Java Development Kit (JDK) 25 (LTS)** ou superior
* **Apache Ant** (opcional)
Verificar se possui os pré-requisitos.
```bash
java -version
javac -version
ant -version
```
#### Build com Ant:
* Na raiz do projeto execute o comando:
```bash
  ant
```
* navegue até a pasta de saída gerada pelo Ant (geralmente dist ou build) e rode:
```bash
  java -jar dist/Calculadora_Polonesa.jar
```
#### Compilar sem o Apache Ant (Apenas usando o Java 25):
* Na pasta raiz do projeto execute o comando:
```bash
mkdir bin
dir /s /b src\*.java > sources.txt && javac -d bin @sources.txt && del sources.txt
```
* Gerar o arquivo .jar com manifest.mf
```bash
jar cvfm Calculadora_Polonesa.jar manifest.mf -C bin .
```
* Executar arquivo:
```bash
java -jar Calculadora_Polonesa.jar
```
