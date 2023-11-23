public class Consulta extends Servico{
    private Pet pet;

    public Consulta(String nomeServico, double precoServico, Pet pet) {
        super(nomeServico, precoServico);
        this.pet = pet;
    }

    //Get
    public Pet getPet() {
        return pet;
    }

    // Set
    public void setPet(Pet pet) {
        this.pet = pet;
    }

    @Override
    public double calcServico(Pet pet) {
        super.precoServico = 100;
        return super.precoServico * pet.getPeso();
    }
}