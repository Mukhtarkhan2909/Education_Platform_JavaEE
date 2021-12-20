import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {ApiService} from '../../../../Service/api.service';
import {Material} from '../../../../Model/material';
import {Task} from '../../../../Model/task';
import {Courses} from '../../../../Model/courses';
import {Answer} from '../../../../Model/answer';

@Component({
  selector: 'app-task-details',
  templateUrl: './task-details.component.html',
  styleUrls: ['./task-details.component.css']
})
export class TaskDetailsComponent implements OnInit {
  private taskId: string;
  private userID: string;
  answer: Answer;
  constructor(private router: Router,
              public route: ActivatedRoute,
              private api: ApiService) {
    this.userID = this.route.snapshot.paramMap.get('userId');
    this.taskId = this.route.snapshot.paramMap.get('taskId');
  }

  ngOnInit() {
    if (this.api.isAuthenticated) {
      this.api.getTaskAnswer(this.userID, this.taskId).subscribe(
        res => {
          this.answer = res;
        }
      );
    }
  }

}
