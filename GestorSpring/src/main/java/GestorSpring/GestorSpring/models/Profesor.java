/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GestorSpring.GestorSpring.models;
import GestorSpring.GestorSpring.models.Alumno;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.annotation.Generated;
import jakarta.persistence.CascadeType;
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
@Table(name = "profesor")
public class Profesor implements Serializable {
    
    @Id
    @GeneratedValue(strategy=IDENTITY)
    int id;
    @Column(name = "nombre")
    String nombre;
    
    @Column(name = "apellidos")
    String apellidos;
    
    @Column(name = "password")
    String password;
    
    @Column(name = "email")
    String email;
  
      
    @OneToMany( mappedBy = "profesor", cascade=CascadeType.ALL)
    @JsonIgnore
    private Set<Alumno> alumnos;
}
