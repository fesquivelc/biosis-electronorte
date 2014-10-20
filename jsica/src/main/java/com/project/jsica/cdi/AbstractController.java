package com.project.jsica.cdi;

import com.project.jsica.cdi.util.JsfUtil;
import com.project.jsica.ejb.dao.AbstractFacade;
import com.project.jsica.ejb.dao.BitacoraFacadeLocal;
import com.project.jsica.ejb.entidades.Bitacora;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.apache.log4j.Logger;

/**
 * Represents an abstract shell of to be used as JSF Controller to be used in
 * AJAX-enabled applications. No outcomes will be generated from its methods
 * since handling is designed to be done inside one page.
 *
 * @param <T> the concrete Entity type of the Controller bean to be created
 */
public abstract class AbstractController<T> implements Serializable {

    protected Class<T> itemClass;
    private Class controller;
    protected T selected;
    protected Collection<T> items;
    protected boolean esNuevo;

    private enum PersistAction {

        CREATE,
        DELETE,
        UPDATE
    }

    public AbstractController() {
    }

    public AbstractController(Class<T> itemClass) {
        this.itemClass = itemClass;
    }

    public AbstractController(Class<T> itemClass, Class controller) {
        this.itemClass = itemClass;
        this.controller = controller;
    }

    /**
     * Retrieve the currently selected item.
     *
     * @return the currently selected Entity
     */
    public T getSelected() {
        return selected;
    }

    /**
     * Pass in the currently selected item.
     *
     * @param selected the Entity that should be set as selected
     */
    public void setSelected(T selected) {
        this.selected = selected;
    }

    /**
     * Sets any embeddable key fields if an Entity uses composite keys. If the
     * entity does not have composite keys, this method performs no actions and
     * exists purely to be overridden inside a concrete controller class.
     */
    protected void setEmbeddableKeys() {
        // Nothing to do if entity does not have any embeddable key.
    }

    ;

    /**
     * Sets the concrete embedded key of an Entity that uses composite keys.
     * This method will be overriden inside concrete controller classes and does
     * nothing if the specific entity has no composite keys.
     */
    protected void initializeEmbeddableKey() {
        // Nothing to do if entity does not have any embeddable key.
    }

    /**
     * Returns all items as a Collection object.
     *
     * @return a collection of Entity items returned by the data layer
     */
//    public Collection<T> getItems() {
//        if (items == null) {
//            items = this.ejbFacade.findAll();
//        }
//        return items;
//    }
    /**
     * Pass in collection of items
     *
     * @param items a collection of Entity items
     */
    public void setItems(Collection<T> items) {
        this.items = items;
    }

    /**
     * Apply changes to an existing item to the data layer.
     *
     * @param event an event from the widget that wants to save an Entity to the
     * data layer
     */
    public void save(ActionEvent event) {
        String msg = ResourceBundle.getBundle("/MyBundle").getString(itemClass.getSimpleName() + "Updated");
        persist(PersistAction.UPDATE, msg);
    }

    /**
     * Store a new item in the data layer.
     *
     * @param event an event from the widget that wants to save a new Entity to
     * the data layer
     */
    public void saveNew(ActionEvent event) {
        String msg = ResourceBundle.getBundle("/MyBundle").getString(itemClass.getSimpleName() + "Created");

        this.esNuevo = true;
        persist(PersistAction.CREATE, msg);
        if (!isValidationFailed()) {
            items = null; // Invalidate list of items to trigger re-query.
        }
    }

    /**
     * Remove an existing item from the data layer.
     *
     * @param event an event from the widget that wants to delete an Entity from
     * the data layer
     */
    public void delete(ActionEvent event) {
        String msg = ResourceBundle.getBundle("/MyBundle").getString(itemClass.getSimpleName() + "Deleted");
        persist(PersistAction.DELETE, msg);
        if (!isValidationFailed()) {
            selected = null; // Remove selection
            items = null; // Invalidate list of items to trigger re-query.
        }
    }

    /**
     * Performs any data modification actions for an entity. The actions that
     * can be performed by this method are controlled by the
     * {@link PersistAction} enumeration and are either CREATE, EDIT or DELETE.
     *
     * @param persistAction a specific action that should be performed on the
     * current item
     * @param successMessage a message that should be displayed when persisting
     * the item succeeds
     */
    protected abstract void edit(T objeto);

    protected abstract void remove(T objeto);

    public abstract T find(Object id);

    public abstract List<T> getItems();

    public abstract List<T> search(String namedQuery);

    public abstract List<T> search(String namedQuery, Map<String, Object> parametros);

    public abstract List<T> search(String namedQuery, Map<String, Object> parametros, int inicio, int tamanio);

    private void persist(PersistAction persistAction, String successMessage) {

        if (selected != null) {
            this.setEmbeddableKeys();

            try {
                if (persistAction != PersistAction.DELETE) {

                    this.edit(selected);
                    this.esNuevo = false;

                } else {
                    this.remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                Throwable cause = JsfUtil.getRootCause(ex.getCause());
                if (cause != null) {
                    if (cause instanceof ConstraintViolationException) {
                        ConstraintViolationException excp = (ConstraintViolationException) cause;
                        for (ConstraintViolation s : excp.getConstraintViolations()) {
                            JsfUtil.addErrorMessage(s.getMessage());
                        }
                    } else {
                        String msg = cause.getLocalizedMessage();
                        if (msg.length() > 0) {
                            JsfUtil.addErrorMessage(msg);
                        } else {
//                            JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                            JsfUtil.addErrorMessage(ex, "error en el try e.e");
                        }
                    }
                }
            } catch (Exception ex) {
//                System.out.println("SELECTED: "+selected);
                Logger.getLogger(controller).error(this.selected.toString());
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/MyBundle").getString("PersistenceErrorOccured"));
//                JsfUtil.addErrorMessage(ex, "error en el catch :c");
            }
        } else {
            Logger.getLogger(this.itemClass).error("selected es null");
        }
    }

    /**
     * Creates a new instance of an underlying entity and assigns it to Selected
     * property.
     *
     * @param event an event from the widget that wants to create a new,
     * unmanaged Entity for the data layer
     * @return a new, unmanaged Entity
     */
    public T prepareCreate(ActionEvent event) {
        T newItem;
        try {
            newItem = itemClass.newInstance();
            this.selected = newItem;
            initializeEmbeddableKey();
            if (selected == null) {

                System.out.println("SELECTED: " + selected);
            }
            return newItem;
        } catch (InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(controller).error(ex);
        }
        return null;
    }

    /**
     * Inform the user interface whether any validation error exist on a page.
     *
     * @return a logical value whether form validation has passed or failed
     */
    public boolean isValidationFailed() {
        return JsfUtil.isValidationFailed();
    }

    /**
     * Retrieve all messages as a String to be displayed on the page.
     *
     * @param clientComponent the component for which the message applies
     * @param defaultMessage a default message to be shown
     * @return a concatenation of all messages
     */
    public String getComponentMessages(String clientComponent, String defaultMessage) {
        return JsfUtil.getComponentMessages(clientComponent, defaultMessage);
    }

    /**
     * Retrieve a collection of Entity items for a specific Controller from
     * another JSF page via Request parameters.
     */
    @PostConstruct
    public void initParams() {
        Object paramItems = FacesContext.getCurrentInstance().getExternalContext().getRequestMap().get(itemClass.getSimpleName() + "_items");
        if (paramItems != null) {
            this.items = (Collection<T>) paramItems;
        }
    }

}
