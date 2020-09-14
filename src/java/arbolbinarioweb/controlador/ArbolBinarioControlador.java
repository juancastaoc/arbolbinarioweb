/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbolbinarioweb.controlador;

import arbolbinario.modelo.ArbolBinario;
import arbolbinario.modelo.Nodo;
import arbolbinario.modelo.excepciones.ArbolBinarioException;
import arbolbinarioweb.controlador.util.JsfUtil;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.model.diagram.Connection;
import org.primefaces.model.diagram.DefaultDiagramModel;
import org.primefaces.model.diagram.Element;
import org.primefaces.model.diagram.connector.Connector;
import org.primefaces.model.diagram.connector.StraightConnector;
import org.primefaces.model.diagram.endpoint.DotEndPoint;
import org.primefaces.model.diagram.endpoint.EndPointAnchor;

/**
 *
 * @author carloaiza
 * @author giovannifranco
 * @author juancastno
 */
@Named(value = "arbolBinarioControlador")
@SessionScoped
public class ArbolBinarioControlador implements Serializable {

    private DefaultDiagramModel model;
    private DefaultDiagramModel modelArbol2;

    private ArbolBinario arbol = new ArbolBinario();
    private int dato;
    private boolean verInOrden = false;
    private boolean verPreorden = false;
    private boolean verPostOrden = false;
    private boolean verNiveles = false;
    private boolean verNivelesOrdenado = false;
    private boolean verhojas = false;
    private boolean verbuscar = false;
    private boolean verBalance = false;
    
    
    
    public boolean isVerRamaMayor() {
        return verRamaMayor;
    }

    public void setVerRamaMayor(boolean verRamaMayor) {
        this.verRamaMayor = verRamaMayor;
    }
    private boolean verRamaMayor = false;
    
    

    private String datoscsv = "18,15,13,17,8,14,-8,10,59,28,80,78,90";
    private int terminado;
    private ArbolBinario arbolTerminados = new ArbolBinario();

    public ArbolBinario getArbolTerminados() {
        return arbolTerminados;
    }

    public void setArbolTerminados(ArbolBinario arbolTerminados) {
        this.arbolTerminados = arbolTerminados;
    }

    public int getTerminado() {
        return terminado;
    }

    public void setTerminado(int terminado) {
        this.terminado = terminado;
    }

    public DefaultDiagramModel getModelArbol2() {
        return modelArbol2;
    }

