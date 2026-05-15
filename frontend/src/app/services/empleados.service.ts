// src/app/services/empleados.service.ts
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Empleado } from '../models/empleado.model';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment'; // ✅ Importamos la configuración global

@Injectable({ providedIn: 'root' })
export class EmpleadosService {
  
  // ✅ Usamos la URL del environment. Ya no más "localhost" escrito a mano aquí.
  private baseUrl = environment.apiUrl;

  constructor(private http: HttpClient) { }

  getPeriodoActual(): Observable<any> {
    return this.http.get(`${this.baseUrl}/api/periodo-nomina/actual`);
  }

  getNovedades(idEmpleado: number): Observable<any[]> {
    return this.http.get<any[]>(`${this.baseUrl}/api/novedades/empleado/${idEmpleado}`);
  }

  pagarNomina(payload: any): Observable<any> {
    return this.http.post(`${this.baseUrl}/api/pagos-nomina`, payload, {
      responseType: 'text'
    });
  }

  getEmpleados(): Observable<Empleado[]> {
    return this.http.get<Empleado[]>(`${this.baseUrl}/api/empleados`);
  }

  guardarNovedad(novedad: any): Observable<any> {
    return this.http.post(`${this.baseUrl}/api/novedades`, novedad);
  }

  getNovedadesPorPeriodo(periodoId: string): Observable<any[]> {
    return this.http.get<any[]>(`${this.baseUrl}/api/novedades/periodo/${periodoId}`);
  }

  getNovedadesPorPeriodoYEmpleado(periodoId: string, empleadoId: string): Observable<any[]> {
    return this.http.get<any[]>(`${this.baseUrl}/api/novedades/periodo/${periodoId}/empleado/${empleadoId}`);
  }

  getNovedadesPeriodoActual(): Observable<any[]> {
    return this.http.get<any[]>(`${this.baseUrl}/api/novedades/periodo-actual`);
  }

  getPeriodos(): Observable<any[]> {
    return this.http.get<any[]>(`${this.baseUrl}/api/periodo-nomina/todos`);
  }

  getPeriodosTest(): Observable<any> {
    return this.http.get<any>(`${this.baseUrl}/api/periodo-nomina/test`);
  }

  cerrarPeriodoActual(): Observable<any> {
    return this.http.patch(`${this.baseUrl}/api/periodo-nomina/cerrar-actual`, {});
  }

  crearPeriodo(periodo: any): Observable<any> {
    return this.http.post(`${this.baseUrl}/api/periodo-nomina`, periodo);
  }

  getNovedadesPorEmpleado(empleadoId: string): Observable<any[]> {
    return this.http.get<any[]>(`${this.baseUrl}/api/novedades/empleado/${empleadoId}`);
  }

  actualizarEmpleado(empleado: any): Observable<any> {
    return this.http.put(`${this.baseUrl}/api/empleados/${empleado.id}`, empleado);
  }

  getConcepto(tipo: string): string {
    const mapa: any = {
      HORAS_EXTRAS: 'HORAS EXTRAS',
      BONIFICACION: 'BONIFICACIÓN',
      COMISION: 'COMISIÓN',
      AUXILIO_TRANSPORTE: 'AUXILIO DE TRANSPORTE',
      PRESTAMO: 'DESCUENTO PRÉSTAMO',
      DEDUCCION: 'DEDUCCIÓN',
      EMBARGO: 'EMBARGO JUDICIAL'
    };
    return mapa[tipo] || tipo;
  }
}

export type { Empleado };