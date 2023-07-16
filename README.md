The RESTful API can be used for managing data in customer relationship management systems. It includes authentication functionality for both Users and Admin Roles, and works with relational db (MySQL). The API also exposes a set of endpoints that represents CRUD operations:

C reate a User
R ead a list of Users / Read a personal information about a User
U pdate User's information
D elete a User

The RESTful API defines:
 - 5 endpoints specification (URL)
 - HTTP methods (GET, POST, PUT, DELETE) that a client uses to interact with the server and perform operations on resources
 - data format (JSON)
What I learned
BACKEND
🧩 Implemented an API. Learned about idempotent HTTP methods (GET, PUT, DELETE) and that a well-designed server should be idempotent!

🧩 Tested endpoints in Postman API: specified the endpoint URLs, along with headers, parameters, and body content to create a request and get response.

🧩 Experienced with Relational Database MySQL. Built relations between entities (Many-To-Many). Practiced lazy loading. Designed DTO object to solve N+1 query problem in ORM. Also I used Join Fetch to join 2 database tables (user & role) within the query and initialize the association on the returned entity.

🧩 Provided Authentication using Spring Security and provided Admin Panel and User Panel.

🧩 Explored Jackson library for JSON processing in Java (convert POJO objects to / from JSON).

🧩 Incorporated Spring REST Client - RestTemplate, as a primary tool for converting an object into a http-request and http-response into an object. Next project I plan to switch to an alternative HTTP client WebClient (that provides both synchronous and asynchronous approaches - no need to wait for http-response) as RestTemplate becomes depricated since Spring 5. In microservices - Feign Client (Spring Cloud).

FRONTEND
🧩 Designed the front end of the web app. Used HTML (page structure), CSS (style information)

🧩 JavaScript (behavior control of different elements) adding jQuery (load() Method) and Bootstrap (grid system) to simplify JavaScript coding. Learned about asynchronous method that runs separately from the main thread.

Screenshots
DTO implementation in order to:
deliver only the rerqiered data (instead of transferring the complete object)
reflect what data is being transmitted
reduction of connectivity between parts of code (client - server / service - controller)
Admin Panel
CRUD methods
Users List
at url: http:/catarion/rest/admin/
User Panel
User's information
Security Features
loginSuccessHandler - for logistic of successful authentication
passwordEncoder
