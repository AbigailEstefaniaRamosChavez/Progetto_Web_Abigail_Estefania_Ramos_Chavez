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
    formData.append('file', this.file as File, (this.file as File).name);
    this.disegniservice.aggiungiDisegno(formData, this.form.value.titolo, this.form.value.descrizione).subscribe(
      (data) => {
        console.log(data);
        alert("Disegno inserito.");
      });
  }

  form: FormGroup = new FormGroup({
    titolo: new FormControl(''),
    descrizione: new FormControl(''),
  });
}
