import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {ClientFormComponent} from "./client-form/client-form.component";
import {ClientListComponent} from "./client-list/client-list.component";

const routes: Routes = [
  {path:'', redirectTo: 'evaluation', pathMatch: 'full'  },
  {path:'form',component: ClientFormComponent},
  {path:'list',component: ClientListComponent}
  // {path:'develop',component: ViewVersionDevComponent}
];


@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
