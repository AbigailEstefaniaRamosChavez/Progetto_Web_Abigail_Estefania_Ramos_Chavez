import { Component } from '@angular/core';

import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import { CommonModule, NgFor } from '@angular/common';
import { Disegno } from '../../interfaces/Disegno';
import { DisegnoServiceService } from '../../services/disegno-service.service';

@Component({
  selector: 'app-disegni',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './disegni.component.html',
  styleUrl: './disegni.component.scss'
})
export class DisegniComponent {

  disegni: Disegno[] = [];

  constructor(disegnoService: DisegnoServiceService) {
    disegnoService.getDisegni().subscribe(
      disegni => {
        console.log(disegni);
        this.disegni = disegni;
      });
  }
}
