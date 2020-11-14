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
@Named(value = "arbolNControlador")
@SessionScoped
public class ArbolNControlador implements Serializable {

    /**
     * @return the edad
     */
    public int getEdad() {
        return edad;
    }

    /**
     * @param edad the edad to set
     */
    public void setEdad(int edad) {
        this.edad = edad;
    }

    /**
     * @return the listaPromedios
     */
    public List<String> getListaPromedios() {
        return listaPromedios;
    }

    /**
     * @param listaPromedios the listaPromedios to set
     */
    public void setListaPromedios(List<String> listaPromedios) {
        this.listaPromedios = listaPromedios;
    }

    /**
     * @return the nuevaDependencia
     */
    public String getNuevaDependencia() {
        return nuevaDependencia;
    }

    /**
     * @param nuevaDependencia the nuevaDependencia to set
     */
    public void setNuevaDependencia(String nuevaDependencia) {
        this.nuevaDependencia = nuevaDependencia;
    }

    /**
     * @return the antiguaDependencia
     */
    public String getAntiguaDependencia() {
        return antiguaDependencia;
    }

    /**
     * @param antiguaDependencia the antiguaDependencia to set
     */
    public void setAntiguaDependencia(String antiguaDependencia) {
        this.antiguaDependencia = antiguaDependencia;
    }

    /**
     * @return the ciudadTrabajador
     */
    public String getCiudadTrabajador() {
        return ciudadTrabajador;
    }

    /**
     * @param ciudadTrabajador the CiudadTrabajador to set
     */
    public void setCiudadTrabajador(String CiudadTrabajador) {
        this.ciudadTrabajador = CiudadTrabajador;
    }

    /**
     * @return the teletrabajo
     */
    public String getTeletrabajo() {
        return teletrabajo;
    }

    /**
     * @param teletrabajo the teletrabajo to set
     */
    public void setTeletrabajo(String teletrabajo) {
        this.teletrabajo = teletrabajo;
    }

    /**
     * @return the genero
     */
    public String getGenero() {
        return genero;
    }

    /**
     * @param genero the genero to set
     */
    public void setGenero(String genero) {
        this.genero = genero;
    }

    /**
     * @return the listaGenero
     */
    public List<String> getListaGenero() {
        return listaGenero;
    }

    /**
     * @param listaGenero the listaGenero to set
     */
    public void setListaGenero(List<String> listaGenero) {
        this.listaGenero = listaGenero;
    }

    /**
     * @return the listaEmpleadosCiudad
     */
    public List<String> getListaEmpleadosCiudad() {
        return listaEmpleadosCiudad;
    }

    /**
     * @param listaEmpleadosCiudad the listaEmpleadosCiudad to set
     */
    public void setListaEmpleadosCiudad(List<String> listaEmpleadosCiudad) {
        this.listaEmpleadosCiudad = listaEmpleadosCiudad;
    }

    /**
     * @return the idJefeDeLaNomina
     */
    public int getIdJefeDeLaNomina() {
        return idJefeDeLaNomina;
    }

    /**
     * @param idJefeDeLaNomina the idJefeDeLaNomina to set
     */
    public void setIdJefeDeLaNomina(int idJefeDeLaNomina) {
        this.idJefeDeLaNomina = idJefeDeLaNomina;
    }

    /**
     * @return the idJefe
     */
    public String getIdJefe() {
        return idJefe;
    }

    /**
     * @param idJefe the idJefe to set
     */
    public void setIdJefe(String idJefe) {
        this.idJefe = idJefe;
    }

    

    /**
     * @return the ciudad
     */
    public String getCiudad() {
        return ciudad;
    }

    /**
     * @param ciudad the ciudad to set
     */
    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    /**
     * @return the salario
     */
    public int getSalario() {
        return salario;
    }

    /**
     * @param salario the salario to set
     */
    public void setSalario(int salario) {
        this.salario = salario;
    }

    /**
     * @return the dependencia
     */
    public String getDependencia() {
        return dependencia;
    }

    /**
     * @param dependencia the dependencia to set
     */
    public void setDependencia(String dependencia) {
        this.dependencia = dependencia;
    }
    private String id;
    private String nombre;
    private String ciudad;
    private int salario;
    private String dependencia;
    private String idJefe;
    private List<NodoEne> hijos;
    private ArbolEne arbol;
    private int idJefeDeLaNomina;
    private List<String> listaEmpleadosCiudad;
    private List<String> listaGenero;
    private String genero;
    private int edad;
    private String teletrabajo;
    private String ciudadTrabajador;
    private String nuevaDependencia;
    private String antiguaDependencia;
    private List<String> listaPromedios;
    private DefaultDiagramModel model;
    private String idSeleccionado; // Atributo distancia. Esta variable va a estar viva todo el tiempo que este beam (arbolSumaControlador) este vivo.

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    

