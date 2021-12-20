import { Injectable } from '@angular/core';
import {User} from '../Model/user';
import {Observable} from 'rxjs';
import {environment} from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class UserIdService {

  userID: any;
  constructor() { }

  setUserID(uId: any): void {
    this.userID = uId;
  }

  getUserID(): any {
    return this.userID;
  }
}
