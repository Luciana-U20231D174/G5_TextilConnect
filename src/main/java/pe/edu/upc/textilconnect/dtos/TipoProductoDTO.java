package pe.edu.upc.textilconnect.dtos;

public class TipoProductoDTO {
    private int idTipoProducto;
    private String nombreTipoProducto;

    public int getIdTipoProducto() {
        return idTipoProducto;
    }

    public void setIdTipoProducto(int idTipoProducto) {
        this.idTipoProducto = idTipoProducto;
    }

    public String getNombreTipoProducto() {
        return nombreTipoProducto;
    }

    public void setNombreTipoProducto(String nombreTipoProducto) {
        this.nombreTipoProducto = nombreTipoProducto;
    }
}
