### REST API

I utilized Spring to develop a Java-based RESTful API implementation for constructing web APIs with CRUD functionality.

#### Project Objectives:
- Construct a RESTful web API with data persistence using Spring and Spring Data JPA
- Use Spring Initializr to generate the initial Java project
- Configure application properties for certain **dependencies**, including the H2 embedded database  
 ----   
- Define the **entities** that comprise this application scenario (5 endpoints specification)
- Define the **repositories** that enable creating, updating, and querying these different entities
- Define the **API contracts** that will enable this application scenario
- Leverage the convenience of **Lombok**  
 ----   

The RESTful API can be used for managing data in customer relationship management systems. It includes authentication functionality for both Users and Admin Roles, and works with relational db (MySQL). The API also exposes a set of endpoints that represents CRUD operations:


## What I learned

#### BACKEND
  
🧩 Implemented an API. Learned about idempotent HTTP methods (GET, PUT, ~~DELETE~~) and that a well-designed server should be idempotent!

🧩 Tested endpoints in **Postman API**: specified the endpoint URLs, along with headers, parameters, and body content to create a request and get response.

🧩 Experienced with Relational Database **MySQL**. Built relations between entities (Many-To-Many). Practiced lazy loading. Designed DTO object to solve N+1 query problem in ORM. Also I used Join Fetch to join 2 database tables (user & role) within the query and initialize the association on the returned entity. 

🧩 Provided Authentication using **Spring Security** and provided Admin Panel and User Panel. 

🧩 Explored **Jackson library** for JSON processing in Java (convert POJO objects to / from JSON).

🧩 Incorporated Spring REST Client - **RestTemplate**, as a primary tool for converting an object into a http-request and http-response into an object. Next project I plan to switch to an alternative HTTP client WebClient (that provides both synchronous and asynchronous approaches - no need to wait for http-response) as RestTemplate becomes depricated since Spring 5. In microservices - Feign Client (Spring Cloud).
  
#### FRONTEND
  
🧩 Designed the front end of the web app. Used **HTML** (page structure), **CSS** (style information)

🧩 **JavaScript** (behavior control of different elements) adding **jQuery** (load() Method) and  **Bootstrap** (grid system) to simplify JavaScript coding. Learned about asynchronous method that runs separately from the main thread.

## Screenshots 

#### DTO implementation in order to:
- deliver only the rerqiered data (instead of transferring the complete object)
- reflect what data is being transmitted
- reduction of connectivity between parts of code (client - server / service - controller)


#### Admin Panel 
  
 ![alt-текст](https://github.com/e-terven/315_restapi/blob/225666ec53456f4b43ee1c134739342b1e7baea3/images/Screenshot%202023-07-16%20at%2019.47.37.png)
 


#### User Panel
  
 ![alt-текст](https://github.com/e-terven/315_restapi/blob/d43f5501e52687325cc5bb5ea58278795e8ca478/images/Screenshot%202023-07-16%20at%2019.47.21.png)

#### method: CREATE
![alt-текст](https://github.com/e-terven/315_restapi/blob/1e82f13174b2843b4c68799aa6fc0f28bd34ddc9/images/Create%202023-07-16%20at%2020.01.39.png)

#### CRUD method: UPDATE
![alt-текст](https://github.com/e-terven/315_restapi/blob/f4193ad92aee4be8b63614a21d5f779b7da79d4a/images/Edit%202023-07-16%20at%2020.01.54.png)

#### CRUD method: DELETE
![alt-текст](https://github.com/e-terven/315_restapi/blob/724b6177219bdbad2d347505768bcec20b9b616c/images/Delete%202023-07-16%20at%2020.02.07.png)

#### Security Features
 - loginSuccessHandler - for logistic of successful authentication
![alt-текст](images/Screenshot 2023-07-16 at 20.09.40.png)
   



