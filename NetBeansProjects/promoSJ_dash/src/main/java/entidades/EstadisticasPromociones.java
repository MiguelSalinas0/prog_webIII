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
import jakarta.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 *
 * @author elias
 */
@Entity
@Table(name = "estadisticas_promociones", catalog = "bd_dash_sj", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EstadisticasPromociones.findAll", query = "SELECT e FROM EstadisticasPromociones e"),
    @NamedQuery(name = "EstadisticasPromociones.findById", query = "SELECT e FROM EstadisticasPromociones e WHERE e.id = :id"),
    @NamedQuery(name = "EstadisticasPromociones.findByCantidadCanjes", query = "SELECT e FROM EstadisticasPromociones e WHERE e.cantidadCanjes = :cantidadCanjes"),
    @NamedQuery(name = "EstadisticasPromociones.findByVisitas", query = "SELECT e FROM EstadisticasPromociones e WHERE e.visitas = :visitas")})
public class EstadisticasPromociones implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "cantidad_canjes")
    private Integer cantidadCanjes;
    @Column(name = "visitas")
    private Integer visitas;
    @JoinColumn(name = "id_promocion", referencedColumnName = "id_promocion")
    @ManyToOne
    private Promociones idPromocion;

    public EstadisticasPromociones() {
    }

    public EstadisticasPromociones(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCantidadCanjes() {
        return cantidadCanjes;
    }

    public void setCantidadCanjes(Integer cantidadCanjes) {
        this.cantidadCanjes = cantidadCanjes;
    }

    public Integer getVisitas() {
        return visitas;
    }

    public void setVisitas(Integer visitas) {
        this.visitas = visitas;
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
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EstadisticasPromociones)) {
            return false;
        }
        EstadisticasPromociones other = (EstadisticasPromociones) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.EstadisticasPromociones[ id=" + id + " ]";
    }
    
}
