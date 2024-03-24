import { HttpClient, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { JwtResponse } from '../interfaces/JwtResponse';

@Injectable({
  providedIn: 'root'
})
export class AutenticazioneService {

  isLogin: boolean = false;
  token: string = '';
  constructor(private http: HttpClient) {
    this.token = window.localStorage.getItem('token') ?? '';
    this.isLogin = this.token !== '';
  }

  public getLogin() {
    return this.isLogin;
  }

  setLogin(token: string) {
    this.isLogin = true;
    this.token = token;
    window.localStorage.setItem('token', token);
  }

  public getToken() {
    return this.token;
  }

  public login(form: any) {
    return this.http.post<JwtResponse>('/api/v0/autenticazione/login', form)
  }
  public registrazione(form: any) {
    return this.http.post<JwtResponse>('/api/v0/autenticazione/register', form)
  }
}
