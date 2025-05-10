<img src="https://raw.githubusercontent.com/thauanbo/thauanbo/refs/heads/main/img/banner-project.png" alt="Banner" width="100%">

# Memory Allocation

Este projeto é uma Atividade da Faculdade para Aprendizagem da Alocação de Memória em Java.
## Funcionalidades

- Blocos de Memória para simular a alocação de memória

- Botão para simular E/S bloqueante

- Menu para escolha Estratégia de Alocação de Memória

- Menu para escolha de Escalonamento de Processos

## Estrutura do Projeto

```
meu-projeto/
├── .env
├── src/
│   └── main
|   ├── org.example
│   │   ├── SimuladorDeAlocacaoDeMemoria.java
├── pom.xml
└── README.md
```
## Stack utilizada

**Back-end:**
Java


## Clone o Projeto

```bash
  git clone https://github.com/thauanbo/MemoryAllocation.git
```


## Uso/Exemplos

Escalonamento de Memória
```Java
 private boolean escalonamentoBaixo(Processo p) {
for (BlocoMemoria block : blocoDeMemoria
) {
if (block.isFree() && block.size >= p.size) {
try{
Thread.sleep(3000);
}catch (InterruptedException ignored) {
}
p.processed = true;
return true;
}else {
for (BlocoAlocacao bloco : blocoDeAlocacao) {
if (block.espera == 0) {
block.process = bloco.process;
block.espera = 1;
return true;
}
}
}
}
return false;
}
```


## 🔗 Links
[![github](https://img.shields.io/badge/my_portfolio-000?style=for-the-badge&logo=ko-fi&logoColor=white)](https://github.com/thauanbo)
[![linkedin](https://img.shields.io/badge/linkedin-0A66C2?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/thauan-barbosa/)
