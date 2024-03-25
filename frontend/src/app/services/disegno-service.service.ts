import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Disegno } from '../interfaces/Disegno';
import { AutenticazioneService } from './autenticazione.service';

@Injectable({
  providedIn: 'root'
})
export class DisegnoServiceService {

  constructor(private http: HttpClient, private auth: AutenticazioneService) { }

  public getDisegni(): Observable<Disegno[]> {

    return this.http.get<Disegno[]>('/api/v0/utente/disegni', { headers: { Authorization: `Bearer ${this.auth.getToken()}` } })
  }

  public aggiungiDisegno(disegno: FormData, titolo : string, descrizione : string): Observable<Disegno> {

    let param = new HttpParams().append('titolo', titolo).append("descrizione", descrizione);

    return this.http.post<Disegno>('/api/v0/disegno/creoDisegno/request', disegno, { headers: { Authorization: `Bearer ${this.auth.getToken()}` }, params: param })
  }
  public getImg(id: number): Observable<any>  {
    return this.http.get(`/api/v0/disegno/img/${id}`, { headers: { Authorization: `Bearer ${this.auth.getToken()}` }, responseType: 'blob' });
  }
}