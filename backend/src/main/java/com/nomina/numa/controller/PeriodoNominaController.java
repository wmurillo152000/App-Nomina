package com.nomina.numa.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.nomina.numa.model.mongo.PeriodoNomina;
import com.nomina.numa.repository.mongo.PeriodoNominaRepository;

import java.time.LocalDate;
import java.util.*;

@RestController
@RequestMapping("/api/periodo-nomina")
@RequiredArgsConstructor
public class PeriodoNominaController {

    private final PeriodoNominaRepository repo;

    @GetMapping("/test")
    public ResponseEntity<Map<String, String>> test() {
        System.out.println("🚀 TEST ENDPOINT FUNCIONA");
        Map<String, String> response = new HashMap<>();
        response.put("mensaje", "El backend está funcionando correctamente");
        response.put("timestamp", LocalDate.now().toString());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/diagnostico")
    public ResponseEntity<Map<String, Object>> diagnosticar() {
        System.out.println("=== DIAGNÓSTICO DE MONGODB ===");
        Map<String, Object> diag = new HashMap<>();
<<<<<<< HEAD
        
        try {
            // Método 1: count()
            long count = repo.count();
            System.out.println("📊 repo.count(): " + count);
            diag.put("totalDocumentos_count", count);
            
            // Método 2: findAll() sin ordenamiento
            List<PeriodoNomina> todos = repo.findAll();
            System.out.println("📊 repo.findAll(): " + todos.size());
            diag.put("totalDocumentos_findAll", todos.size());
            
            // Método 3: findAllByOrderByFechaInicioDesc()
            List<PeriodoNomina> ordenados = repo.findAllByOrderByFechaInicioDesc();
            System.out.println("📊 repo.findAllByOrderByFechaInicioDesc(): " + ordenados.size());
            diag.put("totalDocumentos_ordenados", ordenados.size());
            
            // Mostrar detalles de los documentos
=======

        try {
            long count = repo.count();
            System.out.println("📊 repo.count(): " + count);
            diag.put("totalDocumentos_count", count);

            List<PeriodoNomina> todos = repo.findAll();
            System.out.println("📊 repo.findAll(): " + todos.size());
            diag.put("totalDocumentos_findAll", todos.size());

            List<PeriodoNomina> ordenados = repo.findAllByOrderByFechaInicioDesc();
            System.out.println("📊 repo.findAllByOrderByFechaInicioDesc(): " + ordenados.size());
            diag.put("totalDocumentos_ordenados", ordenados.size());

>>>>>>> 7b9455b3d7abf18e21c0bf9377c72222cbc949a8
            List<Map<String, Object>> documentos = new ArrayList<>();
            for (PeriodoNomina p : todos) {
                Map<String, Object> doc = new HashMap<>();
                doc.put("id", p.getId());
                doc.put("description", p.getDescription());
                doc.put("estado", p.getEstado());
<<<<<<< HEAD
                doc.put("fechaInicio", p.getFechaInicio() != null ? p.getFechaInicio().toString() : "null");
                doc.put("fechaInicio_type", p.getFechaInicio() != null ? p.getFechaInicio().getClass().getSimpleName() : "null");
                doc.put("fechaFin", p.getFechaFin() != null ? p.getFechaFin().toString() : "null");
=======
                doc.put("fechaInicio", p.getFechaInicio() != null ? p.getFechaInicio() : "null");
                doc.put("fechaFin", p.getFechaFin() != null ? p.getFechaFin() : "null");
>>>>>>> 7b9455b3d7abf18e21c0bf9377c72222cbc949a8
                doc.put("anio", p.getAnio());
                doc.put("tipo", p.getTipo());
                documentos.add(doc);
            }
            diag.put("documentos", documentos);
<<<<<<< HEAD
            
            // Buscar período activo
=======

>>>>>>> 7b9455b3d7abf18e21c0bf9377c72222cbc949a8
            Optional<PeriodoNomina> abierto = repo.findTopByEstadoOrderByFechaInicioDesc("ABIERTO");
            diag.put("tienePeriodoActivo", abierto.isPresent());
            if (abierto.isPresent()) {
                diag.put("periodoActivoId", abierto.get().getId());
                diag.put("periodoActivoDesc", abierto.get().getDescription());
            }
<<<<<<< HEAD
            
            diag.put("exito", true);
            System.out.println("✅ Diagnóstico completado. Documentos encontrados: " + todos.size());
            
=======

            diag.put("exito", true);
            System.out.println("✅ Diagnóstico completado. Documentos encontrados: " + todos.size());

>>>>>>> 7b9455b3d7abf18e21c0bf9377c72222cbc949a8
        } catch (Exception e) {
            System.err.println("❌ Error en diagnóstico: " + e.getMessage());
            e.printStackTrace();
            diag.put("exito", false);
            diag.put("error", e.getMessage());
<<<<<<< HEAD
            diag.put("error_tipo", e.getClass().getSimpleName());
        }
        
        return ResponseEntity.ok(diag);
    }

    @GetMapping("/actual")
    public ResponseEntity<PeriodoNomina> getActual(Authentication auth) {
        System.out.println("=== GET /actual ===");
        System.out.println("Usuario: " + (auth != null ? auth.getName() : "null"));
        
        try {
            Optional<PeriodoNomina> periodo = repo.findTopByEstadoOrderByFechaInicioDesc("ABIERTO");
            
            if (periodo.isPresent()) {
                System.out.println("✅ Período encontrado - ID: " + periodo.get().getId());
                System.out.println("   Descripción: " + periodo.get().getDescription());
                return ResponseEntity.ok(periodo.get());
            } else {
                System.out.println("❌ No hay período ABIERTO");
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            System.err.println("❌ Error: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
=======
        }

        return ResponseEntity.ok(diag);
    }

    @GetMapping("/actual")
    public ResponseEntity<PeriodoNomina> getActual(Authentication auth) {
        System.out.println("=== GET /actual ===");
        System.out.println("Usuario: " + (auth != null ? auth.getName() : "null"));

        try {
            Optional<PeriodoNomina> periodo = repo.findTopByEstadoOrderByFechaInicioDesc("ABIERTO");

            if (periodo.isPresent()) {
                System.out.println("✅ Período encontrado - ID: " + periodo.get().getId());
                System.out.println("   Descripción: " + periodo.get().getDescription());
                return ResponseEntity.ok(periodo.get());
            } else {
                System.out.println("❌ No hay período ABIERTO");
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            System.err.println("❌ Error: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
>>>>>>> 7b9455b3d7abf18e21c0bf9377c72222cbc949a8
    }

    @GetMapping("/actual-con-costo")
    public ResponseEntity<?> getActualConCosto(Authentication auth) {
        System.out.println("=== GET /actual-con-costo ===");
<<<<<<< HEAD
        
        try {
            Optional<PeriodoNomina> periodo = repo.findTopByEstadoOrderByFechaInicioDesc("ABIERTO");
            
=======

        try {
            Optional<PeriodoNomina> periodo = repo.findTopByEstadoOrderByFechaInicioDesc("ABIERTO");

>>>>>>> 7b9455b3d7abf18e21c0bf9377c72222cbc949a8
            if (periodo.isPresent()) {
                PeriodoNomina p = periodo.get();
                Map<String, Object> response = new HashMap<>();
                response.put("quincena", p.getTipo() != null ? p.getTipo() : "1ra");
<<<<<<< HEAD
                
                String mes = "";
                if (p.getFechaInicio() != null) {
                    String[] meses = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio",
                                     "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
                    mes = meses[p.getFechaInicio().getMonthValue() - 1];
                }
                response.put("mes", mes);
                response.put("anio", p.getAnio() != null ? p.getAnio() : 
                           (p.getFechaInicio() != null ? p.getFechaInicio().getYear() : 2026));
                response.put("costoMillones", p.getCostoMillones() != null ? p.getCostoMillones() : 12294.24);
                response.put("costoCOP", p.getCostoCOP() != null ? p.getCostoCOP() : 12294241266.0);
                response.put("description", p.getDescription());
                
=======

                String mes = "";
                String anioStr = "";
                if (p.getFechaInicio() != null && !p.getFechaInicio().isEmpty()) {
                    String[] partes = p.getFechaInicio().split("-");
                    if (partes.length >= 2) {
                        String[] meses = { "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio",
                                "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre" };
                        try {
                            int month = Integer.parseInt(partes[1]);
                            mes = meses[month - 1];
                        } catch (NumberFormatException e) {
                            mes = "";
                        }
                    }
                    if (partes.length >= 1) {
                        anioStr = partes[0];
                    }
                }
                response.put("mes", mes);
                response.put("anio",
                        p.getAnio() != null ? p.getAnio() : (!anioStr.isEmpty() ? Integer.parseInt(anioStr) : 2026));
                response.put("costoMillones", p.getCostoMillones() != null ? p.getCostoMillones() : 12294.24);
                response.put("costoCOP", p.getCostoCOP() != null ? p.getCostoCOP() : 12294241266.0);
                response.put("description", p.getDescription());

>>>>>>> 7b9455b3d7abf18e21c0bf9377c72222cbc949a8
                System.out.println("✅ Costo: " + response.get("costoMillones") + " millones");
                return ResponseEntity.ok(response);
            } else {
                System.out.println("❌ No hay período activo");
                Map<String, Object> defaultResponse = new HashMap<>();
                defaultResponse.put("quincena", "2da");
                defaultResponse.put("mes", "Abril");
                defaultResponse.put("anio", 2026);
                defaultResponse.put("costoMillones", 12294.24);
                defaultResponse.put("costoCOP", 12294241266.0);
                defaultResponse.put("description", "2da Quincena Abril 2026");
                defaultResponse.put("default", true);
                return ResponseEntity.ok(defaultResponse);
            }
        } catch (Exception e) {
            System.err.println("❌ Error: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(500).body(Map.of("error", e.getMessage()));
        }
    }

    @GetMapping("/todos")
    public ResponseEntity<List<PeriodoNomina>> getTodosLosPeriodos(Authentication auth) {
        System.out.println("=== GET /todos ===");
<<<<<<< HEAD
        
=======

>>>>>>> 7b9455b3d7abf18e21c0bf9377c72222cbc949a8
        try {
            // Intentar con ordenamiento
            List<PeriodoNomina> periodos = repo.findAllByOrderByFechaInicioDesc();
            System.out.println("✅ Períodos encontrados: " + periodos.size());
<<<<<<< HEAD
            
            if (periodos.isEmpty()) {
                // Si no hay con ordenamiento, intentar sin ordenamiento
=======

            if (periodos.isEmpty()) {
>>>>>>> 7b9455b3d7abf18e21c0bf9377c72222cbc949a8
                System.out.println("⚠️ Sin ordenamiento, intentando findAll()...");
                periodos = repo.findAll();
                System.out.println("✅ findAll() encontró: " + periodos.size());
            }
<<<<<<< HEAD
            
=======

>>>>>>> 7b9455b3d7abf18e21c0bf9377c72222cbc949a8
            return ResponseEntity.ok(periodos);
        } catch (Exception e) {
            System.err.println("❌ Error: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(500).body(null);
        }
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    @CacheEvict(value = "periodos", allEntries = true)
    public ResponseEntity<?> crear(@RequestBody PeriodoNomina nuevo, Authentication auth) {
        System.out.println("=== CREAR NUEVO PERÍODO ===");
        System.out.println("Usuario: " + auth.getName());
<<<<<<< HEAD
        
=======

>>>>>>> 7b9455b3d7abf18e21c0bf9377c72222cbc949a8
        try {
            if (nuevo.getFechaInicio() == null || nuevo.getFechaFin() == null) {
                return ResponseEntity.badRequest().body(Map.of("error", "Las fechas son requeridas"));
            }
<<<<<<< HEAD
            
=======

>>>>>>> 7b9455b3d7abf18e21c0bf9377c72222cbc949a8
            repo.findTopByEstadoOrderByFechaInicioDesc("ABIERTO")
                    .ifPresent(anterior -> {
                        anterior.setEstado("CERRADO");
                        repo.save(anterior);
                    });
<<<<<<< HEAD
            
            nuevo.setEstado("ABIERTO");
            if (nuevo.getAnio() == null) {
                nuevo.setAnio(nuevo.getFechaInicio().getYear());
            }
            
=======

            nuevo.setEstado("ABIERTO");
            if (nuevo.getAnio() == null && nuevo.getFechaInicio() != null && !nuevo.getFechaInicio().isEmpty()) {
                try {
                    nuevo.setAnio(Integer.parseInt(nuevo.getFechaInicio().split("-")[0]));
                } catch (Exception e) {
                    nuevo.setAnio(2026);
                }
            }

>>>>>>> 7b9455b3d7abf18e21c0bf9377c72222cbc949a8
            PeriodoNomina guardado = repo.save(nuevo);
            System.out.println("✅ Período creado: " + guardado.getId());
            return ResponseEntity.ok(guardado);
        } catch (Exception e) {
            System.err.println("❌ Error: " + e.getMessage());
            return ResponseEntity.status(500).body(Map.of("error", e.getMessage()));
        }
    }

    @PatchMapping("/cerrar-actual")
    @PreAuthorize("hasRole('ADMIN')")
    @CacheEvict(value = "periodos", allEntries = true)
    public ResponseEntity<?> cerrarPeriodoActual(Authentication auth) {
        System.out.println("=== CERRAR PERÍODO ACTUAL ===");
<<<<<<< HEAD
        
        try {
            Optional<PeriodoNomina> actual = repo.findTopByEstadoOrderByFechaInicioDesc("ABIERTO");
            
=======

        try {
            Optional<PeriodoNomina> actual = repo.findTopByEstadoOrderByFechaInicioDesc("ABIERTO");

>>>>>>> 7b9455b3d7abf18e21c0bf9377c72222cbc949a8
            if (actual.isPresent()) {
                PeriodoNomina p = actual.get();
                p.setEstado("CERRADO");
                repo.save(p);
                System.out.println("✅ Período cerrado: " + p.getId());
                return ResponseEntity.ok(Map.of("mensaje", "Período cerrado exitosamente", "periodo", p));
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of("error", e.getMessage()));
        }
    }

    @PostMapping("/actualizar-costos")
    @PreAuthorize("hasRole('ADMIN')")
    @CacheEvict(value = "periodos", allEntries = true)
    public ResponseEntity<?> actualizarCostosPeriodos(Authentication auth) {
        System.out.println("=== ACTUALIZAR COSTOS ===");
<<<<<<< HEAD
        
        try {
            List<PeriodoNomina> periodos = repo.findAll();
            int actualizados = 0;
            
=======

        try {
            List<PeriodoNomina> periodos = repo.findAll();
            int actualizados = 0;

>>>>>>> 7b9455b3d7abf18e21c0bf9377c72222cbc949a8
            for (PeriodoNomina periodo : periodos) {
                if ("CERRADO".equals(periodo.getEstado())) {
                    periodo.setCostoMillones(12294.24);
                    periodo.setCostoCOP(12294241266.0);
                    repo.save(periodo);
                    actualizados++;
                }
            }
<<<<<<< HEAD
            
=======

>>>>>>> 7b9455b3d7abf18e21c0bf9377c72222cbc949a8
            return ResponseEntity.ok(Map.of("mensaje", "Actualizados " + actualizados + " períodos"));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of("error", e.getMessage()));
        }
    }
<<<<<<< HEAD
    
=======

>>>>>>> 7b9455b3d7abf18e21c0bf9377c72222cbc949a8
    @PostMapping("/crear-prueba")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> crearPeriodoPrueba(Authentication auth) {
        System.out.println("=== CREAR PERÍODO DE PRUEBA ===");
<<<<<<< HEAD
        
        try {
            PeriodoNomina periodoPrueba = PeriodoNomina.builder()
                .description("2da Quincena Abril 2026")
                .fechaInicio(LocalDate.of(2026, 4, 16))
                .fechaFin(LocalDate.of(2026, 4, 30))
                .estado("ABIERTO")
                .anio(2026)
                .tipo("2da")
                .costoMillones(27485.37)
                .costoCOP(27485368387.0)
                .build();
            
            repo.findTopByEstadoOrderByFechaInicioDesc("ABIERTO")
                .ifPresent(anterior -> {
                    anterior.setEstado("CERRADO");
                    repo.save(anterior);
                });
            
=======

        try {
            PeriodoNomina periodoPrueba = PeriodoNomina.builder()
                    .description("2da Quincena Abril 2026")
                    .fechaInicio("2026-04-16")
                    .fechaFin("2026-04-30")
                    .estado("ABIERTO")
                    .anio(2026)
                    .tipo("2da")
                    .costoMillones(27485.37)
                    .costoCOP(27485368387.0)
                    .build();

            repo.findTopByEstadoOrderByFechaInicioDesc("ABIERTO")
                    .ifPresent(anterior -> {
                        anterior.setEstado("CERRADO");
                        repo.save(anterior);
                    });

>>>>>>> 7b9455b3d7abf18e21c0bf9377c72222cbc949a8
            PeriodoNomina guardado = repo.save(periodoPrueba);
            return ResponseEntity.ok(Map.of("mensaje", "Período creado", "periodo", guardado));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of("error", e.getMessage()));
        }
    }
}