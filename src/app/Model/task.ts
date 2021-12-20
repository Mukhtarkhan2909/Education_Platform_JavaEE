import {Courses} from './courses';
import {User} from './user';

export interface Task {
  id: number;
  name: string;
  description: string;
  deadline: string;
  weekNum: number;
  course: Courses;
  user: User;
}
