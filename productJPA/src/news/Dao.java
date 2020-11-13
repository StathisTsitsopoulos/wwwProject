package news;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
 
public class Dao 
{
  public boolean saveDetails(String product_name,String barcode, String color, String description)
{
    boolean flag=true;
    SessionFactory factory=new Configuration().configure().buildSessionFactory();
      Session session=factory.openSession();
       
      productClass product = new productClass();
        product.setProduct_name(product_name);
		product.setColor(color);
        product.setBarcode(barcode);
        product.setDescription(description);
      Transaction transaction=session.beginTransaction();
      try
      {
          session.save(product);
          transaction.commit();
      }catch(Exception e)
      {
          transaction.rollback();
          flag=false;
           
      }
      session.close();
      return flag;
      }
}
