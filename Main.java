import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        Menu menu = new Menu();

        Petshop petshop = new Petshop("Petshop LP2", "UNEB");
        Funcionario func1 = new Funcionario("Fabricio", "Tosador", "03567659175");
        Funcionario func2 = new Funcionario("Matheus", "Tosador", "09867353155");
        Funcionario func3 = new Funcionario("Joana", "Banho", "13377659737");
        Funcionario func4 = new Funcionario("Carlos", "Veterinario", "14897659361");
        Funcionario func5 = new Funcionario("Rita", "Veterinario", "03175389735");

        Menu.printLogo(petshop);
        menu.iniciarMenu();


    }
}