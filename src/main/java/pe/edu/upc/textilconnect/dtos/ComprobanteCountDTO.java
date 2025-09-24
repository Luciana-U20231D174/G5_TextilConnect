package pe.edu.upc.textilconnect.dtos;

public class ComprobanteCountDTO {
    private int cantidad;

    public ComprobanteCountDTO(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
