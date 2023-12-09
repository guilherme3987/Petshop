package core.regra_de_negocio;

import core.Pet;

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
        // Não use super.precoServico diretamente, use o atributo de instância para evitar problemas de herança
        this.precoServico = 100;
        return this.precoServico * pet.getPeso();
    }
}