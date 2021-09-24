import { Component } from "@angular/core";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import {
  AlertController,
  IonicPage,
  NavController,
  NavParams,
} from "ionic-angular";
import { CidadeDTO } from "../../models/cidade.dto";
import { EstadoDTO } from "../../models/estado.dto";
import { CidadeService } from "../../services/domain/cidade.service";
import { ClienteService } from "../../services/domain/cliente.service";
import { EstadoService } from "../../services/domain/estado.service";

@IonicPage()
@Component({
  selector: "page-signup",
  templateUrl: "signup.html",
})
export class SignupPage {
  formGroup: FormGroup;
  estados: EstadoDTO[];
  cidades: CidadeDTO[];

  constructor(
    public navCtrl: NavController,
    public navParams: NavParams,
    public formBuilder: FormBuilder,
    public cidadeService: CidadeService,
    public estadoService: EstadoService,
    public clienteService: ClienteService,
    public alertController: AlertController
  ) {
    this.formGroup = this.formBuilder.group({
      nome: [
        "Analu Baggio",
        [
          Validators.required,
          Validators.minLength(5),
          Validators.maxLength(120),
        ],
      ],
      email: ["analu@gmail.com", [Validators.required, Validators.email]],
      tipo: ["1", [Validators.required]],
      cpfOuCnpj: [
        "06134596280",
        [
          Validators.required,
          Validators.minLength(11),
          Validators.maxLength(14),
        ],
      ],
      senha: ["123", [Validators.required]],
      logradouro: ["Rua XV de novembro", [Validators.required]],
      numero: ["36", [Validators.required]],
      complemento: ["Casa", []],
      bairro: ["Margarida Galvan", []],
      cep: ["85660000", [Validators.required]],
      telefone1: ["977261827", [Validators.required]],
      telefone2: ["", []],
      telefone3: ["", []],
      estadoId: [null, [Validators.required]],
      cidadeId: [null, [Validators.required]],
    });
  }

  ionViewDidLoad() {
    this.estadoService.findAll().subscribe(
      (response) => {
        this.estados = response;
        this.formGroup.controls.estadoId.setValue(this.estados[0].id);
        this.updateCidades();
      },
      (error) => {}
    );
  }

  updateCidades() {
    let estadoId = this.formGroup.value.estadoId;
    this.cidadeService.findAll(estadoId).subscribe(
      (response) => {
        this.cidades = response;
        this.formGroup.controls.cidadeId.setValue(null);
      },
      (error) => {}
    );
  }

  signupUser() {
    this.clienteService.insert(this.formGroup.value).subscribe(
      (response) => {
        this.showInsertOk();
      },
      (error) => {}
    );
  }

  showInsertOk() {
    let alert = this.alertController.create({
      title: "Sucesso",
      message: "Cadastro efetuado com sucesso!",
      enableBackdropDismiss: false,
      buttons: [
        {
          text: "Ok",
          handler: () => {
            this.navCtrl.pop();
          },
        },
      ],
    });
    alert.present();
  }
}
