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
  file: File | undefined;
  fileSelezionato(e: Event) {
    if (e.target instanceof HTMLInputElement && e.target.files && e.target.files.length > 0) {
      this.file = e.target.files[0];

    }
  }

  constructor(private disegniservice: DisegnoServiceService) { }
  submit() {
    let formData = new FormData();
    formData.append('file', this.file as Blob);
    formData.append('titolo', this.form.value.titolo);
    formData.append('descrizione', this.form.value.descrizione);
    this.disegniservice.aggiungiDisegno(formData).subscribe(
      (data) => {
        console.log(data);
      });
  }

  form: FormGroup = new FormGroup({
    titolo: new FormControl(''),
    descrizione: new FormControl(''),
  });
}
