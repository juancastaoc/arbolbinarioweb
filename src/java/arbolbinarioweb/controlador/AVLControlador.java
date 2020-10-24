/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbolbinarioweb.controlador;

import arbolbinario.modelo.excepciones.ArbolBinarioException;
import arbolbinarioweb.controlador.util.JsfUtil;
import avl.modelo.ArbolBinarioAVL;
import avl.modelo.NodoAVL;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import org.primefaces.model.diagram.Connection;
import org.primefaces.model.diagram.DefaultDiagramModel;
import org.primefaces.model.diagram.Element;
import org.primefaces.model.diagram.connector.StraightConnector;
import org.primefaces.model.diagram.endpoint.DotEndPoint;
import org.primefaces.model.diagram.endpoint.EndPointAnchor;

/**
 *
 * @author carloaiza
 * 
 */
@Named(value = "avlControlador")
@SessionScoped
public class AVLControlador implements Serializable {

    /**
     * @return the datoAgregar
     */
    public int getDatoAgregar() {
        return datoAgregar;
    }

    /**
     * @param datoAgregar the datoAgregar to set
     */
    public void setDatoAgregar(int datoAgregar) {
        this.datoAgregar = datoAgregar;
    }

    /**
     * @return the verRamaMayor
     */
    public boolean isVerRamaMayor() {
        return verRamaMayor;
    }

    /**
     * @param verRamaMayor the verRamaMayor to set
     */
    public void setVerRamaMayor(boolean verRamaMayor) {
        this.verRamaMayor = verRamaMayor;
    }

    /**
     * @return the verNiveles
     */
    public boolean isVerNiveles() {
        return verNiveles;
    }

    /**
     * @param verNiveles the verNiveles to set
     */
    public void setVerNiveles(boolean verNiveles) {
        this.verNiveles = verNiveles;
    }

    /**
     * @return the verNivelesOrdenado
     */
    public boolean isVerNivelesOrdenado() {
        return verNivelesOrdenado;
    }

    /**
     * @param verNivelesOrdenado the verNivelesOrdenado to set
     */
    public void setVerNivelesOrdenado(boolean verNivelesOrdenado) {
        this.verNivelesOrdenado = verNivelesOrdenado;
    }

    /**
     * @return the verhojas
     */
    public boolean isVerhojas() {
        return verhojas;
    }

    /**
     * @param verhojas the verhojas to set
     */
    public void setVerhojas(boolean verhojas) {
        this.verhojas = verhojas;
    }

    /**
     * @return the verbuscar
     */
    public boolean isVerbuscar() {
        return verbuscar;
    }

    /**
     * @param verbuscar the verbuscar to set
     */
    public void setVerbuscar(boolean verbuscar) {
        this.verbuscar = verbuscar;
    }

     private DefaultDiagramModel model;
    

    private ArbolBinarioAVL arbol = new ArbolBinarioAVL();
    private int dato;
    private int datoAgregar;
    private boolean verInOrden = false;
    private boolean verPreorden = false;
    private boolean verPostOrden = false;
    private boolean verNiveles = false;
    private boolean verNivelesOrdenado = false;
    private boolean verhojas = false;
    private boolean verbuscar = false;
    private String datoscsv = "1,2,3,4,5,6,7,8,9,10,11,12,13,14,15";//,-8,10,59,28,80,78,90";
    private int terminado;
    private boolean verRamaMayor = false;
    private ArbolBinarioAVL arbolTerminados = new ArbolBinarioAVL();
    
    /**
     * @return the verPreorden
     */
    public boolean isVerPreorden() {
        return verPreorden;
    }

    /**
     * @param verPreorden the verPreorden to set
     */
    public void setVerPreorden(boolean verPreorden) {
        this.verPreorden = verPreorden;
    }

    /**
     * @return the verPostOrden
     */
    public boolean isVerPostOrden() {
        return verPostOrden;
    }

    /**
     * @param verPostOrden the verPostOrden to set
     */
    public void setVerPostOrden(boolean verPostOrden) {
        this.verPostOrden = verPostOrden;
    }

   

    public ArbolBinarioAVL getArbolTerminados() {
        return arbolTerminados;
    }

    public void setArbolTerminados(ArbolBinarioAVL arbolTerminados) {
        this.arbolTerminados = arbolTerminados;
    }

    public int getTerminado() {
        return terminado;
    }

    public void setTerminado(int terminado) {
        this.terminado = terminado;
    }

    
    public String getDatoscsv() {
        return datoscsv;
    }

    public void setDatoscsv(String datoscsv) {
        this.datoscsv = datoscsv;
    }

    public boolean isVerInOrden() {
        return verInOrden;
    }

    public void setVerInOrden(boolean verInOrden) {
        this.verInOrden = verInOrden;
    }

    public int getDato() {
        return dato;
    }

    public void setDato(int dato) {
        this.dato = dato;
    }

    public ArbolBinarioAVL getArbol() {
        return arbol;
    }

    public void setArbol(ArbolBinarioAVL arbol) {
        this.arbol = arbol;
    }

    /**
     * Creates a new instance of ArbolBinarioControlador
     */
    public AVLControlador() {

    }

