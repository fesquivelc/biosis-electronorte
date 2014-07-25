package com.project.jsica.cdi;

import com.project.jsica.ejb.dao.EmpleadoFacadeLocal;
import com.project.jsica.ejb.entidades.Empleado;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "empleadoController")
@ViewScoped
public class EmpleadoController extends AbstractController<Empleado> {
    @EJB
    private EmpleadoFacadeLocal empleadoFacade;
    @Inject
    private RegistroAsistenciaController registroAsistenciaListController;
    @Inject
    private DetalleContratoController detalleContratoListController;
    @Inject
    private GrupoHorarioController grupoHorarioIdController;
    @Inject
    private EmpleadoController empleadoListController;
    @Inject
    private EmpleadoController empleadoIdController;
    @Inject
    private ServicioController servicioIdController;
    @Inject
    private CambioTurnoController cambioTurnoListController;
    @Inject
    private SuspensionController suspensionListController;
    @Inject
    private EmpleadoPermisoController empleadoPermisoListController;
    @Inject
    private FichaGeneralEmpleadoController fichaGeneralEmpleadoListController;
    @Inject
    private PapeletaController papeletaListController;
    @Inject
    private PapeletaController papeletaList1Controller;
    @Inject
    private PapeletaController papeletaList2Controller;
    @Inject
    private FaltaController faltaListController;
    @Inject
    private VacacionController vacacionListController;
    @Inject
    private EmpleadoHorarioController empleadoHorarioListController;
    @Inject
    private UsuarioController usuarioListController;
    @Inject
    private FichaLaboralEmpleadoController fichaLaboralEmpleadoListController;

