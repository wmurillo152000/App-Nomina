// src/app/interceptors/auth.interceptor.ts
import { HttpInterceptorFn } from '@angular/common/http';
import { inject } from '@angular/core';
import { AuthService } from '../services/auth.service';
import { Router } from '@angular/router';
import { catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';
import { environment } from '../../environments/environment'; // ✅ Agregamos environment

export const authInterceptor: HttpInterceptorFn = (req, next) => {
    const authService = inject(AuthService);
    const router = inject(Router);
    const token = authService.getToken();
    const baseUrl = environment.apiUrl; // ✅ Obtenemos la URL de Railway

    const publicUrls = [
        '/api/periodo-nomina/todos',
        '/api/periodo-nomina/test',
        '/api/periodo-nomina/actual',
        '/api/periodo-nomina/actual-con-costo',
        '/api/prediccion/'
    ];

    const isPublicUrl = publicUrls.some(url => req.url.includes(url));
    
    // ✅ CLAVE: Solo agregamos el token si la petición es para NUESTRO backend
    const isApiUrl = req.url.startsWith(baseUrl) || req.url.startsWith('http://'https://giving-joy-production.up.railway.app'');

    let authReq = req;
    
    // Solo agregamos token si: hay token, NO es URL pública y SÍ es para nuestro Backend
    if (token && !isPublicUrl && isApiUrl) {
        authReq = req.clone({
            setHeaders: {
                Authorization: `Bearer ${token}`
            }
        });
        console.log('🔐 Token agregado a:', req.url);
    } else if (isPublicUrl) {
        console.log('🔓 Endpoint público, sin token:', req.url);
    }

    return next(authReq).pipe(
        catchError(err => {
            console.log('⚠️ Error en petición:', err.status, req.url);

            if (err.status === 401) {
                console.warn('401 → Token inválido/expirado → logout');
                authService.logout();
                router.navigate(['/login']);
            } else if (err.status === 403) {
                console.warn('403 → Acceso denegado a:', req.url);
            }
            return throwError(() => err);
        })
    );
};