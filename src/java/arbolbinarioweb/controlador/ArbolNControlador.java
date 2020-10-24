/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbolbinarioweb.controlador;

import arbolbinario.modelo.excepciones.ArbolBinarioException;
import arbolbinarioweb.controlador.util.JsfUtil;
import arbolnario.modelo.ArbolEne;
import arbolnario.modelo.DatoEne;
import arbolnario.modelo.NodoEne;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
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
@Named(value = "arbolNControlador")
@SessionScoped
public class ArbolNControlador implements Serializable {
    private int id;
    private List<NodoEne> hijos;
    private ArbolEne arbol;
    private DefaultDiagramModel model;

    /**
     * Creates a new instance of ArbolNControlador
     */
    public ArbolNControlador() {
    }
    
    //Método PostConstrut para inicializar el arbol 
    @PostConstruct
    private void inicializar()
    {
        arbol = new ArbolEne(); //aca instanciamos 
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public DefaultDiagramModel getModel() {
        return model;
    }
    
    public void adicionarNodo() throws ArbolBinarioException
    {
        arbol.adicionarNodo(new DatoEne(id), arbol.getRaiz());
        pintarArbol();
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
    
    private void pintarArbol(NodoEne reco, DefaultDiagramModel model, Element padre, int x, int y) {

        if (reco != null) {
            Element elementHijo = new Element("S: " + reco.getDato().getId() + " (N: )");

            elementHijo.setX(String.valueOf(x) + "em");
            elementHijo.setY(String.valueOf(y) + "em");

            if (padre != null) {
                elementHijo.addEndPoint(new DotEndPoint(EndPointAnchor.TOP));
                DotEndPoint conectorPadre = new DotEndPoint(EndPointAnchor.BOTTOM);
                padre.addEndPoint(conectorPadre);
                model.connect(new Connection(conectorPadre, elementHijo.getEndPoints().get(0)));

            }

            model.addElement(elementHijo);

            pintarArbol(reco.getHijos().get(y), model, elementHijo, x - 5, y + 5);
            pintarArbol(reco.getHijos().get(y), model, elementHijo, x + 5, y + 5);
        }
    }
    
}
