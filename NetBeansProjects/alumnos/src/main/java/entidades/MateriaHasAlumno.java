/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author elias
 */
@Entity
@Table(name = "materia_has_alumno", catalog = "bdalumnos", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MateriaHasAlumno.findAll", query = "SELECT m FROM MateriaHasAlumno m"),
    @NamedQuery(name = "MateriaHasAlumno.findByMateriaIdmateria", query = "SELECT m FROM MateriaHasAlumno m WHERE m.materiaHasAlumnoPK.materiaIdmateria = :materiaIdmateria"),
    @NamedQuery(name = "MateriaHasAlumno.findByAlumnoIdalumno", query = "SELECT m FROM MateriaHasAlumno m WHERE m.materiaHasAlumnoPK.alumnoIdalumno = :alumnoIdalumno"),
    @NamedQuery(name = "MateriaHasAlumno.findByNota", query = "SELECT m FROM MateriaHasAlumno m WHERE m.nota = :nota"),
    @NamedQuery(name = "MateriaHasAlumno.findByFecha", query = "SELECT m FROM MateriaHasAlumno m WHERE m.materiaHasAlumnoPK.fecha = :fecha")})
public class MateriaHasAlumno implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected MateriaHasAlumnoPK materiaHasAlumnoPK;
    @Column(name = "nota")
    private Integer nota;
    @JoinColumn(name = "alumno_idalumno", referencedColumnName = "idalumno", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Alumno alumno;
    @JoinColumn(name = "materia_idmateria", referencedColumnName = "idmateria", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Materia materia;

    public MateriaHasAlumno() {
    }

    public MateriaHasAlumno(MateriaHasAlumnoPK materiaHasAlumnoPK) {
        this.materiaHasAlumnoPK = materiaHasAlumnoPK;
    }

    public MateriaHasAlumno(int materiaIdmateria, int alumnoIdalumno, Date fecha) {
        this.materiaHasAlumnoPK = new MateriaHasAlumnoPK(materiaIdmateria, alumnoIdalumno, fecha);
    }

    public MateriaHasAlumnoPK getMateriaHasAlumnoPK() {
        return materiaHasAlumnoPK;
    }

    public void setMateriaHasAlumnoPK(MateriaHasAlumnoPK materiaHasAlumnoPK) {
        this.materiaHasAlumnoPK = materiaHasAlumnoPK;
    }

    public Integer getNota() {
        return nota;
    }

    public void setNota(Integer nota) {
        this.nota = nota;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public Materia getMateria() {
        return materia;
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (materiaHasAlumnoPK != null ? materiaHasAlumnoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MateriaHasAlumno)) {
            return false;
        }
        MateriaHasAlumno other = (MateriaHasAlumno) object;
        if ((this.materiaHasAlumnoPK == null && other.materiaHasAlumnoPK != null) || (this.materiaHasAlumnoPK != null && !this.materiaHasAlumnoPK.equals(other.materiaHasAlumnoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.MateriaHasAlumno[ materiaHasAlumnoPK=" + materiaHasAlumnoPK + " ]";
    }
    
}
