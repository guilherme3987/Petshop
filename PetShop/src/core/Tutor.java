package core;

import core.regra_de_negocio.Servico;

import java.util.ArrayList;
import java.util.List;

public class Tutor {
    private String nome;
    private String cpf;
    private String telefone;
    // private ArrayList<core.Pet> listaDePets;
    private List<Pet> listaDePets;
    private List<Servico> servicosAgendados;

    Singleton singletonIntance = Singleton.getInstance();

    // Construtor
    // public core.Tutor(String nome, String cpf) {
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

    // public List<core.Tutor> getListaDeTutores() {
    //   return listaDeTutores;
    //   }

    // public void setListaDeTutores(ArrayList<core.Tutor> listaDeTutores) {
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

    public void setListaDePets(List<Pet> listaDePets) {
        this.listaDePets = listaDePets;
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
        if (listaDePets.contains(pet)) {
            listaDePets.remove(pet);
            System.out.println("core.Pet removido com sucesso: " + pet);
        } else {
            System.out.println("O pet não está na lista do tutor.");
        }
    }



    @Override
    public String toString() {
        StringBuilder petString = new StringBuilder();
        for (Pet pet : listaDePets) {
            petString.append("core.Pet: ").append(pet.getNome()).append("\nRaça: ").append(pet.getRaca()).append("\nPeso: ").append(pet.getPeso()).append("\nEspecie: ").append(pet.getEspecie()).append("\n\n");
        }
        return "\ncore.Tutor: " + nome + "\n" +
                "CPF: " + cpf + "\n" +
                "\nPets do tutor: \n\n" + petString.toString() + "\n";
    }
}