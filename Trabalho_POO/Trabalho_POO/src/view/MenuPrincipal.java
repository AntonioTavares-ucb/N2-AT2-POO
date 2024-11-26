package view;

import javax.swing.*;
import java.awt.*;
import controller.AlunoController;
import controller.CursoController;
import controller.ProfessorController;

public class MenuPrincipal extends JFrame {

    public MenuPrincipal() {
        setTitle("Menu Principal");
        setSize(300, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Fechamento do programa ao fechar a janela
        setLocationRelativeTo(null); //centraliza a janela
        inicializarComponentes();
    }

    public void inicializarComponentes() {
        setLayout(null);

        JLabel titulo = new JLabel("Menu Principal", SwingConstants.CENTER);
        titulo.setBounds(50, 20, 200, 30);
        add(titulo);

        JButton aluno = new JButton("Aluno");
        aluno.setBounds(75, 60, 140, 40);
        add(aluno);

        JButton professor = new JButton("Professor");
        professor.setBounds(75, 110, 140, 40);
        add(professor);

        JButton curso = new JButton("Curso");
        curso.setBounds(75, 160, 140, 40);
        add(curso);

        JButton botaoInferior = new JButton("Sair");
        botaoInferior.setBounds(85, 240, 120, 40);
        add(botaoInferior);

        aluno.addActionListener(e -> {
            MenuAluno menuAluno = new MenuAluno();  // Cria uma instancia
            AlunoController alunoController = new AlunoController(menuAluno);  // Cria o controller para menuAluno, para manipulacao
            menuAluno.setVisible(true);  // Exibe o menuAluno
            dispose();  // Fecha o menuPrincipal
        });

        professor.addActionListener(e -> {
            MenuProfessor menuProfessor = new MenuProfessor();
            ProfessorController professorController = new ProfessorController(menuProfessor);
            menuProfessor.setVisible(true);
            dispose();
        });

        curso.addActionListener(e -> {
            MenuCurso menuCurso = new MenuCurso();
            CursoController cursoController = new CursoController(menuCurso);
            menuCurso.setVisible(true);
            dispose();
        });

        botaoInferior.addActionListener(e -> {
            int resposta = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja sair?", "Confirmação", JOptionPane.YES_NO_OPTION);
            if (resposta == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });
    }
}
