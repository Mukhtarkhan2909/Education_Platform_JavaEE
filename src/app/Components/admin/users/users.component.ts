import { Component, OnInit } from '@angular/core';
import {Courses} from '../../../Model/courses';
import {ApiService} from '../../../Service/api.service';
import {Router} from '@angular/router';
import {User} from '../../../Model/user';

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css']
})
export class UsersComponent implements OnInit {

  users: User[] = [];
  showAdd = false;
  auth: string;
  constructor(private api: ApiService, private router: Router) { }
  ngOnInit() {
    if (this.api.isAuthenticated) {
      this.auth = this.api.getToken();
      this.api.getUsers().subscribe(
        res => {
          this.users = res;
        }
      );
    }
  }
  show() {
    this.showAdd = true;
  }
  hide() {
    this.showAdd = false;
  }

  addUser(fullName: any, userName: any, password: any, userType: any) {
    this.api.addUser(fullName.value, userName.value, password.value, userType.value).subscribe(res => {
      this.users = res.oblist;
    });
  }
  delUser(userId: any) {
    this.api.delUser(userId.value).subscribe(res => {
      this.users = res.oblist;
      this.ngOnInit();
    });
  }
}
