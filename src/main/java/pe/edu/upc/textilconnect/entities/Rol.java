    package pe.edu.upc.textilconnect.entities;

    import jakarta.persistence.*;

    @Entity
    @Table(name = "Rol")
    public class Rol {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int idRol;

        @Column(name = "nombreRol", length = 50, nullable = false)
        private String nombreRol;

        // ❌ Eliminado el @ManyToOne hacia Usuario
        // Ahora Rol ya no tiene relación directa con Usuario

        public Rol() {}

        public Rol(int idRol, String nombreRol) {
            this.idRol = idRol;
            this.nombreRol = nombreRol;
        }

        // getters y setters
        public int getIdRol() {
            return idRol;
        }

        public void setIdRol(int idRol) {
            this.idRol = idRol;
        }

        public String getNombreRol() {
            return nombreRol;
        }

        public void setNombreRol(String nombreRol) {
            this.nombreRol = nombreRol;
        }
    }
