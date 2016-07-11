package co.nqs.cuestionario.vista;

import co.nqs.cuestionario.modelo.CuestionarioGlosario;
import co.nqs.cuestionario.vista.util.JsfUtil;
import co.nqs.cuestionario.vista.util.JsfUtil.PersistAction;
import co.nqs.cuestionario.vista.bean.CuestionarioGlosarioFacade;

import java.io.Serializable;
import java.util.List;
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


@Named("cuestionarioGlosarioController")
@SessionScoped
public class CuestionarioGlosarioController implements Serializable {


    @EJB private co.nqs.cuestionario.vista.bean.CuestionarioGlosarioFacade ejbFacade;
    private List<CuestionarioGlosario> items = null;
    private CuestionarioGlosario selected;

    public CuestionarioGlosarioController() {
    }

    public CuestionarioGlosario getSelected() {
        return selected;
    }

    public void setSelected(CuestionarioGlosario selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
            selected.getCuestionarioGlosarioPK().setCuestionarioId(selected.getCuestionario().getCuestionarioId());
            selected.getCuestionarioGlosarioPK().setGlosarioId(selected.getGlosario().getGlosarioId());
    }

    protected void initializeEmbeddableKey() {
        selected.setCuestionarioGlosarioPK(new co.nqs.cuestionario.modelo.CuestionarioGlosarioPK());
    }

    private CuestionarioGlosarioFacade getFacade() {
        return ejbFacade;
    }

    public CuestionarioGlosario prepareCreate() {
        selected = new CuestionarioGlosario();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("CuestionarioGlosarioCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("CuestionarioGlosarioUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("CuestionarioGlosarioDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<CuestionarioGlosario> getItems() {
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

    public CuestionarioGlosario getCuestionarioGlosario(co.nqs.cuestionario.modelo.CuestionarioGlosarioPK id) {
        return getFacade().find(id);
    }

    public List<CuestionarioGlosario> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<CuestionarioGlosario> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass=CuestionarioGlosario.class)
    public static class CuestionarioGlosarioControllerConverter implements Converter {

        private static final String SEPARATOR = "#";
        private static final String SEPARATOR_ESCAPED = "\\#";

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            CuestionarioGlosarioController controller = (CuestionarioGlosarioController)facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "cuestionarioGlosarioController");
            return controller.getCuestionarioGlosario(getKey(value));
        }

        co.nqs.cuestionario.modelo.CuestionarioGlosarioPK getKey(String value) {
            co.nqs.cuestionario.modelo.CuestionarioGlosarioPK key;
            String values[] = value.split(SEPARATOR_ESCAPED);
            key = new co.nqs.cuestionario.modelo.CuestionarioGlosarioPK();
            key.setCuestionarioId(Integer.parseInt(values[0]));
            key.setGlosarioId(Integer.parseInt(values[1]));
            return key;
        }

        String getStringKey(co.nqs.cuestionario.modelo.CuestionarioGlosarioPK value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value.getCuestionarioId());
            sb.append(SEPARATOR);
            sb.append(value.getGlosarioId());
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof CuestionarioGlosario) {
                CuestionarioGlosario o = (CuestionarioGlosario) object;
                return getStringKey(o.getCuestionarioGlosarioPK());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), CuestionarioGlosario.class.getName()});
                return null;
            }
        }

    }

}
