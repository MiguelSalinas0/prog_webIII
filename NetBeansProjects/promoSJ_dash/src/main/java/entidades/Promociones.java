/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

/**
 *
 * @author elias
 */
@Entity
@Table(name = "promociones", catalog = "bd_dash_sj", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Promociones.findAll", query = "SELECT p FROM Promociones p"),
    @NamedQuery(name = "Promociones.findByIdPromocion", query = "SELECT p FROM Promociones p WHERE p.idPromocion = :idPromocion"),
    @NamedQuery(name = "Promociones.findByTitulo", query = "SELECT p FROM Promociones p WHERE p.titulo = :titulo"),
    @NamedQuery(name = "Promociones.findByFechaInicio", query = "SELECT p FROM Promociones p WHERE p.fechaInicio = :fechaInicio"),
    @NamedQuery(name = "Promociones.findByFechaFin", query = "SELECT p FROM Promociones p WHERE p.fechaFin = :fechaFin"),
    @NamedQuery(name = "Promociones.findByImagen", query = "SELECT p FROM Promociones p WHERE p.imagen = :imagen"),
    @NamedQuery(name = "Promociones.findByEstado", query = "SELECT p FROM Promociones p WHERE p.estado = :estado")})
public class Promociones implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_promocion", nullable = false)
    private Integer idPromocion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "titulo", nullable = false, length = 100)
    private String titulo;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "descripcion", nullable = false, length = 65535)
    private String descripcion;
    @Column(name = "fecha_inicio")
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;
    @Column(name = "fecha_fin")
    @Temporal(TemporalType.DATE)
    private Date fechaFin;
    @Size(max = 255)
    @Column(name = "imagen", length = 255)
    private String imagen;
    @Size(max = 20)
    @Column(name = "estado", length = 20)
    private String estado;
    @OneToMany(mappedBy = "idPromocion")
    private Collection<Canjes> canjesCollection;
    @OneToMany(mappedBy = "idPromocion")
    private Collection<EstadisticasPromociones> estadisticasPromocionesCollection;
    @JoinColumn(name = "id_comercio", referencedColumnName = "id_comercio")
    @ManyToOne
    private Comercios idComercio;

    public Promociones() {
    }

    public Promociones(Integer idPromocion) {
        this.idPromocion = idPromocion;
    }

    public Promociones(Integer idPromocion, String titulo, String descripcion) {
        this.idPromocion = idPromocion;
        this.titulo = titulo;
        this.descripcion = descripcion;
    }

    public Integer getIdPromocion() {
        return idPromocion;
    }

    public void setIdPromocion(Integer idPromocion) {
        this.idPromocion = idPromocion;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @XmlTransient
    public Collection<Canjes> getCanjesCollection() {
        return canjesCollection;
    }

    public void setCanjesCollection(Collection<Canjes> canjesCollection) {
        this.canjesCollection = canjesCollection;
    }

    @XmlTransient
    public Collection<EstadisticasPromociones> getEstadisticasPromocionesCollection() {
        return estadisticasPromocionesCollection;
    }

    public void setEstadisticasPromocionesCollection(Collection<EstadisticasPromociones> estadisticasPromocionesCollection) {
        this.estadisticasPromocionesCollection = estadisticasPromocionesCollection;
    }

    public Comercios getIdComercio() {
        return idComercio;
    }

    public void setIdComercio(Comercios idComercio) {
        this.idComercio = idComercio;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPromocion != null ? idPromocion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Promociones)) {
            return false;
        }
        Promociones other = (Promociones) object;
        if ((this.idPromocion == null && other.idPromocion != null) || (this.idPromocion != null && !this.idPromocion.equals(other.idPromocion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Promociones[ idPromocion=" + idPromocion + " ]";
    }
    
}
