import { Component, OnInit } from '@angular/core';

import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

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
  msgSuccess: string = 'El ingreso al parqueadero se ha registrado exitosamente.';
  msgError: string;
  
  constructor(
      private parqueaderoService: ParqueaderoService
    , private modalService: NgbModal
  ) { }

  ngOnInit() {
    this.getTiposVehiculo();
  }

  getTiposVehiculo(): void {
    this.parqueaderoService.getTiposVehiculo()
      .subscribe(tiposVehiculo => this.tiposVehiculo = tiposVehiculo);
  }

  registrarIngreso(placa: string, cilindraje: number): void {
    
    var vehiculo: Vehiculo = {
        id: 0
      , placa: placa
      , tipoVehiculo: (this.tipoSeleccionado.id ? this.tipoSeleccionado : this.tiposVehiculo[0])
      , cilindraje: (this.tipoSeleccionado.tieneCilindraje ? cilindraje : 0)
    };
    
    this.parqueaderoService.registrarIngreso(vehiculo)
      .subscribe(
          data => console.log(data)
        , err => this.msgError = err.error.message
      );

  }

  mostrarModal(content): void {
    var modal = this.modalService.open(content, { centered: true });
    modal.result.then(
      () => this.refrescar(),
      () => this.refrescar()
    );
  }

  refrescar(): void {
    window.location.reload();
  }

  onSelect(tipoEscogido: string): void {
    this.tipoSeleccionado = this.tiposVehiculo
      .find(tipo => tipo.id == +tipoEscogido);
  }

}
