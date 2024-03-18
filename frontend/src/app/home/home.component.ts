import { Component } from '@angular/core';
import { DisegnoServiceService } from '../services/disegno-service.service';
import { DisegnoCompleto } from '../interfaces/DisegnoMinimo';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [],
  templateUrl: './home.component.html',
  styleUrl: './home.component.scss'
})
export class HomeComponent {

  disegni: DisegnoCompleto[] = [];
  constructor(disegnoService: DisegnoServiceService) {
    disegnoService.getDisegni().subscribe(
      disegni => {
        console.log(disegni);
        this.disegni = disegni;
      });

  }
}
