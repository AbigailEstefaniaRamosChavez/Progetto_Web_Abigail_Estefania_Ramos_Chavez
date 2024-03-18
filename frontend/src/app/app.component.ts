import { Component, NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterOutlet } from '@angular/router';
import { DisegnoServiceService } from './services/disegno-service.service';
import { DisegnoCompleto } from './interfaces/DisegnoMinimo';
import { DisegnoMinimo } from './interfaces/DisegnoMinimo';
import { MatSlideToggleModule } from '@angular/material/slide-toggle';


@Component({

  selector: 'app-root',
  standalone: true,
  imports: [CommonModule, RouterOutlet,MatSlideToggleModule],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})

export class AppComponent {

  disegno= [
    {nome: "Rossi",
    data: "12-04-2024",
    descizione: "Un cane che si affaccia dalla finestra"}
    ]

  title = 'frontend';
  disegni: DisegnoCompleto[] = [];
  constructor(disegnoService: DisegnoServiceService) {
    disegnoService.getDisegni().subscribe(
      disegni => {
        console.log(disegni);
        this.disegni = disegni;
      });
  }
}