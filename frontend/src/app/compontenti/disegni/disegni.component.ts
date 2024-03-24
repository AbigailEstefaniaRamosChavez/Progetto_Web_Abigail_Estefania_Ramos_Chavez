import { Component } from '@angular/core';

import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import { CommonModule, NgFor } from '@angular/common';
import { Disegno } from '../../interfaces/Disegno';
import { DisegnoServiceService } from '../../services/disegno-service.service';
import { DomSanitizer, SafeUrl } from '@angular/platform-browser';

@Component({
  selector: 'app-disegni',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './disegni.component.html',
  styleUrl: './disegni.component.scss'
})
export class DisegniComponent {

  disegni: Disegno[] = [];

  constructor(private disegnoService: DisegnoServiceService, private santizer: DomSanitizer) {
    disegnoService.getDisegni().subscribe(
      disegni => {
        console.log(disegni);
        this.disegni = disegni;
        this.loadImage(4)
      });
  }

  imgUrl: SafeUrl = '';

  loadImage(id: number) {
    this.disegnoService.getImg(id).subscribe(blob => {
      let objectURL = URL.createObjectURL(blob);
      console.log(objectURL);
      this.imgUrl = this.santizer.bypassSecurityTrustUrl(objectURL);
    });
  }
}
