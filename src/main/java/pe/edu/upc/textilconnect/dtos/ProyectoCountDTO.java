package pe.edu.upc.textilconnect.dtos;

public class ProyectoCountDTO {
    private int cantidad;

    public ProyectoCountDTO(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
