import { Component } from '@angular/core';
import { DisegniComponent } from '../disegni/disegni.component';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-area-personale',
  standalone: true,
  imports: [DisegniComponent, RouterLink],
  templateUrl: './area-personale.component.html',
  styleUrl: './area-personale.component.scss'
})
export class AreaPersonaleComponent {

}
