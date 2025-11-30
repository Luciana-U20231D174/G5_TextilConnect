package pe.edu.upc.textilconnect.dtos;

public class ComprobanteCountDTO {

    private int idPedido;
    private long cantidadComprobantes;

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public long getCantidadComprobantes() {
        return cantidadComprobantes;
    }

    public void setCantidadComprobantes(long cantidadComprobantes) {
        this.cantidadComprobantes = cantidadComprobantes;
    }
}
