package com.nomina.numa.model.mongo;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
<<<<<<< HEAD
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import java.time.LocalDate;
=======
>>>>>>> 7b9455b3d7abf18e21c0bf9377c72222cbc949a8

@Document(collection = "periodonomina")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PeriodoNomina {
    
    @Id
    private String id;
    
    @Field("fechaInicio")
<<<<<<< HEAD
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate fechaInicio;
    
    @Field("fechaFin")
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate fechaFin;
    
    private String description;
    
    private String estado; // "CERRADO", "ABIERTO", "PENDIENTE"
    
    private Integer anio;
    
    private String tipo; // "1ra", "2da", "QUINCENAL"
    
    private Double costoMillones; // Costo de nómina en millones COP
    
    private Double costoCOP; // Costo de nómina en COP
=======
    private String fechaInicio;
    
    @Field("fechaFin")
    private String fechaFin;
    
    private String description;
    
    private String estado;
    
    private Integer anio;
    
    private String tipo;
    
    private Double costoMillones;
    
    private Double costoCOP;
>>>>>>> 7b9455b3d7abf18e21c0bf9377c72222cbc949a8
    
    // Método helper para obtener descripción formateada
    public String getDescription() {
        if (description != null && !description.isEmpty()) {
            return description;
        }
<<<<<<< HEAD
        if (fechaInicio != null && tipo != null) {
            String mes = obtenerMes(fechaInicio.getMonthValue());
            return String.format("%s %s %d", tipo, mes, fechaInicio.getYear());
        }
        return "Período sin descripción";
    }
    
    // Método helper para obtener el mes en español
    private String obtenerMes(int mes) {
        String[] meses = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio",
                         "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
        return meses[mes - 1];
    }
    
=======
        return "Período sin descripción";
    }
    
>>>>>>> 7b9455b3d7abf18e21c0bf9377c72222cbc949a8
    // Método para validar si el período está activo
    public boolean isActivo() {
        return "ABIERTO".equalsIgnoreCase(estado);
    }
}