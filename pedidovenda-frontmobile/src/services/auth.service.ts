import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";

import { JwtHelperService } from "@auth0/angular-jwt";

import { LocalUser } from "./../models/local_user";
import { API_CONFIG } from "../config/api.config";
import { CredenciaisDTO } from "../models/credenciais.dto";
import { StorageService } from "./storage.service";

@Injectable()
export class AuthService {
  jwtHelperService: JwtHelperService = new JwtHelperService();

  constructor(public http: HttpClient, public storage: StorageService) {}

  authenticate(credenciais: CredenciaisDTO) {
    return this.http.post(`${API_CONFIG.baseUrl}/login`, credenciais, {
      observe: "response",
      responseType: "text",
    });
  }

  refreshToken() {
    return this.http.post(
      `${API_CONFIG.baseUrl}/auth/refresh_token`,
      {},
      {
        observe: "response",
        responseType: "text",
      }
    );
  }

  successfulLogin(authorizationValue: string) {
    let token = authorizationValue.substring(7);
    let email = this.jwtHelperService.decodeToken(token).sub;

    let user: LocalUser = {
      token,
      email,
    };
    this.storage.setLocalUser(user);
  }

  logout() {
    this.storage.setLocalUser(null);
  }
}
