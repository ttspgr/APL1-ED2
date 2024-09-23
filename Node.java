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

abstract class Node {
    Node left;  
    Node right;
    Node parent;
    String data;

    public Node(String dado){
        this.data = dado;
    }

    //CONSTRUTOR DA CLASSE
    public Node(Node left, Node right, Node parent, String data){
        this.left = null;  //filhos construídos sem dados no nó
        this.right = null;
        this.parent = parent;
        this.data = data;
    }

    //SETTERS
    public void setLeft(Node left){
        this.left = left;
    }

    public void setRight(Node right){
        this.right = right;
    }
    public void setParent(Node parent){
        this.parent = parent;
    }
    public void setData(String data){
        this.data = data;
    }
    
    //GETTERS
    public Node getLeft(){
        return left;
    }
    public Node getRight(){
        return right;
    }
    public Node getParent(){
        return parent;
    }
    public String getData(){
        return data;
    }

    //metodo para verificar se o nó é folha
    public boolean isLeaf(){
        return this.left == null && this.right == null;
    }
    
    //metodo para acessar se o conteudo do filho a esquerda não é nulo
    public boolean hasLeftChild(){
        return left != null;
    }
    //metodo para acessar se o conteudo do filho a direita não é nulo
    public boolean hasRightChild(){
        return right != null;
    }


    abstract float visitar();
        
    


}
