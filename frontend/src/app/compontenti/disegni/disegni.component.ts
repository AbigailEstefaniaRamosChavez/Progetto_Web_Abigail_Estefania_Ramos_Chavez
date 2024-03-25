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

  constructor(private disegnoService: DisegnoServiceService, private sanitizer: DomSanitizer) {
    disegnoService.getDisegni().subscribe(
      disegni => {
        console.log(disegni);
        this.disegni = disegni;
        disegni.forEach( d => this.loadImage(d));
      });
  }

  loadImage(dis: Disegno) {
    this.disegnoService.getImg(dis.id).subscribe(
      (res: any) => {
        console.log(res);
        const url = window.URL.createObjectURL(res);
        dis.url = this.sanitizer.bypassSecurityTrustUrl(url);
      }
    );
  }
}
