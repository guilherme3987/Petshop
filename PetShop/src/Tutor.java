

import java.util.ArrayList;
import java.util.List;

public class Tutor {
    private String nome;
    private String cpf;
    private String telefone;
    // private ArrayList<Pet> listaDePets;
    private List<Pet> listaDePets;
    private List<Servico> servicosAgendados;

    Singleton singletonIntance = Singleton.getInstance();

    // Construtor
    // public Tutor(String nome, String cpf) {
    //     this.nome = nome;
    //     this.cpf = cpf;
    //     this.listaDePets = new ArrayList<>();
    // }

    public Tutor(String nome, String cpf, String telefone) {
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.listaDePets = new ArrayList<>();
        this.servicosAgendados = new ArrayList<>();
    }

    // public List<Tutor> getListaDeTutores() {
    //   return listaDeTutores;
    //   }

    // public void setListaDeTutores(ArrayList<Tutor> listaDeTutores) {
    //    this.listaDeTutores = listaDeTutores;
    // }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone){
        this.telefone = telefone;
    }

    public List<Pet> getListaDePets() {
        return listaDePets;
    }

    public List<Servico> getServicosAgendados() {
        return servicosAgendados;
    }

    public void adicionarPet(Pet pet) {
        listaDePets.add(pet);
    }

    public void adicionarServico(Servico servico) {
        servicosAgendados.add(servico);
    }

    public void removerPet(Pet pet) {
        Singleton.getInstance().removerPet(pet);
    }

    @Override
    public String toString() {
        StringBuilder petString = new StringBuilder();
        for (Pet pet : listaDePets) {
            petString.append("Pet: ").append(pet.getNome()).append("\nRaça: ").append(pet.getRaca()).append("\nPeso: ").append(pet.getPeso()).append("\nEspecie: ").append(pet.getEspecie()).append("\n\n");
        }
        return "\nTutor: " + nome + "\n" +
                "CPF: " + cpf + "\n" +
                "\nPets do tutor: \n\n" + petString.toString() + "\n";
    }
}