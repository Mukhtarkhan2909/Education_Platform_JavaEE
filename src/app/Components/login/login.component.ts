import { Component, OnInit } from '@angular/core';
import { ApiService } from 'src/app/Service/api.service';
import { Router } from '@angular/router';
import { FormBuilder } from '@angular/forms';
import {UserIdService} from '../../Service/user-id.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  loginForm: any;
  error = false;
  constructor(private apiService: ApiService,
              private router: Router,
              private formBuilder: FormBuilder,
              private uApi: UserIdService) {
    this.createForm();
  }

  ngOnInit() {
  }
  createForm() {
    this.loginForm = this.formBuilder.group({
      userName: '',
      password: ''
    });
  }
  login(): void {
    this.apiService.login(this.loginForm.value).
      subscribe(res => {
        if (res.status === '200' && res.userType === 'STUDENT') {
          this.apiService.storeToken(res.authToken, 'student');
          this.router.navigate(['/student']);
          this.error = false;
        } else if (res.status === '200' && res.userType === 'TEACHER') {
          this.apiService.storeToken(res.authToken, 'teacher');
          this.router.navigate(['/teacher']);
          this.error = false;
        } else if (res.status === '200' && res.userType === 'ADMIN') {
          this.apiService.storeToken(res.authToken, 'admin');
          this.router.navigate(['/admin']);
          this.error = false;
        }
        this.uApi.setUserID(res.userId);
      },
        err => {
          this.router.navigate(['/login']);
      });
  }
}
