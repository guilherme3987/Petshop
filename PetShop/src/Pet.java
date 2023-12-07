
    public class Pet {
        private String nome;
        private String raca;
        private double peso;
        private String especie;


        // Construtor
        public Pet(String nome, String raca, double peso, String especie) {
            this.nome = nome;
            this.raca = raca;
            this.peso = peso;
            this.especie = especie;
        }

        public String getNome() {
            return nome;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }

        public String getRaca() {
            return raca;
        }

        public void setRaca(String raca) {
            this.raca = raca;
        }

        public double getPeso() {
            return peso;
        }

        public void setPeso(double peso) {
            this.peso = peso;
        }

        public String getEspecie() {
            return especie;
        }

        public void setEspecie(String especie) {
            this.especie = especie;
        }

        @Override
        public String toString() {
            return "Pet: " + nome + "\nRaça: " + raca + "\nPeso: " + peso + "\nEspécie: " + especie + "\n";
        }
    }