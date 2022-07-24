import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  registerForm: FormGroup;
  submitted = false;

  constructor(private loginAuth: AuthService, private router: Router) { }
    signup = new FormGroup({

    firstName: new FormControl("", [Validators.required]),

    lastName: new FormControl("", [Validators.required]),

    email: new FormControl("", [Validators.required, Validators.email]),

    user_password: new FormControl("", [Validators.required, Validators.minLength(6), Validators.maxLength(15)]),
  });

  ngOnInit(): void {
  }

  
  
  
  register(){

    this.loginAuth.signupUser(this.signup.value).subscribe((res)=>{

    alert("Registered Successfully");
    this.router.navigateByUrl('login');

    console.log(res);

      // console.log(this.addOfferAsync.value.EmpId);

    },(res)=>{

      if(res.status == 409) {
        alert("Please fill all the required fields");
      }
      else {

          alert("Something went wrong!!!");

        }

    });

  }
}