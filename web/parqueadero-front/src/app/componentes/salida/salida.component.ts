import { Component, OnInit, Input } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ParqueaderoService } from '../../servicios/parqueadero.service';
import { VehiculoParqueado } from '../../objetos/vehiculoParqueado';
import { Salida } from '../../objetos/salida';

@Component({
  selector: 'app-salida',
  templateUrl: './salida.component.html',
  styleUrls: ['./salida.component.css']
})
export class SalidaComponent implements OnInit {

  @Input() id: number;

  parqueado: VehiculoParqueado;
  salida: Salida;

  constructor(
      private route: ActivatedRoute
    , private parqueaderoService: ParqueaderoService
  ) { }

  ngOnInit() {
    this.getId();
  }

  getId(): void {
    this.id = +this.route.snapshot.paramMap.get('id');
    //aqui se podria obtener la informacion del
    //ingreso (tipo vehiculo y fecha inicio) a partir de su id
    alert('Id: ' + this.id);
    this.getIngreso();
  }

  getIngreso(): void {
    //debugger
    this.parqueaderoService.getIngreso(this.id)
      .subscribe(parqueado => this.parqueado = parqueado);
      
  }

  registrarSalida(): void {
    alert('Id: ' + this.id);
    this.parqueaderoService.registrarSalida(this.id)
      .subscribe(salida => alert(
        'Valor a pagar: ' + salida.valor
      ));
      //.subscribe(salida => this.salida = salida);
    //alert('Id Salida: ' + this.salida.id);
  }

}
