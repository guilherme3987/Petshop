package core;

import java.util.ArrayList;
import java.util.List;

public class Singleton {
    private static final Singleton instance = new Singleton();
    private List<Tutor> listaDeTutores;
    private List<Pet> listaDePet;

    private Singleton(){
        listaDeTutores = new ArrayList<>();
        listaDePet = new ArrayList<>();
    }

    public void salvarAlteracoesTutor(Tutor tutor) {
        // Verifica se o tutor já existe na lista
        if (listaDeTutores.contains(tutor)) {
            // Atualiza os dados do tutor existente
            int index = listaDeTutores.indexOf(tutor);
            listaDeTutores.set(index, tutor);
        } else {
            // Adiciona o tutor à lista se não existir
            listaDeTutores.add(tutor);
        }
    }

    public static Singleton getInstance(){
        return instance;
    }

    public List<Tutor> getListaDeTuTores(){
        return listaDeTutores;
    }

    public List<Pet> getListaDePet(){
        return listaDePet;
    }

    public void adicionarTutor(Tutor tutor) {
        listaDeTutores.add(tutor);
    }

    public void adicionarPet(Tutor tutor, Pet pet) {
        tutor.adicionarPet(pet);
        listaDePet.add(pet);
    }

    public void removerPet(Pet pet) {
        listaDePet.remove(pet);

        // Itera sobre todos os tutores para remover o pet de cada tutor
        for (Tutor tutor : listaDeTutores) {
            tutor.removerPet(pet);
        }
    }
}
