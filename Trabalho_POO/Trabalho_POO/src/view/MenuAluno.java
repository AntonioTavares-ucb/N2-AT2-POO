package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class MenuAluno extends JFrame {
    private JTextField txtNome, txtIdade;
    private JButton btnCadastrar, btnConsultar, btnLimpar, btnVoltar;
    private JTable tabelaAlunos;
    private DefaultTableModel modeloTabela;

    public MenuAluno() {
        setTitle("Gerenciamento de Alunos");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        inicializarComponentes();
    }

    private void inicializarComponentes() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JPanel panelCadastro = criarPainelCadastro();
        panel.add(panelCadastro, BorderLayout.NORTH);

        JScrollPane scrollPane = criarTabelaAlunos();
        JPanel panelConsulta = criarPainelConsulta(scrollPane);
        panel.add(panelConsulta, BorderLayout.CENTER);

        JPanel panelBotoes = new JPanel();
        btnVoltar = new JButton("Voltar");
        panelBotoes.add(btnVoltar);
        panel.add(panelBotoes, BorderLayout.SOUTH);

        add(panel);
    }

    private JPanel criarPainelCadastro() {
        JPanel panelCadastro = new JPanel();
        panelCadastro.setLayout(new GridLayout(3, 2, 10, 10));
        panelCadastro.setBorder(BorderFactory.createTitledBorder("Cadastrar Aluno"));

        txtNome = new JTextField();
        txtIdade = new JTextField();
        btnCadastrar = new JButton("Cadastrar");

        panelCadastro.add(new JLabel("Nome:"));
        panelCadastro.add(txtNome);
        panelCadastro.add(new JLabel("Idade:"));
        panelCadastro.add(txtIdade);
        panelCadastro.add(new JLabel());
        panelCadastro.add(btnCadastrar);

        return panelCadastro;
    }

    private JScrollPane criarTabelaAlunos() {
        modeloTabela = new DefaultTableModel(new String[]{"Nome", "Idade", "Matrícula"}, 0);
        tabelaAlunos = new JTable(modeloTabela);
        return new JScrollPane(tabelaAlunos);
    }

    private JPanel criarPainelConsulta(JScrollPane scrollPane) {
        JPanel panelConsulta = new JPanel();
        panelConsulta.setLayout(new BorderLayout());
        panelConsulta.setBorder(BorderFactory.createTitledBorder("Alunos Cadastrados"));

        btnConsultar = new JButton("Consultar");
        btnLimpar = new JButton("Limpar");

        JPanel botoesConsulta = new JPanel();
        botoesConsulta.add(btnConsultar);
        botoesConsulta.add(btnLimpar);

        panelConsulta.add(botoesConsulta, BorderLayout.NORTH);
        panelConsulta.add(scrollPane, BorderLayout.CENTER);

        return panelConsulta;
    }

    public JTextField getTxtNome() { return txtNome; }
    public JTextField getTxtIdade() { return txtIdade; }
    public JButton getBtnCadastrar() { return btnCadastrar; }
    public JButton getBtnConsultar() { return btnConsultar; }
    public JButton getBtnLimpar() { return btnLimpar; }
    public JButton getBtnVoltar() { return btnVoltar; }
    public JTable getTabelaAlunos() { return tabelaAlunos; }
}
