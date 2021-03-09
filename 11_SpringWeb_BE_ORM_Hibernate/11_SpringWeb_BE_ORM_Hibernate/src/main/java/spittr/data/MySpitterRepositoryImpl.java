package spittr.data;

/*
THIS IS A MOCK CLASS - COMMENT AND UNCOMMENT SECTIONS IF U NEED A BEAN THAT IMPLEMENTS SpitterRepository.
BE CARE: When you will implement the real class that implements SpitterRepository, you will probably get the Error for having not uniqueness for the creation of beans that implement SpitterRepository. Uncomment PART 1 and comment PART 2 of this class to solve it.
*/

//PART 1
//Comment this if you want to implement a real Bean that implements SpitterRepository interface
//Uncomment this if you want to implement a mock Bean that will not implement anything

public class MySpitterRepositoryImpl  {

}


//PART 2
//Uncomment this if you want to implement a real Bean that implements SpitterRepository interface
//comment this if you want to implement a mock Bean that will not implement anything
/*
@Component
public class MySpitterRepositoryImpl implements SpitterRepository {

    @Override
    public Spitter save(Spitter spitter) {
        return spitter;
    }

    @Override
    public Spitter findByUsername(String username) {
        return new Spitter(username, "mockPassword", "mockFirstName", "mockLastName", "mockEmail@mockDomain.mock");
    }
}*/