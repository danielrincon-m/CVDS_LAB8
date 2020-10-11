package edu.eci.cvds.view;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;

import edu.eci.cvds.samples.entities.Item;
import edu.eci.cvds.samples.entities.ItemRentado;
import edu.eci.cvds.samples.services.ExcepcionServiciosAlquiler;
import edu.eci.cvds.samples.services.ServiciosAlquiler;

@SuppressWarnings("deprecation")
@ManagedBean(name = "alquilerBean")
@SessionScoped
public class AlquilerBean extends BasePageBean {

    private static final long serialVersionUID = 4223254098664140119L;

    @Inject
    private ServiciosAlquiler serviciosAlquiler;

    private List<ItemRentado> itemsRentados;
    private long documento;
    private int idItem;
    private int diasAlquiler;

    // @ManagedProperty(value = "#{id}")    
    // private Long id;

    public List<ItemRentado> getItems() throws ExcepcionServiciosAlquiler {
        long millis = System.currentTimeMillis();
        java.sql.Date date = new java.sql.Date(millis);

        itemsRentados = serviciosAlquiler.consultarItemsCliente(documento);
        return itemsRentados;
    }

    public long getMulta(int idItem) throws ExcepcionServiciosAlquiler {
        long millis = System.currentTimeMillis();
        java.sql.Date date = new java.sql.Date(millis);
        int index = -1;

        for (int i = 0; i < itemsRentados.size(); i++) {
            if (itemsRentados.get(i).getId() == idItem) {
                index = i;
                break;
            }
        }
        ItemRentado item = itemsRentados.get(index);
        return Math.max(0, serviciosAlquiler.consultarMultaAlquiler(item.getFechafinrenta(), date,
                item.getItem().getTarifaxDia()));
    }

    public long consultarCostoAlquiler() throws ExcepcionServiciosAlquiler {
        // System.out.println(idItem + " : " + diasAlquiler);
        try {
            return serviciosAlquiler.consultarCostoAlquiler(idItem, diasAlquiler);
        } catch (Exception e) {
            return 0;
        }
    }

    public String registrarAlquiler() throws ExcepcionServiciosAlquiler {
        long millis = System.currentTimeMillis();
        java.sql.Date date = new java.sql.Date(millis);
        Item itemRegistrado = serviciosAlquiler.consultarItem(idItem);

        try {
            serviciosAlquiler.registrarAlquilerCliente(date, documento, itemRegistrado, diasAlquiler);        
        } catch (Exception e) {

        }
        clear();
            return "registrocliente.xhtml?faces-redirect=true";
    }

    private void clear() {
        idItem = 0;
        diasAlquiler = 0;
    }

    public long getDocumento() {
        return documento;
    }

    public void setDocumento(long documento) {
        this.documento = documento;
    }

    public int getIdItem() {
        return idItem;
    }

    public void setIdItem(int idItem) {
        this.idItem = idItem;
    }

    public int getDiasAlquiler() {
        return diasAlquiler;
    }

    public void setDiasAlquiler(int diasAlquiler) {
        this.diasAlquiler = diasAlquiler;
    }
}
