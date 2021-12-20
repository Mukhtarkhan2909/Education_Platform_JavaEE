import { Injectable, Inject } from '@angular/core';
import { HttpClient, HttpHeaders, HttpEvent, HttpRequest } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from '../Model/user';
import { SESSION_STORAGE, StorageService } from 'angular-webstorage-service';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ApiService {

  constructor(@Inject(SESSION_STORAGE) private storage: StorageService, private http: HttpClient) {

  }

  login(user: User): Observable<any> {
    return this.http.post(environment.baseUrl + environment.loginUrl,
      JSON.stringify(user),
      {
        headers:
          { 'Content-Type': 'application/json' }
      });
  }

  logout() {
    this.http.get<any>(environment.baseUrl + environment.logoutUrl);
  }

  getCourses(): Observable<any> {
    return this.http.get<any>(environment.baseUrl + environment.coursesUrl);
  }

  getUsers(): Observable<any> {
    return this.http.get<any>(environment.baseUrl + environment.usersUrl);
  }

  getUserCourses(userId: any): Observable<any> {
    return this.http.get<any>(environment.baseUrl + environment.userCoursesUrl + userId);
  }

  getCourseMaterials(courseId: string): Observable<any> {
    return this.http.get<any>(environment.baseUrl + environment.courseMaterialsUrl + courseId);
  }

  getCourseTasks(courseId: string): Observable<any> {
    return this.http.get<any>(environment.baseUrl + environment.courseTasksUrl + courseId);
  }

  getCourseInformation(courseId: string): Observable<any> {
    return this.http.get<any>(environment.baseUrl + environment.courseInformationUrl + courseId);
  }
  getTaskAnswer(userID: string, taskId: string): Observable<any> {
    return this.http.get<any>(environment.baseUrl + environment.userTaskAnswerUrl + userID + '/' + taskId);
  }
  getTaskById(taskId: string): Observable<any> {
    return this.http.get<any>(environment.baseUrl + environment.userTaskById + taskId);
  }
  getTaskAnswers(taskId: string): Observable<any> {
    return this.http.get<any>(environment.baseUrl + environment.userTaskAnswersUrl + taskId);
  }
  getUserMaterials(userID: string, courseID: string): Observable<any> {
    return this.http.get<any>(environment.baseUrl + environment.userMaterialsUrl + userID + '/' + courseID);
  }
  getUserTasks(userID: string, courseID: string): Observable<any> {
    return this.http.get<any>(environment.baseUrl + environment.userTasksUrl + userID + '/' + courseID);
  }

  // Authentication Methods

  isAuthenticated(): boolean {
    return this.getToken() !== null;
  }

  storeToken(token: string, auth_type: string) {
    this.storage.set('auth_token', token);
    this.storage.set('auth_type', auth_type);
  }

  getAuthType(): string {
    if (this.storage.get('auth_type') !== null) {
      return this.storage.get('auth_type');
    }
    return null;
  }

  getToken() {
    return this.storage.get('auth_token');
  }

  removeToken() {
    this.storage.remove('auth_type');
    return this.storage.remove('auth_token');
  }

  addCourse(name: string, numOfWeeks: string): Observable<any> {
    const formData: FormData = new FormData();
    formData.append('name', name);
    formData.append('numOfWeeks', numOfWeeks);
    return this.http.post<any>(environment.baseUrl + environment.addCourseUrl, formData);

  }

  addUser(fullName: string, userName: string, password: string, userType: string) {
    const formData: FormData = new FormData();
    formData.append('fullName', fullName);
    formData.append('userName', userName);
    formData.append('password', password);
    formData.append('userType', userType);
    return this.http.post<any>(environment.baseUrl + environment.addUserUrl, formData);
  }

  delCourse(courseId: string) {
    return this.http.get<any>(environment.baseUrl + environment.deleteCourseUrl + '?courseid=' + courseId);
  }

  delUser(userId: string) {
    return this.http.get<any>(environment.baseUrl + environment.deleteUserUrl + '?userid=' + userId);
  }
}
