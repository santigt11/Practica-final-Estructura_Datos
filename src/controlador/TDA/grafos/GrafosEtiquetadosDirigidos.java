/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador.TDA.grafos;

import controlador.TDA.colas.QueueUltimate;
import controlador.TDA.grafos.exception.LabelEdgeException;
import controlador.TDA.grafos.exception.VerticeException;
import controlador.TDA.listas.DynamicList;
import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.Random;
import modelo.SubEstacion;
import vista.grafoEjemplo.utilidades.UtilesVistaSubEstacion;

/**
 *
 * @author Santiago
 */
public class GrafosEtiquetadosDirigidos<E> extends GrafoDirigido {

    private Double[][] distancias;
    protected E[] labels;
    protected HashMap<E, Integer> dicVertices;
    private Class<E> clazz;

    public GrafosEtiquetadosDirigidos(Integer num_vertices, Class clazz) {
        super(num_vertices);
        this.clazz = clazz;
        labels = (E[]) Array.newInstance(clazz, num_vertices + 1);
        dicVertices = new HashMap<>(num_vertices);
    }

    //Metodo que permite recatar el nro de vertice asociado a la etiqueta
    public Integer getVerticeE(E label) throws Exception {
        Integer aux = dicVertices.get(label);
        if (aux != null) {
            return dicVertices.get(label);
        } else {
            throw new VerticeException("No se encuentra ese vertice asociado a esa etiqueta");
        }
    }

    public E getLabelE(Integer v) throws Exception {
        if (v <= num_vertice()) {
            return labels[v];
        } else {
            throw new VerticeException("No se encuentra ese vertice");
        }
    }

    public Boolean isEdge(E o, E d) throws Exception {
        if (isAllLabelsGraph()) {
            return existe_arista(getVerticeE(o), getVerticeE(d));
        } else {
            throw new LabelEdgeException();
        }
    }

    public void insertEdgeE(E o, E d) throws Exception {
        if (isAllLabelsGraph()) {
            insertar_arista(getVerticeE(o), getVerticeE(d), Double.NaN);
        } else {
            throw new LabelEdgeException();
        }
    }

    public void insertEdgeE(E o, E d, Double peso) throws Exception {
        if (isAllLabelsGraph()) {
            insertar_arista(getVerticeE(o), getVerticeE(d), peso);
        } else {
            throw new LabelEdgeException();
        }
    }

    public DynamicList<Adyacencia> adjacents(E label) throws Exception {
        if (isAllLabelsGraph()) {
            return adyacentes(getVerticeE(label));
        } else {
            throw new LabelEdgeException();
        }
    }

    //Metodo principal que permite etiquetar grafos
    public void labelVertice(Integer v, E label) {
        labels[v] = label;
        dicVertices.put(label, v);
    }

    public Boolean isAllLabelsGraph() throws Exception {
        Boolean band = true;
        for (int i = 1; i < num_vertice(); i++) {
            if (labels[i] == null) {
                throw new LabelEdgeException();
            }
        }
        return band;
    }

    @Override
    public String toString() {
        StringBuilder grafo = new StringBuilder("GRAFO").append("\n");
        try {
            for (int i = 1; i <= num_vertice(); i++) {
                grafo.append("[").append(i).append("] ").append(getLabelE(i) + "\n");
                DynamicList<Adyacencia> list = adyacentes(i);
                for (int j = 0; j < list.getLength(); j++) {
                    Adyacencia a = list.getInfo(j);
                    grafo.append("ady [").append(a.getDestino()).append("] = ").append(getLabelE(a.getDestino())).append(" " + a.getPeso()).append("\n");
                }
            }
        } catch (Exception e) {
        }
        return grafo.toString();
    }
    
    
//========================================================================================================
//========================================================================================================
    public String encontrarCaminoMasCorto(int origen, int destino) throws Exception {
    if (distancias[origen][destino] == Double.POSITIVE_INFINITY) {
        return "No hay ruta entre " + getLabelE(origen) + " y " + getLabelE(destino);
    }

    StringBuilder rutaMasCorta = new StringBuilder();
    rutaMasCorta.append("Camino más corto desde ").append(getLabelE(origen)).append(" hasta ").append(getLabelE(destino)).append(":\n");

    int nodoActual = origen;
    double sumaPesos = 0.0;
    rutaMasCorta.append(getLabelE(nodoActual));

    while (nodoActual != destino) {
        int siguienteNodo = encontrarVecinoMasCercano(nodoActual, destino);
        if (siguienteNodo == -1) {
            break; // No hay vecinos o no se encontró un camino, salir del bucle
        }

        sumaPesos += distancias[nodoActual][siguienteNodo];
        rutaMasCorta.append(" -> ").append(getLabelE(siguienteNodo));
        nodoActual = siguienteNodo;
    }

    rutaMasCorta.append("\nSuma de los pesos: ").append(sumaPesos);

    return rutaMasCorta.toString();
}


