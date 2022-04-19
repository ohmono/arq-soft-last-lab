package com.udea.persona.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@ApiModel(description="Entidad de Datos de la Persona")
@Entity
public class Persona implements Serializable{
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @ApiModelProperty(notes="Id automatico generado en la BD", hidden = true) // agregado el aprametro hidden para remover el id de la documentación de swagger
    @Column(name="idperson")
    private Long idPerson;
    
    @ApiModelProperty(notes="Atributo Nombre de la Persona")
    @Column(name="firstname", nullable=false,length=80)
    private @NonNull String firstName;

    @ApiModelProperty(notes="Atributo Apellido de la Persona")
    @Column(name="lastname", nullable=false,length=80)
     private @NonNull String lastName;
    
    @ApiModelProperty(notes="Atributo Email de la Persona")
    @Column(name="email", nullable=false,length=80)
    private @NonNull String email;
    
    @ApiModelProperty(notes="Atributo Salario de la Persona")
    @Column(name="salary", nullable=false, length=80)
    private @NonNull Float salary;
    
    @ApiModelProperty(notes="Atributo Cargo de la Persona")
    @Column(name="charge", nullable=false,length=80)
    private @NonNull String charge;
    
    @ApiModelProperty(notes="Atributo Dirección de la Persona")
    @Column(name="address", nullable=false,length=80)
    private @NonNull String address;
    
    @ApiModelProperty(notes="Atributo Oficina de la Persona")
    @Column(name="office", nullable=false,length=80)
    private @NonNull String office;
    
    @ApiModelProperty(notes="Atributo Dependencia de la Persona")
    @Column(name="dependency", nullable=false,length=80)
    private @NonNull String dependency;
    
    @ApiModelProperty(notes="Atributo Fecha de Contratación de la Persona")
    @Column(name="hiringDate", nullable=false,length=80)
    private @NonNull Date hiringDate;

    public void setSalary(double d) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
