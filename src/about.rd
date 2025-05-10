
1. Estrutura Geral
O programa é uma aplicação Java Swing que herda de JFrame. Ele utiliza componentes gráficos para criar uma interface que permite:


Adicionar processos com um nome e tamanho.
Escolher uma estratégia de alocação de memória (First Fit, Best Fit, Worst Fit, Next Fit).
Visualizar o estado da memória e os processos alocados.
Simular bloqueio de processos (E/S bloqueante).
Reiniciar o estado da memória.

<hr></hr>

2. Componentes da Interface Gráfica
JComboBox<String> strategyComboBox: Permite selecionar a estratégia de alocação de memória.
DefaultListModel<String> processListModel: Armazena a lista de processos para exibição.
JPanel memoryPanel: Painel onde os blocos de memória são desenhados.
Botões:1. Estrutura Geral
       O programa é uma aplicação Java Swing que herda de JFrame. Ele utiliza componentes gráficos para criar uma interface que permite:


       Adicionar processos com um nome e tamanho.
       Escolher uma estratégia de alocação de memória (First Fit, Best Fit, Worst Fit, Next Fit).
       Visualizar o estado da memória e os processos alocados.
       Simular bloqueio de processos (E/S bloqueante).
       Reiniciar o estado da memória.
       <hr></hr>
       2. Componentes da Interface Gráfica
       JComboBox<String> strategyComboBox: Permite selecionar a estratégia de alocação de memória.
       DefaultListModel<String> processListModel: Armazena a lista de processos para exibição.
       JPanel memoryPanel: Painel onde os blocos de memória são desenhados.
       Botões:
       allocateButton: Aloca um processo na memória.
       resetButton: Reinicia o estado da memória.
       simulateIOButton: Simula um processo bloqueado por E/S.
       Campos de texto:
       nameField: Nome do processo.
       sizeField: Tamanho do processo.
       JLabel memoryStatusLabel: Exibe o status da memória (total, ocupado, livre).
       <hr></hr>
       3. Lógica de Alocação
       O programa implementa quatro estratégias de alocação de memória:


       First Fit: Encontra o primeiro bloco livre que seja grande o suficiente para o processo.
       Best Fit: Encontra o menor bloco livre que seja grande o suficiente para o processo.
       Worst Fit: Encontra o maior bloco livre que seja grande o suficiente para o processo.
       Next Fit: Similar ao First Fit, mas começa a busca a partir do último bloco alocado.
       Cada estratégia é implementada em métodos separados (allocateFirstFit, allocateBestFit, etc.), que percorrem a lista de blocos de memória e tentam alocar o processo.

       <hr></hr>
       4. Atualização e Desenho da Memória
       drawMemoryBlocks(Graphics g): Desenha os blocos de memória no painel memoryPanel. Cada bloco é representado por um retângulo:
       Cinza claro: Bloco livre.
       Verde: Bloco ocupado.
       Laranja: Bloco ocupado por um processo bloqueado.
       updateMemoryStatus(): Calcula e exibe o total de memória, a memória ocupada e a memória livre.
       <hr></hr>
       5. Simulação de Bloqueio de Processos
       O botão "Simular E/S bloqueante" seleciona um processo aleatório, marca-o como bloqueado (blocked = true), e após 3 segundos, desbloqueia o processo (blocked = false). Isso é feito em uma nova thread para não travar a interface gráfica.

       <hr></hr>
       6. Classes Auxiliares
       MemoryBlock: Representa um bloco de memória.
       Atributos: id, size, process (processo alocado no bloco).
       Métodos:
       isFree(): Verifica se o bloco está livre.
       allocate(Process p): Aloca um processo no bloco.
       clear(): Libera o bloco.
       Process: Representa um processo.
       Atributos: name, size, blocked (indica se o processo está bloqueado).
       Método toString(): Retorna uma representação textual do processo.
       <hr></hr>
       7. Fluxo Principal
       O programa inicia com a criação de uma instância de MemoryALLocationSimulator.
       O usuário pode:
       Adicionar processos e alocá-los na memória.
       Escolher uma estratégia de alocação.
       Visualizar o estado da memória.
       Simular bloqueio de processos.
       Reiniciar o estado da memória.1. Estrutura Geral
                                     O programa é uma aplicação Java Swing que herda de JFrame. Ele utiliza componentes gráficos para criar uma interface que permite:


                                     Adicionar processos com um nome e tamanho.
                                     Escolher uma estratégia de alocação de memória (First Fit, Best Fit, Worst Fit, Next Fit).
                                     Visualizar o estado da memória e os processos alocados.
                                     Simular bloqueio de processos (E/S bloqueante).
                                     Reiniciar o estado da memória.
                                     <hr></hr>
                                       2. Componentes da Interface Gráfica

                                     JComboBox<String> strategyComboBox: Permite selecionar a estratégia de alocação de memória.
                                     DefaultListModel<String> processListModel: Armazena a lista de processos para exibição.
                                     JPanel memoryPanel: Painel onde os blocos de memória são desenhados.
                                     Botões:
                                     allocateButton: Aloca um processo na memória.
                                     resetButton: Reinicia o estado da memória.
                                     simulateIOButton: Simula um processo bloqueado por E/S.
                                     Campos de texto:
                                     nameField: Nome do processo.
                                     sizeField: Tamanho do processo.
                                     JLabel memoryStatusLabel: Exibe o status da memória (total, ocupado, livre).
                                     <hr></hr>
                                     3. Lógica de Alocação
                                     O programa implementa quatro estratégias de alocação de memória:


                                     First Fit: Encontra o primeiro bloco livre que seja grande o suficiente para o processo.
                                     Best Fit: Encontra o menor bloco livre que seja grande o suficiente para o processo.
                                     Worst Fit: Encontra o maior bloco livre que seja grande o suficiente para o processo.
                                     Next Fit: Similar ao First Fit, mas começa a busca a partir do último bloco alocado.
                                     Cada estratégia é implementada em métodos separados (allocateFirstFit, allocateBestFit, etc.), que percorrem a lista de blocos de memória e tentam alocar o processo.

                                     <hr></hr>
                                     4. Atualização e Desenho da Memória
                                     drawMemoryBlocks(Graphics g): Desenha os blocos de memória no painel memoryPanel. Cada bloco é representado por um retângulo:
                                     Cinza claro: Bloco livre.
                                     Verde: Bloco ocupado.
                                     Laranja: Bloco ocupado por um processo bloqueado.
                                     updateMemoryStatus(): Calcula e exibe o total de memória, a memória ocupada e a memória livre.
                                     <hr></hr>
                                     5. Simulação de Bloqueio de Processos
                                     O botão "Simular E/S bloqueante" seleciona um processo aleatório, marca-o como bloqueado (blocked = true), e após 3 segundos, desbloqueia o processo (blocked = false). Isso é feito em uma nova thread para não travar a interface gráfica.

                                     <hr></hr>
                                     6. Classes Auxiliares
                                     MemoryBlock: Representa um bloco de memória.
                                     Atributos: id, size, process (processo alocado no bloco).
                                     Métodos:
                                     isFree(): Verifica se o bloco está livre.
                                     allocate(Process p): Aloca um processo no bloco.
                                     clear(): Libera o bloco.
                                     Process: Representa um processo.
                                     Atributos: name, size, blocked (indica se o processo está bloqueado).
                                     Método toString(): Retorna uma representação textual do processo.
                                     <hr></hr>
                                     7. Fluxo Principal
                                     O programa inicia com a criação de uma instância de MemoryALLocationSimulator.
                                     O usuário pode:
                                     Adicionar processos e alocá-los na memória.
                                     Escolher uma estratégia de alocação.
                                     Visualizar o estado da memória.
                                     Simular bloqueio de processos.
                                     Reiniciar o estado da memória.
