import { MatFormFieldModule, MatNativeDateModule, MatCheckboxModule, MatDatepickerModule, MatPaginatorModule, MatSortModule, MatCell } from '@angular/material';

import { CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { AppComponent } from './app.component';
import { ProizvodjacComponent } from './components/proizvodjac/proizvodjac.component';
import { ProizvodComponent } from './components/proizvod/proizvod.component';
import { RacunComponent } from './components/racun/racun.component';
import { StavkaRacunaComponent } from './components/stavka-racuna/stavka-racuna.component';
import { AboutComponent } from './components/core/about/about.component';
import { AuthorComponent } from './components/core/author/author.component';
import { HomeComponent } from './components/core/home/home.component';

import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import {BrowserAnimationsModule} from '@angular/platform-browser/animations';

import { MatButtonModule,
          MatIconModule,
          MatSidenavModule,
          MatListModule,
          MatExpansionModule,
          MatGridListModule,
          MatTableModule,
          MatToolbarModule,
          MatSelectModule,
          MatOptionModule,
          MatDialogModule,
          MatInputModule,
          MatSnackBarModule}
from '@angular/material';

import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';


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
  declarations: [
    AppComponent,
    ProizvodjacComponent,
    ProizvodComponent,
    RacunComponent,
    StavkaRacunaComponent,
    HomeComponent,
    AuthorComponent,
    AboutComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    RouterModule.forRoot(Routes),
    MatButtonModule,
    MatIconModule,
    MatSidenavModule,
    MatListModule,
    MatToolbarModule,
    MatFormFieldModule,
    MatGridListModule,
    MatExpansionModule,
    MatTableModule,
    MatSelectModule,
    MatOptionModule,
    HttpClientModule,
    MatSnackBarModule,
    MatDialogModule,
    MatInputModule,
    MatNativeDateModule,
    MatCheckboxModule,
    MatDatepickerModule,
    MatPaginatorModule,
    MatSortModule,
    FormsModule
  ],
  schemas:[CUSTOM_ELEMENTS_SCHEMA],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