    public void setModelArbol2(DefaultDiagramModel modelArbol2) {
        this.modelArbol2 = modelArbol2;
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

    
        public boolean isVerPreorden() {
        return verPreorden;
    }

    public void setVerPreorden(boolean verPreorden) {
        this.verPreorden = verPreorden;
    }
    
    
            public boolean isVerPostOrden() {
        return verPostOrden;
    }

    public void setVerPostOrden(boolean verPostOrden) {
        this.verPostOrden = verPostOrden;
    }
    
        public boolean isVerNiveles() {
        return verNiveles;
    }

    public void setVerNiveles(boolean verNiveles) {
        this.verNiveles = verNiveles;
    }
    
        public boolean isVerNivelesOrdenado() {
        return verNivelesOrdenado;
    }

    public void setVerNivelesOrdenado(boolean verNivelesOrdenado) {
        this.verNivelesOrdenado = verNivelesOrdenado;
    }
    
    public boolean isVerhojas() {
        return verhojas;
    }

    public void setVerhojas(boolean verhojas) {
        this.verhojas = verhojas;
    }
    
    
        public boolean isVerbuscar() {
        return verbuscar;
    }
    
        public void setVerbuscar(boolean verbuscar) {
        this.verbuscar = verbuscar;
    }
    
    public boolean isVerBalance() {
        return verBalance;
    }

    public void setVerBalance(boolean verBalance) {
        this.verBalance = verBalance;
    }
    
    
    
    
    
    
    
    public int getDato() {
        return dato;
    }

    public void setDato(int dato) {
        this.dato = dato;
    }

    public ArbolBinario getArbol() {
        return arbol;
    }

    public void setArbol(ArbolBinario arbol) {
        this.arbol = arbol;
    }

    /**
     * Creates a new instance of ArbolBinarioControlador
     */
    public ArbolBinarioControlador() {

    }

    public void adicionarNodo() {
        try {
            arbol.adicionarNodo(dato, arbol.getRaiz());
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

    public void habilitarPreOrden (){
        try {
            arbol.isLleno();
            verPreorden = true;
            verInOrden = false;
        

        } catch (ArbolBinarioException ex) {
            JsfUtil.addErrorMessage(ex.getMessage());

        }
    } 
    
        public void habilitarPostOrden (){
        try {
            arbol.isLleno();
            verPostOrden = true;
            verPreorden = false;
            verInOrden = false;  
            

        } catch (ArbolBinarioException ex) {
            JsfUtil.addErrorMessage(ex.getMessage());

        }
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
        System.out.println(arbol.borrar(x));
        this.pintarArbol();
    } 
    public void podarArbol() {
        arbol.podar();
        this.pintarArbol();
    }   
         
    public void balance(){
        try {
            verBalance = true;
            verbuscar = false;
            verhojas = false;
            verNivelesOrdenado = false;
            verNiveles = false;
            verPostOrden = false;
            verPreorden = false;
            verInOrden = false;
                    

        } catch (Exception ex) {
            JsfUtil.addErrorMessage(ex.getMessage());
        }           
        
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
     
     public void multiplicarArbol(int multiplicador){
         this.arbol.multiplicar(multiplicador);
         this.pintarArbol();
     }
     
     public void BorraNivel(int nivel){
         try {
             
             this.arbol.borrarNivel(nivel);
             JsfUtil.addSuccessMessage("El nivel ha sido eliminado");
            this.pintarArbol();         
             
         }catch(Exception e){
             JsfUtil.addSuccessMessage("El nivel ingresado no esta en el arbol ");
         }
             
         
         
     }
     
     public String CantidadNodos(){
        return this.arbol.cantidadNodos();
    }
     
          public String CantidadNodosHoja(){
        return this.arbol.cantidadNodosHoja();
    }
          
          public String menorValor() throws ArbolBinarioException{
              if (this.arbol == null){
                  return "0";
              }
              else
        return this.arbol.menorValor();
    }
     
          public String mayorValor() throws ArbolBinarioException{
        return this.arbol.mayorValor();
    }
     
     public void habilitarRamaMayor(){
         try {
            verRamaMayor = true;
            verBalance = false;
            verbuscar = false;
            verhojas = false;
            verNivelesOrdenado = false;
            verNiveles = false;
            verPostOrden = false;
            verPreorden = false;
            verInOrden = false;

        } catch (Exception ex) {
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

    private void pintarArbol(Nodo reco, DefaultDiagramModel model, Element padre, int x, int y) {

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

    public void buscarTerminadosEn() {
        for (Element ele : model.getElements()) {
            ele.setStyleClass("ui-diagram-element");
            int numTerm = Integer.parseInt(ele.getData().toString());
            if (numTerm < 0) {
                numTerm *= -1;
            }
            if (numTerm % 10 == terminado) {
                ele.setStyleClass("ui-diagram-element-busc");
            }
        }
    }

    public void encontrarTerminadosEn() {
        try {
            arbolTerminados = new ArbolBinario();
            encontrarTerminadosEn(arbol.getRaiz());
            pintarArbolTerminados();
        } catch (ArbolBinarioException ex) {
            JsfUtil.addErrorMessage("Ocurrio un error generando el árbol de terminados" + ex);
        }
    }

    private void encontrarTerminadosEn(Nodo reco) throws ArbolBinarioException {
        if (reco != null) {
            int numTerm= reco.getDato();
            if(numTerm<0)
            {
                numTerm *=-1;
            }
            if(numTerm%10==terminado)
            {
                arbolTerminados.adicionarNodo(reco.getDato(), arbolTerminados.getRaiz());
            }
            encontrarTerminadosEn(reco.getIzquierda());
            encontrarTerminadosEn(reco.getDerecha());
        }
    }

    public void pintarArbolTerminados() {

        modelArbol2 = new DefaultDiagramModel();
        modelArbol2.setMaxConnections(-1);
        modelArbol2.setConnectionsDetachable(false);
        StraightConnector connector = new StraightConnector();
        connector.setPaintStyle("{strokeStyle:'#404a4e', lineWidth:2}");
        connector.setHoverPaintStyle("{strokeStyle:'#20282b'}");
        modelArbol2.setDefaultConnector(connector);
        pintarArbolTerminados(arbolTerminados.getRaiz(), modelArbol2, null, 30, 0);

    }

    private void pintarArbolTerminados(Nodo reco, DefaultDiagramModel model, Element padre, int x, int y) {

        if (reco != null) {
            Element elementHijo = new Element(reco.getDato());

            elementHijo.setX(String.valueOf(x) + "em");
            elementHijo.setY(String.valueOf(y) + "em");

            if (padre != null) {
                elementHijo.addEndPoint(new DotEndPoint(EndPointAnchor.TOP));
                DotEndPoint conectorPadre = new DotEndPoint(EndPointAnchor.BOTTOM);
                padre.addEndPoint(conectorPadre);
                model.connect(new Connection(conectorPadre, elementHijo.getEndPoints().get(0)));

            }

            model.addElement(elementHijo);

            pintarArbolTerminados(reco.getIzquierda(), model, elementHijo, x - 5, y + 5);
            pintarArbolTerminados(reco.getDerecha(), model, elementHijo, x + 5, y + 5);
        }
    }

}
