/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/RestController.java to edit this template
 */
package GestorSpring.GestorSpring.controllers;

import GestorSpring.GestorSpring.models.Alumno;
import GestorSpring.GestorSpring.repositories.AlumnoRepository;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;
import java.util.Optional;
import java.util.HashMap;
import java.util.LinkedHashMap;
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


/**
 *
 * @author Syzer
 */
@RestController
@RequestMapping("/alumnos")
public class AlumnoController {

    @Autowired
    AlumnoRepository repo;

    @GetMapping()
    public List<Alumno> listAlumnos() {
        return repo.findAll();
    }

    //Listar alumno con toda la informacion
    @GetMapping("/alumno/{id}")
    public ResponseEntity<Alumno> get(@PathVariable Long id) {
        if (repo.existsById(id)) {
            return new ResponseEntity<Alumno>(repo.findById(id).get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    //Listar Detalles alumno de horas y empresa
    @GetMapping("/detalleAlumno/{id}")
    public ResponseEntity<LinkedHashMap<String, Object>> alumnoDetalle(@PathVariable Long id) {

        LinkedHashMap<String, Object> detalles = new LinkedHashMap<>();
        Alumno alumno = new Alumno();

        if (repo.existsById(id)) {
            alumno = repo.getOne(id);
                
            detalles.put("Alumno", alumno.getNombre());
            detalles.put("Profesor", alumno.getProfesor().getNombre());
            detalles.put("Empresa asignada", alumno.getEmpresa().getNombre());
            detalles.put("Horas FCT", alumno.getHoras_fct());
            detalles.put("Horas DUAL", alumno.getHoras_dual());

            return new ResponseEntity<LinkedHashMap<String, Object>>(detalles, HttpStatus.OK);

        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }

    }

    //Crear alumno nuevo
    @PostMapping
    public ResponseEntity<Alumno> crearAlumno(@RequestBody Alumno input) {

        repo.save(input);
        System.out.println(input);

        return new ResponseEntity<>(input, HttpStatus.CREATED);
    }

    //Actualizar alumno existente, se actualizan solo los campos introducidos
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Alumno> updateAlumno(@PathVariable Long id, @RequestBody Alumno input) {
        ResponseEntity<Alumno> salida;
        System.out.println(input);

        if (repo.existsById(id)) {
            Alumno alumno = repo.getById(id);
            input.setId(id.intValue());

            if (input.getNombre() == null) {
                input.setNombre(alumno.getNombre());
            }
            if (input.getApellidos() == null) {
                input.setApellidos(alumno.getApellidos());
            }
            if (input.getDni() == null) {
                input.setDni(alumno.getDni());
            }
            if (input.getPassword() == null) {
                input.setPassword(alumno.getPassword());
            }
            if (input.getFecha_nac() == null) {
                input.setFecha_nac(alumno.getFecha_nac());
            }
            if (input.getEmail() == null) {
                input.setEmail(alumno.getEmail());
            }
            if (input.getTelefono() == null) {
                input.setTelefono(alumno.getTelefono());
            }
            if (input.getEmpresa() == null) {
                input.setEmpresa(alumno.getEmpresa());
            }
            if (input.getProfesor() == null) {
                input.setProfesor(alumno.getProfesor());
            }
            if (input.getHoras_dual() <= 0) {
                input.setHoras_dual(alumno.getHoras_dual());
            }
            if (input.getHoras_fct() <= 0) {
                input.setHoras_fct(alumno.getHoras_fct());
            }
            if (input.getObservaciones() == null) {
                input.setObservaciones(alumno.getObservaciones());
            }

            repo.save(input);
            salida = new ResponseEntity<Alumno>(repo.findById(id).get(), HttpStatus.OK);
        } else {
            salida = new ResponseEntity<Alumno>(HttpStatus.NOT_FOUND);
        }

        return salida;
    }
}
