package controller;

import model.Curso;
import model.GerenciadorDeArquivos;
import view.MenuCurso;
import view.MenuPrincipal;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class CursoController {

    private final MenuCurso menuCurso;
    private final GerenciadorDeArquivos gerenciadorDeArquivos;
    private List<Curso> cursos;

    public CursoController(MenuCurso menuCurso) {
        this.menuCurso = menuCurso;
        this.gerenciadorDeArquivos = new GerenciadorDeArquivos();
        this.cursos = gerenciadorDeArquivos.carregarCursos();

        configurarListeners();
        atualizarTabela();
    }

    private void configurarListeners() {
        menuCurso.getBtnCadastrar().addActionListener(e -> cadastrarCurso());
        menuCurso.getBtnConsultar().addActionListener(e -> consultarCurso());
        menuCurso.getBtnLimpar().addActionListener(e -> limparTabela());
        menuCurso.getBtnVoltar().addActionListener(e -> voltarMenuPrincipal());
    }

    private void cadastrarCurso() {
        String nome = menuCurso.getTxtNome().getText();
        String cargaHorariaText = menuCurso.getTxtCargaHoraria().getText();

        if (!nome.isEmpty() && !cargaHorariaText.isEmpty()) {
            try {
                int cargaHoraria = Integer.parseInt(cargaHorariaText);
                if (cargaHoraria <= 0) {
                    JOptionPane.showMessageDialog(menuCurso, "Carga horária deve ser um número positivo.");
                    return;
                }

                Curso curso = new Curso(nome, cargaHoraria);
                cursos.add(curso);
                gerenciadorDeArquivos.salvarCursos(cursos);
                atualizarTabela();
                menuCurso.getTxtNome().setText("");
                menuCurso.getTxtCargaHoraria().setText("");
                JOptionPane.showMessageDialog(menuCurso, "Curso cadastrado com sucesso!");
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(menuCurso, "Carga horária inválida.");
            }
        } else {
            JOptionPane.showMessageDialog(menuCurso, "Preencha todos os campos.");
        }
    }

    private void consultarCurso() {
        String nomeConsulta = JOptionPane.showInputDialog("Digite o nome do curso para consulta:");
        if (nomeConsulta == null || nomeConsulta.isEmpty()) return;

        Curso curso = cursos.stream().filter(c -> c.getNome().equalsIgnoreCase(nomeConsulta)).findFirst().orElse(null);
        if (curso != null) {
            int escolha = JOptionPane.showConfirmDialog(menuCurso, "Nome: " + curso.getNome() + "\nCarga Horária: " + curso.getCargaHoraria() + "\n\nDeseja editar este curso?", "Consultar Curso", JOptionPane.YES_NO_OPTION);
            if (escolha == JOptionPane.YES_OPTION) {
                editarCurso(curso);
            } else if (escolha == JOptionPane.NO_OPTION && confirmarExclusao()) {
                cursos.remove(curso);
                gerenciadorDeArquivos.salvarCursos(cursos);
                atualizarTabela();
            }
        } else {
            JOptionPane.showMessageDialog(menuCurso, "Curso não encontrado.");
        }
    }

    private void editarCurso(Curso curso) {
        String novoNome = JOptionPane.showInputDialog("Novo nome:", curso.getNome());
        String novaCargaHorariaStr = JOptionPane.showInputDialog("Nova carga horária:", curso.getCargaHoraria());

        if (novoNome != null && !novoNome.isEmpty() && novaCargaHorariaStr != null && !novaCargaHorariaStr.isEmpty()) {
            try {
                int novaCargaHoraria = Integer.parseInt(novaCargaHorariaStr);
                if (novaCargaHoraria <= 0) {
                    JOptionPane.showMessageDialog(menuCurso, "Carga horária deve ser um número positivo.");
                    return;
                }
                curso.setNome(novoNome);
                curso.setCargaHoraria(novaCargaHoraria);
                gerenciadorDeArquivos.salvarCursos(cursos);
                atualizarTabela();
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(menuCurso, "Carga horária inválida.");
            }
        }
    }

    private boolean confirmarExclusao() {
        return JOptionPane.showConfirmDialog(menuCurso, "Quer excluir este curso?", "Excluir Curso", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
    }

    private void excluirCurso() {
        String nomeConsulta = JOptionPane.showInputDialog("Digite o nome do curso para exclusão:");
        if (nomeConsulta == null || nomeConsulta.isEmpty()) return;

        Curso curso = cursos.stream().filter(c -> c.getNome().equalsIgnoreCase(nomeConsulta)).findFirst().orElse(null);
        if (curso != null) {
            if (confirmarExclusao()) {
                cursos.remove(curso);
                gerenciadorDeArquivos.salvarCursos(cursos);
                atualizarTabela();
                JOptionPane.showMessageDialog(menuCurso, "Curso excluído com sucesso!");
            }
        } else {
            JOptionPane.showMessageDialog(menuCurso, "Curso não encontrado para exclusão.");
        }
    }
    
    private void limparTabela() {
        cursos.clear();
        gerenciadorDeArquivos.salvarCursos(cursos);
        atualizarTabela();
    }

    private void atualizarTabela() {
        DefaultTableModel model = (DefaultTableModel) menuCurso.getTabelaCursos().getModel();
        model.setRowCount(0);
        cursos.forEach(curso -> model.addRow(new Object[]{curso.getNome(), curso.getCargaHoraria()}));
    }

    private void voltarMenuPrincipal() {
        menuCurso.dispose();
        new MenuPrincipal().setVisible(true);
    }
}
