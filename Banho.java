public class Banho extends Servico {
    private Pet pet;

    // Constructor
    public Banho(String nomeServico, double precoServico, Pet pet) {
        super(nomeServico, precoServico);
        this.pet = pet;
    }

    // Get
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

//System.out.println("Informe o CPF do tutor do qual deseja remover o pet: ");
//        String cpfProcurado = entrada.next();
//
//        Tutor tutorEncontrado = null;
//
//        for (Tutor tutor : listaDeTutores) {
//        if (tutor.getCpf().equals(cpfProcurado)) {
//        tutorEncontrado = tutor;
//        break;
//        }
//        }
//
//        if (tutorEncontrado != null) {
//        System.out.println("Lista de Pets do " + tutorEncontrado.getNome() + ":");
//        for (Pet pet : tutorEncontrado.getListaDePets()) {
//        System.out.println(pet.getNome());
//        }
