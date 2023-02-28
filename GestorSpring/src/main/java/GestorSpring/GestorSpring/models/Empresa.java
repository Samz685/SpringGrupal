/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GestorSpring.GestorSpring.models;

import GestorSpring.GestorSpring.models.Alumno;

import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import java.io.Serializable;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import static jakarta.persistence.GenerationType.IDENTITY;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.util.Set;
import lombok.Data;

@Data
@Entity
@Table(name = "empresa")
public class Empresa implements Serializable {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    int id;

    @Column(name = "nombre")
    String nombre;

    @Column(name = "telefono")
    String telefono;

    @Column(name = "email")
    String email;

    @Column(name = "responsable")
    String responsable;

    @Column(name = "observaciones")
    String observaciones;
    
    @OneToMany(mappedBy = "empresa", fetch = FetchType.EAGER)
    private Set<Alumno> alumnos;

}
