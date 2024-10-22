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
import jakarta.persistence.Lob;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.UniqueConstraint;
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
@Table(name = "comercios", catalog = "bd_dash_sj", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"email"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Comercios.findAll", query = "SELECT c FROM Comercios c"),
    @NamedQuery(name = "Comercios.findByIdComercio", query = "SELECT c FROM Comercios c WHERE c.idComercio = :idComercio"),
    @NamedQuery(name = "Comercios.findByNombre", query = "SELECT c FROM Comercios c WHERE c.nombre = :nombre"),
    @NamedQuery(name = "Comercios.findByDireccion", query = "SELECT c FROM Comercios c WHERE c.direccion = :direccion"),
    @NamedQuery(name = "Comercios.findByTelefono", query = "SELECT c FROM Comercios c WHERE c.telefono = :telefono"),
    @NamedQuery(name = "Comercios.findByEmail", query = "SELECT c FROM Comercios c WHERE c.email = :email"),
    @NamedQuery(name = "Comercios.findByLogo", query = "SELECT c FROM Comercios c WHERE c.logo = :logo"),
    @NamedQuery(name = "Comercios.findByHorarios", query = "SELECT c FROM Comercios c WHERE c.horarios = :horarios"),
    @NamedQuery(name = "Comercios.findByPassword", query = "SELECT c FROM Comercios c WHERE c.password = :password"),
    @NamedQuery(name = "Comercios.findByFechaRegistro", query = "SELECT c FROM Comercios c WHERE c.fechaRegistro = :fechaRegistro")})
public class Comercios implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;
    @Basic(optional = false)
    @NotNull()
    @Size(min = 1, max = 200)
    @Column(name = "direccion", nullable = false, length = 200)
    private String direccion;
    @Size(max = 20)
    @Column(name = "telefono", length = 20)
    private String telefono;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "email", nullable = false, length = 100)
    private String email;
    @Size(max = 255)
    @Column(name = "logo", length = 255)
    private String logo;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Lob()
    @Size(max = 65535)
    @Column(name = "descripcion", length = 65535)
    private String descripcion;
    @Size(max = 255)
    @Column(name = "horarios", length = 255)
    private String horarios;
    @Basic(optional = false)
    @NotNull()
    @Size(min = 1, max = 100)
    @Column(name = "password", nullable = false, length = 100)
    private String password;
    @Basic(optional = false)
    @NotNull()
    @Column(name = "fecha_registro", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_comercio", nullable = false)
    private Integer idComercio;
    @OneToMany(mappedBy = "idComercio")
    private Collection<Promociones> promocionesCollection;

    public Comercios() {
    }

    public Comercios(Integer idComercio) {
        this.idComercio = idComercio;
    }

    public Comercios(Integer idComercio, String nombre, String direccion, String email, String password, Date fechaRegistro) {
        this.idComercio = idComercio;
        this.nombre = nombre;
        this.direccion = direccion;
        this.email = email;
        this.password = password;
        this.fechaRegistro = fechaRegistro;
    }

    public Integer getIdComercio() {
        return idComercio;
    }

    public void setIdComercio(Integer idComercio) {
        this.idComercio = idComercio;
    }


    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    @XmlTransient
    public Collection<Promociones> getPromocionesCollection() {
        return promocionesCollection;
    }

    public void setPromocionesCollection(Collection<Promociones> promocionesCollection) {
        this.promocionesCollection = promocionesCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idComercio != null ? idComercio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Comercios)) {
            return false;
        }
        Comercios other = (Comercios) object;
        if ((this.idComercio == null && other.idComercio != null) || (this.idComercio != null && !this.idComercio.equals(other.idComercio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Comercios[ idComercio=" + idComercio + " ]";
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getHorarios() {
        return horarios;
    }

    public void setHorarios(String horarios) {
        this.horarios = horarios;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
}