    private int encontrarVecinoMasCercano(int nodoActual, int destino) throws Exception {
        int vecinoMasCercano = -1;
        double distanciaMasCorta = Double.POSITIVE_INFINITY;

        for (int k = 1; k <= num_vertice(); k++) {
            if (nodoActual != k && existe_arista(nodoActual, k) && distancias[k][destino] < distanciaMasCorta) {
                vecinoMasCercano = k;
                distanciaMasCorta = distancias[k][destino];
            }
        }

        return vecinoMasCercano;
    }

    
//========================================================================================================
//========================================================================================================
//FLOYD
        public String aplicarAlgoritmoFloydConEtiquetas() throws Exception {
        StringBuilder resultado = new StringBuilder();
        distancias = new Double[num_vertice() + 1][num_vertice() + 1];
        for (int i = 1; i <= num_vertice(); i++) {
            for (int j = 1; j <= num_vertice(); j++) {
                if (i == j) {
                    distancias[i][j] = 0.0;
                } else if (existe_arista(i, j)) {
                    distancias[i][j] = peso_arista(i, j);
                } else {
                    distancias[i][j] = Double.POSITIVE_INFINITY;
                }
            }
        }
        resultado.append("Matriz de distancias con etiquetas después de aplicar Floyd:\n");

        // Encabezados de columnas
        resultado.append(String.format("%-40s", "")); // Espacios adicionales para ajustar la alineación
        for (int i = 1; i <= num_vertice(); i++) {
            resultado.append(String.format("%-30s", getLabelE(i)));
        }
        resultado.append("\n");

        for (int i = 1; i <= num_vertice(); i++) {
            // Etiqueta de fila
            resultado.append(String.format("%-40s", getLabelE(i)));

            for (int j = 1; j <= num_vertice(); j++) {
                // Valor de la matriz de distancias
                if (distancias[i][j] == Double.POSITIVE_INFINITY) {
                    resultado.append(String.format("%-30s", "INF"));
                } else {
                    resultado.append(String.format("%-30.2f", distancias[i][j]));
                }
            }
            resultado.append("\n");
        }
        return resultado.toString();
    }

        
//========================================================================================================
//========================================================================================================
//BELLMAN
    public String aplicarAlgoritmoBellmanFord(int nodoOrigen) throws Exception {
        StringBuilder resultado = new StringBuilder();
        distancias = new Double[num_vertice() + 1][num_vertice() + 1];
        for (int i = 1; i <= num_vertice(); i++) {
            for (int j = 1; j <= num_vertice(); j++) {
                if (i == j) {
                    distancias[i][j] = 0.0;
                } else if (existe_arista(i, j)) {
                    distancias[i][j] = peso_arista(i, j);
                } else {
                    distancias[i][j] = Double.POSITIVE_INFINITY;
                }
            }
        }

        // Inicializamos las distancias desde el nodo de origen
        for (int i = 1; i <= num_vertice(); i++) {
            if (i != nodoOrigen) {
                distancias[nodoOrigen][i] = Double.POSITIVE_INFINITY;
            }
        }

        // Relajamos las aristas repetidamente
        for (int i = 1; i <= num_vertice() - 1; i++) {
            for (int u = 1; u <= num_vertice(); u++) {
                for (int v = 1; v <= num_vertice(); v++) {
                    if (existe_arista(u, v)) {
                        double pesoUV = peso_arista(u, v);

                        if (distancias[nodoOrigen][u] + pesoUV < distancias[nodoOrigen][v]) {
                            distancias[nodoOrigen][v] = distancias[nodoOrigen][u] + pesoUV;
                        }
                    }
                }
            }
        }

        // Comprobamos si hay ciclos negativos después de las iteraciones
        for (int u = 1; u <= num_vertice(); u++) {
            for (int v = 1; v <= num_vertice(); v++) {
                if (existe_arista(u, v)) {
                    double pesoUV = peso_arista(u, v);

                    if (distancias[nodoOrigen][u] + pesoUV < distancias[nodoOrigen][v]) {
                        throw new Exception("Hay ciclo negativo");
                    }
                }
            }
        }

        // Construir la representación de la matriz de distancias con etiquetas
        resultado.append("Matriz de distancias con etiquetas después de aplicar Bellman-Ford:\n");

        // Encabezados de columnas
        resultado.append(String.format("%-40s", "")); // Espacios adicionales para ajustar la alineación
        for (int i = 1; i <= num_vertice(); i++) {
            resultado.append(String.format("%-30s", getLabelE(i)));
        }
        resultado.append("\n");

        for (int i = 1; i <= num_vertice(); i++) {
            // Etiqueta de fila
            resultado.append(String.format("%-40s", getLabelE(i)));

            for (int j = 1; j <= num_vertice(); j++) {
                // Valor de la matriz de distancias
                if (distancias[i][j] == Double.POSITIVE_INFINITY) {
                    resultado.append(String.format("%-30s", "INF"));
                } else {
                    resultado.append(String.format("%-30.2f", distancias[i][j]));
                }
            }
            resultado.append("\n");
        }

        // Actualizar la variable de instancia this.distancias
        this.distancias = distancias;
        return resultado.toString();
    }

// Método DFS modificado para verificar la conectividad
    public boolean DFS(Integer verticeInicial) throws Exception {
        boolean[] visitados = new boolean[num_vertice()];
        DFSUtil(verticeInicial, visitados);
        // Verificar si todos los nodos fueron visitados
        for (boolean visitado : visitados) {
            if (!visitado) {
                return false; // Si hay algún nodo no visitado, el grafo no está conectado
            }
        }
        return true; // Si todos los nodos fueron visitados, el grafo está conectado
    }

