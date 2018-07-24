import { Component, OnInit, Input } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

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

  msgError: string;

  constructor(
      private route: ActivatedRoute
    , private router: Router
    , private parqueaderoService: ParqueaderoService
    , private modalService: NgbModal
  ) { }

  ngOnInit() {
    this.getId();
  }

  getId(): void {
    this.id = +this.route.snapshot.paramMap.get('id');
    this.getIngreso();
  }

  getIngreso(): void {
    this.parqueaderoService.getIngreso(this.id)
      .subscribe(parqueado => this.parqueado = parqueado);
      
  }

  registrarSalida(): void {
    this.parqueaderoService.registrarSalida(this.id)
      .subscribe(
          salida => this.salida = salida
        , err => {
          this.msgError = err.error.message;
        }
      );
  }

  mostrarModal(content): void {
    var modal = this.modalService.open(content, { centered: true });
    modal.result.then(
      () => this.redirigir(),
      () => this.redirigir()
    );
  }

  redirigir(): void {
    this.router.navigate(['/']);
    this.refrescar();
  }

  refrescar(): void {
    window.location.reload();
  }

}
