import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { DisegnoCompleto } from '../interfaces/DisegnoMinimo';

@Injectable({
  providedIn: 'root'
})
export class DisegnoServiceService {

  constructor(private http: HttpClient) { }

  public getDisegni(): Observable<DisegnoCompleto[]> {
    return this.http.get<DisegnoCompleto[]>('/api/v0/utente/disegni/1')
  }
}
