import { HttpClient, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AutenticazioneService {

  isLogin: boolean = false;
  constructor(private http: HttpClient) {

  }

  public getLogin() {
    return this.isLogin;
  }

  setLogin(isLogged: boolean) {
    this.isLogin = isLogged;
  }

  public login(form: any) {
    return this.http.post('/api/v0/autenticazione/login', form)
  }
  public registrazione(form: any) {
    return this.http.post('/api/v0/autenticazione/register', form)
  }
}
