import { Component, OnInit } from '@angular/core';
import { IPorEstadoModel } from 'src/app/models/pessoa-models/por-estado-model';
import { PessoaService } from 'src/app/services/pessoa.service';

@Component({
  selector: 'app-por-estado',
  templateUrl: './por-estado.component.html',
  styleUrls: ['./por-estado.component.css']
})
export class PorEstadoComponent implements OnInit {

  pessoasPorEstado: IPorEstadoModel[] = [];

  constructor(private pessoaService: PessoaService) { }

  ngOnInit(): void {
    this.buscarPessoasPorEstado();
  }

  buscarPessoasPorEstado(){
    this.pessoaService.getQuantidadePorEstado().subscribe((result) => {
      this.pessoasPorEstado = result;
    }, error =>{
      console.error('Error ao buscar Pessoas por Estado', error);
    });
  }

}
