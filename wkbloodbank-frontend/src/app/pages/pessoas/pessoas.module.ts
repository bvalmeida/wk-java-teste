import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { PessoasRoutingModule } from './pessoas-routing.module';
import { SharedModule } from 'src/app/shared/shared.module';
import { PorEstadoComponent } from './por-estado/por-estado.component';
import { ImcMedioComponent } from './imc-medio/imc-medio.component';
import { PercentualObesosComponent } from './percentual-obesos/percentual-obesos.component';
import { IdadeMediaComponent } from './idade-media/idade-media.component';
import { PossiveisDoadoresComponent } from './possiveis-doadores/possiveis-doadores.component';


@NgModule({
  declarations: [
    PorEstadoComponent,
    ImcMedioComponent,
    PercentualObesosComponent,
    IdadeMediaComponent,
    PossiveisDoadoresComponent
  ],
  imports: [
    CommonModule,
    PessoasRoutingModule,
    SharedModule
  ]
})
export class PessoasModule { }
