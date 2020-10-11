package edu.eci.cvds.view;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;

import edu.eci.cvds.samples.entities.Cliente;
import edu.eci.cvds.samples.entities.ItemRentado;
import edu.eci.cvds.samples.services.ExcepcionServiciosAlquiler;
import edu.eci.cvds.samples.services.ServiciosAlquiler;

@SuppressWarnings("deprecation")
@ManagedBean(name = "clientBean")
@SessionScoped
public class ClientBean extends BasePageBean {

    private static final long serialVersionUID = 4223254098664140119L;

    @Inject
    private ServiciosAlquiler serviciosAlquiler;

    private String nombre;
    private long documento;
    private String telefono;
    private String direccion;
    private String email;
    private boolean vetado;

    public List<Cliente> getClientes() throws ExcepcionServiciosAlquiler {
        return serviciosAlquiler.consultarClientes();
    }

    public void registrarCliente() throws ExcepcionServiciosAlquiler {
        Cliente cliente = new Cliente(nombre, documento, telefono, direccion, email, vetado,
                new ArrayList<ItemRentado>());
        serviciosAlquiler.registrarCliente(cliente);
        clear();
    }

    private void clear() {
        nombre = null;
        documento = 0;
        telefono = null;
        direccion = null;
        email = null;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public long getDocumento() {
        return documento;
    }

    public void setDocumento(long documento) {
        this.documento = documento;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isVetado() {
        return vetado;
    }

    public void setVetado(boolean vetado) {
        this.vetado = vetado;
    }
}
