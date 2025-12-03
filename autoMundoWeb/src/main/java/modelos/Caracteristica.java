package modelos;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.List; // Importar List

@Entity
@Table(name = "caracteristicas")
public class Caracteristica implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true) // unique para evitar duplicados
    private String nombre; // Ej: "Aire Acondicionado"

    @Column(name = "ruta_icono", nullable = false)
    private String rutaIcono; 

    // Opcional: Si quieres saber qué autos tienen esta característica (bidireccional)
    @ManyToMany(mappedBy = "caracteristicas")
    private List<Vehiculo> vehiculos;

    public Caracteristica() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getRutaIcono() { return rutaIcono; }
    public void setRutaIcono(String rutaIcono) { this.rutaIcono = rutaIcono; }
}