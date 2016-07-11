package co.nqs.cuestionario.vista;

import co.nqs.cuestionario.modelo.Cuestionario;
import co.nqs.cuestionario.modelo.CuestionarioGlosario;
import co.nqs.cuestionario.modelo.CuestionarioGlosarioPK;
import co.nqs.cuestionario.modelo.Glosario;
import co.nqs.cuestionario.modelo.GlosarioOpcion;
import co.nqs.cuestionario.modelo.dto.PreguntaDTO;
import co.nqs.cuestionario.vista.util.JsfUtil;
import co.nqs.cuestionario.vista.util.JsfUtil.PersistAction;
import co.nqs.cuestionario.vista.bean.CuestionarioFacade;
import co.nqs.cuestionario.vista.bean.CuestionarioGlosarioFacade;
import co.nqs.cuestionario.vista.bean.GlosarioFacade;
import co.nqs.cuestionario.vista.bean.GlosarioOpcionFacade;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import org.primefaces.component.api.UIColumn;
import org.primefaces.component.column.Column;
import org.primefaces.component.columns.Columns;
import org.primefaces.component.datatable.DataTable;


@Named("cuestionarioController")
@SessionScoped
public class CuestionarioController implements Serializable {

    //Variables para hacer los llamados a los EJB paga trabajar la persistencia de los datos
    @EJB private co.nqs.cuestionario.vista.bean.CuestionarioFacade ejbFacade;
    @EJB private co.nqs.cuestionario.vista.bean.GlosarioFacade ejbGlosarioFacade;
    @EJB private co.nqs.cuestionario.vista.bean.CuestionarioGlosarioFacade ejbCuestionarioGlosarioFacade;
    @EJB private co.nqs.cuestionario.vista.bean.GlosarioOpcionFacade ejbGlosarioOpcionFacade;
    //Varibles constantes de los tipos de preguntas
    private static int TIPO_PREGUNTA_ABIERTA=1;
    private static int TIPO_PREGUNTA_FALSO_VERDADERO=2;
    private static int TIPO_PREGUNTA_UNICA_SELECCION=3;
    private static int CANTIDAD_PREGUNTA_MULTIPLE=3;
    private static int CANTIDAD_PREGUNTA_NORMAL=1;
    //Variables para crear los formularios de preguntas dinámicas
    private DataTable tablaPreguntaVerdaderoFalso;
    private List<Cuestionario> items = null;
    private Cuestionario selected;
    private List<PreguntaDTO> preguntasBoleanas;

    public CuestionarioController() {
    }

    public Cuestionario getSelected() {
        return selected;
    }

