import { Component, OnInit } from '@angular/core';
import { ApiService } from 'src/app/Service/api.service';
import {Courses} from '../../Model/courses';
import { HttpEventType, HttpResponse } from '@angular/common/http';
import { NavigationExtras, Router } from '@angular/router';
import { Route } from '@angular/compiler/src/core';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {
  constructor() { }
  ngOnInit() {}
}
