import java.util.ArrayList;

public class Tutor {
    private String nome;
    private String cpf;
    private ArrayList<Pet> listaDePets;
    private ArrayList<Tutor> listaDeTutores;
    private ArrayList<Servico> listaServicos;

    // Construtor
    public Tutor(String nome, String cpf) {
        this.nome = nome;
        this.cpf = cpf;
        this.listaDePets = new ArrayList<>();
        this.listaServicos = new ArrayList<>();
    }

    //Gets
    public ArrayList<Tutor> getListaDeTutores() {
        return listaDeTutores;
    }
    public String getNome() {
        return nome;
    }
    public String getCpf() {
        return cpf;
    }
    public ArrayList<Pet> getListaDePets() {
        return listaDePets;
    }


    //Sets
    public void setListaDeTutores(ArrayList<Tutor> listaDeTutores) {
        this.listaDeTutores = listaDeTutores;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    public void setListaDePet(ArrayList<Pet> listaDePets) {
        this.listaDePets = listaDePets;
    }

    public void setListaDePets(ArrayList<Pet> listaDePets) {
        this.listaDePets = listaDePets;
    }

    public void setListaServicos(ArrayList<Servico> listaServicos) {
        this.listaServicos = listaServicos;
    }

    //Methods
    public void adicionarPet(Pet pet){
        this.listaDePets.add(pet);
    }
    public void adicionarTutor(Tutor tutor){
        this.listaDeTutores.add(tutor);

    }
    public void removerPet(Pet pet) {
        if (listaDePets != null) {
            listaDePets.remove(pet);
        }
    }

    public ArrayList<Servico> getListaDeServicos() {
        return listaServicos;
    }

    public void marcarServico(Servico servico, Pet pet) {
        double preco = servico.calcServico(pet);
        System.out.println("Serviço marcado para o pet " + pet.getNome() + ": " + servico.getNomeServico() + " - Preço: R$" + preco);
    }


    @Override
    public String toString() {
        StringBuilder petString = new StringBuilder();
        for(Pet pet : listaDePets){
            petString.append("Pet: ").append(pet.getNome()).append("\nRaça: ").append(pet.getRaca()).append("\nPeso: ").append(pet.getPeso()).append("\nEspecie: ").append(pet.getEspecie()).append("\n\n");
        }
        return "\nTutor: " + nome + "\n" +
                "CPF: " + cpf + "\n" +
                "\nPets do tutor: \n\n" + petString.toString() + "\n";

    }
}