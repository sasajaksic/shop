import { AuthorComponent } from './components/core/author/author.component';
import { AboutComponent } from './components/core/about/about.component';
import { HomeComponent } from './components/core/home/home.component';
import { StavkaRacunaComponent } from './components/stavka-racuna/stavka-racuna.component';
import { RacunComponent } from './components/racun/racun.component';
import { ProizvodjacComponent } from './components/proizvodjac/proizvodjac.component';
import { ProizvodComponent } from './components/proizvod/proizvod.component';
import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

const Routes = [
  { path: 'proizvod', component: ProizvodComponent },
  { path: 'proizvodjac', component: ProizvodjacComponent },
  { path: 'racun', component: RacunComponent },
  { path: 'home', component: HomeComponent },
  { path: 'about', component: AboutComponent },
  { path: 'author', component: AuthorComponent },
  { path: '',   redirectTo: '/home', pathMatch: 'full' },
];

@NgModule({
  imports: [RouterModule.forRoot(Routes)],
  exports: [RouterModule]
})

export class AppRoutingModule {}