    public void adicionarNodo() {
        try {
            arbol.adicionarNodo(datoAgregar, arbol.getRaiz());
            JsfUtil.addSuccessMessage("El dato ha sido adicionado");
            dato = 0;
            pintarArbol();

        } catch (ArbolBinarioException ex) {
            JsfUtil.addErrorMessage(ex.getMessage());
        }
    }

    public void habilitarInOrden() {
        try {
            arbol.isLleno();
            verInOrden = true;
        } catch (ArbolBinarioException ex) {
            JsfUtil.addErrorMessage(ex.getMessage());
        }
    }
    
    public void habilitarPostOrden() {
        try {
            arbol.isLleno();
            verPostOrden = true;
        } catch (ArbolBinarioException ex) {
            JsfUtil.addErrorMessage(ex.getMessage());
        }
    }
    
    public void habilitarPreOrden() {
        try {
            arbol.isLleno();
            verPreorden = true;
        } catch (ArbolBinarioException ex) {
            JsfUtil.addErrorMessage(ex.getMessage());
        }
    }

    public DefaultDiagramModel getModel() {
        return model;
    }

    public void setModel(DefaultDiagramModel model) {
        this.model = model;
    }

    public void pintarArbol() {

        model = new DefaultDiagramModel();
        model.setMaxConnections(-1);
        model.setConnectionsDetachable(false);
        StraightConnector connector = new StraightConnector();
        connector.setPaintStyle("{strokeStyle:'#404a4e', lineWidth:2}");
        connector.setHoverPaintStyle("{strokeStyle:'#20282b'}");
        model.setDefaultConnector(connector);
        pintarArbol(arbol.getRaiz(), model, null, 30, 0);

    }

    private void pintarArbol(NodoAVL reco, DefaultDiagramModel model, Element padre, int x, int y) {
        if (reco != null) {
            Element elementHijo = new Element(reco.getDato() 
                    + " " + "(" + "G:" + reco.obtenerGradoNodo() + " H:" + reco.obtenerAlturaNodo()+")");

            elementHijo.setX(String.valueOf(x) + "em");
            elementHijo.setY(String.valueOf(y) + "em");

            if (padre != null) {
                elementHijo.addEndPoint(new DotEndPoint(EndPointAnchor.TOP));
                DotEndPoint conectorPadre = new DotEndPoint(EndPointAnchor.BOTTOM);
                padre.addEndPoint(conectorPadre);
                model.connect(new Connection(conectorPadre, elementHijo.getEndPoints().get(0)));

            }

            model.addElement(elementHijo);

            pintarArbol(reco.getIzquierda(), model, elementHijo, x - 5, y + 5);
            pintarArbol(reco.getDerecha(), model, elementHijo, x + 5, y + 5);
        }
    }

    public void extraerDatos() {
        try {
            arbol.setRaiz(null);
            arbol.llenarArbol(datoscsv);
            pintarArbol();
            datoscsv = "";
        } catch (ArbolBinarioException ex) {
            JsfUtil.addErrorMessage("Los datos ingresados no tienen el formato separado por comas");
        }
    }

    
    public void balancear(){
        arbol.balancear(arbol.getRaiz());
        pintarArbol();
        
    }
    
     public void habilitarVistaPorNiveles (){
        try {
            arbol.isLleno();
            verNiveles = true;
            verPostOrden = false;
            verPreorden = false;
            verInOrden = false; 
     
        } catch (ArbolBinarioException ex) {
            JsfUtil.addErrorMessage(ex.getMessage());

        }
    }
    
         public void habilitarVistaPorNivelesOrdenado(){
        try {
            arbol.isLleno();
            verNivelesOrdenado = true;
            verNiveles = false;
            verPostOrden = false;
            
        } catch (ArbolBinarioException ex) {
            JsfUtil.addErrorMessage(ex.getMessage());

        }
    }
    
         public void habilitarVistaHojas(){
        try {
            arbol.isLleno();
            verhojas = true;
            verNivelesOrdenado = false;
            verNiveles = false;
          

        } catch (ArbolBinarioException ex) {
            JsfUtil.addErrorMessage(ex.getMessage());

        }
    } 
         
    public void buscar() {
        
        int x = this.dato;
        System.out.println(arbol.buscar(x));
    } 
    
    public int buscarPadre(){
        try {
            arbol.isLleno();
            verbuscar = true;
            verhojas = false;
            verNivelesOrdenado = false;
            verNiveles = false;
            verPostOrden = false;
            verPreorden = false;
            verInOrden = false;

            int x = this.dato;
            return arbol.padre(x);

        } catch (ArbolBinarioException ex) {
            JsfUtil.addErrorMessage(ex.getMessage());

        }
        return 0;
    }
         
      public void borrar() {
        
        int x = this.dato;
        this.arbol.borrar(x);
        this.arbol.balancear(arbol.getRaiz());
        this.pintarArbol();
    } 
      
    public void podarArbol() {
        arbol.podar();
        this.pintarArbol();
    }   
    
    public void borrarMayor(){
        this.arbol.borrarMayor();
        this.pintarArbol();
    }    
         
     public void borrarMenor(){
        this.arbol.borrarMenor();
        this.pintarArbol();
    }
     
     
     public void cambiarValores(){
        this.arbol.cambiar();
        this.pintarArbol();
    }
    
}