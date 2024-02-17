/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador.DAO.grafosEjemplo;

import controlador.DAO.DaoImplement;
import controlador.TDA.grafos.GrafosEtiquetadosDirigidos;
import controlador.TDA.listas.DynamicList;
import java.io.FileReader;
import java.io.FileWriter;
import modelo.SubEstacion;

/**
 *
 * @author Santiago
 */
public class SubEstacionDao extends DaoImplement<SubEstacion> {

    private DynamicList<SubEstacion> subEstaciones = new DynamicList<>();
    private SubEstacion subEstacion;
    private GrafosEtiquetadosDirigidos<SubEstacion> grafo;

    public DynamicList<SubEstacion> getSubEstaciones() {
        if (subEstaciones.isEmpty()) {
            subEstaciones = all();
        }
        return subEstaciones;
    }

    public void setSubEstaciones(DynamicList<SubEstacion> lista) {
        this.subEstaciones = lista;
    }

    public SubEstacion getSubEstacion() {
        if (subEstacion == null) {
            subEstacion = new SubEstacion();
        }
        return subEstacion;
    }

    public void setSubEstacion  (SubEstacion escuela) {
        this.subEstacion = escuela;
    }

    public Boolean persist() {
        getSubEstacion().setId(all().getLength() + 1);
        getSubEstacion().getCoordenada().setId(all().getLength() + 1);
        return persist(getSubEstacion());
    }

    public void guardarGrafo() throws Exception {
        getConection().toXML(grafo, new FileWriter("files/grafo.json"));
    }

    public SubEstacionDao() {
        super(SubEstacion.class);
    }

    /**
     * @return the grafo
     */
    public GrafosEtiquetadosDirigidos<SubEstacion> getGrafo() throws Exception {
        if (grafo == null) {
            DynamicList<SubEstacion> list = getSubEstaciones();
            if (!list.isEmpty()) {
                grafo = new GrafosEtiquetadosDirigidos(list.getLength(), SubEstacion.class);
                for (int i = 0; i < list.getLength(); i++) {
                    grafo.labelVertice((i + 1), list.getInfo(i));
                }
            }
        }
        return grafo;
    }

    public void loadGraph() throws Exception {
        grafo = (GrafosEtiquetadosDirigidos<SubEstacion>) 
                getConection()
                        .fromXML(new FileReader("files/grafo.json"));
        subEstaciones.reset();
        for (int i = 1; i < grafo.num_vertice(); i++) {
            subEstaciones.add(grafo.getLabelE(i));
        }
    }

    /**
     * @param grafo the grafo to set
     */
    public void setGrafo(GrafosEtiquetadosDirigidos<SubEstacion> grafo) {
        this.grafo = grafo;
    }

}
