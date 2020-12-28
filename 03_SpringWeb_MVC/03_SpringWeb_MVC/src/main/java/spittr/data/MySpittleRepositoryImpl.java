package spittr.data;

import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.MockMvc;
import spittr.Spittle;
import spittr.web.SpittleController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/*
THIS IS A MOCK CLASS - COMMENT AND UNCOMMENT SECTIONS IF U NEED A BEAN THAT IMPLEMENTS SpittleRepository.
BE CARE: When you will implement the real class that implements SpittleRepository, you will probably get the Error for having not uniqueness for the creation of beans that implement SpittleRepository. Uncomment PART 1 and comment PART 2 of this class to solve it.
*/

//PART 1
//Comment this if you want to implement a real Bean that implements SpittleRepository interface
//Uncomment this if you want to implement a mock Bean that will not implement anything
/*
public class MySpittleRepositoryImpl  {

}*/


//PART 2
//Uncomment this if you want to implement a real Bean that implements SpittleRepository interface
//comment this if you want to implement a mock Bean that will not implement anything
@Component
public class MySpittleRepositoryImpl implements SpittleRepository {
    @Override
    public List<Spittle> findSpittles(long max, int count) {
        List<Spittle> spittles = new ArrayList<Spittle>();
        for (int i=0; i < count; i++) {
            spittles.add(new Spittle("Spittle " + i, new Date()));
        }
        return spittles;
    }

    @Override
    public Spittle findOne(long spittleId) {
        return new Spittle(spittleId, "Hello custom", new Date(), 777.77, 888.88);
    }
}
