package com.project.jsica.converter;

import com.project.jsica.ejb.entidades.FichaGeneralEmpleado;
import com.project.jsica.cdi.util.JsfUtil;
import com.project.jsica.ejb.dao.FichaGeneralEmpleadoFacadeLocal;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value = "fichaGeneralEmpleadoConverter")
public class FichaGeneralEmpleadoConverter implements Converter {

    @EJB
    private FichaGeneralEmpleadoFacadeLocal ejbFacade;

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
        if (value == null || value.length() == 0 || JsfUtil.isDummySelectItem(component, value)) {
            return null;
        }
        return this.ejbFacade.find(getKey(value));
    }

    java.lang.Long getKey(String value) {
        java.lang.Long key;
        key = Long.valueOf(value);
        return key;
    }

    String getStringKey(java.lang.Long value) {
        StringBuffer sb = new StringBuffer();
        sb.append(value);
        return sb.toString();
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
        if (object == null
                || (object instanceof String && ((String) object).length() == 0)) {
            return null;
        }
        if (object instanceof FichaGeneralEmpleado) {
            FichaGeneralEmpleado o = (FichaGeneralEmpleado) object;
            return getStringKey(o.getId());
        } else {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), FichaGeneralEmpleado.class.getName()});
            return null;
        }
    }

}
