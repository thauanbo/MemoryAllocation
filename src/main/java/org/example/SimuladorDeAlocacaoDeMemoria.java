package org.example;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class SimuladorDeAlocacaoDeMemoria extends JFrame {

    private final JComboBox<String> componetizacao, componetizacaoEscala;
    private final DefaultListModel<String>  listaDeModelosProcessoos, listaDeProcessosAlocados;
    private final List<BlocoMemoria> blocoDeMemoria;
    private final List<BlocoAlocacao> blocoDeAlocacao;
    private final List<Processo> processos;
    private final JPanel PainelDeMemoria;
    private final JButton butao, butaoResert, BotaoSimularIO;
    private final JTextField nome, tamanho;
    private final JLabel estadoDaMemoria;
    private int nextFitIndex = 0;

    public SimuladorDeAlocacaoDeMemoria() {

        super("Simulador de Alocação de Memória");

        setLayout(new BorderLayout());
        componetizacao = new JComboBox<>(new String[]{
                "First Fit", "Best Fit", "Worst Fit", "Next Fit"
        });
        componetizacaoEscala = new JComboBox<>(new String[]{
                "Escalonamento Baixo", "Escalonamento Alto"
        });
        listaDeModelosProcessoos = new DefaultListModel<>();
        listaDeProcessosAlocados = new DefaultListModel<>();
        processos = new ArrayList<>();
        blocoDeMemoria
                = new ArrayList<>(
                Arrays.asList(
                        new BlocoMemoria(0, 100),
                        new BlocoMemoria(1, 150),
                        new BlocoMemoria(2, 200),
                        new BlocoMemoria(3, 250),
                        new BlocoMemoria(4, 300),
                        new BlocoMemoria(5, 350)
                ));
        blocoDeAlocacao
                = new ArrayList<>(
                Arrays.asList(
                        new BlocoAlocacao(0, 50),
                        new BlocoAlocacao(1, 50),
                        new BlocoAlocacao(2, 50),
                        new BlocoAlocacao(3, 50),
                        new BlocoAlocacao(4, 50),
                        new BlocoAlocacao(5, 50)
                ));

        // Painel de entrada
        JPanel inputPanel = new JPanel(new GridLayout(2, 1));
        JPanel processPanel = new JPanel();
        processPanel.add(new JLabel("Nome:"));
        nome = new JTextField(5);
        processPanel.add(nome);
        processPanel.add(new JLabel("Tamanho:"));
        tamanho = new JTextField(5);
        processPanel.add(tamanho);
        butao = new JButton("Alocar");
        processPanel.add(butao);
        butaoResert = new JButton("Reiniciar");
        processPanel.add(butaoResert);
        BotaoSimularIO = new JButton("Simular E/S bloqueante");
        processPanel.add(BotaoSimularIO);
        inputPanel.add(processPanel);
        JPanel strategyPanel = new JPanel();
        strategyPanel.add(new JLabel("Estratégia:"));
        strategyPanel.add(componetizacao);
        inputPanel.add(strategyPanel);
        JPanel alocaBaixoAlto = new JPanel();
        alocaBaixoAlto.add(new JLabel("Escalonamento:"));
        alocaBaixoAlto.add(componetizacaoEscala);
        inputPanel.add(alocaBaixoAlto);
        add(inputPanel, BorderLayout.NORTH);

        // Painel de memória
        PainelDeMemoria = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                desenharBlocoMemorias(g);
            }
        };
        PainelDeMemoria.setPreferredSize(new Dimension(150, 400));
        add(PainelDeMemoria, BorderLayout.CENTER);

        // Lista de Processoos
        JList<String> processList = new JList<>(listaDeModelosProcessoos);
        add(new JScrollPane(processList), BorderLayout.EAST);
        // Lista de Alocacao
        JList<String> processListA = new JList<>(listaDeProcessosAlocados);
        add(new JScrollPane(processListA), BorderLayout.WEST);

        // Exibição do status da memória
        estadoDaMemoria = new JLabel("Memória: Total: 0KB | Ocupado: 0KB | Livre: 0KB");
        add(estadoDaMemoria, BorderLayout.SOUTH);

        // Ações
        butao.addActionListener(e -> {
            String name = nome.getText().trim();
            int size;
            try {
                size = Integer.parseInt(tamanho.getText().trim());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Tamanho inválido");
                return;
            }
            String estrategia = (String) componetizacao.getSelectedItem();
            Processo p = new Processo(name, size);
            processos.add(p);

            boolean seSucesso = switch (estrategia) {
                case "First Fit" -> alocacaoFirstFit(p);
                case "Best Fit" -> alocacaoBestFit(p);
                case "Worst Fit" -> alocacaoWorstFit(p);
                case "Next Fit" -> alocacaoNextFit(p);
                default -> false;
            };

            if (seSucesso) {
                listaDeModelosProcessoos.addElement(p.toString());
                repaint();
            } else {
                JOptionPane.showMessageDialog(this, "Não foi possível alocar o processo");
            }
            atualizarMemoryStatus();
        });

        //nova feature escalonamento
        butao.addActionListener(e -> {
            String name = nome.getText().trim();
            String nomeAlocacao = tamanho.getText().trim();
            int tamanhoAlocacao;
            try {
                tamanhoAlocacao = Integer.parseInt(nomeAlocacao);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Escalacao inválida");
                return;
            }
            String alocamento = (String) componetizacaoEscala.getSelectedItem();
            Processo p = new Processo(name, tamanhoAlocacao);
            processos.add(p);

            boolean seSucesso = switch (alocamento) {
                case "Escalonamento Baixo" -> escalonamentoBaixo(p);
                case "Escalonamento Alto" -> escalonamentoAlto(p);
                default -> false;
            };

            if (seSucesso) {
                listaDeProcessosAlocados.addElement(p.toString());
                JOptionPane.showMessageDialog(this, "Processo escalado com sucesso!");
                repaint();
            } else {
                JOptionPane.showMessageDialog(this, "Não foi possível escalonar o processo");
            }
        });

        butaoResert.addActionListener(e -> {
            processos.clear();
            listaDeModelosProcessoos.clear();
            listaDeProcessosAlocados.clear();
            blocoDeMemoria
                    .forEach(BlocoMemoria::clear);
            nextFitIndex = 0;
            atualizarMemoryStatus();
            repaint();
        });

        BotaoSimularIO.addActionListener(e -> {
            if (processos.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Nenhum processo em execução");
                return;
            }
            new Thread(() -> {
                Processo p = processos.get(new Random().nextInt(processos.size()));
                p.blocked = true;
                repaint();
                try {
                    Thread.sleep(3000); // Simula espera por E/S
                } catch (InterruptedException ignored) {
                }
                p.blocked = false;
                repaint();
            }).start();
        });

        pack();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    //logica de escalonamento
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

    private boolean escalonamentoAlto(Processo p) {
        for (BlocoMemoria block : blocoDeMemoria
        ) {
            if (block.isFree() && block.size >= p.size) {
                p.processed = false;
                return true;
            }
        }
        return false;
    }

