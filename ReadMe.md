# Getting Started
This project is developed by Tugrul Karakaya on 7th of July 2019.

## Project Architecture
Because the project contain just 1 entity, I am able to show limited functionalities. Please find details below;
* **Controller**
Restfull interface is implemented here.

* **Repository**
There are two different implementation. I intentioanlly created BookRepositoryCustomImplementation to show how to manipultae data layer in complex way. There is another way (@Query attribute on top of the method definition by writing basic queries, but time was not enough to add it)

* **Payload**
in complex world, actually restfull service clients needs more comlex entities (DTO objects??) than the existing entity structure. For examle, to list Book in real applicaiton clients might need Author details, publisher details, etc. each is different entities, but by using this payloads it is possible to combine all them in one entity.
And for the request parameter, it is also possible to add some extra fields. for examle I added Filter field as String. if user just send Filter to Book controller service compare with all string fields (author and title on the given sample). So this is another example of having payload entities.

* **Exception**
Exception management is actually much more comlex than this implementation. AOP or Control advices needs to be used for better handlng. I have just added 2 new Exception to send better detail to rest clients

* **Mapper**
payloads or payload's fileds needs to be mapped to DB layer entities. So I used special mapper instead of setting one by one

* **Entity Architecture**
I have created a basic payload. actually all payloads and entities should be (may be MUST) extended from these basic classes. By having this We can have better pagination and query mechanism. Forexample page size and page number is seperatly managed in the app now but there is better way to do this in real world. Some payloads are axtended pagedEntity which extended Entity interface etc. pagination, sortning and ID types etc all can be layerd properly.   

* **Docker**
My current system is Wondows home edition and coudl nto test docker. I will try to add following days.

* **Source Controller**
you can find project from  [Project GitHub](https://github.com/tugrulkarakaya/LibraryService)
The first step was the adding project to the source controller (git)
PS: After 24 hours (on hackerrank) I cannot send docker expansion etc. You can find these extensions from Github later.

* **Swagger**
you can use Swagger UI to test the system if you'd like from [Swagger UI](http://localhost:1111/swagger-ui.html)
Obviously, You need to run the project first.  

* **Unit Tests**
For Repository and Service layer I tried to add as much as possible unit tests. There are still more tests needs to be added. existing tests just validate basic functionality. not border values, extreeme values etc. 

I have just added one Exception testing

### Framework Used
Springboot current version at the given date above

## Steps to Test
1- You can use Swahger UI from the linke given above
2- You can use Postman file in the project

