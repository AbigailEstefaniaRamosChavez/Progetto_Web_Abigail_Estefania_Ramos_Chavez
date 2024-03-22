import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { AutenticazioneService } from '../services/autenticazione.service';

export const authGuard: CanActivateFn = (route, state) => {
  let isLogged = inject(AutenticazioneService).getLogin();

  if (isLogged) {
    return true;
  } else {
    //se non sei loggato allora ti reindirizzo alla pagina di login
    inject(Router).navigateByUrl('/login');
    return false;
  }

};
