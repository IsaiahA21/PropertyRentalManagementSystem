package database;

import static com.mongodb.client.model.Filters.*;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class PaymentControl {
    private MongoCollection<Document> propertiesCollection;
    private MongoCollection<Document> paymentDetails;
    private int period;
    private double cost;
    private String currentPeriodStart;
    private String currentDate;

    public PaymentControl(MongoCollection<Document> propCol,MongoCollection<Document> pd) {


        propertiesCollection = propCol;
        paymentDetails = pd;
        getDate();
        getDetails();

    }

    void getDate(){
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat  sdf = new SimpleDateFormat("yyyy-MM-dd");
        currentDate = sdf.format(cal.getTime());
    }

    void getDetails(){
        Document periodLength = paymentDetails.find(type("PERIOD","int")).first();
        Document periodStart = paymentDetails.find(type("PERIODSTART","string")).first();
        assert periodLength != null;
        period = periodLength.getInteger("PERIOD");
        assert periodStart != null;
        currentPeriodStart = periodStart.getString("PERIODSTART");

        System.out.println(currentPeriodStart);
    }
}
