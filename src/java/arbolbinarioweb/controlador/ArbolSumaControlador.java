/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbolbinarioweb.controlador;

import arbolbinario.modeloespecial.Nodo;
import arbolbinario.modelo.excepciones.ArbolBinarioException;
import arbolbinario.modeloespecial.ArbolBinario;
import arbolbinario.modeloespecial.Dato;
import arbolbinarioweb.controlador.util.JsfUtil;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import org.primefaces.model.diagram.Connection;
import org.primefaces.model.diagram.DefaultDiagramModel;
import org.primefaces.model.diagram.Element;
import org.primefaces.model.diagram.connector.StraightConnector;
import org.primefaces.model.diagram.endpoint.DotEndPoint;
import org.primefaces.model.diagram.endpoint.EndPointAnchor;

/**
 *
 * @author giovanni
 */
@Named(value = "arbolSumaControlador")
@SessionScoped
public class ArbolSumaControlador implements Serializable {

    private int numero;
    private ArbolBinario arbol;
    private DefaultDiagramModel model;
    private String numSeleccionado; // Atributo distancia. Esta variable va a estar viva todo el tiempo que este beam (arbolSumaControlador) este vivo.
    private String textoHeader;
    
    /**
     * Creates a new instance of ArbolSumaControlador
     */
    public ArbolSumaControlador() {
    }

    //Método PostConstrut para inicializar el arbol 
    @PostConstruct
    private void inicializar() {
        arbol = new ArbolBinario(); //aca instanciamos
        textoHeader = "Arbol sumatoria +";
    }

    public String getTextoHeader() {
        return textoHeader;
    }

    public void setTextoHeader(String textoHeader) {
        this.textoHeader = textoHeader;
    }
    
    

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public DefaultDiagramModel getModel() {
        return model;
    }

    public String getNumSeleccionado() {
        return numSeleccionado;
    }

    public void setNumSeleccionado(String numSeleccionado) {
        this.numSeleccionado = numSeleccionado;
    }

    
    
    
    public void adicionarNodo() {
        try {
            arbol.adicionarNodo(new Dato(numero), arbol.getRaiz());
            pintarArbol();
        } catch (ArbolBinarioException ex) {
            JsfUtil.addErrorMessage(ex.getMessage());
        }
    }

    //Método que recorre el arbol en preorden y pinta los nodos
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
            Element elementHijo = new Element("Suma: " + reco.getDato().getSuma() + " Id:" + reco.getDato().getNumero());
            
            elementHijo.setId(String.valueOf(reco.getDato().getNumero()));
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

    public void onClickRight() {
        String id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("elementId");
        JsfUtil.addSuccessMessage(id);
        System.out.println(id.replaceAll("frmArbolSuma:diagrama-", ""));
        numSeleccionado = String.valueOf(id.replaceAll("frmArbolSuma:diagrama-", ""));
        
        //Short.valueOf(id.replaceAll("mnuForm", ""));

    }

   
}
