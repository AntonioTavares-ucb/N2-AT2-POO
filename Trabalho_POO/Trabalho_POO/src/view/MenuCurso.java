package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class MenuCurso extends JFrame {
    private JTextField txtNome, txtCargaHoraria;
    private JButton btnCadastrar, btnConsultar, btnLimpar, btnVoltar;
    private JTable tabelaCursos;
    private DefaultTableModel modeloTabela;

    public MenuCurso() {
        setTitle("Gerenciamento de Cursos");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        inicializarComponentes();
    }

    private void inicializarComponentes() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JPanel panelCadastro = criarPainelCadastro();
        panel.add(panelCadastro, BorderLayout.NORTH);

        JScrollPane scrollPane = criarTabelaCursos();
        JPanel panelConsulta = criarPainelConsulta(scrollPane);
        panel.add(panelConsulta, BorderLayout.CENTER);

        JPanel panelBotoes = criarPainelBotoes();
        panel.add(panelBotoes, BorderLayout.SOUTH);

        add(panel);
    }

    private JPanel criarPainelCadastro() {
        JPanel panelCadastro = new JPanel();
        panelCadastro.setLayout(new GridLayout(3, 2, 10, 10));
        panelCadastro.setBorder(BorderFactory.createTitledBorder("Cadastrar Curso"));

        txtNome = new JTextField();
        txtCargaHoraria = new JTextField();
        btnCadastrar = new JButton("Cadastrar");

        panelCadastro.add(new JLabel("Nome do Curso:"));
        panelCadastro.add(txtNome);
        panelCadastro.add(new JLabel("Carga Horária:"));
        panelCadastro.add(txtCargaHoraria);
        panelCadastro.add(new JLabel());
        panelCadastro.add(btnCadastrar);

        return panelCadastro;
    }

    private JScrollPane criarTabelaCursos() {
        modeloTabela = new DefaultTableModel(new String[]{"Nome", "Carga Horária"}, 0);
        tabelaCursos = new JTable(modeloTabela);
        return new JScrollPane(tabelaCursos);
    }

    private JPanel criarPainelConsulta(JScrollPane scrollPane) {
        JPanel panelConsulta = new JPanel();
        panelConsulta.setLayout(new BorderLayout());
        panelConsulta.setBorder(BorderFactory.createTitledBorder("Cursos Cadastrados"));

        JPanel botoesConsulta = new JPanel();
        btnConsultar = new JButton("Consultar");
        btnLimpar = new JButton("Limpar");

        botoesConsulta.add(btnConsultar);
        botoesConsulta.add(btnLimpar);

        panelConsulta.add(botoesConsulta, BorderLayout.NORTH);
        panelConsulta.add(scrollPane, BorderLayout.CENTER);

        return panelConsulta;
    }

    private JPanel criarPainelBotoes() {
        JPanel panelBotoes = new JPanel();
        btnVoltar = new JButton("Voltar");
        panelBotoes.add(btnVoltar);
        return panelBotoes;
    }

    public JTextField getTxtNome() { return txtNome; }
    public JTextField getTxtCargaHoraria() { return txtCargaHoraria; }
    public JButton getBtnCadastrar() { return btnCadastrar; }
    public JButton getBtnConsultar() { return btnConsultar; }
    public JButton getBtnLimpar() { return btnLimpar; }
    public JButton getBtnVoltar() { return btnVoltar; }
    public JTable getTabelaCursos() { return tabelaCursos; }
    public DefaultTableModel getModeloTabela() { return modeloTabela; }
}
