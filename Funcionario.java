public class Funcionario {
    private String nome;
    private String funcao;
    private String cpf;
    
    // Construtor
    public Funcionario(String nome, String funcao, String cpf) {
        this.nome = nome;
        this.funcao = funcao;
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getFuncao() {
        return funcao;
    }
    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    public String getCpf() {
        return cpf;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

}