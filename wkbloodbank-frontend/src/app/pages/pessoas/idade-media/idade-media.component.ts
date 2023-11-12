import { Component, OnInit } from '@angular/core';
import { IIdadeMediaPorTipoSangueModel } from 'src/app/models/pessoa-models/idade-media-por-tipo-sangue-model';
import { PessoaService } from 'src/app/services/pessoa.service';

@Component({
  selector: 'app-idade-media',
  templateUrl: './idade-media.component.html',
  styleUrls: ['./idade-media.component.css'],
})
export class IdadeMediaComponent implements OnInit {
  idadeMediaPorTipoSanguineo: IIdadeMediaPorTipoSangueModel[] = [];

  constructor(private pessoaService: PessoaService) {}

  ngOnInit(): void {
    this.buscarIdadeMediaPorTipoSanguineo();
  }

  buscarIdadeMediaPorTipoSanguineo(){
    this.pessoaService.getIdadeMediaPorTipoSanguineo().subscribe((result) => {
      this.idadeMediaPorTipoSanguineo = result;
    }, error => console.error(error));
  }
}