    public String getIdSeleccionado() {
        return idSeleccionado;
    }

    public void setIdSeleccionado(String idSeleccionado) {
        this.idSeleccionado = idSeleccionado;
    }
    

    public DefaultDiagramModel getModel() {
        return model;
    }
    
    public void adicionarNodo() throws ArbolBinarioException
    {   try{
            DatoEne dato = new DatoEne();
            dato.setCiudad(ciudad);
            dato.setDependencia(dependencia);
            dato.setId(id);
            dato.setNombre(nombre);
            dato.setSalario(salario);
            dato.setGenero(genero);
            dato.setEdad(edad);
            if(teletrabajo.equals("si"))
                dato.setTeletrabajo(true);
            else
                dato.setTeletrabajo(false);
            arbol.adicionarNodo(dato, idJefe);
            pintarArbol();
        }catch(ArbolBinarioException e){
            JsfUtil.addErrorMessage(String.valueOf(e));
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
    
    public void totalNomina(String id){
        try {
            idJefeDeLaNomina = arbol.nominaGrupoEmpleados(id);
        } catch (Exception e) {
            JsfUtil.addErrorMessage("No existe el empleado");
        }
        
    }
    
    public void empleadosCiudad(String ciudad){
        listaEmpleadosCiudad = arbol.listaEmpleadosCiudad(ciudad);
    }
    
    public void reporteGenero(String id){
        listaGenero = arbol.cantidadGenero(id);
    }
    
    public void encontrarCiudad(String id){
        try {
           ciudadTrabajador=arbol.ciudadTrabajo(id); 
        } catch (ArbolBinarioException e) {
            JsfUtil.addErrorMessage(String.valueOf(e));
        }
        
    }
    
    public void cambiarDependencia(String id,String dependecia){
        try {
            String []dependecias = arbol.cambiarDependencia(id,dependecia);
            nuevaDependencia = dependecias[1];
            antiguaDependencia = dependecias[0];
            dependencia = nuevaDependencia;
            pintarArbol();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(String.valueOf(e));
        }
    }
    
    public void despedir(String id){
        try {
            arbol.despedirEmpleado(id);
            pintarArbol();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(String.valueOf(e));
        }
    }
    
    public void ascender(String id){
            try {
              arbol.ascender(id);
              pintarArbol();
            } catch (Exception e) {
                JsfUtil.addErrorMessage(String.valueOf(e));
            }
    }
    
    public void promedioEdadPorGenero(){
        try {
            listaPromedios = arbol.promedioGenero();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(String.valueOf(e));
        }
    }
    
    private void pintarArbol(NodoEne reco, DefaultDiagramModel model, Element padre, int x, int y) {

        if (reco != null) {
            Element elementHijo;
            if(reco.getDato()==null){
                elementHijo = new Element("CC: ");
            }
            else{
                 elementHijo = new Element("CC: " + reco.getDato().getId());
            }
            elementHijo.setId(String.valueOf(reco.getDato().getId()));
            elementHijo.setX(String.valueOf(x) + "em");
            elementHijo.setY(String.valueOf(y) + "em");
            if (padre != null) {
                elementHijo.addEndPoint(new DotEndPoint(EndPointAnchor.TOP));
                DotEndPoint conectorPadre = new DotEndPoint(EndPointAnchor.BOTTOM);
                padre.addEndPoint(conectorPadre);
                model.connect(new Connection(conectorPadre, elementHijo.getEndPoints().get(0)));

            }

            model.addElement(elementHijo);
            if(reco.getHijos().size()>0){
                int moverEjex = x - 5;
                for (int i = 0; i < reco.getHijos().size(); i++) {
                    pintarArbol(reco.getHijos().get(i), model, elementHijo, moverEjex, y + 5);
                    moverEjex += 10;
                }
            }
        }
    }
    
    public void onClickRight() {
        String id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("elementId");
        JsfUtil.addSuccessMessage(id);
        System.out.println(id.replaceAll("frmArbolN:diagrama-", ""));
        //idSeleccionado = String.valueOf(id.replaceAll("frmArbolN:diagrama-", ""));
        //nombre = String.valueOf(id.replaceAll("frmArbolN:diagrama-", ""));
        
        //Short.valueOf(id.replaceAll("mnuForm", ""));

    }
    
}