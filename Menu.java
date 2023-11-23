import java.util.Scanner;
import java.util.ArrayList;

class Menu {
    static Scanner entrada = new Scanner(System.in);
    ArrayList<Tutor> listaDeTutores = new ArrayList<Tutor>();
    ArrayList<Pet> listaPets = new ArrayList<Pet>();

    public void iniciarMenu() {
        while(true){

            System.out.println("\nO que você deseja fazer? Escolha um número.\n");
            System.out.println("1 - Cadastrar usuário/tutor ");
            System.out.println("2 - Listar tutores ");
            System.out.println("3 - Cadastrar Pet");
            System.out.println("4 - Listar tutores e pets");
            System.out.println("5 - Marcar um serviço");
            System.out.println("6 - Consultar dados do tutor/pet");
            System.out.println("7 - Editar dados tutor/pet");
            System.out.println("8 - Remover dados tutor/pet");

            int opcao = entrada.nextInt();

            switch (opcao) {
                case 1:
                    System.out.println("\nInforme os dados do usuário/tutor");
                    cadastrarTutor();
                    break;
                case 2:
                    System.out.println("Listar tutores");
                    listarTutores();
                    break;
                case 3:
                    System.out.println("\nVamos cadastrar seu pet! Ele está em nome de quem?");
                    cadastrarPet();
                    break;
                case 4:
                    System.out.println("Listar tutores e pets: ");
                    listarTutoresEPets();
                    break;
                case 5:
                    System.out.println("Qual serviço você deseja marcar? ");
                    agendarServico();
                    break;
                case 6:
                    System.out.println("Consultar dados do tutor/pet");
                    consultarPet();
                    break;
                case 7:
                    System.out.println("Editar dados do tutor/pet");
                    editarDados();
                    break;
                case 8:
                    System.out.println("Remover dados tutor/pet");
                    removerDados();
                    break;
                default:
                    System.out.println("Opção inválida. Por favor, escolha um número válido.");
            }

        }
    }

    private Tutor procurarTutor(String cpf) {
        Tutor tutorEncontrado = null;

        for (Tutor tutor : listaDeTutores) {
            if (tutor.getCpf().equals(cpf)) {
                tutorEncontrado = tutor;
                break;
            }
        }

        return tutorEncontrado;
    }

    private void cadastrarTutor() {

        do {
            entrada.nextLine();
            System.out.println("Nome completo: ");
            String nomeTutor = entrada.nextLine();

            System.out.println("CPF: ");
            String cpfTutor = entrada.nextLine();

            Tutor novoTutor = new Tutor(nomeTutor, cpfTutor);
            listaDeTutores.add(novoTutor);

            System.out.println("\nTutor cadastrado com sucesso!");

            System.out.println("Deseja cadastrar outro tutor? (S/N): ");

        } while (entrada.next().equalsIgnoreCase("S"));
    }

    private void listarTutores() {
        System.out.println("\nLista de Tutores cadastrados:");
        int i = 1;
        for (Tutor tutor : listaDeTutores) {
            System.out.println(i + ". " + tutor.getNome());
            i++;
        }
    }

    private void listarTutoresEPets(){
        System.out.println("\nTutores e Pets: ");

        for(Tutor tutor: listaDeTutores){
            System.out.println("----------------------------------------------------------------");
            System.out.println("Tutor: " + tutor.getNome() + "\nCPF: " + tutor.getCpf());

            if(tutor.getListaDePets().isEmpty()){
                System.out.println("Esse tutor nao possui pets cadastrados");
            }
            else{
                System.out.println("Pets: ");

                for(Pet pet: tutor.getListaDePets()){
                    System.out.println(pet.toString());

                }
            }
        }
    }

    private void cadastrarPet() {
        do {
            listarTutores();

            System.out.println("\nEscolha o número do tutor para cadastrar o pet:");
            int escolhaTutor = entrada.nextInt();
            entrada.nextLine();

            if (escolhaTutor >= 1 && escolhaTutor <= listaDeTutores.size()) {
                Tutor tutorSelecionado = listaDeTutores.get(escolhaTutor - 1);

                System.out.println("\nAgora informe os dados do pet...");

                System.out.print("Nome: ");
                String nome = entrada.nextLine();

                System.out.print("Raça: ");
                String raca = entrada.nextLine();

                System.out.print("Peso: ");
                double peso = entrada.nextDouble();
                entrada.nextLine();

                System.out.print("Espécie: ");
                String especie = entrada.nextLine();

                Pet novoPet = new Pet(nome, raca, peso, especie);
                tutorSelecionado.adicionarPet(novoPet);

                System.out.println("Pet cadastrado com sucesso!");
            } else {
                System.out.println("Opção inválida. Escolha um número de tutor válido.");
            }
            System.out.println("Deseja cadastrar outro pet? (S/N): ");

        } while (entrada.nextLine().equalsIgnoreCase("S"));
    }

