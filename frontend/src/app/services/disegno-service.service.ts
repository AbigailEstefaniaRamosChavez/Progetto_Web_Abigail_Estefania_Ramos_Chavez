import { HttpClient } from '@angular/common/http';
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

  public aggiungiDisegno(disegno: FormData): Observable<Disegno> {
    return this.http.post<Disegno>('/api/v0/disegno/creoDisegno/request', disegno, { headers: { Authorization: `Bearer ${this.auth.getToken()}` } })
  }
  public getImg(id: number): Observable<Blob> {
    return this.http.get<Blob>(`/api/v0/disegno/img/4`, { headers: { Authorization: `Bearer ${this.auth.getToken()}` } });
  }
}