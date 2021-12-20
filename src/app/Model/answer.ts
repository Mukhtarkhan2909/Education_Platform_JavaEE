import {User} from './user';
import {Task} from './task';

export interface Answer {
  id: number;
  passingDate: string;
  approved: boolean;
  grade: number;
  user: User;
  task: Task;
}
