import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { IPessoaModel } from '../models/pessoa-model';

@Injectable({
  providedIn: 'root',
})
export class PessoaService {
  private apiUrl = environment.apiUrl + 'api/v1/blood-bank/pessoas';

  constructor(private httpClient:HttpClient) {}

  getPessoasByPage(page: number): Observable<any>{
    const url = `${this.apiUrl}/buscar-por-filtros-page?page=${page}&size=10`;
    return this.httpClient.post(url, {})
  }
}
