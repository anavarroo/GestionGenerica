package com.api.gestiongenerica.persistence.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto implements Serializable {

    /** Nombre de usuario **/
    private String nombre;

    /** Apellidos del usuario **/
    private String apellidos;

    /** Edad del usuario **/
    private int edad;

    /** Email del usuario **/
    private String correo;

    /** Direccion del usuario **/
    private String direccion;

    /** Telefono del usuario **/
    private int telefono;

}
