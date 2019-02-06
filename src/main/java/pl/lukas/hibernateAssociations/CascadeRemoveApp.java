package pl.lukas.hibernateAssociations;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import pl.lukas.hibernateAssociations.entity.Company;
import pl.lukas.hibernateAssociations.entity.CompanyDetail;

public class CascadeRemoveApp {

    public static void main(String[] args) {

        // tworzenie obiektu Configuration
        Configuration conf = new Configuration();
        // wczytanie pliku konfiguracyjnego
        conf.configure("hibernate.cfg.xml");
        // wczytanie adnotacje klasy
        conf.addAnnotatedClass(Company.class);
        conf.addAnnotatedClass(CompanyDetail.class);
        // tworzenie obiektu SessionFactory
        SessionFactory factory = conf.buildSessionFactory();
        // pobieranie sesji
        Session session = factory.getCurrentSession();

        session.beginTransaction();

        Company company = session.get(Company.class, 13);
        session.remove(company);

        session.getTransaction().commit();

        // zakończeine obiektu SessionFactory
        factory.close();


    }
}
