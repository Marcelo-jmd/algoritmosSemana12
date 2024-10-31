package ArbolBinario;

import java.util.Scanner;
import javax.swing.*;

class Nodo {
    String valor;
    Nodo izquierda, derecha;

    public Nodo(String item) {
        valor = item;
        izquierda = derecha = null;
    }
}

class ArbolBinario {
    Nodo raiz;
    Scanner scanner = new Scanner(System.in);
    int nodosRestantes;

    ArbolBinario() {
        raiz = null;
    }

    // Se crea un procedimiento para cada recorrido
    void preOrder(Nodo nodo) {
        if (nodo == null)
            return;

        System.out.print(nodo.valor + " ");
        preOrder(nodo.izquierda);
        preOrder(nodo.derecha);
    }

    void inOrder(Nodo nodo) {
        if (nodo == null)
            return;

        inOrder(nodo.izquierda);
        System.out.print(nodo.valor + " ");
        inOrder(nodo.derecha);
    }

    void postOrder(Nodo nodo) {
        if (nodo == null)
            return;

        postOrder(nodo.izquierda);
        postOrder(nodo.derecha);
        System.out.print(nodo.valor + " ");
    }

    Nodo construirArbol() {
        if (nodosRestantes == 0) {
            return null;
        }

        System.out.print("Ingrese el valor del nodo: ");
        String valor = scanner.nextLine();
        if (valor.equals("null")) {
            return null;
        }

        nodosRestantes--;

        Nodo nodo = new Nodo(valor);

        System.out.println("Ingrese el valor del hijo izquierdo de " + valor + " (o 'null' si no tiene):");
        nodo.izquierda = construirArbol();

        System.out.println("Ingrese el valor del hijo derecho de " + valor + " (o 'null' si no tiene):");
        nodo.derecha = construirArbol();

        return nodo;
    }

    public static void main(String[] args) {
        ArbolBinario arbol = new ArbolBinario();
        ArbolBinarioPersistente arbolPersistente = new ArbolBinarioPersistente(arbol);
        Scanner scanner = new Scanner(System.in);
        // Preguntar si se desea guardar el árbol
        int pregunta = JOptionPane.showConfirmDialog(null, "¿Desea cargar el árbol?", "Cargar Árbol", JOptionPane.YES_NO_OPTION);
        if (pregunta == JOptionPane.YES_OPTION) {
            String nombreArchivo = JOptionPane.showInputDialog("Ingrese el nombre del archivo (sin extensión):");
            arbolPersistente.cargar(nombreArchivo + ".txt");
        } else {
            System.out.print("Ingrese la cantidad de nodos: ");
            arbol.nodosRestantes = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

            System.out.println("Construyendo el árbol binario:");
            arbol.raiz = arbol.construirArbol();

            System.out.println("Pre-order:");
            arbol.preOrder(arbol.raiz);
            System.out.println("\nIn-order:");
            arbol.inOrder(arbol.raiz);
            System.out.println("\nPost-order:");
            arbol.postOrder(arbol.raiz);

            // Preguntar si se desea guardar el árbol
            int opcion = JOptionPane.showConfirmDialog(null, "¿Desea guardar el árbol?", "Guardar Árbol", JOptionPane.YES_NO_OPTION);
            if (opcion == JOptionPane.YES_OPTION) {
                String nombreArchivo = JOptionPane.showInputDialog("Ingrese el nombre del archivo (sin extensión):");
                arbolPersistente.guardar(nombreArchivo + ".txt");
            }
        }
        
    }
}
