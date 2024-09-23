// Projeto de Esturtura de Dados II
// Aplicação I - Árvore Binária de Expressão Aritimética
//
// Integrantes:
// Felipe Konishi Brum RA: 10417412
// Tiago Teraoka e Sá RA: 10418485

/* Referências
 * 1. "Data Structures and Algorithms in Java" por Robert Lafore
 * 2. "Algorithms" por Robert Sedgewick e Kevin Wayne
 * 3. Documentação oficial do Java: https://docs.oracle.com/en/java/javase/11/docs/api/index.html
 * 4. GeeksforGeeks: https://www.geeksforgeeks.org/
 * 5. Stack Overflow: https://stackoverflow.com/
 */

class OperadorNode extends Node {
    protected char operador;

     public OperadorNode(char operador, Node left, Node right) {
        super(String.valueOf(operador));
        this.operador = operador;
        this.left = left;
        this.right = right;
    }

    @Override
    public float visitar() {
        float leftValor = left.visitar();
        float rightValor = right.visitar();
        float valor = 0.0f;

        switch (operador) {
            case '+': {
                valor = leftValor + rightValor;
                break;
            }
            case '-': {
                valor = leftValor - rightValor;
                break;
            }
            case '*': {
                valor = leftValor * rightValor;
                break;
            }
            case '/': {
                if (rightValor != 0)
                    valor = leftValor / rightValor;
                else {
                    System.out.println("Erro: Divisão por 0");
                    valor = 0.0f;
                }
                break;
            }
            default: {
                System.out.println("Operador desconhecido.");
            }
        }
        return valor;
    }
}
