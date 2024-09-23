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

class OperandoNode extends Node {
    protected float valor;

    public OperandoNode(float valor) {
        super(String.valueOf(valor));
        this.valor = valor;
    }

    @Override
    public float visitar() {
        return this.valor;
    }
}