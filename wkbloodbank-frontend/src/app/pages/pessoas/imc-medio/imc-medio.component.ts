import { Component, OnInit } from '@angular/core';
import { IImcPorFaixaEtariaModel } from 'src/app/models/pessoa-models/imc-por-faixa-etaria-model';
import { PessoaService } from 'src/app/services/pessoa.service';

@Component({
  selector: 'app-imc-medio',
  templateUrl: './imc-medio.component.html',
  styleUrls: ['./imc-medio.component.css']
})
export class ImcMedioComponent implements OnInit {

  imcPorFaixaEtaria: IImcPorFaixaEtariaModel[] = [];

  constructor(private pessoaService: PessoaService) { }

  ngOnInit(): void {
    this.buscarImcPorFaixaEtaria();
  }

  buscarImcPorFaixaEtaria(){
    this.pessoaService.getImcPorFaixaEtaria().subscribe((result) => {
      this.imcPorFaixaEtaria = result;
    }, error => console.error(error));
  }

}
