/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/RestController.java to edit this template
 */
package GestorSpring.GestorSpring.controllers;

import GestorSpring.GestorSpring.models.Actividad;
import GestorSpring.GestorSpring.repositories.ActividadRepository;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;
import java.util.Optional;
import java.util.HashMap;
import java.util.ArrayList;
import org.springframework.ui.Model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.servlet.ModelAndView;



@RestController
@RequestMapping("/actividades")
public class ActividadController {

    @Autowired
    private ActividadRepository repo;

    @GetMapping()
    public List<Actividad> listActividades() {
        return repo.findAll();
    }
    //Ver datos de alumno concreto
    @GetMapping("/actividadByAlumnoId/{alumnoId}")
    public List<Actividad> listarActividades2(@PathVariable int alumnoId) {
       
        return repo.findByAlumnoId(alumnoId);
    }
    
    
    //Listar el detalle de una actividad concreto​
    @GetMapping("actividad/{id}")

    public ResponseEntity<Actividad> get(@PathVariable Long id) {
        if (repo.existsById(id)) {
            return new ResponseEntity<Actividad>(repo.findById(id).get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    //Crear actividad nueva
    @PostMapping
    public ResponseEntity<Actividad> crearActividad(@RequestBody Actividad input) {

        repo.save(input);
        System.out.println(input);

        return new ResponseEntity<>(input, HttpStatus.CREATED);
    }
    
    //Actualizar actividad existente, se actualizan solo los campos introducidos
    @PutMapping("/{id}")
    public ResponseEntity<Actividad> updateActividad(@PathVariable Long id, @RequestBody Actividad input) {
        ResponseEntity<Actividad> salida;
        System.out.println(input);

        if (repo.existsById(id)) {
            Actividad actividad = repo.getById(id);
            input.setId(id.intValue());

            if (input.getFecha() == null) {
                input.setFecha(actividad.getFecha());
            }
            if (input.getTipo_practica() == null) {
                input.setTipo_practica(actividad.getTipo_practica());
            }
            if (input.getHoras_dia() <= 0) {
                input.setHoras_dia(actividad.getHoras_dia());
            }
            if (input.getNombre() == null) {
                input.setNombre(actividad.getNombre());
            }
            if (input.getObservaciones() == null) {
                input.setObservaciones(actividad.getObservaciones());
            }
            if (input.getAlumno() == null) {
                input.setAlumno(actividad.getAlumno());
            }

            repo.save(input);
            salida = new ResponseEntity<Actividad>(repo.findById(id).get(), HttpStatus.OK);
        } else {
            salida = new ResponseEntity<Actividad>(HttpStatus.NOT_FOUND);
        }

        return salida;
    }
    
    //Permitir solicitudes POST para eliminar
    @DeleteMapping("/{id}")
    public ResponseEntity<Actividad> delete(@PathVariable Long id) {

        ResponseEntity<Actividad> salida;

        if (repo.existsById(id)) {
            salida = new ResponseEntity<Actividad>(repo.findById(id).get(), HttpStatus.OK);
            repo.deleteById(id);
        } else {
            salida = new ResponseEntity<Actividad>(HttpStatus.NOT_FOUND);
        }

        return salida;
    }

}
