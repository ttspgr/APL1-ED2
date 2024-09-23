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

import java.util.Scanner;
import java.util.Stack;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArvoreBinariaExpressao arvore = new ArvoreBinariaExpressao();
        String expressao = "";

        while (true) {
            System.out.println("Menu do Programa");
            System.out.println("1- Insira a expressão aritmética em notação infixa.");
            System.out.println("2- Criar árvore binária de expressão aritmética.");
            System.out.println("3- Exibir árvore binária de expressão aritmética.");
            System.out.println("4- Cálculo da expressão.");
            System.out.println("5- Sair.");
            System.out.print("\nDigite sua opção: ");

            try {
                int opcao = sc.nextInt();
                sc.nextLine(); // consome a quebra de linha

                switch (opcao) {
                    case 1:
                        System.out.println("Digite a expressão em notação infixa:");
                        expressao = sc.nextLine();
                        if (ValidarExpressao(expressao)) {
                            System.out.println("Expressão válida!");
                        } else {
                            System.out.println("Expressão inválida! Tente novamente.");
                        }
                        break;
                    case 2:
                        if (expressao.isEmpty()) {
                            System.out.println("Expressão inexistente! Primeiro insira uma expressão válida na opção 1.");
                        } else {
                            try {
                                arvore.construirArvore(expressao);
                                System.out.println("Árvore criada com sucesso!");
                                arvore.exibirArvore(); // Exibe a árvore após a construção
                            } catch (Exception e) {
                                System.out.println("Erro ao construir a árvore: " + e.getMessage());
                            }
                        }
                        break;
                    case 3:
                        System.out.println("Exibição em ordem:");
                        arvore.exibirEmOrdem(arvore.getRaiz());
                        System.out.println("\n");
                        System.out.println("\nExibição pré-ordem:");
                        arvore.exibirPreOrdem(arvore.getRaiz());
                        System.out.println("\n");
                        System.out.println("\nExibição pós-ordem:");
                        arvore.exibirPosOrdem(arvore.getRaiz());
                        System.out.println("\n");
                        System.out.println("\nExibição em nível:");
                        arvore.exibirEmNivel();
                        System.out.println("\n");
                        break;
                    case 4:
                        try {
                            float resultado = arvore.calcular();
                            System.out.println("Resultado da expressão: " + resultado);
                        } catch (IllegalStateException e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    case 5:
                        sc.close();
                        System.exit(0); // saída do programa
                    default:
                        System.out.println("Número inválido, digite 1, 2, 3, 4 ou 5.");
                    }

                    } catch (Exception e) {
                    System.out.println("Erro: entrada inválida. Tente novamente.");
                    sc.nextLine(); // Limpa o buffer de entrada
                }
            }
        }

    //metodo para validar a expressão aritmética
    public static boolean ValidarExpressao(String expressao) {
        expressao = expressao.replaceAll("\\s", ""); // Remove espaços em branco

        // Verifica se a expressão contém apenas números, operadores e parênteses
        if (!Pattern.matches("[0-9+\\-*/().]*", expressao)) 
            return false;

        // Verifica se os parênteses estão equilibrados
        if (!VerificarParenteses(expressao))
            return false;

        // Verifica a sequência de operadores e operandos
        return verificarSequenciaOperadoresOperandos(expressao);
    }

    //metodo para verificar se os parênteses estão equilibrados
    public static boolean VerificarParenteses(String expressao) {
        Stack<Character> pilha = new Stack<>();
        for (char c : expressao.toCharArray()) {
            if (c == '(') {
                pilha.push(c);
            } else if (c == ')') {
                if (pilha.isEmpty()) {
                    return false;
                }
                pilha.pop();
            }
        }
        return pilha.isEmpty();
    }
    public static boolean verificarSequenciaOperadoresOperandos(String expressao) {
        String[] tokens = expressao.split("(?<=[-+*/()])|(?=[-+*/()])");
        boolean ultimoFoiOperador = true;

        for (String token : tokens) {
            if (token.isEmpty()) continue;

            if (token.matches("[0-9]+(\\.[0-9]+)?")) {
                ultimoFoiOperador = false; // Encontrou um operando
            } else if (token.matches("[+\\-*/]")) {
                if (ultimoFoiOperador) {
                    return false; // Não pode ter dois operadores seguidos
                }
                ultimoFoiOperador = true; // O último token foi um operador
            } else if (token.equals("(")) {
                ultimoFoiOperador = true; // Parêntese aberto pode vir após um operador
            } else if (token.equals(")")) {
                if (ultimoFoiOperador) {
                    return false; // Não pode fechar parêntese após um operador
                }
                ultimoFoiOperador = false; // Fecha parêntese deve ser seguido por um operando ou operador
            } else {
                return false; // Caractere inválido
            }
        }

        return !ultimoFoiOperador; // Não pode terminar com um operador
    }
}
