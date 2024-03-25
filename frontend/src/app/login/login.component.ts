import { Component } from '@angular/core';
import { FormControl, FormGroup, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AutenticazioneService } from '../services/autenticazione.service';
import { HttpResponse } from '@angular/common/http';
import e from 'express';
import { LoginScreenComponent } from './login-screen/login-screen.component';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [LoginScreenComponent, CommonModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss'
})
export class LoginComponent {
  toggleRegister() {
    this.isRegister = !this.isRegister;
  }

  constructor(public auth: AutenticazioneService) { }
  isRegister: boolean = false;

  public logout() {
    this.auth.logout();
  }
} 