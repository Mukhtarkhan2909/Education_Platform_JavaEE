import {User} from './user';
import {Courses} from './courses';

export interface Material {
  id: number;
  name: string;
  content: string;
  weekNum: number;
  user: User;
  course: Courses;
}