    // Método auxiliar DFS para marcar nodos visitados y verificar conectividad
    private void DFSUtil(Integer verticeActual, boolean[] visitados) throws Exception {
        visitados[verticeActual - 1] = true;
        System.out.println("Visitando nodo: " + verticeActual);
        DynamicList<Adyacencia> adyacentes = adyacentes(verticeActual);
        for (int i = 0; i < adyacentes.getLength(); i++) {
            Adyacencia adyacente = adyacentes.getInfo(i);
            Integer verticeAdyacente = adyacente.getDestino();
            if (!visitados[verticeAdyacente - 1]) {
                DFSUtil(verticeAdyacente, visitados);
            }
        }
    }

    // Método BFS modificado para verificar la conectividad
    public boolean BFS(Integer verticeInicial) throws Exception {
        boolean[] visitados = new boolean[num_vertice()];
        BFSUtil(verticeInicial, visitados);
        // Verificar si todos los nodos fueron visitados
        for (boolean visitado : visitados) {
            if (!visitado) {
                return false; // Si hay algún nodo no visitado, el grafo no está conectado
            }
        }
        return true; // Si todos los nodos fueron visitados, el grafo está conectado
    }

    // Método auxiliar BFS para marcar nodos visitados y verificar conectividad
    private void BFSUtil(Integer verticeInicial, boolean[] visitados) throws Exception {
        QueueUltimate<Integer> cola = new QueueUltimate<>(num_vertice());
        visitados[verticeInicial - 1] = true;
        cola.queue(verticeInicial);
        while (!cola.isEmpty()) {
            Integer verticeActual = cola.dequeue();
            System.out.println("Visitando nodo: " + verticeActual);
            DynamicList<Adyacencia> adyacentes = adyacentes(verticeActual);
            for (int i = 0; i < adyacentes.getLength(); i++) {
                Adyacencia adyacente = adyacentes.getInfo(i);
                Integer verticeAdyacente = adyacente.getDestino();
                if (!visitados[verticeAdyacente - 1]) {
                    visitados[verticeAdyacente - 1] = true;
                    cola.queue(verticeAdyacente);
                }
            }
        }
    }

