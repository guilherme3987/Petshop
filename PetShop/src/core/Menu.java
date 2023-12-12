package core;

import java.util.Scanner;
import java.util.List;

public class Menu {
    static Scanner entrada = new Scanner(System.in);

    Singleton singletonIntance = Singleton.getInstance();

    List<Tutor> listaDeTutores = singletonIntance.getListaDeTuTores();

    public void cadastrarTutor(String nomeTutor, String cpftutor, String telefone) {

        Tutor novoTutor = new Tutor(nomeTutor, cpftutor, telefone);
        Singleton.getInstance().adicionarTutor(novoTutor);

    }

    public Tutor BuscaTutor(String cpfTutor) {

        System.out.println("aqui foi");

        for (Tutor tutor : listaDeTutores) {
            System.out.println(tutor.getNome() + "cu");

            if (tutor.getCpf().equals(cpfTutor)) {
                System.out.println("achei");
                return tutor;
            }
        }

        return null;
    }

    public void listarTutores() {
        System.out.println("\nLista de Tutores cadastrados:");
        int i = 1;
        for (Tutor tutor : listaDeTutores) {
            System.out.println(i + ". " + tutor.getNome());
            i++;
        }
    }

    public void cadastrarPet(String cpfTutor, String nomePet, String racaPet, double pesoPet, String especiePet) {

        Tutor tutorSelecionado = null;

        for (Tutor tutor : listaDeTutores) {
            if (tutor.getCpf().equals(cpfTutor)) {
                tutorSelecionado = tutor;
                Pet novoPet = new Pet(nomePet, racaPet, pesoPet, especiePet);
                tutorSelecionado.adicionarPet(novoPet);
                break;
            }
        }
    }

    public void consultarPet() {
        System.out.println("CPF do tutor: ");

        String cpfProcurado = entrada.next();
        boolean existe = false;
        for (Tutor tutor : listaDeTutores) {
            if (tutor.getCpf().equals(cpfProcurado)) {
                existe = true;
                System.out.println("core.Tutor encontrado: ");
                System.out.println(tutor.toString());
                break;
            }
        }
        if (existe == false) {
            System.out.println("CPF " + cpfProcurado + " não está cadastrado");
        }
    }
}