package org.example.ocsf.server.src.main.java.il.cshaifasweng.OCSFMediatorExample.server;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;

import org.example.Entities.Clinic;
import org.example.Entities.Employee;
import org.example.Entities.Manager;
import org.example.Entities.User;
import org.example.ocsf.entities.src.main.java.il.cshaifasweng.OCSFMediatorExample.entities.Message;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.example.ocsf.server.src.main.java.il.cshaifasweng.OCSFMediatorExample.server.ocsf.ConnectionToClient;

public class App extends SimpleServer {

    private static SessionFactory sessionFactory = getSessionFactory();
    public static Session session;
    private Message serverMsg;
    private static SimpleServer server;

    public App(int port) {
        super(port);
    }

    public static SessionFactory getSessionFactory() throws HibernateException {
        java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.OFF);
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(Clinic.class);
        configuration.addAnnotatedClass(User.class);
        configuration.addAnnotatedClass(Employee.class);
        configuration.addAnnotatedClass(Manager.class);
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
        return configuration.buildSessionFactory(serviceRegistry);
    }

    @Override
    protected void handleMessageFromClient(Object msg, ConnectionToClient client) {

        try {
            Message currentMsg = ((Message) msg);
            serverMsg = new Message();
            if (currentMsg.getAction().equals("login")) {
                try {
                    if (currentMsg.getUsername().equals("") || ((Message) msg).getPassword().equals("")) {
                    } else {
                        userController.getUser((Message) msg);
                        serverMsg = (Message) msg;
                        serverMsg.setAction("login done");
                        client.sendToClient(serverMsg);
                    }
                } catch (IOException | NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }
            }
            if (currentMsg.getAction().equals("logout")) {
                try {
                    userController.logOut(currentMsg);
                    serverMsg = new Message();
                    serverMsg.setAction("logged out");
                    client.sendToClient(serverMsg);
                } catch (IOException | NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }
            }
            if (currentMsg.getAction().equals("pull openning hours")) {
                try {
                    serverMsg = currentMsg;
                    serverMsg.setOpenningHour(clinicController.getOpenningHourByClinic(currentMsg.getClinic()));
                    serverMsg.setClosingHour(clinicController.getClosingHourByClinic(currentMsg.getClinic()));
                    serverMsg.setAction("got openning hours");
                    client.sendToClient(serverMsg);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (currentMsg.getAction().equals("change hours")) {
                try {
                    serverMsg = currentMsg;
                    if(serverMsg.getOpenningHour()!=null) {
                        updateCellInDB(serverMsg.getClinicName(), "ClinicName", "open", serverMsg.getOpenningHour());
                    }
                    if(serverMsg.getClosingHour()!=null) {
                        updateCellInDB(serverMsg.getClinicName(), "ClinicName", "close", serverMsg.getClosingHour());
                    }
                    serverMsg.setAction("saved new hours");
                    client.sendToClient(serverMsg);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (currentMsg.getAction().equals("GetAllClinics")) {
                try {
                    serverMsg = currentMsg;
                    Message.setClinicList(clinicController.getAllClinicsFromDB());
                    serverMsg.setAction("ShowClinics");
                    client.sendToClient(serverMsg);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (currentMsg.getAction().equals("GetClinicFromName")) {
                try {
                    serverMsg = currentMsg;
                    serverMsg.setClinic(clinicController.getClinicByName(serverMsg.getClinicName()));
                    serverMsg.setAction("ShowTime");
                    client.sendToClient(serverMsg);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static<T> void updateCellInDB(String EntityName,String EntityType,String col,T objectType) {
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            String request = "UPDATE clinics SET col = objectType WHERE " +EntityType + " = "+EntityName;
            SQLQuery sqlQuery = session.createSQLQuery(request);
            sqlQuery.executeUpdate();
            session.flush();
            session.getTransaction().commit();
            session.clear();
        } catch (Exception e) {
            if (session != null) {
                session.getTransaction().rollback();
            }
            System.err.println("An error occured, changes have been rolled back.");
            e.printStackTrace();
        } finally {
            assert session != null;
            session.close();
        }
    }

    public static void main( String[] args ) throws IOException
    {
        server = new App(3000);
        server.listen();
    }
}
