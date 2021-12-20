import { Component, OnInit } from '@angular/core';
import {ApiService} from '../../Service/api.service';
import {Courses} from '../../Model/courses';
import {UserIdService} from '../../Service/user-id.service';

@Component({
  selector: 'app-student',
  templateUrl: './student.component.html',
  styleUrls: ['./student.component.css']
})
export class StudentComponent implements OnInit {

  courses: Courses[] = [];
  constructor(private api: ApiService, private uApi: UserIdService) { }

  ngOnInit() {
    if (this.api.isAuthenticated) {
      this.api.getUserCourses(this.uApi.getUserID()).subscribe(
        res => {
          this.courses = res;
        }
      );
    }
  }
}
