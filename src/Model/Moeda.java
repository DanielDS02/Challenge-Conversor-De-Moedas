package Model;

    public class Moeda {
        private final String codigo;
        private final double valor;

        public Moeda(String codigo, double valor) {
            this.codigo = codigo;
            this.valor = valor;
        }

        public String getCodigo() {
            return codigo;
        }

        public double getValor() {
            return valor;
        }
    }


