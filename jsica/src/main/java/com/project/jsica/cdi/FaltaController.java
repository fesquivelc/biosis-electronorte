package com.project.jsica.cdi;

import com.project.jsica.ejb.entidades.Falta;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "faltaController")
@ViewScoped
public class FaltaController extends AbstractController<Falta> {

    @Inject
    private EmpleadoController empleadoIdController;

    public FaltaController() {
        // Inform the Abstract parent controller of the concrete Falta?cap_first Entity
        super(Falta.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        empleadoIdController.setSelected(null);
    }

    /**
     * Sets the "selected" attribute of the Empleado controller in order to
     * display its data in a dialog. This is reusing existing the existing View
     * dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareEmpleadoId(ActionEvent event) {
        if (this.getSelected() != null && empleadoIdController.getSelected() == null) {
            empleadoIdController.setSelected(this.getSelected().getEmpleadoId());
        }
    }
}
