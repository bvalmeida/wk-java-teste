import { Component, OnInit } from '@angular/core';
import { IPessoaModel } from 'src/app/models/pessoa-model';
import { PessoaService } from 'src/app/services/pessoa.service';

@Component({
  selector: 'app-pessoas',
  templateUrl: './pessoas.component.html',
  styleUrls: ['./pessoas.component.css'],
})
export class PessoasComponent implements OnInit {
  pessoas: IPessoaModel[] = [];
  totalPages: number = 0;
  currentPage: number = 0;

  constructor(private pessoaService: PessoaService) {}

  ngOnInit(): void {
    this.loadPessoas();
  }

  loadPessoas() {
    this.pessoaService.getPessoasByPage(this.currentPage).subscribe(
      (data: any) => {
        this.pessoas = data.content;
        this.totalPages = data.totalPages;
      },
      (error) => {
        console.error('Erro ao carregar as pessoas:', error);
      }
    );
  }

  onPageChange(pageNumber: number) {
    if (pageNumber >= 0 && pageNumber < this.totalPages) {
      this.currentPage = pageNumber;
      this.loadPessoas();
    }
  }

  getNumberArray(start: number, end: number): number[] {
    return new Array(end - start + 1).fill(0).map((_, i) => start + i);
  }

  getVisiblePages(): number[] {
    const visiblePages = 3;
    const startPage = Math.max(
      0,
      this.currentPage - Math.floor(visiblePages / 2)
    );
    const endPage = Math.min(this.totalPages - 1, startPage + visiblePages - 1);
    return this.getNumberArray(startPage, endPage);
  }
}
