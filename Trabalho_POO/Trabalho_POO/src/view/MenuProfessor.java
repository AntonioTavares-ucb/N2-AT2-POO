package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class MenuProfessor extends JFrame {
    private JTextField txtNome, txtIdade, txtEspecialidade;
    private JButton btnCadastrar, btnConsultar, btnLimpar, btnVoltar;
    private JTable tabelaProfessores;
    private DefaultTableModel modeloTabela;

    public MenuProfessor() {
        setTitle("Gerenciamento de Professores");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        inicializarComponentes();
    }

    private void inicializarComponentes() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout(10, 10)); // Adiciona espaçamento entre os componentes

        JPanel panelCadastro = criarPainelCadastro();
        panel.add(panelCadastro, BorderLayout.NORTH);

        JScrollPane scrollPane = criarTabelaProfessores();
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
        panelCadastro.setLayout(new GridLayout(4, 2, 10, 10)); // Mais uma linha para melhor organização
        panelCadastro.setBorder(BorderFactory.createTitledBorder("Cadastrar Professor"));

        txtNome = new JTextField();
        txtIdade = new JTextField();
        txtEspecialidade = new JTextField();
        btnCadastrar = new JButton("Cadastrar");

        panelCadastro.add(new JLabel("Nome:"));
        panelCadastro.add(txtNome);
        panelCadastro.add(new JLabel("Idade:"));
        panelCadastro.add(txtIdade);
        panelCadastro.add(new JLabel("Especialidade:"));
        panelCadastro.add(txtEspecialidade);
        panelCadastro.add(new JLabel()); // Espaço vazio para alinhar o botão
        panelCadastro.add(btnCadastrar);

        return panelCadastro;
    }

    private JScrollPane criarTabelaProfessores() {
        modeloTabela = new DefaultTableModel(new String[]{"Nome", "Idade", "Especialidade"}, 0);
        tabelaProfessores = new JTable(modeloTabela);
        return new JScrollPane(tabelaProfessores);
    }

    private JPanel criarPainelConsulta(JScrollPane scrollPane) {
        JPanel panelConsulta = new JPanel();
        panelConsulta.setLayout(new BorderLayout(10, 10)); // Adicionando espaçamento
        panelConsulta.setBorder(BorderFactory.createTitledBorder("Professores Cadastrados"));

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
    public JTextField getTxtEspecialidade() { return txtEspecialidade; }
    public JButton getBtnCadastrar() { return btnCadastrar; }
    public JButton getBtnConsultar() { return btnConsultar; }
    public JButton getBtnLimpar() { return btnLimpar; }
    public JButton getBtnVoltar() { return btnVoltar; }
    public JTable getTabelaProfessores() { return tabelaProfessores; }
}
