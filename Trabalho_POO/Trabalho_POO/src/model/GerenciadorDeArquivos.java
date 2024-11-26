package model;

import java.io.*;  // Importa classes de entrada e saída
import java.util.ArrayList;  //ArrayList, armazenar listas de objetos
import java.util.List;  // List para definir listas de alunos

public class GerenciadorDeArquivos {

    public void salvarAlunos(List<Aluno> alunos) {  //salvar lista de alunos em arquivo de texto
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("alunos.txt"))) {  //FileWriter para escrever no arquivo
            for (Aluno aluno : alunos) {  // Percorre a lista de alunos
                writer.write(aluno.getNome() + "," + aluno.getIdade() + "," + aluno.getMatricula() + "\n");
            }
        } catch (IOException e) {  // Captura exceções de entrada e saída
            e.printStackTrace();  // rastreamento de pilha em caso de erro
        }
    }

    public List<Aluno> carregarAlunos() {  // Carregar alunos de um arquivo de texto
        List<Aluno> alunos = new ArrayList<>();  // Cria uma nova lista de alunos
        try (BufferedReader reader = new BufferedReader(new FileReader("alunos.txt"))) {  // FileReader para ler o arquivo
            String linha;
            while ((linha = reader.readLine()) != null) {  // Lê cada linha do arquivo enquanto houver linhas
                String[] partes = linha.split(",");  // Divide a linha em partes, separadas por vírgulas
                String nome = partes[0];  // Nome do aluno
                int idade = Integer.parseInt(partes[1]);  // Idade, convertida para int
                int matricula = Integer.parseInt(partes[2]);  // Matrícula, convertida para int

                Aluno aluno = new Aluno(nome, idade);  // Cria o objeto Aluno
                aluno.setMatricula(matricula);  // Define a matrícula
                alunos.add(aluno);  // Adiciona o aluno à lista
            }
        } catch (IOException e) {  // Captura exceções de entrada e saída
            e.printStackTrace();  // Imprime o rastreamento de pilha para depuração em caso de erro
        }
        return alunos;  // Retorna a lista de alunos carregados do arquivo
    }
    
    
    
    public void salvarProfessores(List<Professor> professores) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("professores.txt"))) {
            for (Professor professor : professores) {
                writer.write(professor.getNome() + "," + professor.getIdade() + "," + professor.getEspecialidade() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Professor> carregarProfessores() {
        List<Professor> professores = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("professores.txt"))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] partes = linha.split(",");
                String nome = partes[0];
                int idade = Integer.parseInt(partes[1]);
                String especialidade = partes[2];

                Professor professor = new Professor(nome, idade, especialidade);
                professores.add(professor);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return professores;
    }
    
    
    
    public void salvarCursos(List<Curso> cursos) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("cursos.txt"))) {
            for (Curso curso : cursos) {
                writer.write(curso.getNome() + "," + curso.getCargaHoraria() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Curso> carregarCursos() {
        List<Curso> cursos = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("cursos.txt"))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] partes = linha.split(",");
                String nome = partes[0];
                int cargaHoraria = Integer.parseInt(partes[1]);

                Curso curso = new Curso(nome, cargaHoraria);
                cursos.add(curso);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return cursos;
    }

}
