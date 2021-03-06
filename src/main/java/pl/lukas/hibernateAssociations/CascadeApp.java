package pl.lukas.hibernateAssociations;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import pl.lukas.hibernateAssociations.entity.Company;
import pl.lukas.hibernateAssociations.entity.CompanyDetail;

public class CascadeApp {

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

        Company company = new Company("Orlen",200000000);
        CompanyDetail detail = new CompanyDetail("Poland", 15000);
        company.setCompanyDetail(detail);

        session.beginTransaction();
        session.persist(company);

        session.getTransaction().commit();

        // zakończeine obiektu SessionFactory
        factory.close();


    }
}
