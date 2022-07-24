import { Component, OnInit } from '@angular/core';
import { OrderHistory } from '../../common/order-history';
import { OrderHistoryService } from '../../services/order-history.service';

@Component({
  selector: 'app-order-history',
  templateUrl: './order-history.component.html',
  styleUrls: ['./order-history.component.css']
})
export class OrderHistoryComponent implements OnInit {

  orderHistoryList: OrderHistory[] = [];
  //storage: Storage = localStorage;
  session: any;
  constructor(private orderHistoryService: OrderHistoryService) { }

  ngOnInit(): void {
    this.handleOrderHistory();
  }

  handleOrderHistory() {

    // read the user's email address from browser storage
    //const theEmail = JSON.parse(this.storage.getItem('email'));
    // const theEmail = localStorage.getItem('acess_token');
    //console.log(theEmail);
    let data = localStorage.getItem('acess_token');
    //console.log(data);
    this.session = JSON.parse(data);
    //console.log(this.session);
    let abc = this.session.email;
    console.log(abc);
    // retrieve data from the service
    this.orderHistoryService.getOrderHistory(abc).subscribe(
      data => {
        //console.log(data);
        this.orderHistoryList = data._embedded.orders;
        console.log(this.orderHistoryList);
      }
    );
  }

}