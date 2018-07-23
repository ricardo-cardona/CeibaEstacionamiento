import { Component, OnInit } from '@angular/core';

import { ParqueaderoService } from '../../servicios/parqueadero.service';
import { VehiculoParqueado } from '../../objetos/vehiculoParqueado';

@Component({
  selector: 'app-consulta',
  templateUrl: './consulta.component.html',
  styleUrls: ['./consulta.component.css']
})
export class ConsultaComponent implements OnInit {

  vehiculosParqueados: VehiculoParqueado[];

  salidaUrl: string = '/salidas';

  constructor(private parqueaderoService: ParqueaderoService) { }

  ngOnInit() {
    this.getVehiculosParqueados();
  }

  getVehiculosParqueados(): void {
    this.parqueaderoService.getVehiculosParqueados()
      .subscribe(vehiculosParqueados => this.vehiculosParqueados = vehiculosParqueados);
  }

}
