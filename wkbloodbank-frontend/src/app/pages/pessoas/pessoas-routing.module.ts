import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PessoasComponent } from './pessoas.component';
import { IdadeMediaComponent } from './idade-media/idade-media.component';
import { ImcMedioComponent } from './imc-medio/imc-medio.component';
import { PercentualObesosComponent } from './percentual-obesos/percentual-obesos.component';
import { PorEstadoComponent } from './por-estado/por-estado.component';
import { PossiveisDoadoresComponent } from './possiveis-doadores/possiveis-doadores.component';

const routes: Routes = [
  {
    path: '',
    component: PessoasComponent
  },
  {
    path: 'idade-media',
    component: IdadeMediaComponent
  },
  {
    path: 'imc-medio',
    component: ImcMedioComponent
  },
  {
    path: 'percentual-obesos',
    component: PercentualObesosComponent
  },
  {
    path: 'por-estado',
    component: PorEstadoComponent
  },
  {
    path: 'possiveis-doadores',
    component: PossiveisDoadoresComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PessoasRoutingModule { }
