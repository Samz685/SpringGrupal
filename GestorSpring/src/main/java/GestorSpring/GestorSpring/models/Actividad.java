/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GestorSpring.GestorSpring.models;

import GestorSpring.GestorSpring.models.Alumno;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import java.io.Serializable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import static jakarta.persistence.GenerationType.IDENTITY;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;
import lombok.Data;
import lombok.ToString;

@Data
@Entity
@Table(name = "actividad")
public class Actividad implements Serializable {
    
    @Id
    @GeneratedValue(strategy = IDENTITY)
    int id;
    
    @Column(name = "fecha")
    LocalDate fecha;
    
    @Column(name = "tipo_practica")
    String tipo_practica;
    
    @Column(name = "horas_dia")
    int horas_dia;
    
    @Column(name = "nombre")
    String nombre;
    
    @Column(name = "observaciones")
    String observaciones;
    
    
    @ManyToOne
    @JoinColumn( name = "alumno_id" )
    private Alumno alumno;
      
 

}
