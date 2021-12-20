import { Component, OnInit } from '@angular/core';
import {Courses} from '../../Model/courses';
import {ApiService} from '../../Service/api.service';
import {UserIdService} from '../../Service/user-id.service';

@Component({
  selector: 'app-teacher',
  templateUrl: './teacher.component.html',
  styleUrls: ['./teacher.component.css']
})
export class TeacherComponent implements OnInit {

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
