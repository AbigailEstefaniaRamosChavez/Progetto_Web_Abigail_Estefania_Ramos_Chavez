import { Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { authGuard } from './autenticazione/auth.guard';
import { AreaPersonaleComponent } from './compontenti/area-personale/area-personale.component';
import { AggiungiDisegniComponent } from './compontenti/aggiungi-disegni/aggiungi-disegni.component';

export const routes: Routes = [
    { path: '', redirectTo: 'home', pathMatch: 'full' },
    { path: 'home', component: HomeComponent },
    { path: 'login', component: LoginComponent },
    { path: 'areaPrivata', component: AreaPersonaleComponent, canActivate: [authGuard] },
    { path: 'areaPrivata/aggiungi', component: AggiungiDisegniComponent, canActivate: [authGuard] },
    // { path: '**', redirectTo: 'home' }//da fare una pagine di rotta non trovata
];