//logica de aloca
    private boolean alocacaoFirstFit(Processo p) {
        for (BlocoMemoria block : blocoDeMemoria
        ) {
            if (block.isFree() && block.size >= p.size) {
                block.alocacao(p);
                return true;
            }
        }
        return false;
    }

    private boolean alocacaoBestFit(Processo p) {
        BlocoMemoria best = null;
        for (BlocoMemoria block : blocoDeMemoria
        ) {
            if (block.isFree() && block.size >= p.size &&
                    (best == null || block.size < best.size)) {
                best = block;
            }
        }
        if (best != null) {
            best.alocacao(p);
            return true;
        }
        return false;
    }

    private boolean alocacaoWorstFit(Processo p) {
        BlocoMemoria worst = null;
        for (BlocoMemoria block : blocoDeMemoria
        ) {
            if (block.isFree() && block.size >= p.size &&
                    (worst == null || block.size > worst.size)) {
                worst = block;
            }
        }
        if (worst != null) {
            worst.alocacao(p);
            return true;
        }
        return false;
    }

    private boolean alocacaoNextFit(Processo p) {
        int n = blocoDeMemoria
                .size();
        for (int i = 0; i < n; i++) {
            int index = (nextFitIndex + i) % n;
            BlocoMemoria block = blocoDeMemoria
                    .get(index);
            if (block.isFree() && block.size >= p.size) {
                block.alocacao(p);
                nextFitIndex = (index + 1) % n;
                return true;
            }
        }
        return false;
    }

    private void desenharBlocoMemorias(Graphics g) {
        int y = 20;
        for (BlocoMemoria block : blocoDeMemoria
        ) {
            g.setColor(block.process == null ? Color.LIGHT_GRAY : (
                    block.process.blocked ? Color.ORANGE : Color.GREEN));
            g.fillRect(50, y, 200, 40);
            g.setColor(Color.BLUE);
            g.drawRect(50, y, 200, 40);
            g.drawString("Bloco " + block.id + ": " + block.size + "KB", 60, y + 15);
            if (block.process != null) {
                g.drawString(block.process.name + " (" + block.process.size + "KB)", 60, y + 35);
            }
            y += 60;
        }
    }

    private void atualizarMemoryStatus() {
        int totalMemory = 0;
        int usedMemory = 0;
        int pageMemory = 0;
        for (BlocoMemoria block : blocoDeMemoria
        ) {
            totalMemory += block.size;
            if (block.process != null) {
                usedMemory += block.size;
            }
        }
        for (BlocoAlocacao bloco : blocoDeAlocacao) {
            if (bloco.espera < 0) {
                bloco.espera++;
            }
            pageMemory += bloco.espera;
            if (bloco.process != null) {
                pageMemory += bloco.espera;
            }
        }
        int freeMemory = totalMemory - usedMemory;
        estadoDaMemoria.setText("Memória: Total: " + totalMemory + "KB | Ocupado: " + usedMemory + "KB | Livre: " + freeMemory + "KB |  Paginação:"+ pageMemory);
    }



    public static void main(String[] args) {
        SwingUtilities.invokeLater(SimuladorDeAlocacaoDeMemoria::new);
    }

    // Classes auxiliares
    static class BlocoMemoria {
        int id, size;
        Processo process;

        int espera = 0;
        BlocoMemoria(int id, int size) {
            this.id = id;
            this.size = size;
        }

        boolean isFree() {
            return process == null;
        }

        void alocacao(Processo p) {
            this.process = p;
        }

        void clear() {
            this.process = null;
        }
    }

    static class BlocoAlocacao {

        int id;
        int espera;
        Processo process;

        BlocoAlocacao(int id, int espera) {
            this.id = id;
            this.espera = espera;
        }
    }

    static class Processo {
        String name;

        String textAlto = " [Alocado Alto]";
        String textBaixo = " [Alocado Baixo]";
        int size;
        boolean blocked = false;

        public boolean processed;

        public boolean atualizaText() {
            if (processed) {
                textBaixo = " [Alocado Baixo]";
            } else {
                textAlto = " [Alocado Alto]";
            }
            return processed;
        }
        Processo(String name, int size) {
            this.name = name;
            this.size = size;
        }

        public Processo(String name, String nomeAlocado) {
            processed = false;
        }

        public String toString() {
            return name + " (" + size + "KB" + (blocked ? " [BLOQUEADO]" : "")+(processed ? textBaixo : textAlto) + ")";
        }
    }
}
