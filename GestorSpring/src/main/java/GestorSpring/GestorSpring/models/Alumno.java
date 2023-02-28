/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GestorSpring.GestorSpring.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.annotation.Generated;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import java.io.Serializable;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import static jakarta.persistence.GenerationType.IDENTITY;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.util.Set;
import lombok.Data;
import lombok.ToString;

@Data
@Entity
@Table(name = "alumno")
public class Alumno implements Serializable {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    int id;
    
    @Column(name = "nombre")
    String nombre;
    
    @Column(name = "apellidos")
    String apellidos;
    
    @Column(name = "password")
    String password;
    
    @Column(name = "dni")
    String dni;
    
    @Column(name = "fecha_nac")
    LocalDate fecha_nac;
    
    @Column(name = "email")
    String email;
    
    @Column(name = "telefono")
    String telefono;
    
    @Column(name = "horas_dual")
    int horas_dual;
    
    @Column(name = "horas_fct")
    int horas_fct;
    
    @Column(name = "observaciones")
    String observaciones;

 
    
    @OneToMany( mappedBy = "alumno", cascade=CascadeType.ALL)
    @JsonIgnore
    private Set<Actividad> actividades;
    
    @ManyToOne
    @JoinColumn( name = "tutor_id" )
    @ToString.Exclude
    private Profesor profesor;
    
    @ManyToOne
    @JoinColumn(name="empresa_id")
    Empresa empresa;


}
