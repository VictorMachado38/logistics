import {Component, OnInit} from '@angular/core';
import {ClientDTO} from "../../model/dto/client.dto";
import {HttpClient} from "@angular/common/http";
import { cpf } from 'cpf-cnpj-validator';
import { cnpj } from 'cpf-cnpj-validator';
import {FormControl, FormGroup, Validators} from "@angular/forms";


@Component({
    selector: 'app-client-form',
    templateUrl: './client-form.component.html',
    styleUrls: ['./client-form.component.scss']
})
export class ClientFormComponent implements OnInit {


    constructor(private _http: HttpClient) {

    }

    cliete = new ClientDTO();
    invalName: boolean = false;
    invalCnpj: boolean = false;

    ngOnInit(): void {
        this.cli = new FormGroup({
            name: new FormControl(this.cliete.nome, [
                Validators.required,
                Validators.minLength(4),
                forbiddenNameValidator(/bob/i) // <-- Here's how you pass in the custom validator.
            ]),
            alterEgo: new FormControl(this.hero.alterEgo),
            power: new FormControl(this.hero.power, Validators.required)
        });

    }

    sendForm() {
        // if()
        if(this.cliete.cnpj.length < 14){

        }

        this._http.post(`http://localhost:8080/client/save`, this.cliete).subscribe({
            next: (v) => {
                console.log(v);
            },
            error: (err) => {
                // this.erros.push(nome);
            },
        });


    }
}
