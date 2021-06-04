# spring_security_jwt
implement spring security + toy jwt service

Stacks: 
Springboot 2.5.1(Snapshot) 
MySQL + SpringBoot JPA 
Spring_Security 

To implement this code, customize application.properties file to configure sources pertaining to the database. 

<h2> Implementation Process</h2>
1. User requests login access to /login with parameters, ID and pwd. 
2. In TestController, authenticate the user with ID and pwd, then JwtUtil creatToken and response the JWT token with header "Authorization". 
3. With JWT token, User can access "/home" and TestController response "hello world" 
