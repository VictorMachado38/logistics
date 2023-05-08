import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {ClientFormComponent} from "./client-form/client-form.component";
import {ClientListComponent} from "./client-list/client-list.component";
import {InitComponent} from "./init/init.component";

const routes: Routes = [
  {path:'',component: InitComponent  ,  pathMatch: 'full'},
  {path:'*',component: InitComponent },
  {path:'form',component: ClientFormComponent},
  {path:'list',component: ClientListComponent},
  {path:'init',component: InitComponent}
  // {path:'develop',component: ViewVersionDevComponent}
];


@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
