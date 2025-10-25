/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Jp
 */
@Entity
public class Vehiculo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    // Atributos
    @Column(name = "nombre", nullable = false)
    private String nombre;
    
    @Column(name = "descripcion", nullable = false)
    private String descripcion;
    
    @Column(name = "precio", nullable = false)
    private double precio;
    
    @Column(name = "existencias", nullable = false)
    private Integer existencias;
    
    @Column(name = "disponible", nullable = false)
    private boolean disponible;
    
    @Column(name = "kilometraje", nullable = false)
    private double kilometraje;
    
    @Column(name = "anio", nullable = false)
    private Integer anio;
    
    @Column(name = "motor", nullable = false)
    private String motor;
    
    @Column(name = "transmision", nullable = false)
    private String transmision;
    
    @Column(name = "color", nullable = false)
    private String color;
    
    // Relaciones
    @OneToMany(mappedBy = "vehiculo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ImagenVehiculo> imagenes;
    
    @ManyToOne
    @JoinColumn(name = "categoriaId")
    private Categoria categoria;
    
    @ManyToOne
    @JoinColumn(name = "modeloId")
    private Modelo modelo;
    
    @ManyToOne
    @JoinColumn(name = "marcaId")
    private Marca marca;
    
    @OneToMany(mappedBy = "vehiculo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemCarrito> itemsCarrito;
    
    @OneToMany(mappedBy = "vehiculo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DetallePedido> detallesPedido;
    
    @OneToMany(mappedBy = "vehiculo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Resenia> resenias;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }
    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public Integer getExistencias() {
        return existencias;
    }
    public void setExistencias(Integer existencias) {
        this.existencias = existencias;
    }

    public boolean isDisponible() {
        return disponible;
    }
    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public double getKilometraje() {
        return kilometraje;
    }
    public void setKilometraje(double kilometraje) {
        this.kilometraje = kilometraje;
    }

    public Integer getAnio() {
        return anio;
    }
    public void setAnio(Integer anio) {
        this.anio = anio;
    }

    public String getMotor() {
        return motor;
    }
    public void setMotor(String motor) {
        this.motor = motor;
    }

    public String getTransmision() {
        return transmision;
    }
    public void setTransmision(String transmision) {
        this.transmision = transmision;
    }

    public String getColor() {
        return color;
    }
    public void setColor(String color) {
        this.color = color;
    }

    public List<ImagenVehiculo> getImagenes() {
        return imagenes;
    }
    public void setImagenes(List<ImagenVehiculo> imagenes) {
        this.imagenes = imagenes;
    }

    public Categoria getCategoria() {
        return categoria;
    }
    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Modelo getModelo() {
        return modelo;
    }
    public void setModelo(Modelo modelo) {
        this.modelo = modelo;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public List<ItemCarrito> getItemsCarrito() {
        return itemsCarrito;
    }
    public void setItemsCarrito(List<ItemCarrito> itemsCarrito) {
        this.itemsCarrito = itemsCarrito;
    }

    public List<DetallePedido> getDetallesPedido() {
        return detallesPedido;
    }
    public void setDetallesPedido(List<DetallePedido> detallesPedido) {
        this.detallesPedido = detallesPedido;
    }

    public List<Resenia> getResenias() {
        return resenias;
    }
    public void setResenias(List<Resenia> resenias) {
        this.resenias = resenias;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Vehiculo)) {
            return false;
        }
        Vehiculo other = (Vehiculo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Vehiculo{" +
                "id=" + id +
                ", nombre=" + nombre +
                ", descripcion=" + descripcion +
                ", precio=" + precio +
                ", existencias=" + existencias +
                ", disponible=" + disponible +
                ", kilometraje=" + kilometraje +
                ", anio=" + anio +
                ", motor=" + motor +
                ", transmision=" + transmision +
                ", color=" + color +
                '}';
    }
    
}
