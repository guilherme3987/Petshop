import java.util.ArrayList;
import java.util.List;

public abstract class Servico {
    private String nomeServico;
    protected double precoServico;
    private static List<Servico> servicos = new ArrayList<>();

    // Constructor
    public Servico(String nomeServico, double precoServico) {
        this.nomeServico = nomeServico;
        this.precoServico = precoServico;
        servicos.add(this);
    }

    //Get
    public String getNomeServico() {
        return nomeServico;
    }

    public double getPrecoServico() {
        return precoServico;
    }

    //Set
    public void setNomeServico(String nomeServico) {
        this.nomeServico = nomeServico;
    }

    public void setPrecoServico(double precoServico) {
        this.precoServico = precoServico;
    }

    public abstract double calcServico(Pet pet);

}