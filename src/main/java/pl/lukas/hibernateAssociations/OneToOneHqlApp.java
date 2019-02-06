package pl.lukas.hibernateAssociations;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import pl.lukas.hibernateAssociations.entity.Company;
import pl.lukas.hibernateAssociations.entity.CompanyDetail;

import java.util.List;

public class OneToOneHqlApp {

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

        String select = "select c from Company c";
        String where = "select c from Company c join c.companyDetail cd where cd.residence='Italy'";
        String sum = "select sum(c.value) from Company c join c.companyDetail cd where cd.residence='Poland'";
        String orderBy = "select c.name from CompanyDetail cd join cd.company c where cd.employeeNumber < 35000 order by c.value";

        session.beginTransaction();

        Query query = session.createQuery(orderBy);
//        List<Company> resultList = query.getResultList();
//        Long result = (Long) query.getSingleResult();
        List<String> resultList = query.getResultList();

        session.getTransaction().commit();

        for (String c : resultList) {
            System.out.println(c);
        }


//        System.out.println("wartość polskich firm w bazie: " + result);

//        for (Company c : resultList) {
//            System.out.println(c + ", " + c.getCompanyDetail());
//        }

        // zakończeine obiektu SessionFactory
        factory.close();

    }
}
