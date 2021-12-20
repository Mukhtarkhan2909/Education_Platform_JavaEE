import { Component, OnInit } from '@angular/core';
import {Material} from '../../../../Model/material';
import {Task} from '../../../../Model/task';
import {Courses} from '../../../../Model/courses';
import {ActivatedRoute, Router} from '@angular/router';
import {ApiService} from '../../../../Service/api.service';
import {UserIdService} from '../../../../Service/user-id.service';
import {Answer} from '../../../../Model/answer';

@Component({
  selector: 'app-task-answers',
  templateUrl: './task-answers.component.html',
  styleUrls: ['./task-answers.component.css']
})
export class TaskAnswersComponent implements OnInit {

  private taskId: string;
  answers: Answer[] = [];
  task: Task;
  constructor(private router: Router,
              public route: ActivatedRoute,
              private api: ApiService) {
    this.taskId = this.route.snapshot.paramMap.get('taskId');
  }

  ngOnInit() {
    if (this.api.isAuthenticated) {
      this.api.getTaskAnswers(this.taskId).subscribe(
        res => {
          this.answers = res;
        }
      );
      this.api.getTaskById(this.taskId).subscribe(
        res => {
          this.task = res;
        }
      );
    }
  }
  counter(i: number) {
    return new Array(i);
  }

}
