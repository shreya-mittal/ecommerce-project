import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { JwtHelperService } from '@auth0/angular-jwt'
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})

export class AuthService {
  private currentUser: BehaviorSubject<any> = new BehaviorSubject(null);
  constructor(private http: HttpClient) { }
  baseServerUrl = 'http://localhost:8081/login';
  jwtHelperService = new JwtHelperService();

  loginUser(loginInfo: Array<string>) {

    return this.http.post(this.baseServerUrl, {

      email: loginInfo[0],

      password: loginInfo[1]

    },

      {

        responseType: 'text'

      }

    );

  }

  signupUser(data: any) {
    console.log(data);
    return this.http.post(`http://localhost:8081/register`, data);
  }

  setToken(token: string) {

    localStorage.setItem("acess_token", token);

  }



  loadCurrentUser() {

    const token = localStorage.getItem("acess_token");

    const userInfo = token != null ? this.jwtHelperService.decodeToken(token) : null;

    console.log(userInfo);

  }



  isLoggedIn(): boolean {

    return localStorage.getItem("acess_token") ? true : false;

  }



  removeToken() {

    localStorage.removeItem("acess_token");

  }

}