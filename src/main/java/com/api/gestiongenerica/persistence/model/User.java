package com.api.gestiongenerica.persistence.model;

import java.io.Serializable;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

@Entity
@Table(name = "Usuarios")
@Data
@NoArgsConstructor
<<<<<<< HEAD
=======
@AllArgsConstructor
>>>>>>> main
public class User implements Serializable {

    /** Identificador unico del usuario **/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    /** Nombre de usuario **/
    @Column(name = "nombre", nullable = false)
    @NotBlank(message = "El nombre no puede estar vacio")
    private String nombre;

    /** Apellidos del usuario **/
    @Column(name = "apellidos", nullable = false)
    @NotBlank(message = "Los apellidos no puede estar vacio")
    private String apellidos;

    /** Edad del usuario **/
    @Column(name = "edad", nullable = false)
    private int edad;

    /** Email del usuario **/
    @Column(name = "correo", unique = true, nullable = false)
    @Email(message = "El correo no puede estar vacio")
    private String correo;

    /** Direccion del usuario **/
    @Column(name = "direccion", nullable = false)
    @NotBlank(message = "La direccion no puede estar vacia")
    private String direccion;

    /** Telefono del usuario **/
    @Column(name = "telefono", nullable = false)
    private int telefono;

    /** Contraseña del usuario **/
    @Column(name = "contrasena", nullable = false)
    @NotBlank(message = "La contraseña no puede estar vacia")
    private String contrasena;
}
