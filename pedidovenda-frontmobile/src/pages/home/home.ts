import { Component } from "@angular/core";
import { IonicPage, MenuController, NavController } from "ionic-angular";
import { CredenciaisDTO } from "../../models/credenciais.dto";
import { AuthService } from "../../services/auth.service";

@IonicPage()
@Component({
  selector: "page-home",
  templateUrl: "home.html",
})
export class HomePage {
  credenciais: CredenciaisDTO = {
    email: "rodrigobaggio.precisa@gmail.com",
    senha: "123456",
  };

  constructor(
    public navCtrl: NavController,
    public menu: MenuController,
    public auth: AuthService
  ) {}

  login() {
    this.auth.authenticate(this.credenciais).subscribe(
      (response) => {
        this.auth.successfulLogin(response.headers.get("Authorization"));
        this.navCtrl.setRoot("CategoriasPage");
      },
      (error) => {}
    );
  }

  signup() {
    this.navCtrl.push("SignupPage");
  }

  ionViewDidEnter() {
    this.auth.refreshToken().subscribe(
      (response) => {
        this.auth.successfulLogin(response.headers.get("Authorization"));
        this.navCtrl.setRoot("CategoriasPage");
      },
      (error) => {}
    );
  }

  ionViewWillEnter() {
    this.menu.swipeEnable(false);
  }
  ionViewDidLeave() {
    this.menu.swipeEnable(true);
  }
}
