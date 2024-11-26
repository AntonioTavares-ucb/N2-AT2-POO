package model;

public class Aluno extends Pessoa {
    private int matricula;

    public Aluno(String nome, int idade) {
        super(nome, idade);  // Chama o construtor da classe Pessoa
        this.matricula = gerarMatriculaAleatoria();
    }

    private int gerarMatriculaAleatoria() {
        return (int) (Math.random() * 1000 + 1); 
    }

    @Override
    public String exibirDados() {
        return "Nome: " + getNome() + ", Idade: " + getIdade() + ", Matrícula: " + matricula;
    }

    public int getMatricula() { 
        return matricula; 
    }
    
    public void setMatricula(int matricula) { 
        this.matricula = matricula; 
    }
}
