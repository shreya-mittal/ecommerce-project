import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})

export class LoginComponent implements OnInit {
  errorMessage!: string | null;
  session: any;
  constructor(private loginAuth: AuthService, private router: Router) { }

  ngOnInit(): void {
  }

  loginForm = new FormGroup({
    Email: new FormControl("", [Validators.required, Validators.email]),
    Password: new FormControl("", [Validators.required, Validators.minLength(6), Validators.maxLength(15)])
  });

  isUserValid: boolean = false;


  loginSubmitted() {
    this.loginAuth.loginUser([this.loginForm.value.Email, this.loginForm.value.Password]).subscribe((res) => {
      this.isUserValid = true;
      this.loginAuth.setToken(res);
      let data = localStorage.getItem('acess_token');
      console.log(data);
      this.session = JSON.parse(data);
      console.log(this.session);
      let abc = this.session.email;
      console.log(abc);
      this.router.navigateByUrl('products');
    }, (err) => {
      this.errorMessage = err.message;

      this.isUserValid = false;

    });

  }

  onClick() {
    this.router.navigateByUrl('register');
  }


  get Email(): FormControl {

    return this.loginForm.get('Email') as FormControl;

  }

  get Password(): FormControl {

    return this.loginForm.get('Password') as FormControl;

  }

  loadData() {
    let data = localStorage.getItem('acess_token');
  }

}