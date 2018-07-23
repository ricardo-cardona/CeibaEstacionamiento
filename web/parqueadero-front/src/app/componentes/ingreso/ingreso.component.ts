import { Component, OnInit } from '@angular/core';
import { Vehiculo } from '../../objetos/vehiculo';
import { ParqueaderoService } from '../../servicios/parqueadero.service';
import { TipoVehiculo } from '../../objetos/tipoVehiculo';

@Component({
  selector: 'app-ingreso',
  templateUrl: './ingreso.component.html',
  styleUrls: ['./ingreso.component.css']
})
export class IngresoComponent implements OnInit {

  tiposVehiculo: TipoVehiculo[];
  tipoSeleccionado: TipoVehiculo = new TipoVehiculo();
  
  constructor(private parqueaderoService: ParqueaderoService) { }

  ngOnInit() {
    this.getTiposVehiculo();
    //this.tipoSeleccionado = this.tiposVehiculo[0];
  }

  getTiposVehiculo(): void {
    this.parqueaderoService.getTiposVehiculo()
      .subscribe(tiposVehiculo => this.tiposVehiculo = tiposVehiculo);
  }

  registrarIngreso(placa: string, cilindraje: number): void {
    
    var vehiculo: Vehiculo = {
        id: 0
      , placa: placa
      , tipoVehiculo: this.tipoSeleccionado
      , cilindraje: (this.tipoSeleccionado.tieneCilindraje ? cilindraje : 0)
    };
    
    /*alert(
      'Tipo: ' + vehiculo.tipoVehiculo.id +
      'Placa: ' + vehiculo.placa +
      'Cilindraje: ' + vehiculo.cilindraje
    );*/

    this.parqueaderoService.registrarIngreso(vehiculo)
      .subscribe();

  }

  convertirATipoVehiculo(id: number): TipoVehiculo {
    var tipoVehiculo: TipoVehiculo = {
        id: 1
      , nombre: null
      , tieneCilindraje: null
    };

    return tipoVehiculo;
  }

  onSelect(tipoEscogido: string): void {
    this.tipoSeleccionado = this.tiposVehiculo
      .find(tipo => tipo.id == +tipoEscogido);
    //alert('Seleccionado: ' + this.tipoSeleccionado.nombre);
  }

}
