package org.example.ocsf.server;

public class userController {
        public static void getUser (Message msg) {
            ArrayList<User> users = Main.getAllOfType(User.class);
            int flag = 0;
            for(User arr : users) {
                if(arr.getUsername().equals(msg.getUsername()) && arr.getPassword().equals( msg.getPassword()) && arr.isLoggedIn()==false) {
                    if(arr instanceof Manager) {
                        msg.setUserType("Manager");
                        System.out.println(msg.getUserType());
                        arr.setLoggedIn(true);
                        msg.setUser(arr);
                        flag = 1;
                    } else if(arr instanceof Employee) {
                        msg.setUserType("Employee");
                        System.out.println(msg.getUserType());
                        arr.setLoggedIn(true);
                        msg.setUser(arr);
                        flag = 1;
                    } else msg.setUserType("null");
                } else if(arr.getUsername().equals(msg.getUsername()) && arr.getPassword().equals( msg.getPassword()) &&arr.isLoggedIn()==true) {
                    msg.setType("you are already logged in");
                    flag = 1;
                }

            }
            if(flag==0) {
                msg.setType("This user does not exist");
            }
        }

        public static void logOut (Message msg) {
            ArrayList<User> users = Main.getAllOfType(User.class);
            System.out.println("about to log out");
            for(User arr : users) {
                if(arr.getUsername().equals(msg.getUsername()) && arr.getPassword().equals( msg.getPassword())) {
                    if(arr instanceof Manager) {
                        arr.setLoggedIn(false);

                    } else if(arr instanceof Employee) {
                        arr.setLoggedIn(false);

                    }
                } else if(arr.getUsername().equals(msg.getUsername()) && arr.getPassword().equals( msg.getPassword()) &&arr.isLoggedIn()==true) {
                    msg.setTypeOfWorker("you are already logged out");
                }

            }

        }




