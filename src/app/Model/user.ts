class Authorities {
  id: number;
  username: string;
  authority: string;
}

export interface User {
  id: number;
  fullName: string;
  userName: string;
  password: string;
  userType: string;
  isEnabled: boolean;
  roles: Authorities[];
}
