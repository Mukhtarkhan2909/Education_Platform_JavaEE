import {Component, Input, OnInit} from '@angular/core';
import {Courses} from '../../../Model/courses';
import {ActivatedRoute, Router} from '@angular/router';
import {ApiService} from '../../../Service/api.service';
import {Material} from '../../../Model/material';
import {Task} from '../../../Model/task';
import {User} from '../../../Model/user';
import {UserIdService} from '../../../Service/user-id.service';

@Component({
  selector: 'app-course-details',
  templateUrl: './course-details.component.html',
  styleUrls: ['./course-details.component.css']
})
export class CourseDetailsComponent implements OnInit {
  private courseId: string;
  materials: Material[] = [];
  tasks: Task[] = [];
  course: Courses;
  userID: number;
  constructor(private router: Router,
              public route: ActivatedRoute,
              private api: ApiService,
              private uApi: UserIdService) {
    this.courseId = this.route.snapshot.paramMap.get('id');
  }

  ngOnInit() {
    if (this.api.isAuthenticated) {
      this.api.getCourseMaterials(this.courseId).subscribe(
        res => {
          this.materials = res;
        }
      );
      this.api.getCourseTasks(this.courseId).subscribe(
        res => {
          this.tasks = res;
        }
      );
      this.api.getCourseInformation(this.courseId).subscribe(
        res => {
          this.course = res;
        }
      );
      this.userID = this.uApi.getUserID();
    }
  }
  counter(i: number) {
    return new Array(i);
  }
}