    private void consultarPet() {
        System.out.println("CPF do tutor: ");

        String cpfProcurado = entrada.next();

        boolean existe = false;
        for (Tutor tutor : listaDeTutores) {
            if (tutor.getCpf().equals(cpfProcurado)) {
                existe = true;
                System.out.println("Tutor encontrado: ");
                System.out.println(tutor.toString());
                break;
            }
        }
        if (!existe) {
            System.out.println("CPF " + cpfProcurado + " não está cadastrado");
        }
    }

    private void editarDados() {
        System.out.println("Informe o cpf do tutor: ");
        String cpfProcurado = entrada.next();

        Tutor tutorEncontrado = procurarTutor(cpfProcurado);

        if (tutorEncontrado != null) {
            System.out.println("Tutor encontrado: " + tutorEncontrado.getNome());

            System.out.println("O que deseja alterar: ");
            System.out.println("1- Nome do tutor");
            System.out.println("2- Cpf do tutor");
            System.out.println("3- Alterar dados do pet");

            int opcaoEdicao = entrada.nextInt();
            switch (opcaoEdicao) {
                case 1:
                    System.out.println("Novo nome: ");
                    entrada.nextLine();
                    String novoNome = entrada.nextLine();
                    tutorEncontrado.setNome(novoNome);
                    System.out.println("Nome alterado com sucesso!");
                    break;
                case 2:
                    System.out.println("Cpf do tutor: ");
                    String novoCpf = entrada.next();
                    tutorEncontrado.setCpf(novoCpf);
                    System.out.println("Cpf alterado com sucesso!");
                    break;
                case 3:
                    System.out.println("Lista de Pets do " + tutorEncontrado.getNome() + ":");
                    for (Pet pet : tutorEncontrado.getListaDePets()) {
                        System.out.println(pet.getNome());
                    }

                    System.out.println("Nome do pet que sera editado: ");
                    entrada.nextLine();
                    String petEscolhido = entrada.nextLine();

                    Pet petEncontrado = null;

                    for (Pet pet : tutorEncontrado.getListaDePets()) {
                        if (pet.getNome().equals(petEscolhido)) {
                            petEncontrado = pet;
                            break;
                        }
                    }
                    if (petEncontrado != null) {
                        System.out.println("Pet selecionado: " + petEncontrado.getNome());

                        System.out.println("O que deseja alterar? ");
                        System.out.println("1- Nome do pet ");
                        System.out.println("2- Raça do pet ");
                        System.out.println("3- Peso do pet ");
                        System.out.println("4- Especie do pet ");

                        int opcaoEdicaoPet = entrada.nextInt();

                        switch (opcaoEdicaoPet) {
                            case 1:
                                System.out.println("Novo nome: ");
                                entrada.nextLine();
                                String novoNomePet = entrada.nextLine();
                                petEncontrado.setNome(novoNomePet);
                                System.out.println("Nome do pet alterado com sucesso!");
                                break;
                            case 2:
                                System.out.println("Nova raça do pet: ");
                                entrada.nextLine();
                                String novaRacaPet = entrada.nextLine();
                                petEncontrado.setRaca(novaRacaPet);
                                System.out.println("Raça do pet alterada com sucesso!");
                                break;
                            case 3:
                                System.out.println("Novo peso do pet: ");
                                double novoPesoPet = entrada.nextDouble();
                                petEncontrado.setPeso(novoPesoPet);
                                System.out.println("Peso do pet alterado com sucesso!");
                                break;
                            case 4:
                                System.out.println("Nova especie do pet: ");
                                entrada.nextLine();
                                String novaEspeciePet = entrada.nextLine();
                                petEncontrado.setEspecie(novaEspeciePet);
                                System.out.println("Especie do pet alterada com sucesso!");
                                break;
                            default:
                                System.out.println("Opção inválida!");
                        }
                    } else {
                        System.out.println("O pet " + petEscolhido + " não está cadastrado");
                    }
                    break;
                default:
                    System.out.println("Opção inválida!");
            }

        } else {
            System.out.println("O tutor não está cadastrado no sistema!");
        }
    }

