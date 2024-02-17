/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista.grafoEjemplo.utilidades;

import controlador.DAO.grafosEjemplo.SubEstacionDao;
import controlador.TDA.grafos.Adyacencia;
import controlador.TDA.grafos.GrafosEtiquetadosDirigidos;
import controlador.TDA.listas.DynamicList;
import controlador.Utiles.Utiles;
import javax.swing.JComboBox;
import modelo.SubEstacion;

/**
 *
 * @author Santiago
 */
public class UtilesVistaSubEstacion {

    public static void cargarComboSubEstaciones(JComboBox cbx) throws Exception {
        cbx.removeAllItems();
        DynamicList<SubEstacion> list = new SubEstacionDao().getSubEstaciones();
        for (int i = 0; i < list.getLength(); i++) {
            cbx.addItem(list.getInfo(i));
        }
    }

    public static Double calcularDistanciaSubEstaciones(SubEstacion o, SubEstacion d) {
        Double dist = Utiles.coordGpsToKm(o.getCoordenada().getLatitud(), o.getCoordenada().getLongitud(),
                d.getCoordenada().getLatitud(), d.getCoordenada().getLongitud());
        return redondear(dist);
    }

    public static Double redondear(Double x) {
        Double d = Math.round(x * 100.0) / 100.0;
        return d;
    }

    public static void main(String[] args) throws Exception {
        GrafosEtiquetadosDirigidos<String> grafoFloyd = new GrafosEtiquetadosDirigidos(6, String.class);
        grafoFloyd.labelVertice(1, "Estefania");
        grafoFloyd.labelVertice(2, "Luna");
        grafoFloyd.labelVertice(3, "Jimenez");
        grafoFloyd.labelVertice(4, "Criollo");
        grafoFloyd.labelVertice(5, "Maritza");
        grafoFloyd.labelVertice(6, "Nivelo");
        grafoFloyd.insertEdgeE("Estefania", "Nivelo", 1.0);
        grafoFloyd.insertEdgeE("Estefania", "Maritza", 3.0);
        grafoFloyd.insertEdgeE("Estefania", "Luna", 4.0);
        grafoFloyd.insertEdgeE("Luna", "Jimenez", 3.0);
        grafoFloyd.insertEdgeE("Maritza", "Luna", 2.0);
        grafoFloyd.insertEdgeE("Jimenez", "Criollo", 1.0);
        grafoFloyd.insertEdgeE("Nivelo", "Maritza", 2.0);
        grafoFloyd.insertEdgeE("Criollo", "Maritza", 5.0);
        System.out.println(grafoFloyd.toString());
//        System.out.println(UtilesVistaSubEstacion.aplicarAlgoritmoFloydConEtiquetas(grafoFloyd));

    }
}
