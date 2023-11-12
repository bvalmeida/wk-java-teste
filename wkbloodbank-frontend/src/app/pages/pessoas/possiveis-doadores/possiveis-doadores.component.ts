import { Component, OnInit } from '@angular/core';
import { IPossiveisDoadoresPorTipoSangue } from 'src/app/models/pessoa-models/possiveis-doadores-tipo-sanguineo-model';
import { PessoaService } from 'src/app/services/pessoa.service';

@Component({
  selector: 'app-possiveis-doadores',
  templateUrl: './possiveis-doadores.component.html',
  styleUrls: ['./possiveis-doadores.component.css']
})
export class PossiveisDoadoresComponent implements OnInit {

  possiveisDoadoresPorTipoSanguineo: IPossiveisDoadoresPorTipoSangue[] = [];

  constructor(private pessoaService: PessoaService) { }

  ngOnInit(): void {
    this.buscarPossiveisDoadoresPorTipoSanguineo();
  }

  buscarPossiveisDoadoresPorTipoSanguineo(){
    this.pessoaService.getPossiveisDoadoresPorTipoSanguineo().subscribe((result) => {
      this.possiveisDoadoresPorTipoSanguineo = result;
    }, error => console.error(error));
  }

}
