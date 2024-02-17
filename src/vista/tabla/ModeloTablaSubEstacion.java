/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista.tabla;

import controlador.TDA.listas.DynamicList;
import controlador.TDA.listas.Exception.EmptyException;
import javax.swing.table.AbstractTableModel;
import modelo.SubEstacion;

/**
 *
 * @author santi
 */
public class ModeloTablaSubEstacion extends AbstractTableModel {

    private DynamicList<SubEstacion> subEstaciones;

    @Override
    public int getRowCount() {
        return subEstaciones.getLength();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        try {
            SubEstacion sE = subEstaciones.getInfo(rowIndex);
            switch (columnIndex) {
                case 0:
                    return (sE != null) ? sE.getId(): " ";
                case 1:
                    return (sE != null) ? sE.getNombre(): " ";
                case 2:
                    return (sE != null) ? sE.getCoordenada().getLatitud(): "";
                case 3:
                    return (sE != null) ? sE.getCoordenada().getLongitud(): "";
                default:
                    return null;
            }
        } catch (EmptyException ex) {
            return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "NRO";
            case 1:
                return "NOMBRE";
            case 2:
                return "LATITUD";
            case 3:
                return "LONGITUD";
            default:
                return null;
        }
    }

    public DynamicList<SubEstacion> getSubEstaciones() {
        return subEstaciones;
    }

    public void setEscuelas(DynamicList<SubEstacion> subEstacion) {
        this.subEstaciones = subEstacion;
    }

}