    public void conectarAleatoriamente() throws Exception {
        Random rand = new Random();
        for (int i = 1; i <= num_vertice(); i++) {
            int numConexiones = rand.nextInt(2) + 2; // Genera un número aleatorio entre 2 y 3
            for (int j = 0; j < numConexiones; j++) {
                int nodoDestino = rand.nextInt(num_vertice()) + 1; // Selecciona un nodo destino aleatorio
                // Asegúrate de que el nodo destino no sea el mismo nodo actual ni esté ya conectado
                while (nodoDestino == i || existe_arista(i, nodoDestino)) {
                    nodoDestino = rand.nextInt(num_vertice()) + 1;
                }
                // Conecta el nodo actual con el nodo destino
                Double dist = UtilesVistaSubEstacion.calcularDistanciaSubEstaciones((SubEstacion) getLabelE(i), (SubEstacion) getLabelE(nodoDestino));
                insertar_arista(i, nodoDestino, dist);
            }
        }
    }

    public GrafosEtiquetadosDirigidos<SubEstacion> generarGrafoAleatorio(int numVertices) throws Exception {
        GrafosEtiquetadosDirigidos<SubEstacion> grafo = new GrafosEtiquetadosDirigidos<>(numVertices, SubEstacion.class);
        DynamicList<SubEstacion> subEstacionesAleatorias = generarSubEstacionesAleatorias(numVertices);

        for (int i = 1; i <= numVertices; i++) {
            grafo.labelVertice(i, subEstacionesAleatorias.getInfo(i - 1));
        }

        return grafo;
    }

    private DynamicList<SubEstacion> generarSubEstacionesAleatorias(int numVertices) {
        DynamicList<SubEstacion> subEstaciones = new DynamicList<>();
        for (int i = 0; i < numVertices; i++) {
            SubEstacion subEstacion = new SubEstacion();
            subEstacion.setNombre("Subestacion " + (i + 1));
            subEstacion.getCoordenada().setLongitud(Math.random() * 360 - 180);
            subEstacion.getCoordenada().setLatitud(Math.random() * 180 - 90);
            subEstaciones.add(subEstacion);
        }
        return subEstaciones;
    }

    public static void main(String[] args) throws Exception {
        // Crear el grafo
        int cantidad = 10;
        GrafosEtiquetadosDirigidos grafo = new GrafosEtiquetadosDirigidos(cantidad, SubEstacion.class);
        GrafosEtiquetadosDirigidos grafoAletorio = grafo.generarGrafoAleatorio(cantidad);

        // Medir el tiempo de ejecución del algoritmo de Floyd
        long inicioFloyd = System.currentTimeMillis();
        System.out.println(grafoAletorio.aplicarAlgoritmoFloydConEtiquetas());
        long finFloyd = System.currentTimeMillis();
        long tiempoFloyd = finFloyd - inicioFloyd;
        System.out.println("Tiempo de ejecución del algoritmo de Floyd: " + tiempoFloyd + " milisegundos");

        // Medir el tiempo de ejecución del algoritmo de Bellman-Ford
        long inicioBellman = System.currentTimeMillis();
        System.out.println(grafoAletorio.aplicarAlgoritmoFloydConEtiquetas());
        long finBellman = System.currentTimeMillis();
        long tiempoBellman = finBellman - inicioBellman;
        System.out.println("Tiempo de ejecución del algoritmo de Bellman-Ford: " + tiempoBellman + " milisegundos");
    }
}
