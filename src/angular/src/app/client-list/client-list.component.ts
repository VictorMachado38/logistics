import {Component, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Router} from "@angular/router";
import {ConfirmationService, MessageService} from "primeng/api";

@Component({
    selector: 'app-client-list',
    templateUrl: './client-list.component.html',
    styleUrls: ['./client-list.component.scss']
})

export class ClientListComponent implements OnInit {
    clientes: any [] = [];
    items: any [] = [];


    constructor(private _messageService: MessageService, private _confirmationService: ConfirmationService, private _http: HttpClient, private _router: Router) {
    }

    ngOnInit(): void {
        this.getClientes(" http://localhost:8081/client/list");
    }

    getClientes(url: any) {
        this._http.get(`${url}`)
            .subscribe({
                next: (v: any) => {
                    this.clientes = v.data;
                },
                error: (err) => {
                    // this.erros.push(nome);
                },
            });


        this.items = [
            {
                codigo: 1,
                cnpj: "12345678901234",
                nome: "Empresa A",
                cidade: "São Paulo",
                estado: "SP",
                setor: "Tecnologia",
            },
            {
                codigo: 2,
                cnpj: "98765432109876",
                nome: "Empresa B",
                cidade: "Rio de Janeiro",
                estado: "RJ",
                setor: "Serviços",
            },
            {
                codigo: 3,
                cnpj: "45678901234567",
                nome: "Empresa C",
                cidade: "Belo Horizonte",
                estado: "MG",
                setor: "Indústria",
            },
        ];


    }

    mascaraCPNJ(cnpj: any): string {
        return cnpj.replace(
            /^(\d{2})(\d{3})(\d{3})(\d{4})(\d{2})$/,
            "$1.$2.$3/$4-$5"
        );
    }

    openClient(client: any) {
        this._router.navigate(['/form'], {queryParams: client});

    }

    editarCliente(clienteId: number) {
        this._router.navigate(['/form', clienteId, 'edicao']);
    }

    confirm1() {

    }

    confirm2() {

    }

    delete(client: any) {
        this._confirmationService.confirm({
            message: 'Você tem certeza que quer remover o cliente' + client.nome.toUpperCase() + '?',
            header: 'Remover Cliente',
            icon: 'pi pi-exclamation-triangle',
            accept: () => {
                this.deleteClient(client.clienteId);
                this._messageService.add({severity: 'info', summary: 'Confirmed', detail: 'You have accepted'});
            },
            reject: () => {

            }
        });

    }

    private deleteClient(clienteId: any) {
        this._http.delete(`http://localhost:8081/client/${clienteId}`)
            .subscribe({
                next: (v: any) => {
                    this.getClientes(" http://localhost:8081/client/list");
                },
                error: (err) => {
                    // this.erros.push(nome);
                },
            });

    }
}
