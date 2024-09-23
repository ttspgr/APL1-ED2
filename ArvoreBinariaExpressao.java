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

import java.util.Stack;
import java.util.Queue;
import java.util.LinkedList;

public class ArvoreBinariaExpressao {
    private Node raiz;

    // Método para construir a árvore a partir da expressão
    public void construirArvore(String expressao) {
        expressao = expressao.replaceAll("\\s+", ""); // Remoção de todos os espaços em branco
        String[] tokens = expressao.split("(?<=[-+*/()])|(?=[-+*/()])");
        Stack<Node> operandos = new Stack<>();
        Stack<OperadorNode> operadores = new Stack<>();
    
        for (String token : tokens) {
            if (token.isEmpty()) continue;
    
            if (token.matches("[0-9]+(\\.[0-9]+)?")) {
                // Se o token é um número, cria um OperandoNode
                operandos.push(new OperandoNode(Float.parseFloat(token)));
            } else if (token.matches("[+\\-*/]")) {
                while (!operadores.isEmpty() && 
                       precedencia(operadores.peek().operador) >= precedencia(token.charAt(0))) {
                    OperadorNode operador = operadores.pop();
                    operador.setRight(operandos.pop());
                    operador.setLeft(operandos.pop());
                    operandos.push(operador);
                }
                operadores.push(new OperadorNode(token.charAt(0), null, null));
            } else if (token.equals("(")) {
                operadores.push(new OperadorNode('(', null, null)); // Usar um nó operador para '('
            } else if (token.equals(")")) {
                while (!operadores.isEmpty() && operadores.peek().operador != '(') {
                    OperadorNode operador = operadores.pop();
                    operador.setRight(operandos.pop());
                    operador.setLeft(operandos.pop());
                    operandos.push(operador);
                }
                if (!operadores.isEmpty()) {
                    operadores.pop(); // Remove o '('
                }
            }
        }
    
        // Finaliza a árvore com os operadores restantes
        while (!operadores.isEmpty()) {
            OperadorNode operador = operadores.pop();
            operador.setRight(operandos.pop());
            operador.setLeft(operandos.pop());
            operandos.push(operador);
        }
    
        // A raiz da árvore é o último operando
        raiz = operandos.pop(); 
    }
    

    // Método para exibir a árvore (em ordem, pré e pós)
    public void exibirEmOrdem(Node node) {
        if (node != null) {
            exibirEmOrdem(node.getLeft());
            System.out.print(node.getData() + " ");
            exibirEmOrdem(node.getRight());
        }
    }

    public void exibirArvore(Node node, String prefix, boolean isLeft) {
        if (node != null) {
            System.out.println(prefix + (isLeft ? "├── " : "└── ") + node.getData());
            exibirArvore(node.getLeft(), prefix + (isLeft ? "│   " : "    "), true);
            exibirArvore(node.getRight(), prefix + (isLeft ? "│   " : "    "), false);
        }
    }
    
    public void exibirArvore() {
        exibirArvore(getRaiz(), "", true);
    }

    public void exibirPreOrdem(Node node) {
        if (node != null) {
            System.out.print(node.getData() + " ");
            exibirPreOrdem(node.getLeft());
            exibirPreOrdem(node.getRight());
        }
    }

    public void exibirPosOrdem(Node node) {
        if (node != null) {
            exibirPosOrdem(node.getLeft());
            exibirPosOrdem(node.getRight());
            System.out.print(node.getData() + " ");
        }
    }

    public void exibirEmNivel() {
    if (raiz == null) return;
        Queue<Node> queue = new LinkedList<>();
        queue.add(raiz);  // Começa pela raiz
        while (!queue.isEmpty()) {
            Node current = queue.poll();  // Remove o próximo nó da fila
            System.out.print(current.getData() + " ");  // Exibe o valor do nó

            // Adiciona os filhos à fila (esquerda primeiro, depois direita)
            if (current.getLeft() != null) queue.add(current.getLeft());
            if (current.getRight() != null) queue.add(current.getRight());
        }
    }   

    // Método para calcular a expressão
// Método para calcular a expressão
    public float calcular() {
        if (raiz != null) {
            return raiz.visitar();
        }
        throw new IllegalStateException("A árvore está vazia!");
    }

    // Método para determinar a precedência dos operadores
    private int precedencia(char operador) {
        switch (operador) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            default:
                return 0;
        }
    }

    public Node getRaiz() {
        return raiz;
    }
}