    private void removerDados(){
        System.out.println("Informe o que deseja remover: ");
        System.out.println("1- Tutor\n2- Pet");

        int opcaoRemover = entrada.nextInt();

        switch (opcaoRemover) {
            case 1:
                System.out.println("Escolha o tutor que deseja remover");
                listarTutores();

                int indiceTutorRemovido = entrada.nextInt();

                if (indiceTutorRemovido >= 1 && indiceTutorRemovido <= listaDeTutores.size()) {
                    Tutor tutorRemovido = listaDeTutores.remove(indiceTutorRemovido - 1);
                    System.out.println("O tutor " + tutorRemovido.getNome() + " foi removido: ");
                }

                break;
            case 2:
                System.out.println("Informe o CPF do tutor do qual deseja remover o pet: ");
                String cpfProcurado = entrada.next();

                Tutor tutorEncontrado = procurarTutor(cpfProcurado);

                if (tutorEncontrado != null) {
                    System.out.println("Lista de Pets do " + tutorEncontrado.getNome() + ":");
                    for (Pet pet : tutorEncontrado.getListaDePets()) {
                        System.out.println(pet.getNome());
                    }

                    System.out.println("Nome do pet que será removido: ");
                    entrada.nextLine();

                    String petEscolhido = entrada.nextLine();

                    Pet petEncontrado = null;

                    for (Pet pet : tutorEncontrado.getListaDePets()) {
                        if (pet.getNome().equals(petEscolhido)) {
                            petEncontrado = pet;
                            break;
                        }
                    }

                    if (petEncontrado != null) {
                        tutorEncontrado.removerPet(petEncontrado);
                        System.out.println("O pet " + petEncontrado.getNome() + " foi removido da lista de pets do tutor " + tutorEncontrado.getNome());
                    }
                    else {
                        System.out.println("O pet " + petEscolhido + " não está cadastrado");
                    }
                }
                else {
                    System.out.println("O tutor com CPF " + cpfProcurado + " não está cadastrado");
                }

                break;

        }
    }

    private void agendarServico(){
        System.out.println("Informe o cpf do tutor que deseja marcar um servico: ");
        String cpfProcurado = entrada.next();

        Tutor tutorEncontrado = procurarTutor(cpfProcurado);

        if (tutorEncontrado != null) {
            System.out.println("Lista de Pets do " + tutorEncontrado.getNome() + ":");
            for (Pet pet : tutorEncontrado.getListaDePets()) {
                System.out.println(pet.getNome());
            }

            System.out.println("Nome do pet: ");
            entrada.nextLine();

            String nomePet = entrada.nextLine();

            Pet petEncontrado = null;

            for (Pet pet : tutorEncontrado.getListaDePets()) {
                if (pet.getNome().equals(nomePet)) {
                    petEncontrado = pet;
                    break;
                }
            }

            if (petEncontrado != null) {
                System.out.println("Escolha o serviço que deseja marcar: ");
                System.out.println("1- Banho");
                System.out.println("2- Tosa");
                System.out.println("3- Banho e Tosa");
                System.out.println("4- Consulta");

                int opcaoServico = entrada.nextInt();

                switch (opcaoServico) {
                    case 1:
                        System.out.println("Serviço escolhido: Banho");
                        Servico banhoServico = new Banho("Banho", 0, petEncontrado);
                        tutorEncontrado.marcarServico(banhoServico, petEncontrado);
                        break;
                    case 2:
                        System.out.println("Serviço escolhido: Tosa");
                        Servico tosaServico = new Tosa("Tosa", 0, petEncontrado);
                        tutorEncontrado.marcarServico(tosaServico, petEncontrado);
                        break;
                    case 3:
                        System.out.println("Serviço escolhido: Banho e Tosa");
                        Servico banhoTosaServico = new BanhoETosa("Banho e Tosa", 0, petEncontrado);
                        tutorEncontrado.marcarServico(banhoTosaServico, petEncontrado);
                        break;
                    case 4:
                        System.out.println("Serviço escolhido: Consulta");
                        Servico consultaServico = new Consulta("Consulta", 0, petEncontrado);
                        tutorEncontrado.marcarServico(consultaServico, petEncontrado);
                        break;
                    default:
                        System.out.println("Opção inválida");
                        break;
                }
            } else {
                System.out.println("Pet não encontrado.");
            }
        } else {
            System.out.println("O tutor com CPF " + cpfProcurado + " não está cadastrado");
        }
    }
    static void printLogo (Petshop petshop) {
        System.out.println("=======================");
        System.out.println("----- " + petshop.getNome() + " -----");
        System.out.println("=======================\n\n");
        System.out.println("Bem vindo!!");
    }
}