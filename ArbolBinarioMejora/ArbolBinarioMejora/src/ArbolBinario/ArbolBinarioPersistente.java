package ArbolBinario;

import javax.swing.*;
import java.io.*;
import java.util.Scanner;

public class ArbolBinarioPersistente {

    ArbolBinario arbol;

    public ArbolBinarioPersistente(ArbolBinario arbol) {
        this.arbol = arbol;
    }

    // Método para guardar el árbol en un archivo de texto
    public void guardar(String nombreArchivo) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(nombreArchivo))) {
            guardarArbol(arbol.raiz, writer);
            JOptionPane.showMessageDialog(null, "Árbol guardado correctamente en " + nombreArchivo);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al guardar el árbol: " + e.getMessage());
        }
    }

    // Método recursivo para escribir el árbol en el archivo
    private void guardarArbol(Nodo nodo, PrintWriter writer) {
        if (nodo == null) {
            writer.println("null");
            return;
        }
        writer.println(nodo.valor);
        guardarArbol(nodo.izquierda, writer);
        guardarArbol(nodo.derecha, writer);
    }

    // Método para cargar el árbol desde un archivo de texto
    public void cargar(String nombreArchivo) {
        try (Scanner scanner = new Scanner(new File(nombreArchivo))) {
            arbol.raiz = cargarArbol(scanner);
            JOptionPane.showMessageDialog(null, "Árbol cargado correctamente desde " + nombreArchivo);
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Error al cargar el árbol: " + e.getMessage());
        }
    }

    // Método recursivo para leer el árbol desde el archivo
    private Nodo cargarArbol(Scanner scanner) {
        if (!scanner.hasNextLine()) return null;
        String valor = scanner.nextLine();
        if (valor.equals("null")) return null;
        System.out.print(valor+" ");
        Nodo nodo = new Nodo(valor);
        nodo.izquierda = cargarArbol(scanner);
        nodo.derecha = cargarArbol(scanner);
        return nodo;
    }
}
