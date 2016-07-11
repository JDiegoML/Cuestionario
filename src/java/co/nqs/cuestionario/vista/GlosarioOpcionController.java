package co.nqs.cuestionario.vista;

import co.nqs.cuestionario.modelo.GlosarioOpcion;
import co.nqs.cuestionario.vista.util.JsfUtil;
import co.nqs.cuestionario.vista.util.JsfUtil.PersistAction;
import co.nqs.cuestionario.vista.bean.GlosarioOpcionFacade;

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

@Named("glosarioOpcionController")
@SessionScoped
public class GlosarioOpcionController implements Serializable {

    @EJB
    private co.nqs.cuestionario.vista.bean.GlosarioOpcionFacade ejbFacade;
    private List<GlosarioOpcion> items = null;
    private GlosarioOpcion selected;

    public GlosarioOpcionController() {
    }

    public GlosarioOpcion getSelected() {
        return selected;
    }

    public void setSelected(GlosarioOpcion selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private GlosarioOpcionFacade getFacade() {
        return ejbFacade;
    }

    public GlosarioOpcion prepareCreate() {
        selected = new GlosarioOpcion();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("GlosarioOpcionCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("GlosarioOpcionUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("GlosarioOpcionDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<GlosarioOpcion> getItems() {
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

    public GlosarioOpcion getGlosarioOpcion(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<GlosarioOpcion> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<GlosarioOpcion> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = GlosarioOpcion.class)
    public static class GlosarioOpcionControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            GlosarioOpcionController controller = (GlosarioOpcionController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "glosarioOpcionController");
            return controller.getGlosarioOpcion(getKey(value));
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
            if (object instanceof GlosarioOpcion) {
                GlosarioOpcion o = (GlosarioOpcion) object;
                return getStringKey(o.getGlosarioOpcionId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), GlosarioOpcion.class.getName()});
                return null;
            }
        }

    }

}
