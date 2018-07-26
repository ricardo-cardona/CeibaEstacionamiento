import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { catchError, map, tap } from 'rxjs/operators';

import { Vehiculo } from '../objetos/vehiculo';
import { TipoVehiculo } from '../objetos/tipoVehiculo';
import { VehiculoParqueado } from '../objetos/vehiculoParqueado';
import { Salida } from '../objetos/salida';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class ParqueaderoService {

  private tiposVehiculoUrl = 'http://localhost:8080/tipos';
  private ingresoUrl = 'http://localhost:8080/ingresos';
  private vehiculosParqueadosUrl = 'http://localhost:8080/parqueados';
  private salidaUrl = 'http://localhost:8080/salidas';

  constructor(private http: HttpClient) { }

  /** GET tipos de vehiculo from the server */
  getTiposVehiculo(): Observable<TipoVehiculo[]> {
    return this.http.get<TipoVehiculo[]>(this.tiposVehiculoUrl)
      .pipe(
        catchError(err => {
          throw 'Error ' + err
        })
      );
  }

  /** POST: registra el ingreso del vehiculo al parqueadero */
  registrarIngreso (vehiculo: Vehiculo): Observable<any> {
    return this.http.post<Vehiculo>(this.ingresoUrl, vehiculo, httpOptions)
      /*.pipe(
        catchError(err => {
          console.log('Error en ingreso: ' + err.error.message);
          throw 'Error ' + err
        })
      )*/;
  }

  /** GET vehiculos parqueados */
  getVehiculosParqueados (): Observable<VehiculoParqueado[]> {
    return this.http.get<VehiculoParqueado[]>(this.vehiculosParqueadosUrl)
      .pipe(
        catchError(err => {
          throw 'Error ' + err
        })
      );
  }

  /** GET ingreso a parqueadero particular */
  getIngreso(id: number): Observable<VehiculoParqueado> {
    const url = `${this.ingresoUrl}/${id}`;
    return this.http.get<VehiculoParqueado>(url)
      .pipe(
        catchError(err => {
          throw 'Error ' + err
        })
      );
  }

  /** PUT: registra la salida del vehiculo al parqueadero */
  registrarSalida (id: number): Observable<Salida> {
    return this.http.put<Salida>(this.salidaUrl + '/' + id, +id, httpOptions)
      /*.pipe(
        catchError(err => {
          throw 'Error ' + err
        })
      )*/;
  }

}
