package pl.lukas.hibernateAssociations;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import pl.lukas.hibernateAssociations.entity.Company;
import pl.lukas.hibernateAssociations.entity.CompanyDetail;

public class BidirectionalApp {

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


//        Company company = new Company("PZU", 1000000);
//        CompanyDetail detail = new CompanyDetail("Poland", 17000);
//        detail.setCompany(company);
//        company.setCompanyDetail(detail);

        session.beginTransaction();

//        session.persist(detail);
        CompanyDetail detail = session.get(CompanyDetail.class, 11);

        session.remove(detail);

        session.getTransaction().commit();


//        System.out.println(detail.getCompany());

        // zakończeine obiektu SessionFactory
        factory.close();
    }
}
