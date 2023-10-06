import { Component } from '@angular/core';
import { AuthDetails } from '../authdetails';
import { DetailsService } from '../details.service';
import { Router } from '@angular/router';
import { AppComponent } from '../app.component';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  authDetail: AuthDetails = new AuthDetails();
  isUserValid: any;

  constructor(private detailService: DetailsService, private router: Router, private appObj: AppComponent){}
  ngOnInit(){}

  loginUser(){
    this.detailService.validateUser(this.authDetail).subscribe(data => 
    {
      this.isUserValid = data;
      this.appObj.updateAuth(this.isUserValid);
      console.log(data);

      if(this.isUserValid){
        this.detailService.generateToken(this.authDetail).subscribe(data => {this.detailService.token = data.token; console.log(data.token)});
        this.router.navigate(['/home']);
      }
      else{
        this.authDetail= new AuthDetails();
        alert("Enter correct email or password");
      }
    });
  }
  
}
// localStorage.setItem('currentUser', JSON.stringify({token: "null", name: this.authDetail.emailid}));