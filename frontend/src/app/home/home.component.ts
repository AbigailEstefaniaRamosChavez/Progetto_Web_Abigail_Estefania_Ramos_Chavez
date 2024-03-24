import { Component } from '@angular/core';
import { DisegnoServiceService } from '../services/disegno-service.service';
import { Disegno } from '../interfaces/Disegno';
import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [MatCardModule, MatButtonModule, CommonModule],
  templateUrl: './home.component.html',
  styleUrl: './home.component.scss'
})
export class HomeComponent {



  constructor() {
  }
}