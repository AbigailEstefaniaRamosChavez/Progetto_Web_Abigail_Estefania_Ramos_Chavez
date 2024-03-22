import { Component } from '@angular/core';
import { FormControl, FormGroup, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { DisegnoServiceService } from '../../services/disegno-service.service';

@Component({
  selector: 'app-aggiungi-disegni',
  standalone: true,
  imports: [ReactiveFormsModule, FormsModule],
  templateUrl: './aggiungi-disegni.component.html',
  styleUrl: './aggiungi-disegni.component.scss'
})
export class AggiungiDisegniComponent {

  constructor(private disegniservice: DisegnoServiceService) { }
  submit() {
    this.form.value.utenteId = 1;
    console.log(this.form.value);
    this.disegniservice.aggiungiDisegno(this.form.value).subscribe(
      (data) => {
        console.log(data);
      });
  }

  form: FormGroup = new FormGroup({
    nome: new FormControl(''),
    descrizione: new FormControl(''),
    immagine: new FormControl(''),
  });
}