    public void setSelected(Cuestionario selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private CuestionarioFacade getFacade() {
        return ejbFacade;
    }
    
    private GlosarioFacade getGlosarioFacade() {
        return ejbGlosarioFacade;
    }
    
    private CuestionarioGlosarioFacade getCuestionarioGlosarioFacade() {
        return ejbCuestionarioGlosarioFacade;
    }
    
    private GlosarioOpcionFacade getGlosarioOpcionFacade() {
        return ejbGlosarioOpcionFacade;
    }

    public Cuestionario prepareCreate() {
        selected = new Cuestionario();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("CuestionarioCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
        //Se crean las preguntas de acuerdo a la información del cuestionario
        Cuestionario cuestionarioGenerado=getFacade().consultarUltimoCuestionario();
        //Preguntas Abiertas
        crearGlosarioPreguntaAbiertaFalsoVerdadero(cuestionarioGenerado, TIPO_PREGUNTA_ABIERTA);
        //Preguntas Falso Verdadero
        crearGlosarioPreguntaAbiertaFalsoVerdadero(cuestionarioGenerado, TIPO_PREGUNTA_FALSO_VERDADERO);
        //Preguntas Unica Respuesta
        crearGlosarioPreguntaUnicaSeleccion(cuestionarioGenerado);
    }

    /**
     * Método para crear las preguntas de selección unica y las respuestas de cada una
     * de estas palabras. 
     * - El primer paso es consultar las palabras del glosario que
     * van a aparcer de aduerdo a la cantidad de palabras que usuario desea de este tipo
     * para el cuestionario.
     * - El segundo paso es crear cada palabra de este glosario con el tipo de pregunta,
     * que en este caso es de unica selección
     * - El tercer paso es consultar las opciones de respuesta aleatorias que va tener 
     * cada palabra del glosario
     * @param cuestionarioGenerado 
     */
    public void crearGlosarioPreguntaUnicaSeleccion(Cuestionario cuestionarioGenerado){
        //Se consultan las palabras de selección unica
        List<Glosario> glosarioSeleccionUnica=new ArrayList<>();
        glosarioSeleccionUnica=getGlosarioFacade().consultarGlosarioPorTema( 
                cuestionarioGenerado.getTemaId().getTemaId(),cuestionarioGenerado.getCantidadMultiple(), 
                cuestionarioGenerado.getCuestionarioId());
        //Se almacenan las palabras de cada glosario
        for(Glosario g:glosarioSeleccionUnica){
            CuestionarioGlosario cuestionarioGlosario=new CuestionarioGlosario();
            cuestionarioGlosario.setCuestionario(cuestionarioGenerado);
            cuestionarioGlosario.setGlosario(g);
            cuestionarioGlosario.setTipoPregunta(TIPO_PREGUNTA_UNICA_SELECCION);
            cuestionarioGlosario.setCuestionarioGlosarioPK(new CuestionarioGlosarioPK(
                    cuestionarioGenerado.getCuestionarioId(), g.getGlosarioId()));
            getCuestionarioGlosarioFacade().create(cuestionarioGlosario);
            //Se almacenan las opciones para cada pregunta
            List<Glosario> opcionesRespuesta=getGlosarioFacade().consultarGlosarioOpcionesRespuestaMultiple(
                    cuestionarioGenerado.getTemaId().getTemaId(), CANTIDAD_PREGUNTA_MULTIPLE,
                    g.getGlosarioId());
            for(Glosario opcionRespuesta:opcionesRespuesta){
                GlosarioOpcion opcion=new GlosarioOpcion();
                opcion.setGlosarioId(opcionRespuesta);
                opcion.setCuestionarioId(cuestionarioGenerado);
                opcion.setOpcion(opcionRespuesta.getDescripcion());
                opcion.setEsCorrecta(Objects.equals(g.getGlosarioId(), opcionRespuesta.getGlosarioId()));
                getGlosarioOpcionFacade().create(opcion);
            }
        }
    }
    
    /**
     * Método para crear las preguntas abiertas o de tipo FALSO - VERDADERO y las respuestas de cada una
     * de estas palabras. 
     * - El primer paso es consultar las palabras del glosario que
     * van a aparcer de aduerdo a la cantidad de palabras que usuario desea de este tipo
     * para el cuestionario.
     * - El segundo paso es crear cada palabra de este glosario con el tipo de pregunta,
     * que en este caso es abierta o de falso - verdadero
     * - El tercer paso es consultar las opciones de respuesta aleatorias que va tener 
     * cada palabra del glosario
     * @param cuestionarioGenerado 
     * @param tipoPregunta 
     */
    public void crearGlosarioPreguntaAbiertaFalsoVerdadero(Cuestionario cuestionarioGenerado,
            int tipoPregunta){
        //Se consultan las palabras de selección unica
        List<Glosario> glosarioSeleccionUnica=new ArrayList<>();
        glosarioSeleccionUnica=getGlosarioFacade().consultarGlosarioPorTema( 
                cuestionarioGenerado.getTemaId().getTemaId(),
                tipoPregunta==TIPO_PREGUNTA_ABIERTA?cuestionarioGenerado.getCantidadAbierta():
                cuestionarioGenerado.getCantidadFalsoVerdadero(), 
                cuestionarioGenerado.getCuestionarioId());
        //Se almacenan las palabras de cada glosario
        for(Glosario g:glosarioSeleccionUnica){
            CuestionarioGlosario cuestionarioGlosario=new CuestionarioGlosario();
            cuestionarioGlosario.setCuestionario(cuestionarioGenerado);
            cuestionarioGlosario.setGlosario(g);
            cuestionarioGlosario.setTipoPregunta(tipoPregunta);
            cuestionarioGlosario.setCuestionarioGlosarioPK(new CuestionarioGlosarioPK(
                    cuestionarioGenerado.getCuestionarioId(), g.getGlosarioId()));
            getCuestionarioGlosarioFacade().create(cuestionarioGlosario);
            //Se almacenan las opciones para cada pregunta
            GlosarioOpcion opcion=new GlosarioOpcion();
            opcion.setCuestionarioId(cuestionarioGenerado);
            if(tipoPregunta==TIPO_PREGUNTA_FALSO_VERDADERO){
                Glosario opcionRespuesta=getGlosarioFacade()
                        .consultarGlosarioOpcionRespuestaFalsoVerdadero(cuestionarioGenerado.getTemaId().getTemaId());
                opcion.setGlosarioId(opcionRespuesta);
                opcion.setOpcion(opcionRespuesta.getDescripcion());
                opcion.setEsCorrecta(Objects.equals(g.getGlosarioId(), opcionRespuesta.getGlosarioId()));
            }else{
                opcion.setGlosarioId(g);
                opcion.setOpcion(g.getDescripcion());
                opcion.setEsCorrecta(true);
            }
            getGlosarioOpcionFacade().create(opcion);  
        }
    }
    
    public void crearFormularioPreguntas(){
        if(selected!=null){
            preguntasBoleanas=new ArrayList<>();
            List<CuestionarioGlosario> preguntas=getCuestionarioGlosarioFacade().buscarCuestionarioGlosarioPorIdCuestionario(
                    selected.getCuestionarioId(),TIPO_PREGUNTA_FALSO_VERDADERO);
            for(CuestionarioGlosario cuestionarioGlosario:preguntas){
                GlosarioOpcion opcionRespuesta=getGlosarioOpcionFacade().buscarGlosarioOpcionPorIdCuestionarioIdGlosario(
                        cuestionarioGlosario.getCuestionario().getCuestionarioId(), 
                        cuestionarioGlosario.getGlosario().getGlosarioId());
                preguntasBoleanas.add(new PreguntaDTO(cuestionarioGlosario, opcionRespuesta));
            }
            System.out.println("RT:"+preguntasBoleanas.size()+"RR:"+selected.getCuestionarioId());
        }
    }
    
    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("CuestionarioUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("CuestionarioDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Cuestionario> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getFacade().edit(selected);
                } else {
                    getFacade().remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public Cuestionario getCuestionario(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Cuestionario> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Cuestionario> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    public DataTable getTablaPreguntaVerdaderoFalso() {
        return tablaPreguntaVerdaderoFalso;
    }

    public List<PreguntaDTO> getPreguntasBoleanas() {
        return preguntasBoleanas;
    }

    @FacesConverter(forClass=Cuestionario.class)
    public static class CuestionarioControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            CuestionarioController controller = (CuestionarioController)facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "cuestionarioController");
            return controller.getCuestionario(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Cuestionario) {
                Cuestionario o = (Cuestionario) object;
                return getStringKey(o.getCuestionarioId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Cuestionario.class.getName()});
                return null;
            }
        }

    }

}
