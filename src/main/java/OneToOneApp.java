import entity.Company;
import entity.CompanyDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class OneToOneApp {

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

        Company company = new Company("Strefakursow",10000000);
        CompanyDetail detail = new CompanyDetail("Poland", 150);
        company.setCompanyDetail(detail);

        session.beginTransaction();

        session.save(detail);
        session.save(company);


        session.getTransaction().commit();


        // zakończeine obiektu SessionFactory
        factory.close();

    }
}
