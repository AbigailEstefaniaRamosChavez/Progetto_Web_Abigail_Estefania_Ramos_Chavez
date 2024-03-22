import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Disegno } from '../interfaces/Disegno';

@Injectable({
  providedIn: 'root'
})
export class DisegnoServiceService {

  constructor(private http: HttpClient) { }

  public getDisegni(): Observable<Disegno[]> {
    return this.http.get<Disegno[]>('/api/v0/utente/disegni/1')
  }

  public aggiungiDisegno(disegno: Disegno): Observable<Disegno> {
    return this.http.post<Disegno>('/api/v0/disegno/creoDisegno/request', disegno)
  }
}