import { Component } from '@angular/core';
import { DetailsService } from '../details.service';
import { Router } from '@angular/router';
import { AppComponent } from '../app.component';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent {
  custDetail: any;

  constructor(private detailService: DetailsService, private router: Router, private appObj: AppComponent){}
  ngOnInit(){
    this.detailService.getCustomerDetails().subscribe((data: any) => {
      const dataObj = JSON.parse(data);
      this.custDetail = dataObj
    });
  }

  logout(){
    this.detailService.authDetailObj = {};
    this.appObj.updateAuth(false);
    this.router.navigate(['/home']);
  }
}
