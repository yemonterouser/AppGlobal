package com.AppGlobal.app.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "bdusuarios") //name hace referencia al nombre de la tabla que vamos a manejar
@ToString @EqualsAndHashCode
public class Usuario {

    @Id //con esto le indicamos que este es la llave primaria
    @GeneratedValue(strategy=GenerationType.IDENTITY) //este sirve  para
    @Getter @Setter  @Column(name = "id") //con column hacemos referencia a la columna de la tabla en este caso bdusuarios
    private Long id;

    @Getter @Setter @Column(name = "nombre")
    private String nombre;

    @Getter @Setter @Column(name = "apellido")
    private String apellido;

    @Getter @Setter @Column(name = "email")
    private String email;

    @Getter @Setter @Column(name = "password")
    private String password;

}