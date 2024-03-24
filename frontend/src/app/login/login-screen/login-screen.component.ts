import { Component, Input } from '@angular/core';
import { FormControl, FormGroup, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AutenticazioneService } from '../../services/autenticazione.service';
import { NgIf } from '@angular/common';


@Component({
  selector: 'app-login-screen',
  standalone: true,
  imports: [FormsModule, ReactiveFormsModule, NgIf],
  templateUrl: './login-screen.component.html',
  styleUrl: './login-screen.component.scss'
})
export class LoginScreenComponent {

  constructor(private auth: AutenticazioneService) { }
  @Input() public isRegister: boolean = true;
  formgroup: FormGroup = new FormGroup(
    {
      username: new FormControl(''),
      email: new FormControl(''),
      password: new FormControl('')
    }
  )


  public submit() {

    if (this.isRegister) {
      console.log(this.formgroup.value);
      this.auth.registrazione(this.formgroup.value).subscribe(
        (data) => {

          this.auth.setLogin(data.token)

          console.log("registrazione avvenuta" + this.auth.getToken());
        });
    } else {
      console.log(this.formgroup.value);
      this.auth.login(this.formgroup.value).subscribe(
        (data) => {

          this.auth.setLogin(data.token)
          console.log("login avvenuto" + this.auth.getLogin());
        });
    }
  }

} 