allocateButton: Aloca um processo na memória.
resetButton: Reinicia o estado da memória.
simulateIOButton: Simula um processo bloqueado por E/S.
Campos de texto:
nameField: Nome do processo.
sizeField: Tamanho do processo.
JLabel memoryStatusLabel: Exibe o status da memória (total, ocupado, livre).

<hr></hr>

3. Lógica de Alocação
O programa implementa quatro estratégias de alocação de memória:


First Fit: Encontra o primeiro bloco livre que seja grande o suficiente para o processo.
Best Fit: Encontra o menor bloco livre que seja grande o suficiente para o processo.
Worst Fit: Encontra o maior bloco livre que seja grande o suficiente para o processo.
Next Fit: Similar ao First Fit, mas começa a busca a partir do último bloco alocado.
Cada estratégia é implementada em métodos separados (allocateFirstFit, allocateBestFit, etc.), que percorrem a lista de blocos de memória e tentam alocar o processo.

<hr></hr>

4. Atualização e Desenho da Memória
drawMemoryBlocks(Graphics g): Desenha os blocos de memória no painel memoryPanel. Cada bloco é representado por um retângulo:
Cinza claro: Bloco livre.
Verde: Bloco ocupado.
Laranja: Bloco ocupado por um processo bloqueado.
updateMemoryStatus(): Calcula e exibe o total de memória, a memória ocupada e a memória livre.
<hr></hr>
5. Simulação de Bloqueio de Processos
O botão "Simular E/S bloqueante" seleciona um processo aleatório, marca-o como bloqueado (blocked = true), e após 3 segundos, desbloqueia o processo (blocked = false). Isso é feito em uma nova thread para não travar a interface gráfica.

<hr></hr>

6. Classes Auxiliares
MemoryBlock: Representa um bloco de memória.
Atributos: id, size, process (processo alocado no bloco).
Métodos:
isFree(): Verifica se o bloco está livre.
allocate(Process p): Aloca um processo no bloco.
clear(): Libera o bloco.
Process: Representa um processo.
Atributos: name, size, blocked (indica se o processo está bloqueado).
Método toString(): Retorna uma representação textual do processo.

<hr></hr>

7. Fluxo Principal
O programa inicia com a criação de uma instância de MemoryALLocationSimulator.
O usuário pode:
Adicionar processos e alocá-los na memória.
Escolher uma estratégia de alocação.
Visualizar o estado da memória.
Simular bloqueio de processos.
Reiniciar o estado da memória.