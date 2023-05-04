import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {ClientFormComponent} from './client-form/client-form.component';
import {ClientListComponent} from './client-list/client-list.component';
import {TabMenuModule} from "primeng/tabmenu";
import {RouterModule} from "@angular/router";
import {ButtonModule} from "primeng/button";
import {HttpClientModule} from "@angular/common/http";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {FormsModule} from "@angular/forms";
import {TableModule} from "primeng/table";
import {MenubarModule} from "primeng/menubar";
import {ChipsModule} from "primeng/chips";
import {InputMaskModule} from "primeng/inputmask";
import { cnpj } from 'cpf-cnpj-validator';

@NgModule({
  declarations: [
    AppComponent,
    ClientFormComponent,
    ClientListComponent
  ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        TabMenuModule,
        RouterModule,
        ButtonModule,
        HttpClientModule,
        TableModule,
        BrowserAnimationsModule,
        FormsModule,
        MenubarModule,
        ChipsModule,
        InputMaskModule
    ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
