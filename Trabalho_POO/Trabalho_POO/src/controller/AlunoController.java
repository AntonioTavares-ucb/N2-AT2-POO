package controller;

import model.Aluno;
import model.GerenciadorDeArquivos;
import view.MenuAluno;
import view.MenuPrincipal;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class AlunoController {

    private final MenuAluno menuAluno;
    private final GerenciadorDeArquivos gerenciadorDeArquivos;
    private List<Aluno> alunos;

    public AlunoController(MenuAluno menuAluno) {
        this.menuAluno = menuAluno;
        this.gerenciadorDeArquivos = new GerenciadorDeArquivos();
        this.alunos = gerenciadorDeArquivos.carregarAlunos();

        configurarListeners();
        atualizarTabela();
    }

    private void configurarListeners() {
        menuAluno.getBtnCadastrar().addActionListener(e -> cadastrarAluno());
        menuAluno.getBtnConsultar().addActionListener(e -> consultarAluno());
        menuAluno.getBtnLimpar().addActionListener(e -> limparTabela());
        menuAluno.getBtnVoltar().addActionListener(e -> voltarMenuPrincipal());
    }

    private void cadastrarAluno() {
        String nome = menuAluno.getTxtNome().getText();
        String idadeText = menuAluno.getTxtIdade().getText();

        if (!nome.isEmpty() && !idadeText.isEmpty()) {
            try {
                Aluno aluno = new Aluno(nome, Integer.parseInt(idadeText));
                alunos.add(aluno);
                gerenciadorDeArquivos.salvarAlunos(alunos);
                atualizarTabela();
                menuAluno.getTxtNome().setText("");
                menuAluno.getTxtIdade().setText("");
                JOptionPane.showMessageDialog(menuAluno, "Aluno cadastrado com sucesso!");
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(menuAluno, "Idade inválida.");
            }
        } else {
            JOptionPane.showMessageDialog(menuAluno, "Preencha todos os campos.");
        }
    }

    private void consultarAluno() {
        String matriculaStr = JOptionPane.showInputDialog("Digite a matrícula do aluno para consultar:");
        if (matriculaStr == null || matriculaStr.isEmpty()) return;

        try {
            int matricula = Integer.parseInt(matriculaStr);
            Aluno aluno = alunos.stream().filter(a -> a.getMatricula() == matricula).findFirst().orElse(null);

            if (aluno != null) {
                int escolha = JOptionPane.showConfirmDialog(menuAluno, "Nome: " + aluno.getNome() + "\nIdade: " + aluno.getIdade() + "\nMatrícula: " + aluno.getMatricula() + "\n\nDeseja editar este aluno?", "Consultar Aluno", JOptionPane.YES_NO_CANCEL_OPTION);

                if (escolha == JOptionPane.YES_OPTION) {
                    editarAluno(aluno);
                } else if (escolha == JOptionPane.NO_OPTION && confirmarExclusao()) {
                    alunos.remove(aluno);
                    gerenciadorDeArquivos.salvarAlunos(alunos);
                    atualizarTabela();
                }
            } else {
                JOptionPane.showMessageDialog(menuAluno, "Aluno não encontrado.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(menuAluno, "Matrícula inválida.");
        }
    }

    private void editarAluno(Aluno aluno) {
        String novoNome = JOptionPane.showInputDialog("Novo nome:", aluno.getNome());
        String novaIdadeStr = JOptionPane.showInputDialog("Nova idade:", aluno.getIdade());

        if (novoNome != null && !novoNome.isEmpty() && novaIdadeStr != null && !novaIdadeStr.isEmpty()) {
            aluno.setNome(novoNome);
            aluno.setIdade(Integer.parseInt(novaIdadeStr));
            gerenciadorDeArquivos.salvarAlunos(alunos);
            atualizarTabela();
        }
    }

    private boolean confirmarExclusao() {
        return JOptionPane.showConfirmDialog(menuAluno, "Quer excluir este aluno?", "Excluir Aluno", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
    }

    private void limparTabela() {
        alunos.clear();
        gerenciadorDeArquivos.salvarAlunos(alunos);
        atualizarTabela();
    }

    private void atualizarTabela() {
        DefaultTableModel model = (DefaultTableModel) menuAluno.getTabelaAlunos().getModel();
        model.setRowCount(0);
        alunos.forEach(aluno -> model.addRow(new Object[]{aluno.getNome(), aluno.getIdade(), aluno.getMatricula()}));
    }

    private void voltarMenuPrincipal() {
        menuAluno.dispose();
        new MenuPrincipal().setVisible(true);
    }
}

