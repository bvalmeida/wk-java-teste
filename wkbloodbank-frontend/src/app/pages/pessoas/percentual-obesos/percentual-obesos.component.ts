import { Component, OnInit } from '@angular/core';
import { IPercentualObesosModel } from 'src/app/models/pessoa-models/percentual-obesos-model';
import { PessoaService } from 'src/app/services/pessoa.service';

@Component({
  selector: 'app-percentual-obesos',
  templateUrl: './percentual-obesos.component.html',
  styleUrls: ['./percentual-obesos.component.css']
})
export class PercentualObesosComponent implements OnInit {

  percentualObesesPorSexo: IPercentualObesosModel[] = [];

  constructor(private pessoaService: PessoaService) { }

  ngOnInit(): void {
    this.buscarPercentualObesosPorSexo();
  }

  buscarPercentualObesosPorSexo(){
    this.pessoaService.getPercentualObesosPorSexo().subscribe((result) => {
      this.percentualObesesPorSexo = result;
    }, error => console.error(error));
  }

}
