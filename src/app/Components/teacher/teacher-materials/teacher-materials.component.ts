import { Component, OnInit } from '@angular/core';
import {Material} from '../../../Model/material';
import {Task} from '../../../Model/task';
import {Courses} from '../../../Model/courses';
import {ActivatedRoute, Router} from '@angular/router';
import {ApiService} from '../../../Service/api.service';
import {UserIdService} from '../../../Service/user-id.service';

@Component({
  selector: 'app-teacher-materials',
  templateUrl: './teacher-materials.component.html',
  styleUrls: ['./teacher-materials.component.css']
})
export class TeacherMaterialsComponent implements OnInit {

  private courseId: string;
  materials: Material[] = [];
  tasks: Task[] = [];
  course: Courses;
  constructor(private router: Router,
              public route: ActivatedRoute,
              private api: ApiService,
              private uApi: UserIdService) {
    this.courseId = this.route.snapshot.paramMap.get('id');
  }

  ngOnInit() {
    if (this.api.isAuthenticated) {
      this.api.getUserMaterials(this.uApi.getUserID(), this.courseId).subscribe(
        res => {
          this.materials = res;
        }
      );
      this.api.getUserTasks(this.uApi.getUserID(), this.courseId).subscribe(
        res => {
          this.tasks = res;
        }
      );
      this.api.getCourseInformation(this.courseId).subscribe(
        res => {
          this.course = res;
        }
      );
    }
  }
  counter(i: number) {
    return new Array(i);
  }

}
