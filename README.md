# OnlineBookStoreService

This is an online book store which can store books. 
It allows users to search for existing books by title, author and isbn. 
Media coverage of books can also be searched. The posts of books are being fetched from "https://jsonplaceholder.typicode.com/posts"

#### API Documentation :
Swagger has been integrated for proper API documentation of the service. After running this service in a server please browse through this url "http://localhost:8080/swagger-ui.html#/" if your server is running on port number 8080.

#### How to run this?
1. Install docker in your machine
2. Clone this repo
3. Change this to your working directory
4. Run this command: docker-compose -f docker-compose.yml up --build
5. Browse this url : http://localhost:8080/swagger-ui.html#/

Hola! the onlinebookstore is up........

#### DESIGN:
The primary entity of this service is Book. The constraints of each data member is added at database level. 
There are 2 other entities for request and response. The Filter entity allows users to combine multiple searches in a single search.
There are 3 controllers namely BookController where all CRUD APIS are exposed, OrderController where order related APIS are exposed
, SearchController which has search related APIS. All the controllers calls their respective services to fulfill teh request. 
 The services has all the logic required to fulfill the request. Every service implements their own interfaces where all the methods are defined.
 Exceptions with error message are added wherever required, like the Quantity insufficient case, book id doesn't exist. The concurrency issue 
 while multiple users are trying to place order for same book has been solved by using serialization concept. 

#### TECHNOLOGIES USED:
  JAVA<br/>
  POSTGRESQL<br/>
  SPRING BOOT FRAMEWORK</br>

### POSSIBLE EXTENSIONS:<br/>
1. Implement Redis Cache to reduce latency.
2. Searching for media coverage could be improved by using elastic search.