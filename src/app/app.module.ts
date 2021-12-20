import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';

import { AppComponent } from './app.component';
import {StorageServiceModule} from 'angular-webstorage-service';
import {Router, Routes, RouterModule} from '@angular/router';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {HttpClientModule, HTTP_INTERCEPTORS} from '@angular/common/http';
import { NavigationComponent } from './Components/navigation/navigation.component';
import { LoginComponent } from './Components/login/login.component';
import { AuthguardGuard } from './Service/authguard.guard';
import { AdminComponent } from './Components/admin/admin.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { AuthInterceptor } from './Service/AuthInterceptor';
import { StudentComponent } from './Components/student/student.component';
import { TeacherComponent } from './Components/teacher/teacher.component';
import { CoursesComponent } from './Components/admin/courses/courses.component';
import { EditCourseComponent } from './Components/admin/courses/edit-course/edit-course.component';
import { UsersComponent } from './Components/admin/users/users.component';
import { EditUserComponent } from './Components/admin/users/edit-user/edit-user.component';
import { CourseDetailsComponent } from './Components/student/course-details/course-details.component';
import { TaskDetailsComponent } from './Components/student/course-details/task-details/task-details.component';
import { TeacherMaterialsComponent } from './Components/teacher/teacher-materials/teacher-materials.component';
import { TaskAnswersComponent } from './Components/teacher/teacher-materials/task-answers/task-answers.component';
const appRoutes: Routes = [
  { path: '',
    redirectTo: '/login',
    pathMatch: 'full'
  },
  {
    path: 'login',
    component: LoginComponent
  },
  {
    path: 'admin',
    component: AdminComponent,
    canActivate: [AuthguardGuard]
  },
  {
    path: 'teacher',
    component: TeacherComponent,
    canActivate: [AuthguardGuard]
  },
  {
    path: 'teacher-materials/:id',
    component: TeacherMaterialsComponent,
    canActivate: [AuthguardGuard]
  },
  {
    path: 'task-answers/:taskId',
    component: TaskAnswersComponent,
    canActivate: [AuthguardGuard]
  },
  {
    path: 'student',
    component: StudentComponent,
    canActivate: [AuthguardGuard]
  },
  {
    path: 'course-details/:id',
    component: CourseDetailsComponent,
    canActivate: [AuthguardGuard]
  },
  {
    path: 'task-details/:userId/:taskId',
    component: TaskDetailsComponent,
    canActivate: [AuthguardGuard]
  },
  {
    path: 'admin/courses',
    component: CoursesComponent,
    canActivate: [AuthguardGuard]
  },
  {
    path: 'admin/users',
    component: UsersComponent,
    canActivate: [AuthguardGuard]
  },
  {
    path: 'admin/edit-course',
    component: EditCourseComponent,
    canActivate: [AuthguardGuard]
  },
  {
    path: 'admin/edit-user',
    component: EditUserComponent,
    canActivate: [AuthguardGuard]
  }];

@NgModule({
  declarations: [
    AppComponent,
    NavigationComponent,
    LoginComponent,
    AdminComponent,
    StudentComponent,
    TeacherComponent,
    CoursesComponent,
    EditCourseComponent,
    UsersComponent,
    EditUserComponent,
    CourseDetailsComponent,
    TaskDetailsComponent,
    TeacherMaterialsComponent,
    TaskAnswersComponent
  ],
  imports: [
    BrowserModule,
    RouterModule,
    HttpClientModule,
    StorageServiceModule,
    RouterModule.forRoot(appRoutes),
    FormsModule,
    ReactiveFormsModule,
    NgbModule.forRoot(),
    NgbModule,
    BrowserAnimationsModule
  ],
  providers: [{
    provide : HTTP_INTERCEPTORS,
    useClass: AuthInterceptor,
    multi   : true,
  }, ],
  bootstrap: [AppComponent]
})
export class AppModule { }
