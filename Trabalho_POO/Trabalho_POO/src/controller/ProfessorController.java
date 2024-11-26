package controller;

import model.Professor;
import model.GerenciadorDeArquivos;
import view.MenuProfessor;
import view.MenuPrincipal;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class ProfessorController {

    private final MenuProfessor menuProfessor;
    private final GerenciadorDeArquivos gerenciadorDeArquivos;
    private List<Professor> professores;

    public ProfessorController(MenuProfessor menuProfessor) {
        this.menuProfessor = menuProfessor;
        this.gerenciadorDeArquivos = new GerenciadorDeArquivos();
        this.professores = gerenciadorDeArquivos.carregarProfessores();

        configurarListeners();
        atualizarTabela();
    }

    private void configurarListeners() {
        menuProfessor.getBtnCadastrar().addActionListener(e -> cadastrarProfessor());
        menuProfessor.getBtnConsultar().addActionListener(e -> consultarProfessor());
        menuProfessor.getBtnLimpar().addActionListener(e -> limparTabela());
        menuProfessor.getBtnVoltar().addActionListener(e -> voltarMenuPrincipal());
    }

    private void cadastrarProfessor() {
        String nome = menuProfessor.getTxtNome().getText();
        String idadeText = menuProfessor.getTxtIdade().getText();
        String especialidade = menuProfessor.getTxtEspecialidade().getText();

        if (!nome.isEmpty() && !idadeText.isEmpty() && !especialidade.isEmpty()) {
            try {
                Professor professor = new Professor(nome, Integer.parseInt(idadeText), especialidade);
                professores.add(professor);
                gerenciadorDeArquivos.salvarProfessores(professores);
                atualizarTabela();
                menuProfessor.getTxtNome().setText("");
                menuProfessor.getTxtIdade().setText("");
                menuProfessor.getTxtEspecialidade().setText("");
                JOptionPane.showMessageDialog(menuProfessor, "Professor cadastrado com sucesso!");
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(menuProfessor, "Idade inválida.");
            }
        } else {
            JOptionPane.showMessageDialog(menuProfessor, "Preencha todos os campos.");
        }
    }

    private void consultarProfessor() {
        String nome = JOptionPane.showInputDialog("Digite o nome do professor para consultar:");
        if (nome == null || nome.isEmpty()) return;

        Professor professor = professores.stream().filter(p -> p.getNome().equalsIgnoreCase(nome)).findFirst().orElse(null);

        if (professor != null) {
            int escolha = JOptionPane.showConfirmDialog(menuProfessor, "Nome: " + professor.getNome() + "\nIdade: " + professor.getIdade() + "\nEspecialidade: " + professor.getEspecialidade() + "\n\nDeseja editar este professor?", "Consultar Professor", JOptionPane.YES_NO_CANCEL_OPTION);

            if (escolha == JOptionPane.YES_OPTION) {
                editarProfessor(professor);
            } else if (escolha == JOptionPane.NO_OPTION && confirmarExclusao()) {
                professores.remove(professor);
                gerenciadorDeArquivos.salvarProfessores(professores);
                atualizarTabela();
            }
        } else {
            JOptionPane.showMessageDialog(menuProfessor, "Professor não encontrado.");
        }
    }

    private void editarProfessor(Professor professor) {
        String novoNome = JOptionPane.showInputDialog("Novo nome:", professor.getNome());
        String novaIdadeStr = JOptionPane.showInputDialog("Nova idade:", professor.getIdade());
        String novaEspecialidade = JOptionPane.showInputDialog("Nova especialidade:", professor.getEspecialidade());

        if (novoNome != null && !novoNome.isEmpty() && novaIdadeStr != null && !novaIdadeStr.isEmpty() && novaEspecialidade != null && !novaEspecialidade.isEmpty()) {
            professor.setNome(novoNome);
            professor.setIdade(Integer.parseInt(novaIdadeStr));
            professor.setEspecialidade(novaEspecialidade);
            gerenciadorDeArquivos.salvarProfessores(professores);
            atualizarTabela();
        }
    }

    private boolean confirmarExclusao() {
        return JOptionPane.showConfirmDialog(menuProfessor, "Quer excluir este professor?", "Excluir Professor", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
    }

    private void limparTabela() {
        professores.clear();
        gerenciadorDeArquivos.salvarProfessores(professores);
        atualizarTabela();
    }

    private void atualizarTabela() {
        DefaultTableModel model = (DefaultTableModel) menuProfessor.getTabelaProfessores().getModel();
        model.setRowCount(0);
        professores.forEach(professor -> model.addRow(new Object[]{professor.getNome(), professor.getIdade(), professor.getEspecialidade()}));
    }

    private void voltarMenuPrincipal() {
        menuProfessor.dispose();
        new MenuPrincipal().setVisible(true);
    }
}
