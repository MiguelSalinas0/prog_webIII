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
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author elias
 */
@Entity
@Table(name = "canjes", catalog = "bd_dash_sj", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Canjes.findAll", query = "SELECT c FROM Canjes c"),
    @NamedQuery(name = "Canjes.findByIdCanje", query = "SELECT c FROM Canjes c WHERE c.idCanje = :idCanje"),
    @NamedQuery(name = "Canjes.findByFechaCanje", query = "SELECT c FROM Canjes c WHERE c.fechaCanje = :fechaCanje"),
    @NamedQuery(name = "Canjes.findByEstado", query = "SELECT c FROM Canjes c WHERE c.estado = :estado")})
public class Canjes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_canje", nullable = false)
    private Integer idCanje;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_canje", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCanje;
    @Size(max = 20)
    @Column(name = "estado", length = 20)
    private String estado;
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    @ManyToOne
    private Usuarios idUsuario;
    @JoinColumn(name = "id_promocion", referencedColumnName = "id_promocion")
    @ManyToOne
    private Promociones idPromocion;

    public Canjes() {
    }

    public Canjes(Integer idCanje) {
        this.idCanje = idCanje;
    }

    public Canjes(Integer idCanje, Date fechaCanje) {
        this.idCanje = idCanje;
        this.fechaCanje = fechaCanje;
    }

    public Integer getIdCanje() {
        return idCanje;
    }

    public void setIdCanje(Integer idCanje) {
        this.idCanje = idCanje;
    }

    public Date getFechaCanje() {
        return fechaCanje;
    }

    public void setFechaCanje(Date fechaCanje) {
        this.fechaCanje = fechaCanje;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Usuarios getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuarios idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Promociones getIdPromocion() {
        return idPromocion;
    }

    public void setIdPromocion(Promociones idPromocion) {
        this.idPromocion = idPromocion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCanje != null ? idCanje.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Canjes)) {
            return false;
        }
        Canjes other = (Canjes) object;
        if ((this.idCanje == null && other.idCanje != null) || (this.idCanje != null && !this.idCanje.equals(other.idCanje))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Canjes[ idCanje=" + idCanje + " ]";
    }
    
}
