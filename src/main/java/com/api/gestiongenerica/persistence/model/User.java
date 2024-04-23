package com.api.gestiongenerica.persistence.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Usuarios")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    /** Identificador unico del usuario **/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Usuario")
    private long IDUsuario;

    /** Nombre de usuario **/
    @Column(name = "Nombre", nullable = false)
    @NotBlank(message = "El nombre no puede estar vacio")
    private String nombre;

    /** Apellidos del usuario **/
    @Column(name = "Apellidos", nullable = false)
    @NotBlank(message = "Los apellidos no puede estar vacio")
    private String apellidos;

    /** Edad del usuario **/
    @Column(name = "Edad", nullable = false)
    private int edad;

    /** Email del usuario **/
    @Column(name = "Correo", unique = true, nullable = false)
    @Email(message = "El correo no puede estar vacio")
    private String correo;

    /** Direccion del usuario **/
    @Column(name = "Apellidos", nullable = false)
    @NotBlank(message = "La direccion no puede estar vacia")
    private String direccion;

    /** Telefono del usuario **/
    @Column(name = "Telefono", nullable = false)
    @Size(min = 9, max = 9, message = "El telefono tiene que tener 9 numeros")
    private int telefono;

    /** Contraseña del usuario **/
    @Column(name = "password", nullable = false)
    @NotBlank(message = "La contraseña no puede estar vacia")
    private String password;
}
