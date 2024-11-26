package model;

public class Professor extends Pessoa {
    protected String especialidade;

    public Professor(String nome, int idade, String especialidade) {
        super(nome, idade);
        this.especialidade = especialidade;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    @Override
    public String exibirDados() {
        return "Nome: " + getNome() + ", Idade: " + getIdade() + ", Especialidade: " + getEspecialidade();
    }
}
