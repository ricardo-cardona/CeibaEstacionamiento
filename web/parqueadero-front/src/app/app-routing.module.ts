import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { SalidaComponent } from './componentes/salida/salida.component';

const routes: Routes = [
  { path: 'salidas/:id', component: SalidaComponent }
];

@NgModule({
  imports: [
    RouterModule.forRoot(routes)
  ],
  exports: [ RouterModule ]
})
export class AppRoutingModule { }
