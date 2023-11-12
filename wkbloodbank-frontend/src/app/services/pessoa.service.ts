import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { IPorEstadoModel } from '../models/pessoa-models/por-estado-model';
import { IImcPorFaixaEtariaModel } from '../models/pessoa-models/imc-por-faixa-etaria-model';
import { IPercentualObesosModel } from '../models/pessoa-models/percentual-obesos-model';
import { IIdadeMediaPorTipoSangueModel } from '../models/pessoa-models/idade-media-por-tipo-sangue-model';
import { IPossiveisDoadoresPorTipoSangue } from '../models/pessoa-models/possiveis-doadores-tipo-sanguineo-model';

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

  getQuantidadePorEstado(): Observable<IPorEstadoModel[]>{
    const url = `${this.apiUrl}/buscar-quantidade-por-estado`;
    return this.httpClient.get<IPorEstadoModel[]>(url);
  }

  getImcPorFaixaEtaria(): Observable<IImcPorFaixaEtariaModel[]>{
    const url = `${this.apiUrl}/imc-medio-por-faixa-idade`;
    return this.httpClient.get<IImcPorFaixaEtariaModel[]>(url);
  }

  getPercentualObesosPorSexo(): Observable<IPercentualObesosModel[]>{
    const url = `${this.apiUrl}/percentual-obesos-por-sexo`;
    return this.httpClient.get<IPercentualObesosModel[]>(url);
  }

  getIdadeMediaPorTipoSanguineo(): Observable<IIdadeMediaPorTipoSangueModel[]>{
    const url = `${this.apiUrl}/media-idade-por-tipo-sangue`;
    return this.httpClient.get<IIdadeMediaPorTipoSangueModel[]>(url);
  }

  getPossiveisDoadoresPorTipoSanguineo():Observable<IPossiveisDoadoresPorTipoSangue[]>{
    const url = `${this.apiUrl}/quantidade-possiveis-doadores-por-tipo-sangue`;
    return this.httpClient.get<IPossiveisDoadoresPorTipoSangue[]>(url);
  }
}
