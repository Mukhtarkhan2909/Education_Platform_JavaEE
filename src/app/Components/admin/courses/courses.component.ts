import { Component, OnInit } from '@angular/core';
import {Courses} from '../../../Model/courses';
import {ApiService} from '../../../Service/api.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-courses',
  templateUrl: './courses.component.html',
  styleUrls: ['./courses.component.css']
})
export class CoursesComponent implements OnInit {

  courses: Courses[] = [];
  showAdd = false;
  auth: string;
  constructor(private api: ApiService, private router: Router) { }
  ngOnInit() {
    if (this.api.isAuthenticated) {
      this.auth = this.api.getToken();
      this.api.getCourses().subscribe(
        res => {
          this.courses = res;
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
  addCourse(name: any, numOfWeeks: any) {
    this.api.addCourse(name.value, numOfWeeks.value).subscribe(res => {
      this.courses = res.oblist;
    });
  }
  delCourse(courseId: any) {
    this.api.delCourse(courseId.value).subscribe(res => {
      this.courses = res.oblist;
      this.ngOnInit();
    });
  }
}
