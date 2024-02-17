/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador.TDA.grafos;

import controlador.TDA.grafos.exception.VerticeException;
import controlador.TDA.listas.DynamicList;

/**
 *
 * @author Santiago
 */
public class GrafoDirigido extends Grafo {

    private Integer num_vertice;
    private Integer num_aristas;
    private DynamicList<Adyacencia> listaAdyacencias[];

    public GrafoDirigido(Integer num_vertices) {
        this.num_vertice = num_vertices;
        this.num_aristas = 0;
        this.listaAdyacencias = new DynamicList[num_vertices + 1];
        for (int i = 0; i <= this.num_vertice; i++) {
            listaAdyacencias[i] = new DynamicList<>();
        }
    }

    @Override
    public Integer num_vertice() {
        return num_vertice;
    }

    @Override
    public Integer num_aristas() {
        return num_aristas;
    }

    @Override
    public Boolean existe_arista(Integer v1, Integer v2) throws Exception {
        Boolean band = false;
        if (v1.intValue() <= num_vertice && v2.intValue() <= num_vertice) {
            DynamicList<Adyacencia> listaA = listaAdyacencias[v1];
            for (int i = 0; i < listaA.getLength(); i++) {
                Adyacencia a = listaA.getInfo(i);
                if (a.getDestino().intValue() == v2.intValue()) {
                    band = true;
                    break;
                }
            }
        } else {
            throw new VerticeException();
        }
        return band;
    }

    @Override
    public Double peso_arista(Integer v1, Integer v2) throws Exception {
        Double peso = Double.NaN;
        if (existe_arista(v1, v2)) {
            DynamicList<Adyacencia> listaA = listaAdyacencias[v1];
            for (int i = 0; i < listaA.getLength(); i++) {
                Adyacencia a = listaA.getInfo(i);
                if (a.getDestino().intValue() == v2.intValue()) {
                    peso = a.getPeso();
                    break;
                }
            }
        }
        return peso;
    }

    @Override
    public void insertar_arista(Integer v1, Integer v2, Double peso) throws Exception{
        if (v1.intValue() <= num_vertice && v2.intValue() <= num_vertice) {
            if (!existe_arista(v1, v2)) {
                num_aristas++;
                listaAdyacencias[v1].add(new Adyacencia(v2, peso));
            }
        }else
            throw new VerticeException();
    }

    public DynamicList<Adyacencia>[] getListaAdyacencias() {
        return listaAdyacencias;
    }

    @Override
    public void insertar_arista(Integer v1, Integer v2) throws Exception{
        insertar_arista(v1, v2, Double.NaN);
    }

    public void setNum_aristas(Integer num_aristas) {
        this.num_aristas = num_aristas;
    }

    public void setListaAdyacencias(DynamicList<Adyacencia>[] listaAdyacencias) {
        this.listaAdyacencias = listaAdyacencias;
    }

    @Override
    public DynamicList<Adyacencia> adyacentes(Integer v1) {
        return listaAdyacencias[v1];
    }
}
