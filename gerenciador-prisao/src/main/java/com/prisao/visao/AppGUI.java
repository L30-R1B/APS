package com.prisao.visao;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.prisao.controle.gerenciamento.pessoa.GerenciaPrisioneiros;
import com.prisao.modelo.local.Cela;
import com.prisao.modelo.pessoa.Prisioneiro;

public class AppGUI extends JFrame {
    private JTextArea displayArea;

    public AppGUI() {
        setTitle("Gerenciador de Prisão");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        displayArea = new JTextArea();
        displayArea.setEditable(false);
        displayArea.setLineWrap(true);
        displayArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(displayArea);
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(5, 1));

        JButton btnCadastrar = new JButton("Cadastrar Prisioneiro");
        JButton btnDesvincular = new JButton("Desvincular Prisioneiro");
        JButton btnPesquisar = new JButton("Pesquisar Prisioneiro");
        JButton btnPrintCela = new JButton("Informações da Cela");
        JButton btnEncerrar = new JButton("Encerrar");

        buttonPanel.add(btnCadastrar);
        buttonPanel.add(btnDesvincular);
        buttonPanel.add(btnPesquisar);
        buttonPanel.add(btnPrintCela);
        buttonPanel.add(btnEncerrar);

        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.EAST);

        btnCadastrar.addActionListener(e -> cadastrarPrisioneiro());
        btnDesvincular.addActionListener(e -> desvincularPrisioneiro());
        btnPesquisar.addActionListener(e -> pesquisarPrisioneiro());
        btnPrintCela.addActionListener(e -> printInformacoesCela());
        btnEncerrar.addActionListener(e -> System.exit(0));
    }

    private void cadastrarPrisioneiro() {
        try {
            int identificador = Integer.parseInt(JOptionPane.showInputDialog("Digite o identificador único do prisioneiro:"));
            String nome = JOptionPane.showInputDialog("Digite o nome do prisioneiro:");
            String crime = JOptionPane.showInputDialog("Digite o crime cometido:");
            float pena = Float.parseFloat(JOptionPane.showInputDialog("Digite a pena em anos:"));
            String comportamento = JOptionPane.showInputDialog("Digite o comportamento do prisioneiro:");

            boolean sucesso = GerenciaPrisioneiros.cadastrarPrisioneiro(identificador, nome, crime, pena, comportamento);
            displayArea.append(sucesso ? "Prisioneiro cadastrado com sucesso.\n" : "Falha ao cadastrar prisioneiro.\n");
        } catch (Exception ex) {
            displayArea.append("Erro: " + ex.getMessage() + "\n");
        }
    }

    private void desvincularPrisioneiro() {
        try {
            int identificador = Integer.parseInt(JOptionPane.showInputDialog("Digite o identificador do prisioneiro:"));
            boolean sucesso = GerenciaPrisioneiros.desvincularPrisioneiro(identificador);
            displayArea.append(sucesso ? "Prisioneiro desvinculado com sucesso.\n" : "Falha ao desvincular prisioneiro.\n");
        } catch (Exception ex) {
            displayArea.append("Erro: " + ex.getMessage() + "\n");
        }
    }

    private void pesquisarPrisioneiro() {
        try {
            int identificador = Integer.parseInt(JOptionPane.showInputDialog("Digite o identificador do prisioneiro:"));
            Prisioneiro prisioneiro = GerenciaPrisioneiros.pesquisarPrisioneiro(identificador);
            if (prisioneiro != null) {
                displayArea.append("Informações do Prisioneiro:\n");
                displayArea.append("    ID :" + prisioneiro.getIdentificador() + "\n    Nome : " + prisioneiro.getNome() + "\n    Crime : " + prisioneiro.getCrime() + "\n    Pena : " + prisioneiro.getPena() + " anos\n    Comportamento : " + prisioneiro.getComportamento() + "\n");
            } else {
                displayArea.append("Prisioneiro não encontrado.\n");
            }
        } catch (Exception ex) {
            displayArea.append("Erro: " + ex.getMessage() + "\n");
        }
    }

    private void printInformacoesCela() {
        StringBuilder infoCela = new StringBuilder();
        infoCela.append("Informações da Cela:\n");
        infoCela.append("\t\tIDENTIFICADOR DA CELA : " + Cela.getInstancia().getIdentificador() + "\n");
        infoCela.append("\t\tCAPACIDADE MÁXIMA     : (" + Cela.getInstancia().getPrisioneiros().size() + "/" + Cela.getInstancia().getCapacidadeMaxima() + ")\n");
        infoCela.append("\t\tNÍVEL DE SEGURANÇA    : " + Cela.getInstancia().getNivelSeguranca() + "\n");
        infoCela.append("\t\tLISTA DE PRISIONEIROS : {\n");
    
        if (!Cela.getInstancia().getPrisioneiros().isEmpty()) {
            for (Prisioneiro prisioneiroAtual : Cela.getInstancia().getPrisioneiros()) {
                infoCela.append("    ID :" + prisioneiroAtual.getIdentificador() + "; Nome : " + prisioneiroAtual.getNome() + "; Crime : " + prisioneiroAtual.getCrime() + "; Pena : " + prisioneiroAtual.getPena() + " anos; Comportamento : " + prisioneiroAtual.getComportamento() + "\n");
            }
        } else {
            infoCela.append("    NENHUM\n");
        }
        infoCela.append("\t\t}\n");
    
        JOptionPane.showMessageDialog(this, infoCela.toString(), "Informações da Cela", JOptionPane.INFORMATION_MESSAGE);
    }
    
}
