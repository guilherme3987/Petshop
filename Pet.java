import java.util.ArrayList;
import java.util.ServiceConfigurationError;

public class Pet {
    private String nome;
    private String raca;
    private double peso;
    private String especie;
    private ArrayList<Pet> listaDePets;
    private ArrayList<Servico> listaServicos;


    // Construtor
    public Pet(String nome, String raca, double peso, String especie) {
        this.nome = nome;
        this.raca = raca;
        this.peso = peso;
        this.especie = especie;
        this.listaDePets = new ArrayList<>();
        this.listaServicos = new ArrayList<>();
    }

    //Gets
    public String getNome() {
        return nome;
    }
    public String getRaca() {
        return raca;
    }
    public double getPeso() {
        return peso;
    }
    public String getEspecie() {
        return especie;
    }
    public ArrayList<Pet> getListaDePets() {
        return listaDePets;
    }
    public ArrayList<Servico> getListaServicos() {
        return listaServicos;
    }

    //Sets
    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setRaca(String raca) {
        this.raca = raca;
    }
    public void setPeso(double peso) {
        this.peso = peso;
    }
    public void setEspecie(String especie) {
        this.especie = especie;
    }
    public void setListaDePets(ArrayList<Pet> listaDePets) {
        this.listaDePets = listaDePets;
    }
    public void setListaServicos(ArrayList<Servico> listaServicos) {
        this.listaServicos = listaServicos;
    }

    //Methods
    public void adicionarServico(Servico servico){
        this.listaServicos.add(servico);
    }

//    @Override
//    public String toString() {
//        StringBuilder petString = new StringBuilder();
//        for(Pet pet : listaDePets){
//            petString.append("Pet: ").append(pet.getNome()).append("\nRaça: ").append(pet.getRaca()).append("\n");
//        }
//        return "\nPets do tutor: \n" + petString.toString() + "\n";
//
//    }
    @Override
    public String toString() {
        StringBuilder petString = new StringBuilder();
        petString.append("Pet: ").append(this.nome).append("\nRaça: ").append(this.raca).append("\nPeso: ").append(this.peso).append("\nEspécie: ").append(this.especie).append("\n");
        return petString.toString();
    }
}