    public EmpleadoController() {
        // Inform the Abstract parent controller of the concrete Empleado?cap_first Entity
        super(Empleado.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        grupoHorarioIdController.setSelected(null);
        empleadoIdController.setSelected(null);
        servicioIdController.setSelected(null);
    }

    /**
     * Sets the "items" attribute with a collection of RegistroAsistencia
     * entities that are retrieved from Empleado?cap_first and returns the
     * navigation outcome.
     *
     * @return navigation outcome for RegistroAsistencia page
     */
    public String navigateRegistroAsistenciaList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("RegistroAsistencia_items", this.getSelected().getRegistroAsistenciaList());
        }
        return "/registroAsistencia/index";
    }

    /**
     * Sets the "items" attribute with a collection of DetalleContrato entities
     * that are retrieved from Empleado?cap_first and returns the navigation
     * outcome.
     *
     * @return navigation outcome for DetalleContrato page
     */
    public String navigateDetalleContratoList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("DetalleContrato_items", this.getSelected().getDetalleContratoList());
        }
        return "/detalleContrato/index";
    }

    /**
     * Sets the "selected" attribute of the GrupoHorario controller in order to
     * display its data in a dialog. This is reusing existing the existing View
     * dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareGrupoHorarioId(ActionEvent event) {
        if (this.getSelected() != null && grupoHorarioIdController.getSelected() == null) {
            grupoHorarioIdController.setSelected(this.getSelected().getGrupoHorarioId());
        }
    }

    /**
     * Sets the "items" attribute with a collection of Empleado entities that
     * are retrieved from Empleado?cap_first and returns the navigation outcome.
     *
     * @return navigation outcome for Empleado page
     */
    public String navigateEmpleadoList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Empleado_items", this.getSelected().getEmpleadoList());
        }
        return "/empleado/index";
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

    /**
     * Sets the "selected" attribute of the Servicio controller in order to
     * display its data in a dialog. This is reusing existing the existing View
     * dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareServicioId(ActionEvent event) {
        if (this.getSelected() != null && servicioIdController.getSelected() == null) {
            servicioIdController.setSelected(this.getSelected().getServicioId());
        }
    }

    /**
     * Sets the "items" attribute with a collection of CambioTurno entities that
     * are retrieved from Empleado?cap_first and returns the navigation outcome.
     *
     * @return navigation outcome for CambioTurno page
     */
    public String navigateCambioTurnoList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("CambioTurno_items", this.getSelected().getCambioTurnoList());
        }
        return "/cambioTurno/index";
    }

    /**
     * Sets the "items" attribute with a collection of Suspension entities that
     * are retrieved from Empleado?cap_first and returns the navigation outcome.
     *
     * @return navigation outcome for Suspension page
     */
    public String navigateSuspensionList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Suspension_items", this.getSelected().getSuspensionList());
        }
        return "/suspension/index";
    }

    /**
     * Sets the "items" attribute with a collection of EmpleadoPermiso entities
     * that are retrieved from Empleado?cap_first and returns the navigation
     * outcome.
     *
     * @return navigation outcome for EmpleadoPermiso page
     */
    public String navigateEmpleadoPermisoList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("EmpleadoPermiso_items", this.getSelected().getEmpleadoPermisoList());
        }
        return "/empleadoPermiso/index";
    }

    /**
     * Sets the "items" attribute with a collection of FichaGeneralEmpleado
     * entities that are retrieved from Empleado?cap_first and returns the
     * navigation outcome.
     *
     * @return navigation outcome for FichaGeneralEmpleado page
     */
    public String navigateFichaGeneralEmpleadoList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("FichaGeneralEmpleado_items", this.getSelected().getFichaGeneralEmpleadoList());
        }
        return "/fichaGeneralEmpleado/index";
    }

    /**
     * Sets the "items" attribute with a collection of Papeleta entities that
     * are retrieved from Empleado?cap_first and returns the navigation outcome.
     *
     * @return navigation outcome for Papeleta page
     */
    public String navigatePapeletaList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Papeleta_items", this.getSelected().getPapeletaList());
        }
        return "/papeleta/index";
    }

    /**
     * Sets the "items" attribute with a collection of Papeleta entities that
     * are retrieved from Empleado?cap_first and returns the navigation outcome.
     *
     * @return navigation outcome for Papeleta page
     */
    public String navigatePapeletaList1() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Papeleta_items", this.getSelected().getPapeletaList1());
        }
        return "/papeleta/index";
    }

    /**
     * Sets the "items" attribute with a collection of Papeleta entities that
     * are retrieved from Empleado?cap_first and returns the navigation outcome.
     *
     * @return navigation outcome for Papeleta page
     */
    public String navigatePapeletaList2() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Papeleta_items", this.getSelected().getPapeletaList2());
        }
        return "/papeleta/index";
    }

    /**
     * Sets the "items" attribute with a collection of Falta entities that are
     * retrieved from Empleado?cap_first and returns the navigation outcome.
     *
     * @return navigation outcome for Falta page
     */
    public String navigateFaltaList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Falta_items", this.getSelected().getFaltaList());
        }
        return "/falta/index";
    }

    /**
     * Sets the "items" attribute with a collection of Vacacion entities that
     * are retrieved from Empleado?cap_first and returns the navigation outcome.
     *
     * @return navigation outcome for Vacacion page
     */
    public String navigateVacacionList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Vacacion_items", this.getSelected().getVacacionList());
        }
        return "/vacacion/index";
    }

    /**
     * Sets the "items" attribute with a collection of EmpleadoHorario entities
     * that are retrieved from Empleado?cap_first and returns the navigation
     * outcome.
     *
     * @return navigation outcome for EmpleadoHorario page
     */
    public String navigateEmpleadoHorarioList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("EmpleadoHorario_items", this.getSelected().getEmpleadoHorarioList());
        }
        return "/empleadoHorario/index";
    }

    /**
     * Sets the "items" attribute with a collection of Usuario entities that are
     * retrieved from Empleado?cap_first and returns the navigation outcome.
     *
     * @return navigation outcome for Usuario page
     */
    public String navigateUsuarioList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Usuario_items", this.getSelected().getUsuarioList());
        }
        return "/usuario/index";
    }

    /**
     * Sets the "items" attribute with a collection of FichaLaboralEmpleado
     * entities that are retrieved from Empleado?cap_first and returns the
     * navigation outcome.
     *
     * @return navigation outcome for FichaLaboralEmpleado page
     */
    public String navigateFichaLaboralEmpleadoList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("FichaLaboralEmpleado_items", this.getSelected().getFichaLaboralEmpleadoList());
        }
        return "/fichaLaboralEmpleado/index";
    }

    @Override
    protected void edit(Empleado objeto) {
        this.empleadoFacade.edit(objeto);
    }

    @Override
    protected void remove(Empleado objeto) {
        this.empleadoFacade.remove(objeto);
    }

    @Override
    public Empleado find(Object id) {
        return this.empleadoFacade.find(id);
    }

    @Override
    public List<Empleado> getItems() {
        return this.empleadoFacade.findAll();
    }

    @Override
    public List<Empleado> search(String namedQuery) {
        return this.empleadoFacade.search(namedQuery);
    }

    @Override
    public List<Empleado> search(String namedQuery, Map<String, Object> parametros) {
        return this.empleadoFacade.search(namedQuery, parametros);
    }

    @Override
    public List<Empleado> search(String namedQuery, Map<String, Object> parametros, int inicio, int tamanio) {
        return this.empleadoFacade.search(namedQuery, parametros, inicio, tamanio);
    }

}