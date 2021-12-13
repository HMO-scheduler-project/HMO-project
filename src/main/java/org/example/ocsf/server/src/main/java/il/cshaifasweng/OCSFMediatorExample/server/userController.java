package org.example.ocsf.server;


public class userController {
    private static List<User> getAllUsersFromDB() {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<User> query = builder.createQuery(User.class);
        query.from(User.class);
        return session.createQuery(query).getResultList();
    }

    public static void getUser (Message msg) {
            List<User> users = getAllUsersFromDB();
            int flag = 0;
            for(User user : users) {
                if(user.getUsername().equals(msg.getUsername()) && user.getPassword().equals( msg.getPassword()) && user.isLoggedIn()==false) {
                    if(user instanceof Manager) {
                        msg.setUserType("Manager");
                        System.out.println(msg.getUserType());
                        user.setLoggedIn(true);
                        msg.setUser(user);
                        flag = 1;
                    } else if(user instanceof Employee) {
                        msg.setUserType("Employee");
                        System.out.println(msg.getUserType());
                        user.setLoggedIn(true);
                        msg.setUser(user);
                        flag = 1;
                    } else msg.setUserType("null");
                } else if(user.getUsername().equals(msg.getUsername()) && user.getPassword().equals( msg.getPassword()) &&user.isLoggedIn()==true) {
                    msg.setType("you are already logged in");
                    flag = 1;
                }

            }
            if(flag==0) {
                msg.setType("This user does not exist");
            }
        }

        public static void logOut (Message msg) {
            List<User> users = getAllUsersFromDB();
            System.out.println("about to log out");
            for(User user : users) {
                if(user.getUsername().equals(msg.getUsername()) && user.getPassword().equals( msg.getPassword())) {
                    if(user instanceof Manager) {
                        user.setLoggedIn(false);

                    } else if(user instanceof Employee) {
                        user.setLoggedIn(false);

                    }
                } else if(user.getUsername().equals(msg.getUsername()) && user.getPassword().equals( msg.getPassword()) &&user.isLoggedIn()==true) {
                    msg.setTypeOfWorker("you are already logged out");
                }

            }

        }




