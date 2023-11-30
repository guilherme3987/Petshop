

import java.util.ArrayList;

public class Petshop {
    private String nome;
    private String endereco;
    private ArrayList<Funcionario> listaDeFuncionarios;
    
    // Construtor
    public Petshop(String nome, String endereco) {
        this.nome = nome;
        this.endereco = endereco;
        this.listaDeFuncionarios = new ArrayList<>();
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public ArrayList<Funcionario> getListaDeFuncionarios() {
        return listaDeFuncionarios;
    }

}